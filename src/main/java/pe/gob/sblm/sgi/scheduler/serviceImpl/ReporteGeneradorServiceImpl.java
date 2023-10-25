package pe.gob.sblm.sgi.scheduler.serviceImpl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.sql.Connection;
import java.util.Map;

import org.springframework.stereotype.Service;

import pe.gob.sblm.sgi.scheduler.constantes.ConstantesReporte;
import pe.gob.sblm.sgi.scheduler.service.ReporteGeneradorService;
import pe.gob.sblm.sgi.scheduler.util.ExportarArchivo;

@Service(value="reporteGeneradorService")
public class ReporteGeneradorServiceImpl implements ReporteGeneradorService {

	@SuppressWarnings("static-access")
	public byte[] generarPDF(String nombreReporte,Map<String, Object> parameters, Connection connection) {
		
		ExportarArchivo printer = new ExportarArchivo();
		StringBuilder jasperFile = new StringBuilder();
		
		
		jasperFile.append(getApplcatonPath());
		
		jasperFile.append(ConstantesReporte.REPORT_PATH);
		jasperFile.append(nombreReporte);
		
		
		StringBuilder temp = new StringBuilder();
		temp.append(getApplcatonPath());
		temp.append(ConstantesReporte.REPORT_PATH);
		String imagenRuta = temp.toString();		
		parameters.put("LOGO_DIR", imagenRuta);
		
		
		StringBuilder temp2 = new StringBuilder();
		temp2.append(getApplcatonPath());
		temp2.append(ConstantesReporte.REPORT_PATH);
		String subReportRuta = temp2.toString();		
		parameters.put("SUBREPORT_DIR", subReportRuta);
		
		try{
			byte[] array = printer.exportPdfUseConnectionExpression(jasperFile.toString(), parameters, connection);
			return array;
		}catch(Exception e){
			System.out.println("ExceptioN:"+e.getMessage());
			return null;
		}
	}
	public static String getApplcatonPath(){
        CodeSource codeSource = ReporteGeneradorServiceImpl.class.getProtectionDomain().getCodeSource();
        File rootPath = null;
        try {
            rootPath = new File(codeSource.getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }           
        return rootPath.getParentFile().getParent().concat("/");
    }//end of getApplcatonPath()
}
