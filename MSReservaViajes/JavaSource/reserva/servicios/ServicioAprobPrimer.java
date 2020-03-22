package reserva.servicios;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import reserva.entidades.SolicitudHotel;
import reserva.entidades.UnidadesNegocio;

@Stateless
public class ServicioAprobPrimer {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<SolicitudHotel> recuperarSolicitudes(String codEmpresa) {
		String sql = "select * from solicitud_hotel where departamento='E200' "
				+ "and aprobacion_primera is null order by id_sol_hot desc";
		System.out.println("**********-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, SolicitudHotel.class);
		return q.getResultList();
	}
	
	public void aprobarSoliHotel(SolicitudHotel objSoliHotel) {
		em.merge(objSoliHotel);
	}
	
	

}
