package reserva.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "niveles_acceso")
public class NivelesAcceso {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sec_id_acceso")
	@SequenceGenerator(sequenceName = "sec_id_acceso", name = "sec_id_acceso", initialValue = 1, allocationSize = 1000)

	@Column(name = "id_acceso")
	private Integer id_acceso;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "nivel")
	private Integer nivel;
	//metodos
	public Integer getId_acceso() {
		return id_acceso;
	}
	public void setId_acceso(Integer id_acceso) {
		this.id_acceso = id_acceso;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getNivel() {
		return nivel;
	}
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}	
	
}
