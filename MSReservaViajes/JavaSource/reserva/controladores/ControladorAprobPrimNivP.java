package reserva.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import reserva.entidades.Empresa;
import reserva.entidades.SolicitudHotel;
import reserva.servicios.ServicioAprobPrimer;

@ManagedBean
@ViewScoped
public class ControladorAprobPrimNivP {
	@EJB
	private ServicioAprobPrimer srvAprobPrimer;
	private List<SolicitudHotel> lstSolicitudHotel;
	private List<SolicitudHotel> lstSolHotSel;
	private String cedulaUsuario;
	private String nombreUsuario;
	private String codEmpresa;

	public ControladorAprobPrimNivP() {
		lstSolicitudHotel = new ArrayList<SolicitudHotel>();
		lstSolHotSel = new ArrayList<SolicitudHotel>();
		cedulaUsuario = new String("");
		nombreUsuario = new String("");
		codEmpresa = new String("");

	}

	@PostConstruct
	public void inicio() {
		// cedulaUsuario =
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("CEDULA").toString();
		// nombreUsuario =
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO")
		// .toString();
		// codEmpresa =
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("CODEMPRESA")
		// .toString();

		// //recuperar solicitudes
		// lstSolicitudHotel =
		// srvAprobPrimer.recuperarSolicitudes("codEmpresa");
		Date fecha1 = new Date();
		SolicitudHotel objS = new SolicitudHotel();
		for (Integer i = 0; i < 5; i++) {
			objS = new SolicitudHotel();
			objS.setFecha_solicitud(fecha1);
			objS.setId_sol_hot(i);
			objS.setNombres_huespeds("nombres huespesd" + i);
			objS.setDepartamento("departamento" + i);
			objS.setHotel("hotel" + i);
			lstSolicitudHotel.add(objS);
		}

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

	public void aprobarSolicitudes() {
		System.out.println("Metodo Aprobacion Solicitudes");
		System.out.println("Tamanio Lista:" + lstSolHotSel.size());
		for (SolicitudHotel objSoli : lstSolHotSel) {
			System.out.println("Selecionados" + objSoli.getId_sol_hot());
			eliminarSolicitudesAprobadas(objSoli.getId_sol_hot());
		}
		

	}
	
	public void eliminarSolicitudesAprobadas(Integer para) {
		Integer contador = 0;

		Iterator<SolicitudHotel> it = lstSolicitudHotel.iterator();
		while (it.hasNext()) {
			if (it.next().getId_sol_hot() == para) {
				it.remove();
				// System.out.println("contador" + contador);
			}
			contador = contador + 1;
		}
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
