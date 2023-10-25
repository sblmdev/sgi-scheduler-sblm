package pe.gob.sblm.sgi.scheduler.daoImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import pe.gob.sblm.sgi.scheduler.dao.IpcDAO;
import pe.gob.sblm.sgi.scheduler.serviceImpl.ArrendamientoServiceImpl;

public class IpcDAOImpl implements IpcDAO,Serializable{
	

	private static final long serialVersionUID = 3393474679670239530L;
	private JdbcTemplate jdbcTemplate;
	

	private static final Logger Logger = LoggerFactory.getLogger(ArrendamientoServiceImpl.class);
	
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
	public Boolean siIPCcreado(String anio) throws Exception {
		
		try{
		
			String sql = "SELECT ID_IPC as ID_IPC FROM MAE_IPC WHERE ANIO='"+anio+"' ";
			Logger.info("obtenerIPC CREADO:" +anio);
			Logger.info(sql);
			
			List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
			
			
			if (rows.size()==0) {
				
				return Boolean.FALSE;
			
			} else {
	
				return Boolean.TRUE;
			
			}
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public Double obtenerProximoIPC(String anio) throws Exception {
		
		try{
			String sql = "SELECT VALOR as VALOR FROM MAE_IPC WHERE ANIO='"+anio+"' ";
	
			Double value=jdbcTemplate.queryForObject(sql, new Object[] { }, Double.class);
	
			return value;
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public String obtenerUltimoIPCRegistrado() throws Exception {

		try{
			String sql = "SELECT TOP 1 ANIO as VALOR FROM MAE_IPC ORDER BY ANIO DESC ";
	
			String anio=jdbcTemplate.queryForObject(sql, new Object[] { }, String.class);
	
			return anio;
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}
		
}