package reserva.entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ciudades_tbl")
public class Ciudades {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_id_ciudades")
	@SequenceGenerator(sequenceName = "secuencia_id_ciudades", name = "secuencia_id_ciudades", initialValue= 1, allocationSize = 1000)
	@Column(name="id_ciudades")
	private Integer id_ciudades;
	
	@Column(name="codigo")
	private String codigo;	
	@Column(name="descrip_cod")
	private String descrip_cod;
	public Integer getId_ciudades() {
		return id_ciudades;
	}
	public void setId_ciudades(Integer id_ciudades) {
		this.id_ciudades = id_ciudades;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescrip_cod() {
		return descrip_cod;
	}
	public void setDescrip_cod(String descrip_cod) {
		this.descrip_cod = descrip_cod;
	}
	
}
