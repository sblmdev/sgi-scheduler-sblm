package pe.gob.sblm.sgi.scheduler.task.arrendamiento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.gob.sblm.api.commons.constants.sgi.Constantes;
import pe.gob.sblm.api.commons.utility.FechaUtil;
import pe.gob.sblm.sgi.scheduler.bean.NotificacionBean;
import pe.gob.sblm.sgi.scheduler.bean.Usuario;
import pe.gob.sblm.sgi.scheduler.service.ArrendamientoService;
import pe.gob.sblm.sgi.scheduler.service.CorreoService;
import pe.gob.sblm.sgi.scheduler.service.NotificacionService;
import pe.gob.sblm.sgi.scheduler.task.base.SgiTask;


@Component("alertaRegistroIPC")
public class AlertarRegistroIPC extends SgiTask{
	
	@Autowired
	private ArrendamientoService arrendamientoService;
	
	@Autowired
	private NotificacionService notificacionService;
	
	@Autowired
	private transient CorreoService correoService;
    
	private static final Logger logger = LoggerFactory.getLogger(AlertarRegistroIPC.class);


    
    public void excecuteTask() {
    
    	try {
    		
    	List<Usuario> destinatarios= new ArrayList<Usuario>();
    	destinatarios=arrendamientoService.obtenerUsuarioDestinoTareaProgramada(Constantes.TAREA_ARRENDAMIENTO_ALERTA_REGISTRO_IPC);
    	
//    	String anioUltimoIPCRegistrado=arrendamientoService.obtenerUltimoIPCRegistrado();
    	
		Integer anioSgt=Integer.parseInt(FechaUtil.getYear(new Date()))+1;
    	
    	if (!arrendamientoService.siProximoIPCcreado(String.valueOf(anioSgt))) {
    		
    		
        	for (Usuario usuario : destinatarios) {
    			NotificacionBean notificacion= new NotificacionBean();
    			
    			notificacion.setFechaCreacion(new Date());
    			notificacion.setIdusuariodestino(usuario.getIdusuario());
    			notificacion.setUsuarioCreador("SGI");
    			notificacion.setEstadoLeido(false);
    			notificacion.setMensaje("le notifica que a\u00fan no ha sido creado el IPC correspondiente al año "+anioSgt);
    			notificacion.setEstadoCorreoEnviado(Boolean.FALSE);
    			notificacion.setSiNotificacionTipoReporte(Boolean.FALSE);
    			
    			logger.info("Enviando Correo Electronico...");
    			
    			try {
        			correoService.enviarNotificacion(usuario.getEmailusr(), Constantes.TITULO_NOTIFICACION_REGISTRO_IPC	,usuario.getNombres(),notificacion.getMensaje());
        		    notificacion.setEstadoCorreoEnviado(Boolean.TRUE);
        		    notificacion.setFechaEnvioCorreo(new Date());
        		    logger.info("Correo Enviado satisfactoriamente.");
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					logger.error("Notificacion no registrada satisfactoriamente. ");

				}
			
    			logger.info("Registrando notificacion... ");
				notificacionService.grabarNotificacion(notificacion);
				logger.info("Notificacion  registrada satisfactoriamente. ");
				
    		}
		}
    	

		} catch (Exception e) {
			logger.error("Error en el envio de notificaciones. ");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
    }


	public ArrendamientoService getArrendamientoService() {
		return arrendamientoService;
	}


	public void setArrendamientoService(ArrendamientoService arrendamientoService) {
		this.arrendamientoService = arrendamientoService;
	}


	public NotificacionService getNotificacionService() {
		return notificacionService;
	}


	public void setNotificacionService(NotificacionService notificacionService) {
		this.notificacionService = notificacionService;
	}


	public CorreoService getCorreoService() {
		return correoService;
	}


	public void setCorreoService(CorreoService correoService) {
		this.correoService = correoService;
	}


	
	
}
