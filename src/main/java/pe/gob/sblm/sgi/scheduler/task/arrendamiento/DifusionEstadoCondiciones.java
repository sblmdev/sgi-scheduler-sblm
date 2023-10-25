package pe.gob.sblm.sgi.scheduler.task.arrendamiento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.gob.sblm.api.commons.constants.sgi.Constantes;
import pe.gob.sblm.api.commons.utility.FechaUtil;
import pe.gob.sblm.sgi.scheduler.bean.ArchivoAdjuntoBean;
import pe.gob.sblm.sgi.scheduler.bean.NotificacionBean;
import pe.gob.sblm.sgi.scheduler.bean.Usuario;
import pe.gob.sblm.sgi.scheduler.constantes.ConstantesReporte;
import pe.gob.sblm.sgi.scheduler.properties.PropiedadesBaseDatos;
import pe.gob.sblm.sgi.scheduler.service.ArrendamientoService;
import pe.gob.sblm.sgi.scheduler.service.CorreoService;
import pe.gob.sblm.sgi.scheduler.service.NotificacionService;
import pe.gob.sblm.sgi.scheduler.service.ReporteGeneradorService;
import pe.gob.sblm.sgi.scheduler.service.SFTPService;
import pe.gob.sblm.sgi.scheduler.service.UploadRepositorioService;
import pe.gob.sblm.sgi.scheduler.task.base.SgiTask;


@Component("difusionEstadoCondiciones")
public class DifusionEstadoCondiciones extends SgiTask{
	
	@Autowired
	private ArrendamientoService arrendamientoService;
    
	@Autowired
	private NotificacionService notificacionService;
	
	@Autowired
	private ReporteGeneradorService reporteGeneradorService;
	
	@Autowired
	private transient UploadRepositorioService uploadRepositorioService;
	
	@Autowired
	private transient SFTPService sftpService;
	
	@Autowired
	private transient CorreoService correoService;
	
