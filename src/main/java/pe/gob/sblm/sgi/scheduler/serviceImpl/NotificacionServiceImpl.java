package pe.gob.sblm.sgi.scheduler.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.sblm.sgi.scheduler.bean.NotificacionBean;
import pe.gob.sblm.sgi.scheduler.dao.NotificacionDAO;
import pe.gob.sblm.sgi.scheduler.service.NotificacionService;

@Service(value="notificacionService")

public class NotificacionServiceImpl implements NotificacionService,Serializable{

	private static final long serialVersionUID = 8342581877199832156L;
	private static final Logger Logger = LoggerFactory.getLogger(NotificacionServiceImpl.class);
	
	@Autowired
	NotificacionDAO notificacionDAO;

	@Override
	public void grabarNotificacion(NotificacionBean notificacion) throws Exception {
		
		try {
			notificacionDAO.grabarNotificacion(notificacion);
			
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
		
	}

	@Override
	public List<NotificacionBean> obtenerNotificacionesPendienteEnvioCorreo() throws Exception {
		
		try {
			return notificacionDAO.obtenerNotificacionesPendienteEnvioCorreo();
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public void actualizarEstadoCorreoEnviadoNotificacion(int idNotificacion) throws Exception {
		
		try {
			notificacionDAO.actualizarEstadoCorreoEnviadoNotificacion(idNotificacion);
		} catch (Exception e) {
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}


	
	
}
