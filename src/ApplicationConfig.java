 
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("rest")
public class ApplicationConfig extends Application {
	
	public ApplicationConfig() {
		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:1234");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("org.fma.api");
        beanConfig.setScan(true);
    }
}