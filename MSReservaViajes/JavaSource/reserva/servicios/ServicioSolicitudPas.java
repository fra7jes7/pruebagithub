package reserva.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import reserva.entidades.CentroCostos;
import reserva.entidades.Ciudades;
import reserva.entidades.ConectarBaseSAPHCM;
import reserva.entidades.Empresa;
import reserva.entidades.SolicitudPas;

@Stateless
public class ServicioSolicitudPas {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Ciudades> recuperaCiudades() {
		String sql = "select * from ciudades_tbl order by descrip_cod asc";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Ciudades.class);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<CentroCostos> recuperaCentroCostos() {
		String sql = "select * from centro_costos order by id_centro_costos asc";
		System.out.println("-------------------QUERY: " + sql);
		Query q = em.createNativeQuery(sql, CentroCostos.class);
		return q.getResultList();
	}

	public void insertSolicitudPas(SolicitudPas objSolicitudPas) {
		em.persist(objSolicitudPas);
	}

	// public List<String> listaEmpresas() {
	// Connection conn;
	// ResultSet res = null;
	// List<String> lstAux = new ArrayList<String>();
	// try {
	// conn = ConectarBaseSAPHCM.getOracleConnection();
	//
	// String sql = "select distinct(nomina_descripcion_sociedad) from
	// oc_accion_personal";
	// System.out.println("*-- Query: " + sql);
	//
	// PreparedStatement cs = conn.prepareStatement(sql);
	// res = cs.executeQuery();
	// while (res.next()) {
	// String empresa = "";
	// empresa = (res.getString(1));
	// lstAux.add(empresa);
	// }
	// res.close();
	// cs.close();
	// conn.close();
	// } catch (Exception e) {
	// System.out.println("error " + e.getMessage());
	// }
	// return lstAux;
	// }

	public List<Empresa> recuperaEmpresa() {
		Connection conn;
		ResultSet res = null;
		List<Empresa> lstAux = new ArrayList<Empresa>();
		Empresa aux = new Empresa();
		String sql = null;
		Integer cont = 1;
		try {
			conn = ConectarBaseSAPHCM.getOracleConnection();
			sql = "select distinct cod_empresa,empresa from ms_colaboradores";
			System.out.println("*********-----QUERY: " + sql);
			PreparedStatement cs = conn.prepareStatement(sql);
			res = cs.executeQuery();
			while (res.next()) {
				aux = new Empresa();
				aux.setSecuiencial_sociedad(cont);
				aux.setNomina_codigo_sociedad(res.getString(1));
				aux.setNomina_descripcion_sociedad(res.getString(2));
				lstAux.add(aux);
				cont = cont + 1;
			}
			res.close();
			cs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("error " + e.getMessage());
		}
		return lstAux;
	}

}
