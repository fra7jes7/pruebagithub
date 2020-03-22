package reserva.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "aerolinea_tbl")
public class Aerolinea {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_id_aerolinea")
	@SequenceGenerator(sequenceName = "secuencia_id_aerolinea", name = "secuencia_id_aerolinea", initialValue = 1, allocationSize = 1000)
	@Column(name = "id_aerolinea")
	private Integer id_aerolinea;

	@Column(name = "nombre")
	private String nombre;
	@Column(name = "telefono")
	private String telefono;
	public Integer getId_aerolinea() {
		return id_aerolinea;
	}
	public void setId_aerolinea(Integer id_aerolinea) {
		this.id_aerolinea = id_aerolinea;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
