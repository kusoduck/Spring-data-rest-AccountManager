package com.kusoduck.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	private static final String PATH = "spring-data-rest-api/";

	// here is greater then application properties

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {

		UserDetails sam = User.builder().username("sam").password("{noop}test123").roles("employee", "manager", "admin")
				.build();
		UserDetails cher = User.builder().username("cher").password("{noop}test123").roles("employee", "manager")
				.build();
		UserDetails john = User.builder().username("john").password("{noop}test123").roles("employee").build();

		return new InMemoryUserDetailsManager(sam, cher, john);
	}

//	// it's using spring security default table, users(username,password,enabled) and authorities(username,authority)
//	@Bean
//	public UserDetailsManager userDetailsManager(DataSource dataSource) {
//		return new JdbcUserDetailsManager(dataSource);
//	}

	/*
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

		// define query to retrieve a user by username
		jdbcUserDetailsManager.setUsersByUsernameQuery("select name, password, active from t_user where name=?");

		// define query to retrieve the role by username
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select name, role from t_role where name=?");

		return jdbcUserDetailsManager;
	}
	*/

	// ROLE need to be upper-case
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				configurer -> configurer.anyRequest().authenticated() // 任何到達的request必須驗證
//				.requestMatchers(HttpMethod.GET, "css/**").authenticated() //Specify that URLs are allowed by any authenticated user.
//				.requestMatchers(HttpMethod.GET, PATH + "accounts").hasRole("EMPLOYEE")
//				.requestMatchers(HttpMethod.GET, PATH + "accounts/**").hasRole("EMPLOYEE")
//				.requestMatchers(HttpMethod.POST, PATH + "accounts").hasRole("MANAGER")
//				.requestMatchers(HttpMethod.PUT, PATH + "accounts/**").hasRole("MANAGER")
//				.requestMatchers(HttpMethod.DELETE, PATH + "accounts/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.GET, "api/**").hasRole("EMPLOYEE")
//				.requestMatchers(HttpMethod.POST, "api/**").hasRole("EMPLOYEE")
				).formLogin(form->
					form.loginPage("/showLoginPage")
						.loginProcessingUrl("/authenticateTheUser") // SpringSecurity用來檢查ID和password的URL
						.permitAll()
				).logout(logout-> logout.permitAll());

		// use HTTP Basic authentication
		http.httpBasic(Customizer.withDefaults());

		// disable Cross Site Request Forgery(CSRF)
		// in general, not required for stateless REST APIs that use POST, PUT, DELETE
//		http.csrf(csrf -> csrf.disable());

		return http.build();
	}

}
