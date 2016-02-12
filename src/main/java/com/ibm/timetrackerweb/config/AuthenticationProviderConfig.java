package com.ibm.timetrackerweb.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
public class AuthenticationProviderConfig {
	@Autowired
	DataSource dataSource;
	/*@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/userbase");
	    driverManagerDataSource.setUsername("root");
	    driverManagerDataSource.setPassword("root");
	    return driverManagerDataSource;
	}
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("org.h2.Driver");
	    driverManagerDataSource.setUrl("jdbc:h2:mem:testdb");
	    driverManagerDataSource.setUsername("sa");
	    driverManagerDataSource.setPassword("");
	    return driverManagerDataSource;
	}*/
    
    @Bean(name="userDetailsService")
    public UserDetailsService userDetailsService(){
    	JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
    	jdbcImpl.setDataSource(dataSource);
    	jdbcImpl.setUsersByUsernameQuery("select username,password, enabled from users where username=?");
    	jdbcImpl.setAuthoritiesByUsernameQuery("select b.username, a.role from user_roles a, users b where b.username=? and a.username=b.username");
    	return jdbcImpl;
    }
}
