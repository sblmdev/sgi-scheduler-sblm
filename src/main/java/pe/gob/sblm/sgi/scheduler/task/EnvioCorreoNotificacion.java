	package pe.gob.sblm.sgi.scheduler.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.gob.sblm.api.commons.constants.sgi.Constantes;
import pe.gob.sblm.sgi.scheduler.bean.NotificacionBean;
import pe.gob.sblm.sgi.scheduler.properties.PropiedadesAlfresco;
import pe.gob.sblm.sgi.scheduler.service.CorreoService;
import pe.gob.sblm.sgi.scheduler.service.NotificacionService;
import pe.gob.sblm.sgi.scheduler.task.base.SgiTask;


@Component("envioCorreoNotificacion")
public class EnvioCorreoNotificacion extends SgiTask{
	
	@Autowired
	private NotificacionService notificacionService;
	
	@Autowired
	CorreoService correoService;
	
    private static final Logger Logger = LoggerFactory.getLogger(EnvioCorreoNotificacion.class);



    public void excecuteTask() throws Exception {
    	Logger.info("execute task envioCorreoNotificacion");
    	try {
    		
			List<NotificacionBean> lista=notificacionService.obtenerNotificacionesPendienteEnvioCorreo();
			
			if (lista.size()>0) {
					
				for (NotificacionBean notificacionBean : lista) {
				  if(notificacionBean.getSiNotificacionTipoReporte()!=null){
					  if (notificacionBean.getSiNotificacionTipoReporte()) {
						String urlAlfrescoShare="http://"+PropiedadesAlfresco.getString("ip")+":"+PropiedadesAlfresco.getString("puerto")+"/share/page/document-details?nodeRef=workspace://SpacesStore/"+notificacionBean.getUidAlfresco();
						correoService.enviarNotificacion(notificacionBean.getCorreoUsuarioDestino(), Constantes.TITULO_CORREO_NOTIFICACION_NUEVO_REPORTE, notificacionBean.getNombreUsuarioDestino(), notificacionBean.getMensaje(),urlAlfrescoShare);
					  } else {
						correoService.enviarNotificacion(notificacionBean.getCorreoUsuarioDestino(), "Notificación Electronica"+"-"+notificacionBean.getTitulo(), notificacionBean.getNombreUsuarioDestino(), notificacionBean.getMensaje());
						
					  }
				  }

					notificacionService.actualizarEstadoCorreoEnviadoNotificacion(notificacionBean.getIdNotificacion());
				}
				
			}else {
				Logger.info("No hay notificaciones pendiente de envio");
			}
		
    	} catch(Exception e){
			Logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
    }
}
