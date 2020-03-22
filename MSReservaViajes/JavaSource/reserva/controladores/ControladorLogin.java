package reserva.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import reserva.entidades.Colaboradores;
import reserva.entidades.UnidadesNegocio;
import reserva.servicios.ServicioLogin;
import reserva.servicios.ServicioPerUsu;

@ManagedBean
@ViewScoped
public class ControladorLogin {
	@EJB
	private ServicioLogin srvLogin;
	@EJB
	private ServicioPerUsu srvPerUsu;
	private String strCedula;
	private String strPassword;
	private String tipoSolicitud;
	List<UnidadesNegocio> lstUnidadesNegocioPer;
	private Integer cdAccion;
	private String strAprobacionPrimer;

	public ControladorLogin() {
		strCedula = new String();
		strPassword = new String();
		tipoSolicitud = new String();
		lstUnidadesNegocioPer = new ArrayList<UnidadesNegocio>();
		cdAccion = new Integer(0);
		strAprobacionPrimer = new String("");
	}

	@PostConstruct
	public void inicio() {

	}

	public String login() {
		System.out.println("Ingreso al metodo log in");
		System.out.println("Cedula:" + strCedula);
		System.out.println("Contraseña" + strPassword);
		// validar clave
		Colaboradores objColaboradores = new Colaboradores();
		objColaboradores = srvLogin.recuperaColaborador(strCedula, strPassword);
		System.out.println("objetoBase:" + objColaboradores.getColaborador());

		if (objColaboradores.getTicket_present() == null) {
			System.out.println("objColaboradores.getTicket_present():" + objColaboradores.getTicket_present());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Credenciales incorrectas");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "/login.jsf";
		}
		// recupero colaborador esquema reserva Viajes

		lstUnidadesNegocioPer = srvPerUsu.recuperaPermisosUsuarios(strCedula);
		System.out.println("tamanio Lista:" + lstUnidadesNegocioPer.size());
		if (lstUnidadesNegocioPer.size() == 0) {
			System.out.println("No tiene permisos tamanio:" + lstUnidadesNegocioPer.size());
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Usuario no tiene permisos");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "/login.jsf";
		} else if (lstUnidadesNegocioPer.size() == 1) {
			// cuando solo tiene un tipo de acceso
			// verificar tipo de acceso
			UnidadesNegocio objUnidadesNegocio1 = new UnidadesNegocio();
			objUnidadesNegocio1 = lstUnidadesNegocioPer.get(0);
			if (objUnidadesNegocio1.getCd_acceso() == 1) {
				System.out.println("Generacion de solicitudes");
				// Generacion de solicitudes
				FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido",
						objColaboradores.getColaborador());
				FacesContext.getCurrentInstance().addMessage(null, msg2);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CEDULA", strCedula);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("USUARIO",
						objColaboradores.getColaborador());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CODEMPRESA",
						objUnidadesNegocio1.getEmpresa());
				System.out.println("cedulaLog:" + strCedula);
				System.out.println("NombreLog:" + objColaboradores.getColaborador());
				PrimeFaces.current().executeScript("PF('dlgOption').show();");
				// return "/login.jsf";
			}

			if (objUnidadesNegocio1.getCd_acceso() == 2) {
				System.out.println("Aprobación primer nivel");
				// Aprobación primer nivel
				FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido",
						objColaboradores.getColaborador());
				FacesContext.getCurrentInstance().addMessage(null, msg2);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CEDULA", strCedula);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("USUARIO",
						objColaboradores.getColaborador());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CODEMPRESA",
						objUnidadesNegocio1.getEmpresa());
				PrimeFaces.current().executeScript("PF('dlgAprob').show();");
			}

			if (objUnidadesNegocio1.getCd_acceso() == 3) {
				System.out.println("Aprobación segundo nivel");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CEDULA", strCedula);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("USUARIO",
						objColaboradores.getColaborador());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CODEMPRESA",
						objUnidadesNegocio1.getEmpresa());
				FacesMessage msg2 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido",
						objColaboradores.getColaborador());
				FacesContext.getCurrentInstance().addMessage(null, msg2);
				// Aprobación segundo nivel
				return "/aprobacionSegundoNivel.jsf";
			}
		} else {
			UnidadesNegocio objUnidadesNegocioVar = new UnidadesNegocio();
			objUnidadesNegocioVar = lstUnidadesNegocioPer.get(0);
			// Tiene varios permisos de aprobacion
			// return "/aprobacionSegundoNivel.jsf";
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CEDULA", strCedula);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("USUARIO",
					objColaboradores.getColaborador());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CODEMPRESA",
					objColaboradores.getCod_empresa());
			FacesMessage msgPer = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido",
					objColaboradores.getColaborador());
			FacesContext.getCurrentInstance().addMessage(null, msgPer);
			PrimeFaces.current().executeScript("PF('dlgPermiso').show();");
			lstUnidadesNegocioPer = srvPerUsu.recuperaPermisosUsuarios(strCedula);
			PrimeFaces.current().ajax().update("frmDgl2:selOnePer");
			for (UnidadesNegocio objUn : lstUnidadesNegocioPer) {
				System.out.println("objUn.getDescripcion():" + objUn.getDescripcion());
			}
		}
		return "";

	}

	public String solicitudAprobPrimer() {
		String link = "";
		if (strAprobacionPrimer.equals("pasajeA")) {
			link = "/aprobPrimNivPasa.jsf";
		} else if (strAprobacionPrimer.equals("reservacionH")) {
			link = "/aprobacionPrimerNiv.jsf";
		}
		return link;
	}

	public String seleccionarAccion() {
		System.out.println("Seleccionar nivel de aprobacion");
		System.out.println("cdAccion:" + cdAccion);
		String pagina;
		pagina = new String("/login.jsf");
		if (cdAccion == 1) {
			PrimeFaces.current().executeScript("PF('dlgOption').show();");
		} else if (cdAccion == 2) {
//			pagina = "/aprobacionPrimerNivel.jsf";
			PrimeFaces.current().executeScript("PF('dlgAprob').show();");
		} else {
			pagina = "/aprobacionSegundoNivel.jsf";
		}
		System.out.println("pagina:" + pagina);
		return pagina;
	}

	public String solicitudAingresar() {
		System.out.println("Metodo tipo de solicitud a ingresar");
		String linkPagina;
		linkPagina = new String();
		if (tipoSolicitud.equals("pasajeA")) {
			linkPagina = "/solicitudPas.jsf";
		}
		if (tipoSolicitud.equals("reservacionH")) {
			linkPagina = "/solicitudHotel.jsf";
		}
		System.out.println("linkPagina" + linkPagina);
		return linkPagina;
	}

	public String getStrCedula() {
		return strCedula;
	}

	public void setStrCedula(String strCedula) {
		this.strCedula = strCedula;
	}

	public String getStrPassword() {
		return strPassword;
	}

	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}

	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public List<UnidadesNegocio> getLstUnidadesNegocioPer() {
		return lstUnidadesNegocioPer;
	}

	public void setLstUnidadesNegocioPer(List<UnidadesNegocio> lstUnidadesNegocioPer) {
		this.lstUnidadesNegocioPer = lstUnidadesNegocioPer;
	}

	public Integer getCdAccion() {
		return cdAccion;
	}

	public void setCdAccion(Integer cdAccion) {
		this.cdAccion = cdAccion;
	}

	public String getStrAprobacionPrimer() {
		return strAprobacionPrimer;
	}

	public void setStrAprobacionPrimer(String strAprobacionPrimer) {
		this.strAprobacionPrimer = strAprobacionPrimer;
	}

}
