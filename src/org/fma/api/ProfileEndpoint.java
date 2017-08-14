package org.fma.api;
 
import javax.persistence.NoResultException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.fma.entities.BasicInfo;
import org.hibernate.Session;

import util.HibernateUtil;

@Path("profile")
public class ProfileEndpoint {

	@GET
	@Path("/get/{userId}")
	@Produces("application/json")
	public Response Get(@PathParam("userId") int userId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		BasicInfo basicInfo = null;
		try {
			basicInfo = (BasicInfo) session.createQuery("Select b FROM BasicInfo b WHERE b.userId = :userId")
	    		.setParameter("userId", userId)
	    		.getSingleResult();
		}
		catch(NoResultException nre)
		{
			System.out.println("NOT FOUND");
			Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(basicInfo).build();
	}
	
	@POST
	@Path("/upsert")
	@Produces("application/json")
	public BasicInfo Upsert(BasicInfo basicInfo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
		session.saveOrUpdate(basicInfo);
	    session.getTransaction().commit();
	    
		return basicInfo;
	}
	
}
