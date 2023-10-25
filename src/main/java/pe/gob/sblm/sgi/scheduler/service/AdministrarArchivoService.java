package pe.gob.sblm.sgi.scheduler.service;

import java.util.List;

import pe.gob.sblm.sgi.scheduler.bean.ArchivoAdjuntoBean;



public interface AdministrarArchivoService {

	public void grabarArchivo(ArchivoAdjuntoBean archivoAdjuntoBean, String vinculoDocumento, int idEntidad,String identificadorEntidad) throws Exception;
	public List<ArchivoAdjuntoBean> obtenerArchivosReferencia(int idinmueble,String nombreEntidad)  throws Exception;
}
