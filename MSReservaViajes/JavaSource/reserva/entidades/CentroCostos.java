package reserva.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CENTRO_COSTOS")
public class CentroCostos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sec_id_centro_costos")
	@SequenceGenerator(sequenceName = "sec_id_centro_costos", name = "sec_id_centro_costos", initialValue = 1, allocationSize = 1000)	
	@Column(name = "id_centro_costos")
	private Integer id_centro_costos;
	
	@Column(name = "cod_centro_costos")
	private String cod_centro_costos;
	@Column(name = "denominacion")
	private String denominacion;
	@Column(name = "responsable")
	private String responsable;
	@Column(name = "departamento")
	private String departamento;
	@Column(name = "clase")
	private String clase;
	@Column(name = "centrode_beneficio")
	private String centrode_beneficio;
	public Integer getId_centro_costos() {
		return id_centro_costos;
	}
	public void setId_centro_costos(Integer id_centro_costos) {
		this.id_centro_costos = id_centro_costos;
	}
	public String getCod_centro_costos() {
		return cod_centro_costos;
	}
	public void setCod_centro_costos(String cod_centro_costos) {
		this.cod_centro_costos = cod_centro_costos;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	public String getCentrode_beneficio() {
		return centrode_beneficio;
	}
	public void setCentrode_beneficio(String centrode_beneficio) {
		this.centrode_beneficio = centrode_beneficio;
	}

	
}
