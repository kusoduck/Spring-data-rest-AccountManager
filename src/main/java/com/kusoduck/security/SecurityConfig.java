package com.kusoduck.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	private static final String PATH = "spring-data-rest-api/";


	// level 1. Using hard-coding to set user
	/*
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {

		UserDetails sam = User.builder().username("sam").password("{noop}test123").roles("EMPLOYEE", "MANAGER", "ADMIN").build();
		UserDetails cher = User.builder().username("cher").password("{noop}test123").roles("EMPLOYEE", "MANAGER").build();
		UserDetails john = User.builder().username("john").password("{noop}test123").roles("EMPLOYEE").build();

		return new InMemoryUserDetailsManager(sam, cher, john);
	}
	*/

	// level 2.
	// it's using spring security default table, users(username,password,enabled) and authorities(username,authority)
	// So don't need to code.
	/**
	 * @param dataSource Inject data source auto-configured by Spring Boot
	 * @return
	 */
//	@Bean
//	public UserDetailsManager userDetailsManager(DataSource dataSource) {
//		return new JdbcUserDetailsManager(dataSource);
//	}

	// level 3.
	// custom my user table and authorities table
	/**
	 * @param dataSource Inject data source auto-configured by Spring Boot
	 * @return
	 */
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

		// define query to retrieve a user by username
		jdbcUserDetailsManager.setUsersByUsernameQuery("select name, password, active from t_user where name=?");

		// define query to retrieve the role by username
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select name, role from t_role where name=?");

		return jdbcUserDetailsManager;
	}

	// ROLE value must be case-sensitive
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests(configurer -> configurer
                                .requestMatchers("/accounts/showFormForAdd", "/accounts/save").permitAll()
//				.requestMatchers(HttpMethod.GET, "css/**").authenticated() //Specify that URLs are allowed by any authenticated user.
//				.requestMatchers(HttpMethod.GET, PATH + "accounts").hasRole("EMPLOYEE")
//				.requestMatchers(HttpMethod.GET, PATH + "accounts/**").hasRole("EMPLOYEE")
//				.requestMatchers(HttpMethod.POST, PATH + "accounts").hasRole("MANAGER")
//				.requestMatchers(HttpMethod.PUT, PATH + "accounts/**").hasRole("MANAGER")
//				.requestMatchers(HttpMethod.DELETE, PATH + "accounts/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.GET, "api/**").hasRole("EMPLOYEE")
//				.requestMatchers(HttpMethod.POST, "api/**").hasRole("EMPLOYEE")
				.requestMatchers("api/demo/leaders").hasRole("MANAGER") // api/thymeleaf/leaders 下的資源僅限 MANAGER 角色
				.requestMatchers("api/demo/systems").hasRole("ADMIN") // api/thymeleaf/leaders 下的資源僅限 MANAGER 角色
				.anyRequest().authenticated() // 其餘請求需要身份驗證
		).formLogin(form -> form.loginPage("/showLoginPage").loginProcessingUrl("/authenticateTheUser") // SpringSecurity用來檢查ID和password的URL
				.permitAll()).logout(logout -> logout.permitAll()).exceptionHandling(configurer -> configurer.accessDeniedPage("/access-denied"));

		// use HTTP Basic authentication
		http.httpBasic(Customizer.withDefaults());

		// disable Cross Site Request Forgery(CSRF)
		// in general, not required for stateless REST APIs that use POST, PUT, DELETE
//		http.csrf(csrf -> csrf.disable());

		return http.build();
	}

}
