package pe.gob.sblm.sgi.scheduler.service;


/**
 * Created by cvallejos on 25/05/2016.
 */
public interface CorreoService {

    public void enviarNotificacion(String correoDestino,String asunto,String nombresDestino, String mensaje, String url) throws Exception;
    public void enviarNotificacion(String correoDestino,String asunto, String nombresDestino, String mensaje) throws Exception;
    public void enviarNotificacion(String correoDestino, String asunto,String nombresDestino, String mensaje, byte[] stream) throws Exception;
}
