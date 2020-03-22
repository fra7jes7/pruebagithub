package reserva.entidades;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "solicitud_pas")
public class SolicitudPas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sec_id_sol_pas")
	@SequenceGenerator(sequenceName = "sec_id_sol_pas", name = "sec_id_sol_pas", initialValue= 1, allocationSize = 1000)
	
	@Column(name="id_sol_pas")
	private Integer id_sol_pas;
	@Column(name="fecha_sol")
	private Date fecha_sol;
	@Column(name="nombres_pas")
	private String nombres_pas;
	@Column(name="cedula")
	private String cedula;
	@Column(name="empresa")
	private String empresa;
	@Column(name="ruta_desde")
	private String ruta_desde;
	@Column(name="ruta_hacia")
	private String ruta_hacia;
	@Column(name="aerolinea")
	private String aerolinea;
	@Column(name="fecha_salida")
	private Date fecha_salida;
	@Column(name="hora_salida")
	private Date hora_salida;
	@Column(name="fecha_regreso")
	private Date fecha_regreso;
	@Column(name="hora_regreso")
	private Date hora_regreso;
	@Column(name="motivo_viaje")
	private String motivo_viaje;
	@Column(name="factura_anombre")
	private String factura_anombre;
	@Column(name="autorizado_por")
	private String autorizado_por;
	
	@Column(name="aprobacion_primera")
	private String aprobacion_primera;
	@Column(name="fecha_aprob_primera")
	private Date fecha_aprob_primera;
	@Column(name="usuario_aprob_primera")
	private String usuario_aprob_primera;
	@Column(name="aprobacion_segunda")
	private String aprobacion_segunda;
	@Column(name="fecha_aprob_segun")
	private Date fecha_aprob_segun;
	@Column(name="usuario_aprob_segun")
	private String usuario_aprob_segun;
	@Column(name="solicitado_por")
	private String solicitado_por;
	@Column(name="cedula_solicitante")
	private String cedula_solicitante;
	@Column(name="centro_costos")
	private String centro_costos;
	@Column(name="maleta")
	private String maleta;
	@Column(name="departamento_solicitante")
	private String departamento_solicitante;
	
	
	public String getDepartamento_solicitante() {
		return departamento_solicitante;
	}
	public void setDepartamento_solicitante(String departamento_solicitante) {
		this.departamento_solicitante = departamento_solicitante;
	}
	public String getMaleta() {
		return maleta;
	}
	public void setMaleta(String maleta) {
		this.maleta = maleta;
	}
	public String getCentro_costos() {
		return centro_costos;
	}
	public void setCentro_costos(String centro_costos) {
		this.centro_costos = centro_costos;
	}
	public Integer getId_sol_pas() {
		return id_sol_pas;
	}
	public void setId_sol_pas(Integer id_sol_pas) {
		this.id_sol_pas = id_sol_pas;
	}
	public Date getFecha_sol() {
		return fecha_sol;
	}
	public void setFecha_sol(Date fecha_sol) {
		this.fecha_sol = fecha_sol;
	}
	public String getNombres_pas() {
		return nombres_pas;
	}
	public void setNombres_pas(String nombres_pas) {
		this.nombres_pas = nombres_pas;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getRuta_desde() {
		return ruta_desde;
	}
	public void setRuta_desde(String ruta_desde) {
		this.ruta_desde = ruta_desde;
	}
	public String getRuta_hacia() {
		return ruta_hacia;
	}
	public void setRuta_hacia(String ruta_hacia) {
		this.ruta_hacia = ruta_hacia;
	}
	public String getAerolinea() {
		return aerolinea;
	}
	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}
	public Date getFecha_salida() {
		return fecha_salida;
	}
	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}
	public Date getHora_salida() {
		return hora_salida;
	}
	public void setHora_salida(Date hora_salida) {
		this.hora_salida = hora_salida;
	}
	public Date getFecha_regreso() {
		return fecha_regreso;
	}
	public void setFecha_regreso(Date fecha_regreso) {
		this.fecha_regreso = fecha_regreso;
	}
	public Date getHora_regreso() {
		return hora_regreso;
	}
	public void setHora_regreso(Date hora_regreso) {
		this.hora_regreso = hora_regreso;
	}
	public String getMotivo_viaje() {
		return motivo_viaje;
	}
	public void setMotivo_viaje(String motivo_viaje) {
		this.motivo_viaje = motivo_viaje;
	}
	public String getFactura_anombre() {
		return factura_anombre;
	}
	public void setFactura_anombre(String factura_anombre) {
		this.factura_anombre = factura_anombre;
	}
	public String getAutorizado_por() {
		return autorizado_por;
	}
	public void setAutorizado_por(String autorizado_por) {
		this.autorizado_por = autorizado_por;
	}
	public String getAprobacion_primera() {
		return aprobacion_primera;
	}
	public void setAprobacion_primera(String aprobacion_primera) {
		this.aprobacion_primera = aprobacion_primera;
	}
	public Date getFecha_aprob_primera() {
		return fecha_aprob_primera;
	}
	public void setFecha_aprob_primera(Date fecha_aprob_primera) {
		this.fecha_aprob_primera = fecha_aprob_primera;
	}
	public String getUsuario_aprob_primera() {
		return usuario_aprob_primera;
	}
	public void setUsuario_aprob_primera(String usuario_aprob_primera) {
		this.usuario_aprob_primera = usuario_aprob_primera;
	}
	public String getAprobacion_segunda() {
		return aprobacion_segunda;
	}
	public void setAprobacion_segunda(String aprobacion_segunda) {
		this.aprobacion_segunda = aprobacion_segunda;
	}
	public Date getFecha_aprob_segun() {
		return fecha_aprob_segun;
	}
	public void setFecha_aprob_segun(Date fecha_aprob_segun) {
		this.fecha_aprob_segun = fecha_aprob_segun;
	}
	public String getUsuario_aprob_segun() {
		return usuario_aprob_segun;
	}
	public void setUsuario_aprob_segun(String usuario_aprob_segun) {
		this.usuario_aprob_segun = usuario_aprob_segun;
	}
	public String getSolicitado_por() {
		return solicitado_por;
	}
	public void setSolicitado_por(String solicitado_por) {
		this.solicitado_por = solicitado_por;
	}
	public String getCedula_solicitante() {
		return cedula_solicitante;
	}
	public void setCedula_solicitante(String cedula_solicitante) {
		this.cedula_solicitante = cedula_solicitante;
	}
	
	
	
}
