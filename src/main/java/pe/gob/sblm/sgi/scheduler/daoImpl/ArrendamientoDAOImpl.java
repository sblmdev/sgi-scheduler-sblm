package pe.gob.sblm.sgi.scheduler.daoImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import pe.gob.sblm.api.commons.constants.sgi.Constantes;
import pe.gob.sblm.api.commons.utility.FechaUtil;
import pe.gob.sblm.sgi.bean.CondicionBean;
import pe.gob.sblm.sgi.bean.RentaBean;
import pe.gob.sblm.sgi.scheduler.bean.Usuario;
import pe.gob.sblm.sgi.scheduler.dao.ArrendamientoDAO;
import pe.gob.sblm.sgi.scheduler.serviceImpl.ArrendamientoServiceImpl;
import pe.gob.sblm.sgi.scheduler.util.*;
public class ArrendamientoDAOImpl implements ArrendamientoDAO,Serializable{
	

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
	public void actualizarEstadoContratosFinalizados() {
		
		String sql = " UPDATE CONTRATO"
				+ 	 " SET ESTADO='FINALIZADO',SIFINALIZADO='TRUE',FECMODFINALIZADO='"+FechaUtil.fechaHORAtoString(new Date())+"' "
				+	 " "
				+ 	 " WHERE CONDICION='"+Constantes.CONDICION_CONTRATO+"'  AND ESTADO='VIGENTE' AND SIRESUELTO='FALSE' AND SIADENDA='FALSE'   AND FINCONTRATO<(SELECT GETDATE( ))";		 
					
		jdbcTemplate.update(sql, new Object[] {});
	}
	@Override
	public void actualizarEstadoPreContratosFinalizados() {
		
		String sql = " UPDATE CONTRATO"
				+ 	 " SET ESTADO='FINALIZADO',SIFINALIZADO='TRUE',FECMODFINALIZADO='"+FechaUtil.fechaHORAtoString(new Date())+"' "
				+	 " "
				+ 	 " WHERE CONDICION='"+Constantes.CONDICION_PRE_CONTRATO+"' AND ESTADO='VIGENTE' AND SIRESUELTO='FALSE'  AND FINCONTRATO<(SELECT GETDATE( ))";		 
					
		jdbcTemplate.update(sql, new Object[] {});
	}
	@Override
	public void actualizarEstadoReconocimientoDeudaFinalizados() {
		
		String sql = " UPDATE CONTRATO"
				+ 	 " SET ESTADO='FINALIZADO',SIFINALIZADO='TRUE',FECMODFINALIZADO='"+FechaUtil.fechaHORAtoString(new Date())+"' "
				+	 " "
				+ 	 " WHERE CONDICION='"+Constantes.CONDICION_RECONOCIMIENTO_DEUDA+"' AND ESTADO='VIGENTE' AND SIRESUELTO='FALSE'  AND FINCONTRATO<(SELECT GETDATE( ))";		 
					
		jdbcTemplate.update(sql, new Object[] {});
	}
	@Override
	public void recuperarVigenciaEstadoContratosFinalizados() {
		String sql = " UPDATE CONTRATO"
				+ 	 " SET ESTADO='VIGENTE',SIFINALIZADO='FALSE',FECMODFINALIZADO=NULL "
				+	 " "
				+ 	 " WHERE CONDICION='Contrato' AND ESTADO='FINALIZADO' AND SIRESUELTO='FALSE' AND SIADENDA='FALSE'   AND FINCONTRATO>(SELECT GETDATE ( ))";		 
					
		jdbcTemplate.update(sql, new Object[] {});
	}


	@SuppressWarnings("rawtypes")
	@Override
	public List<CondicionBean> obtenerContratosPendientesGeneracionRentas() {
		
		String sql = " SELECT IDCONTRATO AS IDCONTRATO,REAJUSTEANUAL as REAJUSTEANUAL , INICIOCONTRATO AS INICIOCONTRATO, FINCONTRATO AS FINCONTRATO from CONTRATO C WHERE C.SIGENERACIONRENTACOMPLETADA=0 AND C.TIPOREAJUSTEANUAL=2 ";
	

		List<CondicionBean> lista = new ArrayList<CondicionBean>();
	
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		for (Map row : rows) {
			CondicionBean condicionBean= new CondicionBean();
			
			condicionBean.setIdcontrato((Integer) row.get("IDCONTRATO"));
			condicionBean.setReajusteanual((Double) row.get("REAJUSTEANUAL"));
			condicionBean.setIniciocontrato((Date) row.get("INICIOCONTRATO"));
			
			lista.add(condicionBean);	
		}
		
		return lista;
	}

