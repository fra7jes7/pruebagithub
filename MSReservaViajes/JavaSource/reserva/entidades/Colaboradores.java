package reserva.entidades;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ms_colaboradores")
public class Colaboradores {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SECUENCIA_CD_COLABORADORES")
	@SequenceGenerator(sequenceName = "SECUENCIA_CD_COLABORADORES", name = "SECUENCIA_CD_COLABORADORES", initialValue= 1, allocationSize = 1000)
	@Column(name="cd_colaboradores")
	private Integer cd_colaboradores;
	
	@Column(name="cedide_mf")
	private String cedide_mf;
	@Column(name="ticket_present")
	private Integer ticket_present;
	@Column(name="colaborador")
	private String colaborador;
	@Column(name="empresa")
	private String empresa;
	@Column(name="unidad_negocio")
	private String unidad_negocio;	
	@Column(name="cod_empresa")
	private String cod_empresa;
	
	
	public Integer getCd_colaboradores() {
		return cd_colaboradores;
	}
	public void setCd_colaboradores(Integer cd_colaboradores) {
		this.cd_colaboradores = cd_colaboradores;
	}
	public String getCedide_mf() {
		return cedide_mf;
	}
	public void setCedide_mf(String cedide_mf) {
		this.cedide_mf = cedide_mf;
	}
	public Integer getTicket_present() {
		return ticket_present;
	}
	public void setTicket_present(Integer ticket_present) {
		this.ticket_present = ticket_present;
	}
	public String getColaborador() {
		return colaborador;
	}
	public void setColaborador(String colaborador) {
		this.colaborador = colaborador;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getUnidad_negocio() {
		return unidad_negocio;
	}
	public void setUnidad_negocio(String unidad_negocio) {
		this.unidad_negocio = unidad_negocio;
	}
	public String getCod_empresa() {
		return cod_empresa;
	}
	public void setCod_empresa(String cod_empresa) {
		this.cod_empresa = cod_empresa;
	}
	
	
	
}
