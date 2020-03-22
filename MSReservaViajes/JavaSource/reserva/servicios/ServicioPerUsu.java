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

import reserva.entidades.Colaboradores;
import reserva.entidades.ConectarBaseSAPHCM;
import reserva.entidades.NivelesAcceso;
import reserva.entidades.UnidadesNegocio;

@Stateless
public class ServicioPerUsu {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<UnidadesNegocio> extraerPermisos() {
		String sql = "SELECT a.id_unidad_negocio, a.cd_usuario," + "a.cd_acceso,a.empresa,a.nom_usuario, a.clave,"
				+ "a.descripcion,a.mcu FROM unidades_negocio a " + "order by id_unidad_negocio asc";
		System.out.println("**********-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, UnidadesNegocio.class);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<UnidadesNegocio> verificarExistenciaPermiso(UnidadesNegocio objUnidadesNegocio) {
		String sql = "SELECT * FROM unidades_negocio WHERE cd_usuario ='" + objUnidadesNegocio.getCd_usuario() + "' "
				+ "and empresa='" + objUnidadesNegocio.getEmpresa() + "' and cd_acceso="
				+ objUnidadesNegocio.getCd_acceso();
		System.out.println("**********-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, UnidadesNegocio.class);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<UnidadesNegocio> recuperaPermisosUsuarios(String cedula) {
		String sql = "select * from unidades_negocio where cd_usuario='" + cedula + "'";
		System.out.println("**********QUERY: " + sql);
		Query q = em.createNativeQuery(sql, UnidadesNegocio.class);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<NivelesAcceso> recuperaNivelesAcceso() {
		String sql = "select * from niveles_acceso order by nivel asc";
		System.out.println("**********QUERY: " + sql);
		Query q = em.createNativeQuery(sql, NivelesAcceso.class);
		return q.getResultList();
	}

	public void insertarPermisosUn(UnidadesNegocio objUnidadNegocio) {
		em.persist(objUnidadNegocio);
	}

	public void eliminarPermisosUn(UnidadesNegocio objUnidadNegocio) {
		em.remove(em.contains(objUnidadNegocio) ? objUnidadNegocio : em.merge(objUnidadNegocio));
	}

	public List<Colaboradores> recuperaColaboradores() {
		Connection conn;
		ResultSet res = null;
		List<Colaboradores> lstColaboradores = new ArrayList<Colaboradores>();
		Colaboradores objCol = new Colaboradores();
		try {
			conn = ConectarBaseSAPHCM.getOracleConnection();
			String sql = "select cd_colaboradores,cedide_mf,ticket_present, colaborador,"
					+ " empresa,unidad_negocio from ms_colaboradores "
					+ "where fecha_desv is null and ticket_present is not null ";

			System.out.println("*-- Query: " + sql);
			PreparedStatement cs = conn.prepareStatement(sql);
			res = cs.executeQuery();
			while (res.next()) {
				objCol = new Colaboradores();
				objCol.setCd_colaboradores(res.getInt(1));
				objCol.setCedide_mf(res.getString(2));
				objCol.setTicket_present(res.getInt(3));
				objCol.setColaborador(res.getString(4));
				objCol.setEmpresa(res.getString(5));
				objCol.setUnidad_negocio(res.getString(6));
				lstColaboradores.add(objCol);
			}
			res.close();
			cs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}
		return lstColaboradores;
	}

}
