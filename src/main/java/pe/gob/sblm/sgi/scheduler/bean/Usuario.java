package pe.gob.sblm.sgi.scheduler.bean;
// Generated 19/03/2014 03:10:50 PM by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private int idusuario;
     private String nombres;
     private String apellidopat;
     private String apellidomat;
     private Date fechanacimiento;
     private Boolean estado;
     private String nombreusr;
     private String cargo;
     private String nombrescompletos;
     private String emailusr;
	/**
	 * @return the idusuario
	 */
	public int getIdusuario() {
		return idusuario;
	}
	/**
	 * @param idusuario the idusuario to set
	 */
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	/**
	 * @return the nombres
	 */
	public String getNombres() {
		return nombres;
	}
	/**
	 * @param nombres the nombres to set
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	/**
	 * @return the apellidopat
	 */
	public String getApellidopat() {
		return apellidopat;
	}
	/**
	 * @param apellidopat the apellidopat to set
	 */
	public void setApellidopat(String apellidopat) {
		this.apellidopat = apellidopat;
	}
	/**
	 * @return the apellidomat
	 */
	public String getApellidomat() {
		return apellidomat;
	}
	/**
	 * @param apellidomat the apellidomat to set
	 */
	public void setApellidomat(String apellidomat) {
		this.apellidomat = apellidomat;
	}
	/**
	 * @return the fechanacimiento
	 */
	public Date getFechanacimiento() {
		return fechanacimiento;
	}
	/**
	 * @param fechanacimiento the fechanacimiento to set
	 */
	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}
	/**
	 * @return the estado
	 */
	public Boolean getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	/**
	 * @return the nombreusr
	 */
	public String getNombreusr() {
		return nombreusr;
	}
	/**
	 * @param nombreusr the nombreusr to set
	 */
	public void setNombreusr(String nombreusr) {
		this.nombreusr = nombreusr;
	}
	/**
	 * @return the cargo
	 */
	public String getCargo() {
		return cargo;
	}
	/**
	 * @param cargo the cargo to set
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	/**
	 * @return the nombrescompletos
	 */
	public String getNombrescompletos() {
		return nombrescompletos;
	}
	/**
	 * @param nombrescompletos the nombrescompletos to set
	 */
	public void setNombrescompletos(String nombrescompletos) {
		this.nombrescompletos = nombrescompletos;
	}
	/**
	 * @return the emailusr
	 */
	public String getEmailusr() {
		return emailusr;
	}
	/**
	 * @param emailusr the emailusr to set
	 */
	public void setEmailusr(String emailusr) {
		this.emailusr = emailusr;
	}
	@Override
	public String toString() {
		return "Usuario [idusuario=" + idusuario + ", nombres=" + nombres
				+ ", apellidopat=" + apellidopat + ", apellidomat="
				+ apellidomat + ", fechanacimiento=" + fechanacimiento
				+ ", estado=" + estado + ", nombreusr=" + nombreusr
				+ ", cargo=" + cargo + ", nombrescompletos=" + nombrescompletos
				+ ", emailusr=" + emailusr + "]";
	}

	
}


