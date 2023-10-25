package pe.gob.sblm.sgi.scheduler.service;

import java.sql.Connection;
import java.util.Map;


public interface ReporteGeneradorService {

	public byte[]  generarPDF(String nombreReporte,Map<String, Object> parameters, Connection connection);
	
}
