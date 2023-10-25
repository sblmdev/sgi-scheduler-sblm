package pe.gob.sblm.sgi.scheduler.task.arrendamiento;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.gob.sblm.api.commons.utility.FechaUtil;
import pe.gob.sblm.api.commons.utility.FuncionesHelper;
import pe.gob.sblm.sgi.bean.CondicionBean;
import pe.gob.sblm.sgi.bean.RentaBean;
import pe.gob.sblm.sgi.scheduler.service.ArrendamientoService;
import pe.gob.sblm.sgi.scheduler.task.base.SgiTask;
import pe.gob.sblm.sgi.scheduler.util.Almanaque;


@Component("generarRentasPendientes")
public class GenerarRentasPendientes extends SgiTask{
	
	@Autowired
	private ArrendamientoService arrendamientoService;
    
	private static final Logger logger = LoggerFactory.getLogger(GenerarRentasPendientes.class);


    
    public void excecuteTask() {
    	
    	try {
    		
//    		Integer anioSgt=Integer.parseInt(FechaUtil.getYear(new Date()))+1;
    		Integer anioSgt=Integer.parseInt(FechaUtil.getYear(new Date()));
    		
    		if (arrendamientoService.siProximoIPCcreado(String.valueOf(anioSgt))) {
				Double valorProximoIPC=arrendamientoService.obtenerProximoIPC(String.valueOf(anioSgt));
				
    			List<CondicionBean> listaCondicionBean=arrendamientoService.obtenerContratosPendientesGeneracionRentas();
        		
        		for (CondicionBean condicionBean : listaCondicionBean) {
        			
        			Double valorUltimaRenta=arrendamientoService.obtenerValorUltimaRenta(condicionBean.getIdcontrato());
        			
        			List<RentaBean> listaRentaBeans = arrendamientoService.obtenerRentaPendienteGeneracion(condicionBean.getIdcontrato(),String.valueOf(anioSgt));
        			
        			/**/
        			
        			if(listaRentaBeans != null ){
        				if(listaRentaBeans.size()>0){
        				Integer a  = listaCondicionBean.get(0).getIniciocontrato().getMonth();
        				System.out.println("IDCONTRATO="+condicionBean.getIdcontrato());
            			System.out.println( "mes numero="+ a );
            			System.out.println("listaRentaBeans.get(0).getNumeromes()="+listaRentaBeans.get(0).getNumeromes());
            			System.out.println("listaRentaBeans.get(0).getSecuencia()%12==1"+listaRentaBeans.get(0).getSecuencia()%12);
        			if (listaRentaBeans.size()==12 && listaRentaBeans.get(0).getSecuencia()%12==1){
        				
        			}else{
        				/*hallar primer extremo*/
        				int secuenciainicial=0,secuenciafinal=0;
        				secuenciainicial=(listaRentaBeans.get(0).getSecuencia()/12)*12+1;
        				secuenciafinal	=(listaRentaBeans.get(0).getSecuencia()/12+1)*12;
        				System.out.println("secuencia inicial="+secuenciainicial+" ; "+"secuencia final="+secuenciafinal);
        				listaRentaBeans.clear();
        				listaRentaBeans = arrendamientoService.obtenerRentaPendienteGeneracion(condicionBean.getIdcontrato(),String.valueOf(anioSgt),secuenciainicial,secuenciafinal);
        			    for(RentaBean rentaBean : listaRentaBeans){
        			    	System.out.println("renta="+rentaBean.getMes()+" ; anio= "+rentaBean.getAnio()+" => "+rentaBean.getRenta());
        			    }
        			}
        				}
        			}
        			
        			/*INICIO*/
        			if (condicionBean.getReajusteanual()>=valorProximoIPC) {
        				
        				for (RentaBean rentaBean : listaRentaBeans) {
        					
        					if (rentaBean.getSiclausulaperiodogracia()) {
        						
        						rentaBean.setRenta(0.0);
        						
							} else {
								rentaBean.setRenta(FuncionesHelper.redondear(valorUltimaRenta*(100+condicionBean.getReajusteanual())/100, 2));
							}
        					System.out.println("Renta:"+rentaBean.getRenta());
        					System.out.println("Renta descuentoReconocimientoInversion:"+rentaBean.getDescuentoreconocimientoinversion());
        					System.out.println("Renta descuentoReconocimientoRenta:"+rentaBean.getDescuentoreconocimientorenta());
        					System.out.println("Renta montoPagoPosterior:"+rentaBean.getMontopagoposterior());
        					
//        					rentaBean.setMontopagar(rentaBean.getRenta()+rentaBean.getDescuentoreconocimientoinversion()+rentaBean.getDescuentoreconocimientorenta()+rentaBean.getMontopagoposterior());
        					rentaBean.setMontopagar(rentaBean.getRenta()+rentaBean.getDescuentoreconocimientorenta()+rentaBean.getMontopagoposterior());
							
        					System.out.println("Renta:"+rentaBean.getMontopagar());
        					
        					arrendamientoService.actualizarRentaPendiente(rentaBean);
        					arrendamientoService.actualizarRentaPendiente(condicionBean.getIdcontrato(),anioSgt);
            			}
						
					} else {
						
						for (RentaBean rentaBean : listaRentaBeans) {
							
							if (rentaBean.getSiclausulaperiodogracia()) {
								
								rentaBean.setRenta(0.0);
								
							}else {
								
								rentaBean.setRenta(FuncionesHelper.redondear(valorUltimaRenta*(100+valorProximoIPC)/100, 2));
								
							}
							
//        					rentaBean.setMontopagar(rentaBean.getRenta()+rentaBean.getDescuentoreconocimientoinversion()+rentaBean.getDescuentoreconocimientorenta()+rentaBean.getMontopagoposterior());
        					rentaBean.setMontopagar(rentaBean.getRenta()+rentaBean.getDescuentoreconocimientorenta()+rentaBean.getMontopagoposterior());
        					
							arrendamientoService.actualizarRentaPendiente(rentaBean);
							arrendamientoService.actualizarRentaPendiente(condicionBean.getIdcontrato(),anioSgt);
	        			}
					}/*FIN*/
        			
        			
    				
    			}
        		
			} else {

				logger.info("Aun no se ha creado proximo IPC");
				
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
