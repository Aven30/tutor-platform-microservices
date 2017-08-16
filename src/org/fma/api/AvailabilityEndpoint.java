package org.fma.api;
 
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fma.entities.Availability;
import org.hibernate.HibernateException;
import org.hibernate.Session;

//import io.swagger.annotations.Api;
import util.HibernateUtil;

@Stateless
@Path("availability")
//@Api(value="/availability", consumes="application/json")
public class AvailabilityEndpoint {

//	@PersistenceContext(unitName = "dbPU")
//	private EntityManager em;
//	
//	@GET
//	@Path("/user/{userId}")
//	@Produces("application/json")
//	public Response Get(@PathParam("userId") int userId) {
//		
//		try {
//			Query query = em.createQuery("SELECT a from Availability a WHERE a.userId = :userId and a.deletedAt is NULL");
//			query.setParameter("userId", userId);
//			System.out.println("HERE");
//			Availability availability = (Availability) query.getSingleResult();
//			
//			return Response.ok(availability).build();
//		}
//		catch(NoResultException nre)
//		{
//			return Response.status(Status.NOT_FOUND).build();
//		}
//	}
//	
	@POST
	@Produces("application/json")
	public Response Upsert(Availability availability) {
		
		try 
		{
			int oldId = availability.id;
			Session session = HibernateUtil.getSessionFactory().openSession();
		    session.beginTransaction();
			session.saveOrUpdate(availability);
			System.out.println("works");
		    session.getTransaction().commit();
		    
		    if (oldId != availability.id)
		    	return Response.status(Status.CREATED).entity(availability).build();
		    else
		    	return Response.ok(availability).build();
		}
		catch(HibernateException e)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		 
	}
}
