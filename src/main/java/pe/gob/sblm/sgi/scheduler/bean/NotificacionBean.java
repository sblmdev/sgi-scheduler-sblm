package pe.gob.sblm.sgi.scheduler.bean;


import java.util.Date;

public class NotificacionBean  implements java.io.Serializable {


     private int 	idNotificacion;
     private int 	idusuarioorigen;
     private int 	idusuariodestino;
     private String rutaimgusr;
     private String titulo;
     private String mensaje;
     private String uidAlfresco;
     private Boolean estadoLeido;
     private String usuarioCreador;
     private Date fechaCreacion;
     private Date fechaEnvioCorreo;
     private String nombreUsuarioDestino;
     private String correoUsuarioDestino;
     private Boolean siNotificacionTipoReporte;
     private Boolean estadoCorreoEnviado;


    public int getIdNotificacion() {
        return this.idNotificacion;
    }
    
    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

	public String getMensaje() {
        return this.mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String getUidAlfresco() {
        return this.uidAlfresco;
    }
    
    public void setUidAlfresco(String uidAlfresco) {
        this.uidAlfresco = uidAlfresco;
    }

    public Boolean getEstadoLeido() {
        return this.estadoLeido;
    }
    
    public void setEstadoLeido(Boolean estadoLeido) {
        this.estadoLeido = estadoLeido;
    }
    
    public String getUsuarioCreador() {
        return this.usuarioCreador;
    }
    
    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
	public int getIdusuarioorigen() {
		return idusuarioorigen;
	}

	public void setIdusuarioorigen(int idusuarioorigen) {
		this.idusuarioorigen = idusuarioorigen;
	}

	public int getIdusuariodestino() {
		return idusuariodestino;
	}

	public void setIdusuariodestino(int idusuariodestino) {
		this.idusuariodestino = idusuariodestino;
	}

	public String getRutaimgusr() {
		return rutaimgusr;
	}

	public void setRutaimgusr(String rutaimgusr) {
		this.rutaimgusr = rutaimgusr;
	}

	public Date getFechaEnvioCorreo() {
		return fechaEnvioCorreo;
	}

	public void setFechaEnvioCorreo(Date fechaEnvioCorreo) {
		this.fechaEnvioCorreo = fechaEnvioCorreo;
	}

	public String getNombreUsuarioDestino() {
		return nombreUsuarioDestino;
	}

	public void setNombreUsuarioDestino(String nombreUsuarioDestino) {
		this.nombreUsuarioDestino = nombreUsuarioDestino;
	}

	public String getCorreoUsuarioDestino() {
		return correoUsuarioDestino;
	}

	public void setCorreoUsuarioDestino(String correoUsuarioDestino) {
		this.correoUsuarioDestino = correoUsuarioDestino;
	}

	public Boolean getSiNotificacionTipoReporte() {
		return siNotificacionTipoReporte;
	}

	public void setSiNotificacionTipoReporte(Boolean siNotificacionTipoReporte) {
		this.siNotificacionTipoReporte = siNotificacionTipoReporte;
	}

	/**
	 * @return the estadoCorreoEnviado
	 */
	public Boolean getEstadoCorreoEnviado() {
		return estadoCorreoEnviado;
	}

	/**
	 * @param estadoCorreoEnviado the estadoCorreoEnviado to set
	 */
	public void setEstadoCorreoEnviado(Boolean estadoCorreoEnviado) {
		this.estadoCorreoEnviado = estadoCorreoEnviado;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
}


