package pe.gob.sblm.sgi.scheduler.constantes;

import java.io.File;
import java.io.Serializable;

public class ConstantesReporte implements Serializable {
	
	private static final long serialVersionUID = 5016964361609493799L;

	public static final String REPORT_PATH							= new StringBuilder("reportes").append(File.separator).toString();
	
	public static final String REP_INFORMACION_ESTADO_CONDICIONES	= new StringBuilder("ARRENDAMIENTO_REP_INFORMACION_CONDICIONES.jrxml").toString();
	public static final String REP_CONTRATO_POR_FINALIZAR			= new StringBuilder("ARRENDAMIENTO_REP_CONTRATOS_POR_FINALIZAR.jrxml").toString();
	
}
