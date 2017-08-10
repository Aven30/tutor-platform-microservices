package org.fma.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.hibernate.Session;
import util.HibernateUtil;

@Path("profile")
public class Profile {

	@GET
	@Produces("application/json")
	public models.Profile getFirstName() {
		Session session = HibernateUtil.getSessionFactory().openSession();
	    
	    models.Profile profile = (models.Profile) session.createQuery("Select p FROM Profile p").getSingleResult();
	    
		return profile;
	}
	
}
