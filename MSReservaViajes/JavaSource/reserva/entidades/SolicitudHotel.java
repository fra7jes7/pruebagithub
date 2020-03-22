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
@Table(name = "solicitud_hotel")
public class SolicitudHotel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sec_sol_hot")
	@SequenceGenerator(sequenceName = "sec_sol_hot", name = "sec_sol_hot", initialValue= 1, allocationSize = 1000)
	
	@Column(name="id_sol_hot")
	private Integer id_sol_hot;
	
	@Column(name="fecha_solicitud")
	private Date fecha_solicitud;
	@Column(name="nombres_huespeds")
	private String nombres_huespeds;
	@Column(name="departamento")
	private String departamento;
	@Column(name="hotel")
	private String hotel;
	@Column(name="habitaciones_sencillas")
	private Integer habitaciones_sencillas;
	@Column(name="habitaciones_dobles")
	private Integer habitaciones_dobles;
	@Column(name="fecha_ingreso")
	private Date fecha_ingreso;
	@Column(name="fecha_salida")
	private Date fecha_salida;
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
	@Column(name="centro_costo")
	private String centro_costo;
	
	
	public String getCentro_costo() {
		return centro_costo;
	}
	public void setCentro_costo(String centro_costo) {
		this.centro_costo = centro_costo;
	}
	public Integer getId_sol_hot() {
		return id_sol_hot;
	}
	public void setId_sol_hot(Integer id_sol_hot) {
		this.id_sol_hot = id_sol_hot;
	}
	public Date getFecha_solicitud() {
		return fecha_solicitud;
	}
	public void setFecha_solicitud(Date fecha_solicitud) {
		this.fecha_solicitud = fecha_solicitud;
	}
	public String getNombres_huespeds() {
		return nombres_huespeds;
	}
	public void setNombres_huespeds(String nombres_huespeds) {
		this.nombres_huespeds = nombres_huespeds;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getHotel() {
		return hotel;
	}
	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
	public Integer getHabitaciones_sencillas() {
		return habitaciones_sencillas;
	}
	public void setHabitaciones_sencillas(Integer habitaciones_sencillas) {
		this.habitaciones_sencillas = habitaciones_sencillas;
	}
	public Integer getHabitaciones_dobles() {
		return habitaciones_dobles;
	}
	public void setHabitaciones_dobles(Integer habitaciones_dobles) {
		this.habitaciones_dobles = habitaciones_dobles;
	}
	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}
	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
	public Date getFecha_salida() {
		return fecha_salida;
	}
	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
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
