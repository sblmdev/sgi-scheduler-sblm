package pe.gob.sblm.sgi.scheduler.daoImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import pe.gob.sblm.api.commons.utility.FechaUtil;
import pe.gob.sblm.sgi.scheduler.bean.NotificacionBean;
import pe.gob.sblm.sgi.scheduler.dao.NotificacionDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotificacionDAOImpl implements NotificacionDAO,Serializable{
	

	private static final long serialVersionUID = 3393474679670239530L;
	private static final Logger Logger = LoggerFactory.getLogger(NotificacionDAOImpl.class);
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public void grabarNotificacion(NotificacionBean notificacion) throws Exception {
		try{
		
			String sql = " INSERT INTO MAE_NOTIFICACION"
				+ 	 " (IDUSUARIODESTINO,MENSAJE,UID_ALFRESCO,ESTADO_LEIDO,USUARIO_CREADOR,FECHA_CREACION,SINOTIFICACIONTIPOREPORTE,ESTADO_CORREO_ENVIADO,FECHA_ENVIO_CORREO)"
				+	 " VALUES"
				+ 	 " (?,?,?,?,?,?,?,?,?)";		 
					
			jdbcTemplate.update(sql, new Object[] {notificacion.getIdusuariodestino(),notificacion.getMensaje(),notificacion.getUidAlfresco(),notificacion.getEstadoLeido(),notificacion.getUsuarioCreador(),notificacion.getFechaCreacion(),notificacion.getSiNotificacionTipoReporte(),notificacion.getEstadoCorreoEnviado(),notificacion.getFechaEnvioCorreo()});
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
    		throw new Exception(e.getMessage(), e);   		
		}
	}

	@Override
	public List<NotificacionBean> obtenerNotificacionesPendienteEnvioCorreo() throws Exception {
		List<NotificacionBean> lista = new ArrayList<NotificacionBean>();
		
		try{
			
			String sql = " SELECT N.ID_NOTIFICACION as ID_NOTIFICACION,U.EMAILUSR AS EMAILUSR, U.NOMBRES AS NOMBRES,N.MENSAJE AS MENSAJE,N.TITULO AS TITULO,N.SINOTIFICACIONTIPOREPORTE AS SINOTIFICACIONTIPOREPORTE,N.UID_ALFRESCO AS UID_ALFRESCO FROM MAE_NOTIFICACION  N"
						+" INNER JOIN USUARIO  U ON N.IDUSUARIODESTINO=U.IDUSUARIO"
						+" WHERE N.ESTADO_CORREO_ENVIADO='0' AND U.ESTADO='1' ";
			//PRUEBA
//			String sql = " SELECT N.ID_NOTIFICACION as ID_NOTIFICACION,U.EMAILUSR AS EMAILUSR, U.NOMBRES AS NOMBRES,N.MENSAJE AS MENSAJE,N.TITULO AS TITULO,N.SINOTIFICACIONTIPOREPORTE AS SINOTIFICACIONTIPOREPORTE,N.UID_ALFRESCO AS UID_ALFRESCO FROM MAE_NOTIFICACION  N"
//					+" INNER JOIN USUARIO  U ON N.IDUSUARIODESTINO=U.IDUSUARIO"
//					+" WHERE N.ESTADO_CORREO_ENVIADO='0' AND U.ESTADO='1' AND U.IDUSUARIO='100085' ";
		
				
			List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
				
				for (Map row : rows) {
					NotificacionBean notificacionBean= new NotificacionBean();
					
					notificacionBean.setIdNotificacion((Integer) row.get("ID_NOTIFICACION"));
					notificacionBean.setNombreUsuarioDestino((String) row.get("NOMBRES"));
					notificacionBean.setCorreoUsuarioDestino((String) row.get("EMAILUSR"));
					notificacionBean.setMensaje((String) row.get("MENSAJE"));
					notificacionBean.setSiNotificacionTipoReporte((Boolean) row.get("SINOTIFICACIONTIPOREPORTE"));
					notificacionBean.setUidAlfresco((String) row.get("UID_ALFRESCO"));
					notificacionBean.setTitulo((String) row.get("TITULO"));
					
					lista.add(notificacionBean);	
				
				}
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
    		throw new Exception(e.getMessage(), e);   		
		}
		
		return lista;
	}

	@Override
	public void actualizarEstadoCorreoEnviadoNotificacion(int idNotificacion)
			throws Exception {
		
		try{
		String sql = " UPDATE MAE_NOTIFICACION"
				+ 	 " SET ESTADO_CORREO_ENVIADO='1',FECHA_ENVIO_CORREO='"+FechaUtil.fechaHORAtoString(new Date())+"' "
				+ 	 " WHERE ID_NOTIFICACION="+idNotificacion;		 
					
		jdbcTemplate.update(sql, new Object[] {});
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
    		throw new Exception(e.getMessage(), e);   		
		}
		
	}
}