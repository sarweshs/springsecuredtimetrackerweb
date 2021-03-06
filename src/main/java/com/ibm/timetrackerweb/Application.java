package com.ibm.timetrackerweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.ibm.timetrackerweb.config.MvcConfig;
import com.ibm.timetrackerweb.config.WebSecurityConfig;

//@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }
    
   // @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }
    
    /**
     * Register dispatcherServlet programmatically 
     * 
     * @return ServletRegistrationBean
     */
  //  @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {

       /* ServletRegistrationBean registration = new ServletRegistrationBean(
                dispatcherServlet(), "/login/*");

        registration
                .setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
                return registration;*/
    	DispatcherServlet dispatcherServlet =  dispatcherServlet();   
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(MvcConfig.class,WebSecurityConfig.class);
        dispatcherServlet.setApplicationContext(applicationContext);
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet, "/");
        servletRegistrationBean.setName("timetrackerweb");
        return servletRegistrationBean;

    }
}