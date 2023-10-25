package pe.gob.sblm.sgi.scheduler.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.sblm.sgi.bean.CondicionBean;
import pe.gob.sblm.sgi.bean.RentaBean;
import pe.gob.sblm.sgi.scheduler.bean.Usuario;
import pe.gob.sblm.sgi.scheduler.dao.ArrendamientoDAO;
import pe.gob.sblm.sgi.scheduler.dao.IpcDAO;
import pe.gob.sblm.sgi.scheduler.dao.UsuarioDAO;
import pe.gob.sblm.sgi.scheduler.service.ArrendamientoService;
import pe.gob.sblm.sgi.scheduler.task.arrendamiento.AlertarRegistroIPC;

@Service(value="arrendamientoService")
public class ArrendamientoServiceImpl implements ArrendamientoService,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8250485634336562159L;

	@Autowired
	ArrendamientoDAO arrendamientoDAO;
	
	@Autowired
	UsuarioDAO usuarioDAO;
	
	
	@Autowired
	IpcDAO ipcDAO;
	
	private static final Logger Logger = LoggerFactory.getLogger(ArrendamientoServiceImpl.class);
	

	@Override
	public Double obtenerProximoIPC(String anio) throws Exception {
		
		try{
		
			return ipcDAO.obtenerProximoIPC(anio);
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}
	
	@Override
	public void actualizarEstadoContratosFinalizados() throws Exception {
		
		try{
			arrendamientoDAO.actualizarEstadoContratosFinalizados();
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public void actualizarEstadoPreContratosFinalizados() throws Exception {
		
		try{
			arrendamientoDAO.actualizarEstadoPreContratosFinalizados();
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}
	@Override
	public void actualizarEstadoReconocimientoDeudaFinalizados() throws Exception {
		
		try{
			arrendamientoDAO.actualizarEstadoReconocimientoDeudaFinalizados();
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}
	@Override
	public void recuperarVigenciaEstadoContratosFinalizados() throws Exception {
		try{
		
			arrendamientoDAO.recuperarVigenciaEstadoContratosFinalizados();
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
		
	}


	@Override
	public List<Usuario> obtenerUsuarioDestinoTareaProgramada(String nombreTareaProgramada) throws Exception {
		
		try{
			return usuarioDAO.obtenerUsuarioDestinoTareaProgramada(nombreTareaProgramada);
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	
	}


	public Boolean siProximoIPCcreado(String anio) throws Exception {
		try{
			return ipcDAO.siIPCcreado(anio);
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
		
	}

	@Override
	public String obtenerUltimoIPCRegistrado() throws Exception {
		try{
			return ipcDAO.obtenerUltimoIPCRegistrado();
			
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}
	

	@Override
	public List<CondicionBean> obtenerContratosPendientesGeneracionRentas() throws Exception {
		try{
			return arrendamientoDAO.obtenerContratosPendientesGeneracionRentas();
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
	}
	}


	@Override
	public List<RentaBean> obtenerRentaPendienteGeneracion(int idcontrato,String anio) throws Exception {
		try{	
			return arrendamientoDAO.obtenerRentaPendienteGeneracion(idcontrato,anio);
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}
	@Override
	public List<RentaBean> obtenerRentaPendienteGeneracion(int idcontrato,String anio , int sec_ini,int sec_fin) throws Exception {
		try{	
			return arrendamientoDAO.obtenerRentaPendienteGeneracion(idcontrato,anio,sec_ini,sec_fin);
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}
	@Override
	public Double obtenerValorUltimaRenta(int idcontrato) throws Exception {
		try{
			return arrendamientoDAO.obtenerValorUltimaRenta(idcontrato);
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public void actualizarRentaPendiente(RentaBean rentaBean) throws Exception {
		try{
			arrendamientoDAO.actualizarRentaPendiente(rentaBean);
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}
	@Override
	public void actualizarRentaPendiente(int idcontrato,int anio) throws Exception {
		try{
			arrendamientoDAO.actualizarRentaPendiente(idcontrato,anio);
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public List<CondicionBean> obtenerContratosFinalizadosSinActaDevolucion() throws Exception {
		try{
			return arrendamientoDAO.obtenerContratosFinalizadosSinActaDevolucion();
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}

	@Override
	public Double obtenerUltimaRentaContrato(int idcontrato, Boolean sinuevomantenimiento) throws Exception {
		try{
			return arrendamientoDAO.obtenerUltimaRentaContrato(idcontrato,sinuevomantenimiento);
		
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor= Exception.class)
	public void grabarSinContrato(CondicionBean condicionBean) throws Exception {
		
		try{
			arrendamientoDAO.grabarSinContrato(condicionBean);
			arrendamientoDAO.actualizarEstadoGeneradoContrato(condicionBean.getIdcontrato());
			
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
	}
}
