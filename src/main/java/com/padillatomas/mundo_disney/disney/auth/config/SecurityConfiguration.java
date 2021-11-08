package com.padillatomas.mundo_disney.disney.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.padillatomas.mundo_disney.disney.auth.filter.JwtRequestFilter;
import com.padillatomas.mundo_disney.disney.auth.service.UserDetailsCustomService;



@EnableWebSecurity
@SuppressWarnings("deprecation")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	// Importamos CustomService y CustomFilter.
	@Autowired
	private UserDetailsCustomService userDetailsCustomService;
	@Autowired
	private JwtRequestFilter jwtReqFilter;
	
	// == MUST ===
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
	    return super.authenticationManagerBean();
	}
	// ===========
			
	// Prevent Passowrd Encoding: JUST FOR THIS PROJECT.
	// DEPRECATED NUNCA USAR
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
	// 1) Define our USER DETAILS -> Creados en Service (UserDetailsCustomService)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsCustomService);
	}
	
	// 2) Main Configure:
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		// Disable CSRF (no importante para nosotros AHORA)
		httpSecurity.csrf().disable()
		// Permitir All dentro de "/auth/*" - El Resto quedara RESTRINGIDO!!!
		.authorizeRequests().antMatchers("/auth/*").permitAll()
		.anyRequest().authenticated()
		.and().exceptionHandling()
		.and().sessionManagement()
		// Policy = For Each Endpoint Called, NEW Headers. (Always Request Auth, stateless)
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// Add Filter Before processing Request, SIEMPRE (Nuestras Custom Filters)
		httpSecurity.addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
