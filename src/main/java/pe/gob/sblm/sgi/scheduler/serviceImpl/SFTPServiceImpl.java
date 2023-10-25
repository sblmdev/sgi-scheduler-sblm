package pe.gob.sblm.sgi.scheduler.serviceImpl;

import java.io.InputStream;
import java.util.Date;

import org.springframework.stereotype.Service;

import pe.gob.sblm.api.sftp.client.SftpClient;
import pe.gob.sblm.sgi.scheduler.service.SFTPService;

@Service(value="sftpService")
public class SFTPServiceImpl implements SFTPService {
	

	@Override
	public void upload(InputStream stream, String nombre, String path)
			throws Exception {
		
		SftpClient sftpClient = new SftpClient("127.0.0.1", "desarrollo", "d3s4rr0ll0$", "22");
		sftpClient.iniciarConexion();
//		sftpClient.upload(stream, nombre, path);
		sftpClient.cerrarConexion();
	
	}

	@Override
	public InputStream downloadInputStream(String num_certi, Date fexpedicion)
			throws Exception {
		
		return null;
	}

	@Override
	public byte[] downloadByteArray(String num_certi, Date fexpedicion)
			throws Exception {

		return null;
	}
	
}
