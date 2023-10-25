package pe.gob.sblm.sgi.scheduler.task.arrendamiento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.gob.sblm.api.commons.constants.sgi.Constantes;
import pe.gob.sblm.sgi.bean.CondicionBean;
import pe.gob.sblm.sgi.scheduler.service.ArrendamientoService;
import pe.gob.sblm.sgi.scheduler.task.base.SgiTask;


@Component("generarSinContrato")
public class GenerarSinContrato extends SgiTask{
	
	@Autowired
	private ArrendamientoService arrendamientoService;
    
	private static final Logger logger = LoggerFactory.getLogger(GenerarSinContrato.class);


    
    public void excecuteTask() {
    	
    	try {
    		
    		logger.info("Ejecutando tarea programada : " + Constantes.TAREA_ARRENDAMIENTO_GENERAR_SIN_CONTRATO);
        	List<CondicionBean> condiciones= new ArrayList<CondicionBean>();
        	condiciones=arrendamientoService.obtenerContratosFinalizadosSinActaDevolucion();
        	
        	logger.info(condiciones.size()+" contrato pendientes para generar sin contrato");
        	
        	for (CondicionBean condicionBean : condiciones) {
        		
        		Double rentaactual=	arrendamientoService.obtenerUltimaRentaContrato(condicionBean.getIdcontrato(),condicionBean.getSinuevomantenimiento());
        		Integer anioContrato;
        		
        		condicionBean.setCuota1(rentaactual);
        		
        		Calendar iniciocobro = Calendar.getInstance();
        		iniciocobro.setTime(condicionBean.getFincobro());
        		
        		anioContrato=iniciocobro.get(Calendar.YEAR);
        		iniciocobro.add(Calendar.MONTH, 1);
        		
        		condicionBean.setIniciocobro(iniciocobro.getTime());
        		
        		condicionBean.setCondicion("Sin Contrato");
        		condicionBean.setEstado("VIGENTE");
    			
        		condicionBean.setAniocontrato(String.valueOf(anioContrato));
    			
        		condicionBean.setSiresuelto(false);
        		condicionBean.setSiadenda(false);
        		condicionBean.setFeccre(new Date());
        		condicionBean.setUsrcre("admin");
        		condicionBean.setSicuotascanceladas(false);
        		condicionBean.setSicompromisopago(false);
    			
    			
        		condicionBean.setObservacion("");
    			
    			
        		condicionBean.setSisuscrito(false);
        		condicionBean.setSiactaentrega(false);
        		condicionBean.setSiresolucion(false);
        		condicionBean.setSifinalizado(false);
    			
        		condicionBean.setNumerocuotas(0);
        		
        		
        		arrendamientoService.grabarSinContrato(condicionBean);
        	
			}
    		
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
