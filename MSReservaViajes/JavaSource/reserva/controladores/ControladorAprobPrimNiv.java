package reserva.controladores;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import reserva.entidades.SolicitudHotel;
import reserva.servicios.ServicioAprobPrimer;

@ManagedBean
@ViewScoped
public class ControladorAprobPrimNiv {

	@EJB
	private ServicioAprobPrimer srvAprobPrimer;
	private List<SolicitudHotel> lstSolicitudHotel;
	private List<SolicitudHotel> lstSolHotSel;
	private String cedulaUsuario;
	private String nombreUsuario;
	private String codEmpresa;
	public String patron = "dd/MMM/yyyy";
	public SimpleDateFormat formato;
	public String forFecha;
	Date fechaAprobSolHot;

	public ControladorAprobPrimNiv() {
		lstSolicitudHotel = new ArrayList<SolicitudHotel>();
		lstSolHotSel = new ArrayList<SolicitudHotel>();
		cedulaUsuario = new String("");
		nombreUsuario = new String("");
		codEmpresa = new String("");
		fechaAprobSolHot = new Date();
		formato = new SimpleDateFormat(patron);
		forFecha = formato.format(fechaAprobSolHot);
	}

	@PostConstruct
	public void inicio() {
		cedulaUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("CEDULA").toString();
		nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO")
				.toString();
		codEmpresa = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("CODEMPRESA")
				.toString();

		// //recuperar solicitudes
		lstSolicitudHotel = srvAprobPrimer.recuperarSolicitudes(codEmpresa);
	}

	public void aprobarSolicitudes() {
		System.out.println("Metodo Aprobacion Solicitudes");
		System.out.println("Tamanio Lista:" + lstSolHotSel.size());
		if (lstSolHotSel.size() == 0) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡Error!", "Elija una solicitud");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		Date aprobPrimerSoli = new Date();
		for (SolicitudHotel objSoli : lstSolHotSel) {
			// System.out.println("Selecionados" +
			// objSoli.getFecha_solicitud());
			// actualizar esttado de aprobacion
			objSoli.setAprobacion_primera("APROBADA");
			objSoli.setFecha_aprob_primera(aprobPrimerSoli);
			objSoli.setUsuario_aprob_primera(nombreUsuario);
			srvAprobPrimer.aprobarSoliHotel(objSoli);
		}
		lstSolHotSel = new ArrayList<SolicitudHotel>();
		lstSolicitudHotel = srvAprobPrimer.recuperarSolicitudes(codEmpresa);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "¡Éxito!", "Aprobación Exitosa");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onSelect(SolicitudHotel soli, String typeOfSelection, String indexes) {
		System.out.println("OnSelect:" + soli + " typeOfSelection: " + typeOfSelection + " indexes: " + indexes);
		System.out.println("ObjetoSeleccionado:" + soli.getFecha_solicitud());
		System.out.println("ObjetoSeleccionado:" + soli.getDepartamento());
		if (soli != null) {
			lstSolHotSel.add(soli);
		}
	}

	public void onDeselect(SolicitudHotel soli, String typeOfSelection, String indexes) {
		System.out.println("OnDeselect:" + soli + " typeOfSelection: " + typeOfSelection + " indexes: " + indexes);
		if (null != soli) {
			lstSolHotSel.remove(soli);
		}
	}

	public String salir() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CEDULA", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("USUARIO", null);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CODEMPRESA", null);
		return "/login.jsf";
	}

	public List<SolicitudHotel> getLstSolicitudHotel() {
		return lstSolicitudHotel;
	}

	public void setLstSolicitudHotel(List<SolicitudHotel> lstSolicitudHotel) {
		this.lstSolicitudHotel = lstSolicitudHotel;
	}

	public List<SolicitudHotel> getLstSolHotSel() {
		return lstSolHotSel;
	}

	public void setLstSolHotSel(List<SolicitudHotel> lstSolHotSel) {
		this.lstSolHotSel = lstSolHotSel;
	}

}
