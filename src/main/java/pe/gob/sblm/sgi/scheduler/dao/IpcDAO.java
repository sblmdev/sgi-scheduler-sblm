package pe.gob.sblm.sgi.scheduler.dao;





public interface IpcDAO {

	public Double obtenerProximoIPC(String anio)throws Exception;
	public Boolean siIPCcreado(String anio) throws Exception;
	public String obtenerUltimoIPCRegistrado()throws Exception;
	
}
