package pe.gob.sblm.sgi.scheduler.task.arrendamiento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.gob.sblm.sgi.scheduler.service.ArrendamientoService;
import pe.gob.sblm.sgi.scheduler.task.base.SgiTask;


@Component("actualizarEstadoContrato")
public class ActualizarEstadoContrato extends SgiTask{
	
	@Autowired
	private ArrendamientoService arrendamientoService;
    
	private static final Logger logger = LoggerFactory.getLogger(ActualizarEstadoContrato.class);


    
    /**
	actualizarEstadoContratosFinalizados: Actualiza el estado de los contratos que finalizaron
	recuperarVigenciaEstadoContratosFinalizados: Recupera es estado de contratos en caso de modificacion de fecha de fincontrato.
    **/
    public void excecuteTask() {
    	logger.info("Iniciando tarea actualizar estado de contrato, precontrato y reconocimiento deuda");
    	
    	try {
        	arrendamientoService.actualizarEstadoContratosFinalizados();
        	arrendamientoService.actualizarEstadoPreContratosFinalizados();
        	arrendamientoService.actualizarEstadoReconocimientoDeudaFinalizados();
//        	arrendamientoService.recuperarVigenciaEstadoContratosFinalizados();
		} catch (Exception e) {
			
			logger.error(e.getMessage(), e);
		}
    }


	public ArrendamientoService getArrendamientoService() {
		return arrendamientoService;
	}


	public void setArrendamientoService(ArrendamientoService arrendamientoService) {
		this.arrendamientoService = arrendamientoService;
	}


}
