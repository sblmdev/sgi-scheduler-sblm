package pe.gob.sblm.sgi.scheduler.serviceImpl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.gob.sblm.sgi.scheduler.dao.UsuarioDAO;
import pe.gob.sblm.sgi.scheduler.service.UsuarioService;

@Service(value="usuarioService")
public class UsuarioServiceImpl implements UsuarioService,Serializable{

	@Autowired
	UsuarioDAO usuarioDAO;

	@Override
	public void desactivarCuenta() {
		usuarioDAO.desactivarCuenta();
	}

}
