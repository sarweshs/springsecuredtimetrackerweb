package com.ibm.timetrackerweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.ibm.timetrackerweb.config.MvcConfig;
import com.ibm.timetrackerweb.config.WebSecurityConfig;

@SpringBootApplication
public class LibertyInitializer extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(applicationClass, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<LibertyInitializer> applicationClass = LibertyInitializer.class;
    
   @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }
    
    /**
     * Register dispatcherServlet programmatically 
     * 
     * @return ServletRegistrationBean
     */
    @Bean
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



@RestController
class GreetingController {

    @RequestMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        return "Hello, " + name + "!";
    }
}