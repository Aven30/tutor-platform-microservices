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

import org.fma.entities.BasicInfo;
import org.hibernate.HibernateException;
import org.hibernate.Session;

//import io.swagger.annotations.Api;
import util.HibernateUtil;

@Path("basicinfo")
//@Api(value="/subjects", consumes="application/json")
public class BasicInfoEndpoint {

	@GET
	@Path("/user/{userId}")
	@Produces("application/json")
	public Response Get(@PathParam("userId") int userId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			BasicInfo basicInfo = (BasicInfo) session.createQuery("Select b FROM BasicInfo b WHERE b.userId = :userId and b.deletedAt is NULL")
	    		.setParameter("userId", userId)
	    		.uniqueResult();
			return Response.ok(basicInfo).build();
		}
		catch(NoResultException nre)
		{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Produces("application/json")
	public Response Upsert(BasicInfo basicInfo) {
		
		try 
		{
			int oldId = basicInfo.id;
			Session session = HibernateUtil.getSessionFactory().openSession();
		    session.beginTransaction();
			session.saveOrUpdate(basicInfo);
		    session.getTransaction().commit();
		    
		    if (oldId != basicInfo.id)
		    	return Response.status(Status.CREATED).entity(basicInfo).build();
		    else
		    	return Response.ok(basicInfo).build();
		}
		catch(HibernateException e)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		 
	}
	
	@DELETE
	@Produces("application/json")
	public Response delete(BasicInfo basicInfo) {
		
		try 
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
		    session.beginTransaction();
		    basicInfo.deletedAt = new Date();
		    session.update(basicInfo);
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
