package reserva.entidades;

public class Empresa {
	private Integer secuiencial_sociedad;
	private String nomina_codigo_sociedad;
	private String nomina_descripcion_sociedad;
	private Integer flag;

	public Empresa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Empresa(Integer secuiencial_sociedad, String nomina_codigo_sociedad, String nomina_descripcion_sociedad,
			Integer flag) {
		super();
		this.secuiencial_sociedad = secuiencial_sociedad;
		this.nomina_codigo_sociedad = nomina_codigo_sociedad;
		this.nomina_descripcion_sociedad = nomina_descripcion_sociedad;
		this.flag = flag;
	}

	public Integer getSecuiencial_sociedad() {
		return secuiencial_sociedad;
	}

	public void setSecuiencial_sociedad(Integer secuiencial_sociedad) {
		this.secuiencial_sociedad = secuiencial_sociedad;
	}

	public String getNomina_codigo_sociedad() {
		return nomina_codigo_sociedad;
	}

	public void setNomina_codigo_sociedad(String nomina_codigo_sociedad) {
		this.nomina_codigo_sociedad = nomina_codigo_sociedad;
	}

	public String getNomina_descripcion_sociedad() {
		return nomina_descripcion_sociedad;
	}

	public void setNomina_descripcion_sociedad(String nomina_descripcion_sociedad) {
		this.nomina_descripcion_sociedad = nomina_descripcion_sociedad;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	

}