	@Override
	public List<RentaBean> obtenerRentaPendienteGeneracion(int idcontrato, String anio) {
		
		
		String sql = " SELECT TOP 12 * from MAE_DETALLE_CONDICION DC WHERE DC.IDCONTRATO="+idcontrato+" AND DC.SIRENTAGENERADA=0 AND DC.ANIO="+anio+"  AND DC.RENTA=0 ORDER BY SECUENCIA ASC";
		

		List<RentaBean> lista = new ArrayList<RentaBean>();
	
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		for (Map row : rows) {
			RentaBean rentaBean= new RentaBean();
			
			rentaBean.setId((Integer) row.get("ID_DETALLE_CONDICION"));
			rentaBean.setSiclausulaperiodogracia( (Boolean) row.get("SICLAUSULAPERIODOGRACIA"));
			rentaBean.setDescuentoreconocimientoinversion((Double) row.get("DESCUENTORECONOCIMIENTOINVERSION"));
			rentaBean.setDescuentoreconocimientorenta((Double) row.get("DESCUENTORECONOCIMIENTORENTA"));
			rentaBean.setMontopagoposterior((Double) row.get("MONTOPAGOPOSTERIOR"));
			/* agregago el 10-03-20*/
			rentaBean.setMes((String)row.get("MES"));
			rentaBean.setAnio((String)row.get("ANIO"));
			rentaBean.setNumeromes((Integer) row.get("NUMEROMES"));
			rentaBean.setSecuencia((Integer)row.get("SECUENCIA"));
			//
			lista.add(rentaBean);	
		}
		
		return lista;
	}
	@Override
	public List<RentaBean> obtenerRentaPendienteGeneracion(int idcontrato, String anio,int sec_ini,int sec_fin) {
		
		
		String sql = " SELECT TOP 12 * from MAE_DETALLE_CONDICION DC WHERE DC.IDCONTRATO="+idcontrato+" AND DC.SECUENCIA BETWEEN "+sec_ini+" AND "+sec_fin+" ORDER BY SECUENCIA ASC";
		

		List<RentaBean> lista = new ArrayList<RentaBean>();
	
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
		
		for (Map row : rows) {
			RentaBean rentaBean= new RentaBean();
			
			rentaBean.setId((Integer) row.get("ID_DETALLE_CONDICION"));
			rentaBean.setSiclausulaperiodogracia( (Boolean) row.get("SICLAUSULAPERIODOGRACIA"));
			rentaBean.setDescuentoreconocimientoinversion((Double) row.get("DESCUENTORECONOCIMIENTOINVERSION"));
			rentaBean.setDescuentoreconocimientorenta((Double) row.get("DESCUENTORECONOCIMIENTORENTA"));
			rentaBean.setMontopagoposterior((Double) row.get("MONTOPAGOPOSTERIOR"));
			/* agregago el 10-03-20*/
			rentaBean.setMes((String)row.get("MES"));
			rentaBean.setAnio((String)row.get("ANIO"));
			rentaBean.setNumeromes((Integer) row.get("NUMEROMES"));
			rentaBean.setSecuencia((Integer)row.get("SECUENCIA"));
			//
			lista.add(rentaBean);	
		}
		
		return lista;
	}
	@Override
	public Double obtenerValorUltimaRenta(int idcontrato) {
		
		String sql = "SELECT TOP 1 DC.RENTA  from MAE_DETALLE_CONDICION DC WHERE DC.IDCONTRATO="+idcontrato+" AND DC.SIRENTAGENERADA=1 AND DC.RENTA>0 ORDER BY SECUENCIA DESC";


		Double value=jdbcTemplate.queryForObject(sql, new Object[] { }, Double.class);


			
		return value;
	}