	private static final Logger Logger = LoggerFactory.getLogger(DifusionEstadoCondiciones.class);


    
    public void excecuteTask() throws Exception {
    	
    	try {
    	Logger.info("Ejecutando tarea programada : " + Constantes.TAREA_ARRENDAMIENTO_CONTRATO_POR_FINALIZAR);
    	List<Usuario> destinatarios= new ArrayList<Usuario>();
    	destinatarios=arrendamientoService.obtenerUsuarioDestinoTareaProgramada(Constantes.TAREA_ARRENDAMIENTO_CONTRATO_POR_FINALIZAR);
    	
    	String pathFecha="/"+FechaUtil.getYear(new Date())+"/"+FechaUtil.getMonth(new Date())+"/"+FechaUtil.getDay(new Date());
    	String nombreReporte=ConstantesReporte.REP_CONTRATO_POR_FINALIZAR.substring(0, ConstantesReporte.REP_CONTRATO_POR_FINALIZAR.length()-6);
    	
//    	Object rpta=uploadRepositorioService.buscarDocumento(Constantes.DIR_REP_CONTRATO_POR_VENCER+pathFecha , nombreReporte+".pdf");
    	
    	
//    	if (rpta.equals(false)) {
    		
    	    String  reportPath=  ConstantesReporte.REP_CONTRATO_POR_FINALIZAR;
    	    Connection connection = null;
    	    
    			Class.forName(PropiedadesBaseDatos.getString("driverClassName"));
    			connection = DriverManager.getConnection(PropiedadesBaseDatos.getString("url"), PropiedadesBaseDatos.getString("usuario"), PropiedadesBaseDatos.getString("contrasenia"));
    		
    	    
    	    
    	    Map<String, Object> parameters = new HashMap<String, Object>();
    	    parameters.put("USUARIOCREADOR", "admin");
    	    parameters.put("REPORT_LOCALE", new Locale("es", "ES"));
    	    
    		
    		ArchivoAdjuntoBean archivoAdjuntoBean= new ArchivoAdjuntoBean();
    		archivoAdjuntoBean.setTitulo("Reporte estado de condiciones al "+FechaUtil.fechaToString(new Date()));
    		archivoAdjuntoBean.setNombre(nombreReporte);
    		archivoAdjuntoBean.setTipo(Constantes.EXTENSION_FORMATO_PDF);
    		archivoAdjuntoBean.setStream(reporteGeneradorService.generarPDF(reportPath, parameters, connection));
    		archivoAdjuntoBean.setRuta(Constantes.DIR_REP_CONTRATO_POR_VENCER);
    		archivoAdjuntoBean.setFeccre(new Date());
    		
    		
//    		String uid=uploadRepositorioService.grabarDocumento(archivoAdjuntoBean,Constantes.TAREA_ARRENDAMIENTO_CONTRATO_POR_FINALIZAR);
//    		uid=uid.replace(";1.0", "");
    		
    		
    		for (Usuario usuario : destinatarios) {
    			NotificacionBean notificacion= new NotificacionBean();
    			
    			notificacion.setFechaCreacion(new Date());
    			notificacion.setIdusuariodestino(usuario.getIdusuario());
    			notificacion.setUsuarioCreador("SGI");
//    			notificacion.setUidAlfresco(uid);
    			notificacion.setEstadoLeido(false);
    			notificacion.setMensaje("le notifica que usted ha recibido un nuevo reporte " +Constantes.CONTENIDO_NOTIFICACION_CONTRATO_POR_VENCER);
    			notificacion.setSiNotificacionTipoReporte(Boolean.TRUE);
    			notificacion.setEstadoCorreoEnviado(Boolean.FALSE);
    			
    			Logger.info("Enviando Correo Electronico...");
    			
    			try {
    				correoService.enviarNotificacion(usuario.getEmailusr(), Constantes.TITULO_NOTIFICACION_CONTRATO_POR_VENCER, usuario.getNombres(), notificacion.getMensaje(), archivoAdjuntoBean.getStream());
        		    notificacion.setEstadoCorreoEnviado(Boolean.TRUE);
        		    notificacion.setFechaEnvioCorreo(new Date());
        		    Logger.info("Correo Enviado satisfactoriamente.");	
				} catch (Exception e) {
					Logger.error(e.getMessage(), e);
				}
			
    			Logger.info("Registrando notificacion... ");
				notificacionService.grabarNotificacion(notificacion);
				Logger.info("Notificacion registrada satisfactoriamente. ");
    			
			
    		}

    		Logger.info("Reporte 'Contratos Finalizados Con Deuda' para el presente dia se ha creado exitosamente");
    	
//		}else {
//			Logger.info("Reporte 'Contratos Finalizados Con Deuda' para el presente dia ya fue creado exitosamente");
//		}
//    
    		
    		
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			
			Logger.error(e.getMessage(), e);
		}
    
    
    }


	/**
	 * @return the arrendamientoService
	 */
	public ArrendamientoService getArrendamientoService() {
		return arrendamientoService;
	}

	/**
	 * @param arrendamientoService the arrendamientoService to set
	 */
	public void setArrendamientoService(ArrendamientoService arrendamientoService) {
		this.arrendamientoService = arrendamientoService;
	}

	/**
	 * @return the reporteGeneradorService
	 */
	public ReporteGeneradorService getReporteGeneradorService() {
		return reporteGeneradorService;
	}

	/**
	 * @param reporteGeneradorService the reporteGeneradorService to set
	 */
	public void setReporteGeneradorService(
			ReporteGeneradorService reporteGeneradorService) {
		this.reporteGeneradorService = reporteGeneradorService;
	}



	/**
	 * @return the uploadRepositorioService
	 */
	public UploadRepositorioService getUploadRepositorioService() {
		return uploadRepositorioService;
	}


	/**
	 * @param uploadRepositorioService the uploadRepositorioService to set
	 */
	public void setUploadRepositorioService(
			UploadRepositorioService uploadRepositorioService) {
		this.uploadRepositorioService = uploadRepositorioService;
	}


	/**
	 * @return the correoService
	 */
	public CorreoService getCorreoService() {
		return correoService;
	}


	/**
	 * @param correoService the correoService to set
	 */
	public void setCorreoService(CorreoService correoService) {
		this.correoService = correoService;
	}


	public NotificacionService getNotificacionService() {
		return notificacionService;
	}


	public void setNotificacionService(NotificacionService notificacionService) {
		this.notificacionService = notificacionService;
	}


	public SFTPService getSftpService() {
		return sftpService;
	}


	public void setSftpService(SFTPService sftpService) {
		this.sftpService = sftpService;
	}

	
}
