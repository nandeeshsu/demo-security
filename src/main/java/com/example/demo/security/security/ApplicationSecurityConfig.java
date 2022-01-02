package com.example.demo.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder; 
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.csrf().disable()
                .authorizeRequests()
                .antMatchers("/actuator", "/actuator/*").permitAll()
                .antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.toString())
                .antMatchers(HttpMethod.POST, "/mgmt/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.toString())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    
    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
    	UserDetails student1 =  User.builder()
    	.username("student1")
    	.password(passwordEncoder.encode("test123"))
    	.roles(ApplicationUserRole.STUDENT.toString()) //ROLE_STUDENT
    	.build();
    	
    	UserDetails admin =  User.builder()
    	    	.username("admin")
    	    	.password(passwordEncoder.encode("test123"))
    	    	.roles(ApplicationUserRole.ADMIN.toString()) //ROLE_ADMIN
    	    	.build();
    	
    	UserDetails adminTrainee =  User.builder()
    	    	.username("admintrainee")
    	    	.password(passwordEncoder.encode("test123"))
    	    	.roles(ApplicationUserRole.ADMIN_TRAINEE.toString()) //ROLE_ADMIN_TRAINEE
    	    	.build();
    	
    	return new InMemoryUserDetailsManager(student1, admin, adminTrainee);
    	
    }
}
