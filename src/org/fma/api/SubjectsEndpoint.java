package org.fma.api;
 
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
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

import org.fma.entities.BasicInfo;
import org.fma.entities.Subjects;
import org.hibernate.HibernateException;
import org.hibernate.Session;

//import io.swagger.annotations.Api;
import util.HibernateUtil;

//@Api(value="/subjects", consumes="application/json")
@Stateless
@Path("subjects")
public class SubjectsEndpoint {

	@PersistenceContext(unitName="dbPU")
	public EntityManager em;

	@GET
	@Path("/user/{userId}")
	@Produces("application/json")
	public Response Get(@PathParam("userId") int userId) {
		try {
//			Query query = em.createQuery("SELECT s from BasicInfo s WHERE s.userId = :userId and s.deletedAt is NULL");
			Query query = em.createQuery("FROM BasicInfo");
			
//			query.setParameter("userId", userId);
			System.out.println("HERE");
			List<BasicInfo> availability =  query.getResultList();
			
			return Response.ok(availability).build();
		}
		catch(NoResultException nre)
		{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Produces("application/json")
	public Response Upsert(Subjects subjects) {
		
		try 
		{
			int oldId = subjects.id;
			Session session = HibernateUtil.getSessionFactory().openSession();
		    session.beginTransaction();
			session.saveOrUpdate(subjects);
		    session.getTransaction().commit();
		    
		    if (oldId != subjects.id)
		    	return Response.status(Status.CREATED).entity(subjects).build();
		    else
		    	return Response.ok(subjects).build();
		}
		catch(HibernateException e)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		 
	}
	
	@DELETE
	@Produces("application/json")
	public Response delete(Subjects subjects) {
		
		try 
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
		    session.beginTransaction();
		    subjects.deletedAt = new Date();
		    session.update(subjects);
		    session.getTransaction().commit();
		    
		    return Response.status(Status.NO_CONTENT).build();
		}
		catch(NoResultException nre)
		{
			return Response.status(Status.NOT_FOUND).build();
		}
		catch(HibernateException e)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		 
	}
}
