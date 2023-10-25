package pe.gob.sblm.sgi.scheduler.daoImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import pe.gob.sblm.sgi.scheduler.bean.Usuario;
import pe.gob.sblm.sgi.scheduler.dao.UsuarioDAO;

public class UsuarioDAOImpl implements UsuarioDAO,Serializable{
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioDAOImpl.class);

	private static final long serialVersionUID = 3393474679670239530L;
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


	public String test() {
		  	StringBuffer sb = new StringBuffer();
			 
		   sb.append("SELECT count(*) FROM Contrato ");
		   
		   int x=jdbcTemplate.queryForObject(sb.toString(), new Object[] { }, Integer.class);
		   
		   return String.valueOf(x);
	}

	@Override
	public void desactivarCuenta() {
		
		String sql = "SELECT IDUSUARIO as IDUSUARIO FROM USUARIO WHERE ESTADO='1' ";/**VERIFICAR HACER UN SELECT CON ACCESO***/

		List<Integer> lista = new ArrayList<Integer>();

		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		for (Map row : rows) {
			
			logger.info("verificando usuario con idusuario="+row.get("IDUSUARIO"));
			
			
			System.out.println(row.get("IDUSUARIO"));
			
//			String sql = "UPDATE Usuario " +
//					"SET estado=? WHERE f_publicacion=convert(DATE,'"+FechaUtil.fechaToString(f_publicacion)+"',103)";
//						
//			jdbcTemplate.update(sql, new Object[] {false});
		}
	}

	@Override
	public List<Usuario> obtenerUsuarioDestinoTareaProgramada(
			String nombreTareaProgramada) {

		String sql = " SELECT U.IDUSUARIO as IDUSUARIO,U.EMAILUSR AS EMAILUSR, U.NOMBRES AS NOMBRES FROM MOV_TAREAPROGRAMADA_USUARIO  TPU"
					+" INNER JOIN MAE_TAREA_PROGRAMADA TP  ON TP.ID_TAREA_PROGRAMADA=TPU.ID_TAREA_PROGRAMADA"
					+" INNER JOIN USUARIO  U ON U.IDUSUARIO=TPU.IDUSUARIO"
					+" WHERE U.ESTADO='1' AND TP.NOMBRE='"+nombreTareaProgramada+"' ";
		//PRUEBA
//		String sql = " SELECT U.IDUSUARIO as IDUSUARIO,U.EMAILUSR AS EMAILUSR, U.NOMBRES AS NOMBRES FROM MOV_TAREAPROGRAMADA_USUARIO  TPU"
//				+" INNER JOIN MAE_TAREA_PROGRAMADA TP  ON TP.ID_TAREA_PROGRAMADA=TPU.ID_TAREA_PROGRAMADA"
//				+" INNER JOIN USUARIO  U ON U.IDUSUARIO=TPU.IDUSUARIO"
//				+" WHERE U.ESTADO='1' AND TP.NOMBRE='"+nombreTareaProgramada+"' AND U.IDUSUARIO=100085";
		
		logger.info("obtenerUsuarioDestinoTareaProgramada:" +nombreTareaProgramada);
		logger.info(sql);
		
		List<Usuario> lista = new ArrayList<Usuario>();

		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		
		for (Map row : rows) {
			Usuario usuario= new Usuario();
			
			usuario.setIdusuario((Integer) row.get("IDUSUARIO"));
			usuario.setNombres((String) row.get("NOMBRES"));
			usuario.setEmailusr((String) row.get("EMAILUSR"));
		lista.add(usuario);	
		}
		
		return lista;
	}
}