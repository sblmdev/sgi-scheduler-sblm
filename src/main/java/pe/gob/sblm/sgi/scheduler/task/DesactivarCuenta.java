package pe.gob.sblm.sgi.scheduler.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.gob.sblm.sgi.scheduler.service.UsuarioService;
import pe.gob.sblm.sgi.scheduler.task.base.SgiTask;


@Component("desactivarCuenta")
public class DesactivarCuenta extends SgiTask{
	
	@Autowired
	private UsuarioService usuarioService;
    private static final Logger log = LoggerFactory.getLogger(DesactivarCuenta.class);


    
    /**1. desactivarCuenta**/
    /**2. actualizar estado de contratos**/
    
    
	/**
	 * Desactiva la cuenta de aquellos usuarios cuyo tiempo de inactividad sea mayor a los 15 dias
	 */
    public void excecuteTask() {

    	
//    	usuarioService.desactivarCuenta();

    }



	/**
	 * @return the usuarioService
	 */
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	/**
	 * @param usuarioService the usuarioService to set
	 */
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	/**
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}


}
