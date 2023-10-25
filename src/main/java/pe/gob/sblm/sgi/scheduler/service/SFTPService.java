package pe.gob.sblm.sgi.scheduler.service;

import java.io.InputStream;
import java.util.Date;



public interface SFTPService {

	public void upload(InputStream stream, String nombre, String path) throws Exception;
	public InputStream downloadInputStream(String nombre, Date fexpedicion) throws Exception;
	public byte[] downloadByteArray(String nombre, Date fexpedicion)throws Exception ;
	

}
