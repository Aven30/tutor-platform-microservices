package org.fma.api;
 
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.fma.entities.Profile;
import org.hibernate.Session;

import util.HibernateUtil;

@Path("profile")
public class ProfileEndpoint {

	@GET
	@Path("/get/{userId}")
	@Produces("application/json")
	public Profile Get(@PathParam("userId") int userId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
	    Profile profile = (Profile) session.createQuery("Select p FROM Profile p WHERE p.userId= :userId")
	    		.setParameter("userId", userId)
	    		.getSingleResult();
	
		return profile;
	}
	
	@POST
	@Path("/upsert")
	@Produces("application/json")
	public Profile Upsert(Profile profile) {
		Session session = HibernateUtil.getSessionFactory().openSession();
	    session.beginTransaction();
		session.saveOrUpdate(profile);
	    session.getTransaction().commit();
	    
		return profile;
	}
	
}
