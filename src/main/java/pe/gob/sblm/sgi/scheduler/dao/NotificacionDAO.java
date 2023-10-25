package pe.gob.sblm.sgi.scheduler.dao;

import java.util.List;

import pe.gob.sblm.sgi.scheduler.bean.NotificacionBean;





public interface NotificacionDAO {


	public void grabarNotificacion(NotificacionBean notificacion) throws Exception;
	public List<NotificacionBean> obtenerNotificacionesPendienteEnvioCorreo() throws Exception;
	public void actualizarEstadoCorreoEnviadoNotificacion(int idNotificacion) throws Exception;
	
}
