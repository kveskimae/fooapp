package com.foo.app;

import java.util.TimeZone;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration.Dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class RestApiInitializer extends AbstractSecurityWebApplicationInitializer {
	
	static Logger log = LoggerFactory.getLogger(RestApiInitializer.class);
	
    public RestApiInitializer() {
        super(RestApiSecurityConfig.class);
    }
    
    @Override
    protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
    	super.afterSpringSecurityFilterChain(servletContext);
	    log.info("Initializing REST API started");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(RestApiConfig.class);
		ctx.setServletContext(servletContext);
		Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.addMapping("/rest/*");
		servlet.setLoadOnStartup(1);
	    log.info("Initializing REST API completed");
    }

}