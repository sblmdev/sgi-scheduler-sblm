package pe.gob.sblm.sgi.scheduler.bean;

import java.io.InputStream;
import java.util.Date;

public class ArchivoAdjuntoBean {
	private int idarchivoadjunto;
	private String uid;
	private String uidAlfresco;
	private String tipo;
	private String nombre;
	private String titulo;
	private String descripcion;
	private String observacion;
	private String ruta;
	private String usrcre;
	private String usrmod;
	private Date feccre;
	private Date fecmod;
	private byte[] stream;
	
	
	public int getIdarchivoadjunto() {
		return idarchivoadjunto;
	}
	public void setIdarchivoadjunto(int idarchivoadjunto) {
		this.idarchivoadjunto = idarchivoadjunto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getUsrcre() {
		return usrcre;
	}
	public void setUsrcre(String usrcre) {
		this.usrcre = usrcre;
	}
	public String getUsrmod() {
		return usrmod;
	}
	public void setUsrmod(String usrmod) {
		this.usrmod = usrmod;
	}
	public Date getFeccre() {
		return feccre;
	}
	public void setFeccre(Date feccre) {
		this.feccre = feccre;
	}
	public Date getFecmod() {
		return fecmod;
	}
	public void setFecmod(Date fecmod) {
		this.fecmod = fecmod;
	}
	public byte[] getStream() {
		return stream;
	}
	public void setStream(byte[] stream) {
		this.stream = stream;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUidAlfresco() {
		return uidAlfresco;
	}
	public void setUidAlfresco(String uidAlfresco) {
		this.uidAlfresco = uidAlfresco;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
