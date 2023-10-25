package pe.gob.sblm.sgi.scheduler.dao;

import java.util.List;

import pe.gob.sblm.sgi.bean.CondicionBean;
import pe.gob.sblm.sgi.bean.RentaBean;




public interface ArrendamientoDAO {


	public void actualizarEstadoContratosFinalizados();
	public void actualizarEstadoPreContratosFinalizados();
	public void actualizarEstadoReconocimientoDeudaFinalizados();
	public void recuperarVigenciaEstadoContratosFinalizados();
	public List<CondicionBean> obtenerContratosPendientesGeneracionRentas();
	public List<RentaBean> obtenerRentaPendienteGeneracion(int idcontrato,String anio);
	public List<RentaBean> obtenerRentaPendienteGeneracion(int idcontrato,String anio, int sec_ini,int sec_fin);
	public Double obtenerValorUltimaRenta(int idcontrato);
	public void actualizarRentaPendiente(RentaBean rentaBean) throws Exception;
	public void actualizarRentaPendiente(int idcontrato,int anio) throws Exception;
	public List<CondicionBean> obtenerContratosFinalizadosSinActaDevolucion() throws Exception;
	public Double obtenerUltimaRentaContrato(int idcontrato,Boolean sinuevomantenimiento)throws Exception;
	public void grabarSinContrato(CondicionBean condicionBean)throws Exception;
	public void actualizarEstadoGeneradoContrato(int idcontrato)throws Exception;
	
}
