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
import reserva.entidades.UnidadesNegocio;

@Stateless
public class ServicioLogin {
	@PersistenceContext
	private EntityManager em;

	public Colaboradores recuperaColaborador(String cedula, String password) {
		Connection conn;
		ResultSet res = null;
		Colaboradores objCol = new Colaboradores();
		try {
			conn = ConectarBaseSAPHCM.getOracleConnection();
			String sql = "select cd_colaboradores,cedide_mf,ticket_present, colaborador,cod_empresa from ms_colaboradores "
					+ "where fecha_desv is null and ticket_present is not null " + "and cedide_mf='" + cedula
					+ "' and ticket_present=" + password + "";
			System.out.println("*-- Query: " + sql);

			PreparedStatement cs = conn.prepareStatement(sql);
			res = cs.executeQuery();
			while (res.next()) {

				objCol.setCd_colaboradores(res.getInt(1));
				objCol.setCedide_mf(res.getString(2));
				objCol.setTicket_present(res.getInt(3));
				objCol.setColaborador(res.getString(4));
				objCol.setCod_empresa(res.getString(5));
			}
			res.close();
			cs.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}
		return objCol;
	}

	@SuppressWarnings("unchecked")
	public List<UnidadesNegocio> recuperaPermisosUsuarios(String cedula) {
		String sql = "select * from unidades_negocio where cd_usuario='" + cedula + "'";
		System.out.println("**********QUERY: " + sql);
		Query q = em.createNativeQuery(sql, UnidadesNegocio.class);
		return q.getResultList();
	}

}
