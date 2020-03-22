package reserva.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import reserva.entidades.SolicitudPas;

@Stateless
public class ServicioAprobPrimerPas {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<SolicitudPas> recuperarSolicitudes(String codEmpresa) {
		String sql = "select * from solicitud_pas where EMPRESA='" + codEmpresa + "' "
				+ "and aprobacion_primera is null order by id_sol_pas desc";
		System.out.println("**********QUERY: " + sql);
		Query q = em.createNativeQuery(sql, SolicitudPas.class);
		return q.getResultList();
	}

	public void aprobarSoliPasa(SolicitudPas objSoliPas) {
		em.merge(objSoliPas);
	}

}
