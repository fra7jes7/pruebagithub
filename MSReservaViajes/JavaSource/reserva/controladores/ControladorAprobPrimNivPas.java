package reserva.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.jfree.util.BooleanList;

import reserva.entidades.SolicitudHotel;
import reserva.entidades.SolicitudPas;
import reserva.servicios.ServicioAprobPrimerPas;

@ManagedBean
@ViewScoped
public class ControladorAprobPrimNivPas {

	@EJB
	private ServicioAprobPrimerPas srvAprobPrimerPas;
	private List<SolicitudPas> lstSolicitudPas;
	private List<SolicitudPas> lstSolicitudPasSelec;
	private String cedulaUsuario;
	private String nombreUsuario;
	private String codEmpresa;
	private Boolean flaAprobPrimNivPas;
	private Boolean flaConsultas;

	public ControladorAprobPrimNivPas() {
		lstSolicitudPas = new ArrayList<SolicitudPas>();
		lstSolicitudPasSelec = new ArrayList<SolicitudPas>();
		cedulaUsuario = new String();
		flaAprobPrimNivPas = new Boolean(false);
		flaConsultas = new Boolean(false);
	}

	@PostConstruct
	public void inicio() {
		cedulaUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("CEDULA").toString();
		nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO")
				.toString();
		codEmpresa = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("CODEMPRESA")
				.toString();

		// //recuperar solicitudes
		lstSolicitudPas = srvAprobPrimerPas.recuperarSolicitudes(codEmpresa);
	}

	public void controlPanelAprob() {
		System.out.println("controlPanelAprob");
		flaAprobPrimNivPas = true;
		flaConsultas = false;
	}
	
	public void controlPanelConsulta() {
		System.out.println("controlPanelAprob");
		flaConsultas = true;
		flaAprobPrimNivPas = false;
	}

	public void aprobarSolicitudes() {
		System.out.println("Metodo Aprobacion Solicitudes pasajes");
		System.out.println("Tamanio Lista:" + lstSolicitudPasSelec.size());
		if (lstSolicitudPasSelec.size() == 0) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "¡Error!",
					"Tiene que seleccionar una solicitud");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		Date aprobPrimerSoli = new Date();
		for (SolicitudPas objSoli : lstSolicitudPasSelec) {
			System.out.println("Id sol" + objSoli.getId_sol_pas());
		}
		for (SolicitudPas objSoli : lstSolicitudPasSelec) {
			objSoli.setAprobacion_primera("APROBADA");
			objSoli.setFecha_aprob_primera(aprobPrimerSoli);
			objSoli.setUsuario_aprob_primera(nombreUsuario);
			srvAprobPrimerPas.aprobarSoliPasa(objSoli);
		}

		lstSolicitudPasSelec = new ArrayList<SolicitudPas>();

		lstSolicitudPas = srvAprobPrimerPas.recuperarSolicitudes(codEmpresa);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Éxito!", "Aprobación Exitosa");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onSelect(SolicitudPas soli, String typeOfSelection, String indexes) {
		System.out.println("OnSelect:" + soli + " typeOfSelection: " + typeOfSelection + " indexes: " + indexes);
		System.out.println("ObjetoSeleccionado:" + soli.getId_sol_pas());
		if (soli != null) {
			lstSolicitudPasSelec.add(soli);
		}
	}

	//
	public void onDeselect(SolicitudPas soli, String typeOfSelection, String indexes) {
		System.out.println("OnDeselect:" + soli + " typeOfSelection: " + typeOfSelection + " indexes: " + indexes);
		System.out.println("ObjetoSeleccionado:" + soli.getId_sol_pas());
		if (null != soli) {
			lstSolicitudPasSelec.remove(soli);
		}
	}

	public String salir() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CEDULA", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("USUARIO", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CODEMPRESA", null);
		return "/login.jsf";
	}

	public List<SolicitudPas> getLstSolicitudPas() {
		return lstSolicitudPas;
	}

	public void setLstSolicitudPas(List<SolicitudPas> lstSolicitudPas) {
		this.lstSolicitudPas = lstSolicitudPas;
	}

	public List<SolicitudPas> getLstSolicitudPasSelec() {
		return lstSolicitudPasSelec;
	}

	public void setLstSolicitudPasSelec(List<SolicitudPas> lstSolicitudPasSelec) {
		this.lstSolicitudPasSelec = lstSolicitudPasSelec;
	}

	public Boolean getFlaAprobPrimNivPas() {
		return flaAprobPrimNivPas;
	}

	public void setFlaAprobPrimNivPas(Boolean flaAprobPrimNivPas) {
		this.flaAprobPrimNivPas = flaAprobPrimNivPas;
	}

	public Boolean getFlaConsultas() {
		return flaConsultas;
	}

	public void setFlaConsultas(Boolean flaConsultas) {
		this.flaConsultas = flaConsultas;
	}
	

}
