package pe.gob.sblm.sgi.scheduler.util;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ExportarArchivo implements Serializable{

	private static final long serialVersionUID = 1L;

    public static byte[] exportPdfUseConnectionExpression(String jasperFile, Map<String, Object> parameters,Connection connection) throws Exception {
        JasperReport report = JasperCompileManager.compileReport(jasperFile);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, connection);
        JRPdfExporter jRPdfExporter = new JRPdfExporter();
        
        jRPdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        
        jRPdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        
        jRPdfExporter.exportReport();
        byte[] bytes = byteArrayOutputStream.toByteArray(); 
        jRPdfExporter = null;
        return bytes;
    }



}
