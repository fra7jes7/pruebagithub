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

import reserva.entidades.CentroCostos;
import reserva.entidades.Empresa;
import reserva.entidades.Hoteles;
import reserva.entidades.SolicitudHotel;
import reserva.servicios.ServicioSolicitudHotel;

@ManagedBean
@ViewScoped
public class ControladorSolicitudHot {
	@EJB
	private ServicioSolicitudHotel srvSolicitudHotel;
	private List<Empresa> lstEmpresa;
	private List<Hoteles> lstHoteles;
	private SolicitudHotel objSolicitudHotel;
	private Boolean flagHotel;
	private String cedulaUsuario;
	private String nombreUsuario;
	
	private List<CentroCostos> lstCentroCostos;

	public ControladorSolicitudHot() {
		lstEmpresa = new ArrayList<Empresa>();
		lstHoteles = new ArrayList<Hoteles>();
		lstCentroCostos = new ArrayList<CentroCostos>();
		objSolicitudHotel = new SolicitudHotel();
		flagHotel = false;
		cedulaUsuario = null;
		nombreUsuario = null;
	}

	@PostConstruct
	public void inicio() {
		cedulaUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("CEDULA").toString();
		nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO")
				.toString();
		objSolicitudHotel.setCedula_solicitante(cedulaUsuario);
		objSolicitudHotel.setSolicitado_por(nombreUsuario);
		
		lstEmpresa = srvSolicitudHotel.recuperaEmpresa();
		// valida que la empresa no se repita
		eliminarNullosLstEmpresa();
		validarEmpresa1();
		lstHoteles = srvSolicitudHotel.recuperaHoteles();
		lstCentroCostos = srvSolicitudHotel.recuperaCentroCostos();
	}

	public void guardarSolicitud() {
		System.out.println("***Metodo de guardar solicitud hotel***");
		if (objSolicitudHotel.getFecha_solicitud() == null) {
			System.out.println("Ingrese la fecha de solicitud");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!",
					"Ingrese la Fecha de solicitud ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		System.out.println("getNombres_huespeds():" + objSolicitudHotel.getNombres_huespeds());
		if (objSolicitudHotel.getNombres_huespeds() == null
				|| objSolicitudHotel.getNombres_huespeds().isEmpty() == true) {
			System.out.println("Ingrese los Nombre(s) del lo(s) huésped(es)");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!",
					"Ingrese los Nombre(s) del lo(s) huésped(es)");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		System.out.println("getDepartamento():" + objSolicitudHotel.getDepartamento());
		if (objSolicitudHotel.getDepartamento() == null || objSolicitudHotel.getDepartamento().isEmpty() == true) {
			System.out.println("ingrese el departamento");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!",
					"Ingrese el Departamento al que pertenece");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		System.out.println("getHotel():" + objSolicitudHotel.getHotel());
		if (objSolicitudHotel.getHotel() == null || objSolicitudHotel.getHotel().isEmpty() == true) {
			System.out.println("ingrese el hotel");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!",
					"Ingrese el Nombre del Hotel / ciudad");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objSolicitudHotel.getHabitaciones_sencillas() == null
				&& objSolicitudHotel.getHabitaciones_dobles() == null) {
			System.out.println("ingrese el habitaciones");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!",
					"Ingrese el No. de Habitaciones");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objSolicitudHotel.getFecha_ingreso() == null) {
			System.out.println("ingrese fecha de ingreso");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!", "Ingrese la Fecha de ingreso");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objSolicitudHotel.getFecha_salida() == null) {
			System.out.println("ingrese la fecha de salida");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!", "Ingrese la Fecha de Salida");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objSolicitudHotel.getMotivo_viaje() == null || objSolicitudHotel.getMotivo_viaje().isEmpty() == true) {
			System.out.println("ingrese el motivo del viaje");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!", "Ingrese el motivo del viaje");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objSolicitudHotel.getFactura_anombre() == null
				|| objSolicitudHotel.getFactura_anombre().isEmpty() == true) {
			System.out.println("ingrese Emitir Factura a Nombre de");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!",
					"Ingrese la Emisión de Factura a Nombre de");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		if (objSolicitudHotel.getAutorizado_por() == null || objSolicitudHotel.getAutorizado_por().isEmpty() == true) {
			System.out.println("ingrese el campo de autorizacion por:");
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "¡ERROR!", "Ingrese la Autorización por");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		
		srvSolicitudHotel.insertarSolHotel(objSolicitudHotel);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "¡INFORMACIÓN!",
				"Se ha ingresado correctamente la solicitud");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		// limpiamos los campos de entrada
		objSolicitudHotel = new SolicitudHotel();
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

	public void validacionHotelXthml() {
		System.out.println("validacionHotelXthml");
		if (objSolicitudHotel.getHotel() == null || objSolicitudHotel.getHotel().isEmpty() == true) {
			System.out.println("ingrese el hotel");
			flagHotel = false;
		} else {
			flagHotel = true;
		}
		PrimeFaces.current().ajax().update("frmSolVia:otroHotel");

	}

	public List<Empresa> getLstEmpresa() {
		return lstEmpresa;
	}

	public void setLstEmpresa(List<Empresa> lstEmpresa) {
		this.lstEmpresa = lstEmpresa;
	}

	public List<Hoteles> getLstHoteles() {
		return lstHoteles;
	}

	public void setLstHoteles(List<Hoteles> lstHoteles) {
		this.lstHoteles = lstHoteles;
	}

	public SolicitudHotel getObjSolicitudHotel() {
		return objSolicitudHotel;
	}

	public void setObjSolicitudHotel(SolicitudHotel objSolicitudHotel) {
		this.objSolicitudHotel = objSolicitudHotel;
	}

	public Boolean getFlagHotel() {
		return flagHotel;
	}

	public void setFlagHotel(Boolean flagHotel) {
		this.flagHotel = flagHotel;
	}

	public List<CentroCostos> getLstCentroCostos() {
		return lstCentroCostos;
	}

	public void setLstCentroCostos(List<CentroCostos> lstCentroCostos) {
		this.lstCentroCostos = lstCentroCostos;
	}
	

}
