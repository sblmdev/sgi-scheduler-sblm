package pe.gob.sblm.sgi.scheduler.dao;

import java.util.List;

import pe.gob.sblm.sgi.scheduler.bean.Usuario;



public interface UsuarioDAO {

	public void desactivarCuenta();

	public List<Usuario> obtenerUsuarioDestinoTareaProgramada(
			String nombreTareaProgramada);
	
}
