package beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Hello {
	final String hello = "{'message': 'HI IT WORKED'}";
	
	public String gethello()
	{
		return hello;
	}
	
	
}
