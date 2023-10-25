package pe.gob.sblm.sgi.scheduler.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pe.gob.sblm.api.commons.utility.CorreoUtil;
import pe.gob.sblm.api.mail.properties.MailProperties;
import pe.gob.sblm.api.mail.properties.MessageProperties;
import pe.gob.sblm.api.mail.stmp.Mail;
import pe.gob.sblm.api.mail.stmp.MailBuilder;
import pe.gob.sblm.api.mail.util.FileAttach;
import pe.gob.sblm.sgi.scheduler.properties.PropiedadesCorreo;
import pe.gob.sblm.sgi.scheduler.service.CorreoService;

@Service("correoService")
public class CorreoServiceImpl implements CorreoService {
	
	
	private static final Logger Logger = LoggerFactory.getLogger(CorreoServiceImpl.class);

	@Override
	public void enviarNotificacion(String correoDestino, String asunto,String nombresDestino, String mensaje, String url) throws Exception {
		try{
			
			MailProperties mailProperties = new MailProperties();
			mailProperties.setSmtpIp(PropiedadesCorreo.getString("smtp_host"));
			mailProperties.setSmtpAuth("false");
			mailProperties.setSmtpTlsEnable("false");
			mailProperties.setSmtpProtocolo("smtp");
			mailProperties.setSmtpPuerto(PropiedadesCorreo.getString("smtp_puerto"));
			mailProperties.setSmtpCorreoEmisor(PropiedadesCorreo.getString("smtp_correo_emisor"));
			
			MailBuilder mailBuilder = new MailBuilder();
			Mail mail;
			mail = mailBuilder.build(mailProperties,PropiedadesCorreo.getString("smtp_correo_usuario"),PropiedadesCorreo.getString("smtp_correo_contrasenia"));
			
			
			MessageProperties messageProperties = new MessageProperties();
			messageProperties.setAsunto(asunto);
			messageProperties.setContenido(CorreoUtil.obtenerPlantillaContenidoCorreoNotificacion(nombresDestino,mensaje,url));
			messageProperties.setDestinatarios(correoDestino);
			System.out.println("Correo Destino="+correoDestino);
			
			mail.send(messageProperties);
			
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
		
	}
	
	@Override
	public void enviarNotificacion(String correoDestino, String asunto,String nombresDestino, String mensaje, byte[] stream) throws Exception {
		try{
			
			MailProperties mailProperties = new MailProperties();
			//mailProperties.setSmtpIp("mail.sblm.gob.pe");
			mailProperties.setSmtpIp(PropiedadesCorreo.getString("smtp_host"));			
			mailProperties.setSmtpAuth("false");
			mailProperties.setSmtpTlsEnable("false");
			mailProperties.setSmtpProtocolo("smtp");
			mailProperties.setSmtpPuerto(PropiedadesCorreo.getString("smtp_puerto"));
			mailProperties.setSmtpCorreoEmisor(PropiedadesCorreo.getString("smtp_correo_emisor"));
			
			MailBuilder mailBuilder = new MailBuilder();
			Mail mail;
			mail = mailBuilder.build(mailProperties,"sistemasgi@sblm.gob.pe","beneficencia2019");
			//mail = mailBuilder.build(mailProperties,"sgi@sblm.gob.pe","s1st3m@s");
			
			MessageProperties messageProperties = new MessageProperties();
			messageProperties.setAsunto(asunto);
			messageProperties.setContenido(CorreoUtil.obtenerPlantillaContenidoCorreoNotificacion(nombresDestino,mensaje));
			messageProperties.setDestinatarios(correoDestino);
			System.out.println("Correo DestinoA="+correoDestino);
			List<FileAttach> listaAttachs= new ArrayList<FileAttach>();
			FileAttach fileAttach= new FileAttach();
			fileAttach.setFilename("reporte.pdf");
			fileAttach.setStream(stream); 
			
			listaAttachs.add(fileAttach);
			
			
			mail.send(messageProperties,listaAttachs);
			
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			throw new Exception(e.getMessage(), e);
		}
		
	}

	@Override
	public void enviarNotificacion(String correoDestino, String asunto, String nombresDestino, String mensaje)
			throws Exception {
		
		try{
			MailProperties mailProperties = new MailProperties();
			mailProperties.setSmtpIp(PropiedadesCorreo.getString("smtp_host"));
			mailProperties.setSmtpAuth("false");
			mailProperties.setSmtpTlsEnable("false");
			mailProperties.setSmtpProtocolo("smtp");
			mailProperties.setSmtpPuerto(PropiedadesCorreo.getString("smtp_puerto"));
			mailProperties.setSmtpCorreoEmisor(PropiedadesCorreo.getString("smtp_correo_emisor"));
			
			MailBuilder mailBuilder = new MailBuilder();
			Mail mail;
			mail = mailBuilder.build(mailProperties,PropiedadesCorreo.getString("smtp_correo_usuario"),PropiedadesCorreo.getString("smtp_correo_contrasenia"));
				
			MessageProperties messageProperties = new MessageProperties();
			messageProperties.setAsunto(asunto);
			messageProperties.setContenido(CorreoUtil.obtenerPlantillaContenidoCorreoNotificacion(nombresDestino,mensaje));
			messageProperties.setDestinatarios(correoDestino);
			System.out.println("Correo DestinoB="+correoDestino);
			mail.send(messageProperties);
	
		}catch(Exception e){
			Logger.error(e.getMessage(), e);
			e.printStackTrace();
			throw new Exception(e.getMessage(), e);
		}
	
	}

}
