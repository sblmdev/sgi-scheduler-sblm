package pe.gob.sblm.sgi.scheduler.service;

import pe.gob.sblm.sgi.scheduler.bean.ArchivoAdjuntoBean;

public interface UploadRepositorioService {

	public String grabarDocumento(ArchivoAdjuntoBean archivoAdjuntoBean,String tipoReporte);
	public Object buscarDocumento(String path,String filename);
}
