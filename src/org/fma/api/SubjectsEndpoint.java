package org.fma.api;
 
import java.util.Date;

import javax.persistence.NoResultException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.fma.entities.Subjects;
import org.hibernate.HibernateException;
import org.hibernate.Session;

//import io.swagger.annotations.Api;
import util.HibernateUtil;

//@Api(value="/subjects", consumes="application/json")
@Path("subjects")
public class SubjectsEndpoint {

	@GET
	@Path("/user/{userId}")
	@Produces("application/json")
	public Response Get(@PathParam("userId") int userId) {System.out.println("HERE!!!");
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			Subjects subjects = (Subjects) session.createQuery("Select b FROM Subjects b WHERE b.userId = :userId and b.deletedAt is NULL")
	    		.setParameter("userId", userId)
	    		.uniqueResult();
			return Response.ok(subjects).build();
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
