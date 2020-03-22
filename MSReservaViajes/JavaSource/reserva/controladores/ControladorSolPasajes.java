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

import reserva.entidades.Aerolinea;
import reserva.entidades.CentroCostos;
import reserva.entidades.Ciudades;
import reserva.entidades.Empresa;
import reserva.entidades.SolicitudPas;
import reserva.servicios.ServicioAerolinea;
import reserva.servicios.ServicioSolicitudPas;

@ManagedBean
@ViewScoped
public class ControladorSolPasajes {
	@EJB
	private ServicioSolicitudPas srvSolicitudPas;
	@EJB
	private ServicioAerolinea srvAerolinea;
	private List<Empresa> lstEmpresa;
	private List<Ciudades> lstCiudades;
	private List<Aerolinea> lstAerolineas;
	private List<CentroCostos> lstCentroCostos;
	private SolicitudPas objSolicitudPas;
	private String cedulaUsuario;
	private String nombreUsuario;

	public ControladorSolPasajes() {
		lstEmpresa = new ArrayList<Empresa>();
		lstCiudades = new ArrayList<Ciudades>();
		lstAerolineas = new ArrayList<Aerolinea>();
		lstCentroCostos = new ArrayList<CentroCostos>();
		objSolicitudPas = new SolicitudPas();
		cedulaUsuario = null;
		nombreUsuario = null;
		
	}

	@PostConstruct
	public void inicio() {
		cedulaUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("CEDULA").toString();
		nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO")
				.toString();
		objSolicitudPas.setCedula_solicitante(cedulaUsuario);
		objSolicitudPas.setSolicitado_por(nombreUsuario);
		objSolicitudPas.setMaleta("NO");
		lstEmpresa = srvSolicitudPas.recuperaEmpresa();
		lstCiudades = srvSolicitudPas.recuperaCiudades();
		lstAerolineas = srvAerolinea.recuperaAerolineas();
		lstCentroCostos = srvSolicitudPas.recuperaCentroCostos();
		// valida que la empresa no se repita
		eliminarNullosLstEmpresa();
		validarEmpresa1();
	}

	public void guardarSolicitud() {

		System.out.println("***Metodo de guardar solicitud***");
		if (objSolicitudPas.getFecha_sol() == null) {
			System.out.println("Ingrese la fecha de solicitud");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!",
					"Ingrese la Fecha de solicitud ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objSolicitudPas.getNombres_pas() == null || objSolicitudPas.getNombres_pas().isEmpty()) {
			System.out.println("Ingrese los nombres de los pasajeros");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!",
					"Ingrese los nombres de los pasajeros ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objSolicitudPas.getCedula() == null || objSolicitudPas.getCedula().isEmpty() == true) {
			System.out.println("Ingrese la cedula");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!", "Ingrese la cédula ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objSolicitudPas.getEmpresa() == null || objSolicitudPas.getEmpresa().isEmpty() == true) {
			System.out.println("Ingrese la empresa");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!", "Ingrese la empresa");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objSolicitudPas.getRuta_desde() == null || objSolicitudPas.getRuta_desde().isEmpty()) {
			System.out.println("Ingrese la reta desde");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!", "Ingrese la ruta desde");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objSolicitudPas.getRuta_hacia() == null || objSolicitudPas.getRuta_hacia().isEmpty() == true) {
			System.out.println("Ingrese la ruta hacia");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!", "Ingrese la ruta hacia");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objSolicitudPas.getFecha_salida() == null) {
			System.out.println("Ingrese la fecha salida");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!", "Ingrese la fecha salida");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objSolicitudPas.getHora_salida() == null) {
			System.out.println("Ingrese la hora salida");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!", "Ingrese la hora salida");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objSolicitudPas.getFecha_regreso() == null) {
			System.out.println("Ingrese la fecha regreso");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!", "Ingrese la fecha regreso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objSolicitudPas.getHora_regreso() == null) {
			System.out.println("Ingrese la hora regreso");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!", "Ingrese la hora regreso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objSolicitudPas.getMotivo_viaje() == null || objSolicitudPas.getMotivo_viaje().isEmpty() == true) {
			System.out.println("Ingrese el motivo del viaje");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!", "Ingrese el motivo del viaje");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if (objSolicitudPas.getFactura_anombre() == null || objSolicitudPas.getFactura_anombre().isEmpty() == true) {
			System.out.println("Ingrese factura a nombre");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!", "Ingrese factura a nombre");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		if (objSolicitudPas.getAutorizado_por() == null || objSolicitudPas.getAutorizado_por().isEmpty() == true) {
			System.out.println("Ingrese autorizado por");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!", "Ingrese la autorizacion por");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		srvSolicitudPas.insertSolicitudPas(objSolicitudPas);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "¡INFORMACIÓN!",
				"Se ha ingresado correctamente la solicitud");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		objSolicitudPas = new SolicitudPas();
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

	public void eliminarNullosLstEmpresa() {

		Iterator<Empresa> it = lstEmpresa.iterator();
		while (it.hasNext()) {
			if (it.next().getNomina_codigo_sociedad() == null) {
				it.remove();
			}
		}

	}

	public void eliminarEmpresaRepetida() {
		Integer contador = 0;

		Iterator<Empresa> it = lstEmpresa.iterator();
		while (it.hasNext()) {
			if (it.next().getFlag() != null) {
				it.remove();
				System.out.println("contador" + contador);
			}
			contador = contador + 1;
		}

		for (Empresa objEmp1 : lstEmpresa) {
			System.out.println("Codigo EMpresa:" + objEmp1.getNomina_codigo_sociedad());
			System.out.println("fla:" + objEmp1.getFlag());
		}
	}

	public ServicioSolicitudPas getsrvSolicitudPas() {
		return srvSolicitudPas;
	}

	public void setsrvSolicitudPas(ServicioSolicitudPas srvSolicitudPas) {
		this.srvSolicitudPas = srvSolicitudPas;
	}

	public List<Empresa> getLstEmpresa() {
		return lstEmpresa;
	}

	public void setLstEmpresa(List<Empresa> lstEmpresa) {
		this.lstEmpresa = lstEmpresa;
	}

	public List<Ciudades> getLstCiudades() {
		return lstCiudades;
	}

	public void setLstCiudades(List<Ciudades> lstCiudades) {
		this.lstCiudades = lstCiudades;
	}

	public List<Aerolinea> getLstAerolineas() {
		return lstAerolineas;
	}

	public void setLstAerolineas(List<Aerolinea> lstAerolineas) {
		this.lstAerolineas = lstAerolineas;
	}

	public SolicitudPas getObjSolicitudPas() {
		return objSolicitudPas;
	}

	public void setObjSolicitudPas(SolicitudPas objSolicitudPas) {
		this.objSolicitudPas = objSolicitudPas;
	}

	public List<CentroCostos> getLstCentroCostos() {
		return lstCentroCostos;
	}

	public void setLstCentroCostos(List<CentroCostos> lstCentroCostos) {
		this.lstCentroCostos = lstCentroCostos;
	}
	
}
