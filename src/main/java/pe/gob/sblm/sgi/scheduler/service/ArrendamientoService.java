package pe.gob.sblm.sgi.scheduler.service;

import java.util.List;

import pe.gob.sblm.sgi.bean.CondicionBean;
import pe.gob.sblm.sgi.bean.RentaBean;
import pe.gob.sblm.sgi.scheduler.bean.Usuario;



public interface ArrendamientoService {

	public void actualizarEstadoContratosFinalizados()throws Exception;
	public void actualizarEstadoPreContratosFinalizados()throws Exception;
	public void actualizarEstadoReconocimientoDeudaFinalizados()throws Exception;
	public void recuperarVigenciaEstadoContratosFinalizados()throws Exception;
	public List<Usuario> obtenerUsuarioDestinoTareaProgramada(String arrendamientoDifusionEstadoCondiciones)throws Exception;
	public Boolean siProximoIPCcreado(String anio)throws Exception;
	public Double obtenerProximoIPC(String anio)throws Exception;
	public List<CondicionBean> obtenerContratosPendientesGeneracionRentas()throws Exception;
	public List<RentaBean> obtenerRentaPendienteGeneracion(int idcontrato,String anio)throws Exception;
	public List<RentaBean> obtenerRentaPendienteGeneracion(int idcontrato,String anio,int sec_ini , int sec_fin)throws Exception;
	public Double obtenerValorUltimaRenta(int idcontrato)throws Exception;
	public void actualizarRentaPendiente(RentaBean rentaBean) throws Exception;
	public void actualizarRentaPendiente(int idcontrato,int anio) throws Exception;
	public String obtenerUltimoIPCRegistrado() throws Exception;
	public List<CondicionBean> obtenerContratosFinalizadosSinActaDevolucion() throws Exception;
	public Double obtenerUltimaRentaContrato(int idcontrato,Boolean sinuevomantenimiento) throws Exception;
	public void grabarSinContrato(CondicionBean condicionBean)throws Exception;
}
