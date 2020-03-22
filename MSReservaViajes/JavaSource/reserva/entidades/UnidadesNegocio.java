package reserva.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "unidades_negocio")
public class UnidadesNegocio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sec_id_unidad_negocio")
	@SequenceGenerator(sequenceName = "sec_id_unidad_negocio", name = "sec_id_unidad_negocio", initialValue = 1, allocationSize = 1000)

	@Column(name = "id_unidad_negocio")
	private Integer id_unidad_negocio;	
	@Column(name = "cd_usuario")
	private String cd_usuario;
	@Column(name = "cd_acceso")
	private Integer cd_acceso;
	@Column(name = "empresa")
	private String empresa;
	@Column(name = "nom_usuario")
	private String nom_usuario;
	@Column(name = "clave")
	private String clave;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "mcu")
	private String mcu;
	//
	public Integer getId_unidad_negocio() {
		return id_unidad_negocio;
	}
	public void setId_unidad_negocio(Integer id_unidad_negocio) {
		this.id_unidad_negocio = id_unidad_negocio;
	}
	public String getCd_usuario() {
		return cd_usuario;
	}
	public void setCd_usuario(String cd_usuario) {
		this.cd_usuario = cd_usuario;
	}
	public Integer getCd_acceso() {
		return cd_acceso;
	}
	public void setCd_acceso(Integer cd_acceso) {
		this.cd_acceso = cd_acceso;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getNom_usuario() {
		return nom_usuario;
	}
	public void setNom_usuario(String nom_usuario) {
		this.nom_usuario = nom_usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getMcu() {
		return mcu;
	}
	public void setMcu(String mcu) {
		this.mcu = mcu;
	}
	
}
