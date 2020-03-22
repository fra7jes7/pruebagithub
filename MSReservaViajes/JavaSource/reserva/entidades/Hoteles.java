package reserva.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "hoteles")
public class Hoteles {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sec_id_hotel")
	@SequenceGenerator(sequenceName = "sec_id_hotel", name = "sec_id_hotel", initialValue = 1, allocationSize = 1000)

	@Column(name = "id_hotel")
	private Integer id_hotel;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "ciudad")
	private String ciudad;
	
	public Integer getId_hotel() {
		return id_hotel;
	}
	public void setId_hotel(Integer id_hotel) {
		this.id_hotel = id_hotel;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
}
