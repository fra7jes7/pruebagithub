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

import reserva.entidades.Aerolinea;
import reserva.entidades.Ciudades;
import reserva.entidades.ConectarBaseSAPHCM;

@Stateless
public class ServicioAerolinea {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Aerolinea> recuperaAerolineas(){
		String sql = "select * from aerolinea_tbl order by id_aerolinea";
		System.out.println("********************-----QUERY: " + sql);
		Query q = em.createNativeQuery(sql, Aerolinea.class);
		return q.getResultList();
	}

	
}
