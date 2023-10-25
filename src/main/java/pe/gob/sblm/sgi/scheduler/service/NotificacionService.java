package pe.gob.sblm.sgi.scheduler.service;

import java.util.List;

import pe.gob.sblm.sgi.scheduler.bean.NotificacionBean;



public interface NotificacionService {

	public void grabarNotificacion(NotificacionBean notificacion) throws Exception;
	public List<NotificacionBean> obtenerNotificacionesPendienteEnvioCorreo() throws Exception;
	public void actualizarEstadoCorreoEnviadoNotificacion(int idNotificacion) throws Exception;
	
}
