package reserva.controladores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import reserva.entidades.Colaboradores;
import reserva.entidades.Empresa;
import reserva.entidades.NivelesAcceso;
import reserva.entidades.UnidadesNegocio;
import reserva.servicios.ServicioPerUsu;
import reserva.servicios.ServicioSolicitudHotel;

@ManagedBean
@ViewScoped
public class ControladorPerUsu {
	@EJB
	private ServicioPerUsu srvPerUsu;
	@EJB
	private ServicioSolicitudHotel srvSolicitudHotel;

	private List<UnidadesNegocio> lstUnidadesNegocio;
	private List<Empresa> lstEmpresa;
	private List<Colaboradores> lstColaboradores;
	private List<NivelesAcceso> lstNivelesAcceso;
	private String strEmpresa;
	private String strColaborador;
	private Colaboradores objColaborador;
	private NivelesAcceso objNivelesAcceso;

	public ControladorPerUsu() {
		lstUnidadesNegocio = new ArrayList<UnidadesNegocio>();
		lstEmpresa = new ArrayList<Empresa>();
		lstColaboradores = new ArrayList<Colaboradores>();
		lstNivelesAcceso = new ArrayList<NivelesAcceso>();
		strEmpresa = new String();
		strColaborador = new String();
		objColaborador = new Colaboradores();
		objColaborador = null;
		objNivelesAcceso = new NivelesAcceso();
		objNivelesAcceso = null;
	}

	@PostConstruct
	public void inicio() {
		// Extraer listado de colaboradores
		lstUnidadesNegocio = srvPerUsu.extraerPermisos();
		lstEmpresa = srvSolicitudHotel.recuperaEmpresa();
		// valida que la empresa no se repita
		eliminarNullosLstEmpresa();
		validarEmpresa1();
	}