	@Override
	public void actualizarRentaPendiente(RentaBean rentaBean) throws Exception {
		try{
		String sql = " UPDATE MAE_DETALLE_CONDICION"
				+ 	 " SET RENTA='"+rentaBean.getRenta()+"',MONTOPAGAR='"+rentaBean.getMontopagar()+"' ,SIRENTAGENERADA=1"
				+ 	 " WHERE ID_DETALLE_CONDICION="+rentaBean.getId();		 
					
		jdbcTemplate.update(sql, new Object[] {});
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
    		throw new Exception(e.getMessage(), e);   		
		}
	}
	@Override
	public void actualizarRentaPendiente(int idcontrato , int anio) throws Exception {
		try{
		String sql = " UPDATE MAE_DETALLE_CONDICION"
				+ 	 " SET SIRENTAGENERADA=1"
				+ 	 " WHERE IDCONTRATO="+idcontrato+" AND ANIO="+anio+" ";		 
					
		jdbcTemplate.update(sql, new Object[] {});
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
    		throw new Exception(e.getMessage(), e);   		
		}
	}
	@Override
	public List<CondicionBean> obtenerContratosFinalizadosSinActaDevolucion() throws Exception {
		
		try{
			String sql =  " SELECT C.IDCONTRATO AS IDCONTRATO,C.TIPOMONEDA as MONEDA, C.IDUPA as IDUPA, C.IDINQUILINO as IDINQUILINO, "
						+ " C.SINUEVOMANTENIMIENTO AS SINUEVOMANTENIMIENTO,FINCOBRO AS FINCOBRO "
						+ " FROM CONTRATO C "
						+ " WHERE C.CONDICION='"+Constantes.CONDICION_CONTRATO+"' AND C.SIACTADEVOLUCION=0 "
						+ " AND C.ESTADO='VIGENTE' AND C.SIGENEROSINCONTRATO=0 AND C.FINCONTRATO<dateadd(DD,-1,getdate())";
			
			
			Logger.info("Query:"+sql);
			
			List<CondicionBean> lista = new ArrayList<CondicionBean>();
		
			List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);
			
			for (Map row : rows) {
				
				CondicionBean condicionBean= new CondicionBean();
				
				condicionBean.setIdcontrato((Integer) row.get("IDCONTRATO"));
				condicionBean.setIdupa((Integer) row.get("IDUPA"));
				condicionBean.setIdinquilino((Integer) row.get("IDINQUILINO"));
				condicionBean.setMoneda((String) row.get("MONEDA"));
				condicionBean.setSinuevomantenimiento((Boolean) row.get("SINUEVOMANTENIMIENTO"));
				condicionBean.setFincobro((Date) row.get("FINCOBRO"));
				
				lista.add(condicionBean);	
			}
			
			return lista;
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
    		throw new Exception(e.getMessage(), e);   		
		}
	}

	@Override
	public Double obtenerUltimaRentaContrato(int idcontrato,Boolean sinuevomantenimiento) throws Exception {
		
		try{
			String sql; 
			if (sinuevomantenimiento) {
				
				sql =  "SELECT TOP 1 mdc.MONTOPAGAR AS MONTOPAGAR FROM MAE_DETALLE_CONDICION mdc "
					+ " WHERE mdc.IDCONTRATO="+idcontrato+"ORDER BY mdc.SECUENCIA desc";
				
			} else {
				sql =  "SELECT CASE WHEN C.MONTOCUOTASOLES6 IS NOT NULL AND C.MONTOCUOTASOLES6<>0 THEN C.MONTOCUOTASOLES6 "
					   + "WHEN C.MONTOCUOTASOLES5 IS NOT NULL AND C.MONTOCUOTASOLES5<>0 THEN C.MONTOCUOTASOLES5 "
					   + "WHEN C.MONTOCUOTASOLES4 IS NOT NULL AND C.MONTOCUOTASOLES4<>0 THEN C.MONTOCUOTASOLES4 "
					   + "WHEN C.MONTOCUOTASOLES3 IS NOT NULL AND C.MONTOCUOTASOLES3<>0 THEN C.MONTOCUOTASOLES3 "
					   + "WHEN C.MONTOCUOTASOLES2 IS NOT NULL AND C.MONTOCUOTASOLES2<>0 THEN C.MONTOCUOTASOLES2 "
					   + "ELSE C.MONTOCUOTASOLES "
					   + " END AS MONTOPAGAR "
					   + "FROM Contrato C WHERE C.IDCONTRATO="+idcontrato;
			}
			
			Double value=jdbcTemplate.queryForObject(sql, new Object[] { }, Double.class);
			
			return value;
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);   		
		}
	}

	@Override
	public void grabarSinContrato(CondicionBean condicionBean) throws Exception {
		
		try{
			
			String sql = " INSERT INTO CONTRATO"
					+ 	 " (IDUPA,IDINQUILINO,SIFINALIZADO,ESTADO,CONDICION,ANIOCONTRATO,USOESPECIFICO,NUMEROCUOTAS,TIPOMONEDA, "
					+ 	 " MONTOCUOTASOLES,MONTOCUOTASOLES2,MONTOCUOTASOLES3,MONTOCUOTASOLES4,MONTOCUOTASOLES5,MONTOCUOTASOLES6,FECHACREACION,USUARIOCREADOR,"
					+    " SICUOTASCANCELADAS,INICIOCOBRO,SISUSCRITO,SIACTAENTREGA,SICOMPROMISOPAGO,SIADENDA,SIRESUELTO,SIRESOLUCION, SIACTADEVOLUCION,ACTIVO)"
					+	 " VALUES"
					+ 	 " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";		 
						
				jdbcTemplate.update(sql, new Object[] {condicionBean.getIdupa(),condicionBean.getIdinquilino(),
						condicionBean.getSifinalizado(),condicionBean.getEstado(),condicionBean.getCondicion(),condicionBean.getAniocontrato(),
						condicionBean.getUsoespecifico(),condicionBean.getNumerocuotas(),condicionBean.getMoneda(),condicionBean.getCuota1(),
						0,0,0,0,0,condicionBean.getFeccre(),condicionBean.getUsrcre(),condicionBean.getSicuotascanceladas(),condicionBean.getIniciocobro(),
						condicionBean.getSisuscrito(),condicionBean.getSiactaentrega(),condicionBean.getSicompromisopago(),condicionBean.getSiadenda(),
						condicionBean.getSiresuelto(),condicionBean.getSiresolucion(),0,1});
			
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);   		
		}
	}

	@Override
	public void actualizarEstadoGeneradoContrato(int idcontrato)
			throws Exception {
		
		String sql = " UPDATE CONTRATO"
				+ 	 " SET SIGENEROSINCONTRATO=1"
				+ 	 " WHERE IDCONTRATO="+idcontrato;		 
					
		jdbcTemplate.update(sql, new Object[] {});
	}
}