	public void guardarPermiso() {
		System.out.println("Metodo guardarPermiso");
		System.out.println("Empresa:" + strEmpresa);
		// System.out.println("objColaborador.getCedide_mf():"+objColaborador.getCedide_mf());
		// System.out.println("objNivelesAcceso.getDescripcion():"+objNivelesAcceso.getDescripcion());

		if (strEmpresa == null || strEmpresa.isEmpty()) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡ERROR!", "La empresa es requerida");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objColaborador == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡ERROR!", "El usuario es obligatorio");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objNivelesAcceso == null) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡ERROR!",
					"El nivel de acceso es obligatorio");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		UnidadesNegocio objUnidadesNegocio = new UnidadesNegocio();
		objUnidadesNegocio.setCd_usuario(objColaborador.getCedide_mf());
		objUnidadesNegocio.setCd_acceso(objNivelesAcceso.getId_acceso());
		objUnidadesNegocio.setEmpresa(strEmpresa);
		objUnidadesNegocio.setNom_usuario(objColaborador.getColaborador());
		objUnidadesNegocio.setClave(objColaborador.getTicket_present().toString());
		objUnidadesNegocio.setDescripcion(objNivelesAcceso.getDescripcion());
		objUnidadesNegocio.setMcu(objColaborador.getUnidad_negocio());
		// consultar si existe el permiso
		List<UnidadesNegocio> lstUnv = new ArrayList<UnidadesNegocio>();
		lstUnv = srvPerUsu.verificarExistenciaPermiso(objUnidadesNegocio);
		System.out.println("Tamanio:" + lstUnv.size());
		if (lstUnv.size() == 1) {
			System.out.println("El usuario ya tiene este permiso");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "¡INFORMACIÓN!",
					"El usuario ya tiene este permiso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		srvPerUsu.insertarPermisosUn(objUnidadesNegocio);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "¡INFORMACIÓN!", "Permiso realizado con éxito");
		FacesContext.getCurrentInstance().addMessage(null, msg);

		// Extraer listado de colaboradores colaboradores
		lstUnidadesNegocio = srvPerUsu.extraerPermisos();

		objUnidadesNegocio = new UnidadesNegocio();
		strEmpresa = new String();
		objColaborador = new Colaboradores();
		objNivelesAcceso = new NivelesAcceso();
		PrimeFaces.current().ajax().update("frmDgl1:selOneMenEmp");
		PrimeFaces.current().ajax().update("frmDgl1:selOneMenCol");
		PrimeFaces.current().ajax().update("frmDgl1:selOneMenNivelAcceso");

	}

	public void eliminarPermisos(UnidadesNegocio objUnidadesNegocio) {
		System.out.println("Metodo de eliminar permisos");
		System.out.println("Objeto:" + objUnidadesNegocio.getId_unidad_negocio());
		srvPerUsu.eliminarPermisosUn(objUnidadesNegocio);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "¡INFO!", "Permiso eliminado");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		lstUnidadesNegocio = srvPerUsu.extraerPermisos();
		PrimeFaces.current().ajax().update("frmSolVia:tblPer");

	}

	public void dialogoPermisos() {
		System.out.println("Metodo crearNuevoPermiso");
		PrimeFaces.current().executeScript("PF('dlgPermisos').show();");
		lstColaboradores = srvPerUsu.recuperaColaboradores();
		lstNivelesAcceso = srvPerUsu.recuperaNivelesAcceso();
		PrimeFaces.current().ajax().update("frmDgl1:selOneMenEmp");
		PrimeFaces.current().ajax().update("frmDgl1:selOneMenCol");
		PrimeFaces.current().ajax().update("frmDgl1:selOneMenNivelAcceso");

	}

	public void eliminarNullosLstEmpresa() {

		Iterator<Empresa> it = lstEmpresa.iterator();
		while (it.hasNext()) {
			if (it.next().getNomina_codigo_sociedad() == null) {
				it.remove();
			}
		}

	}

	public void validarEmpresa1() {
		String auxCodEmpre = "";
		Integer contador = 0;
		for (Empresa objEmpresa : lstEmpresa) {
			auxCodEmpre = objEmpresa.getNomina_codigo_sociedad();
			validarEmpresa2(contador, auxCodEmpre);
			contador = contador + 1;
		}
		eliminarEmpresaRepetida();
	}

	public void validarEmpresa2(Integer indice, String auxCodEmpre) {
		Integer contador = 0;
		for (Empresa objEmpresa : lstEmpresa) {
			if (contador > indice) {
				if (objEmpresa.getNomina_codigo_sociedad().equals(auxCodEmpre)) {
					objEmpresa.setFlag(1);
				}
			}
			contador = contador + 1;
		}
	}

	public void eliminarEmpresaRepetida() {
		Integer contador = 0;

		Iterator<Empresa> it = lstEmpresa.iterator();
		while (it.hasNext()) {
			if (it.next().getFlag() != null) {
				it.remove();
				// System.out.println("contador" + contador);
			}
			contador = contador + 1;
		}
	}

	public List<UnidadesNegocio> getLstUnidadesNegocio() {
		return lstUnidadesNegocio;
	}

	public void setLstUnidadesNegocio(List<UnidadesNegocio> lstUnidadesNegocio) {
		this.lstUnidadesNegocio = lstUnidadesNegocio;
	}

	public List<Empresa> getLstEmpresa() {
		return lstEmpresa;
	}

	public void setLstEmpresa(List<Empresa> lstEmpresa) {
		this.lstEmpresa = lstEmpresa;
	}

	public String getStrEmpresa() {
		return strEmpresa;
	}

	public void setStrEmpresa(String strEmpresa) {
		this.strEmpresa = strEmpresa;
	}

	public List<Colaboradores> getLstColaboradores() {
		return lstColaboradores;
	}

	public void setLstColaboradores(List<Colaboradores> lstColaboradores) {
		this.lstColaboradores = lstColaboradores;
	}

	public String getStrColaborador() {
		return strColaborador;
	}

	public void setStrColaborador(String strColaborador) {
		this.strColaborador = strColaborador;
	}

	public List<NivelesAcceso> getLstNivelesAcceso() {
		return lstNivelesAcceso;
	}

	public void setLstNivelesAcceso(List<NivelesAcceso> lstNivelesAcceso) {
		this.lstNivelesAcceso = lstNivelesAcceso;
	}

	public Colaboradores getObjColaborador() {
		return objColaborador;
	}

	public void setObjColaborador(Colaboradores objColaborador) {
		this.objColaborador = objColaborador;
	}

	public NivelesAcceso getObjNivelesAcceso() {
		return objNivelesAcceso;
	}

	public void setObjNivelesAcceso(NivelesAcceso objNivelesAcceso) {
		this.objNivelesAcceso = objNivelesAcceso;
	}

}
