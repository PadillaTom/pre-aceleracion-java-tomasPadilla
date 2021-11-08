package com.padillatomas.mundo_disney.disney.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.padillatomas.mundo_disney.disney.auth.service.JwtUtils;
import com.padillatomas.mundo_disney.disney.auth.service.UserDetailsCustomService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsCustomService userDetailsCS;
	
	@Autowired
	private JwtUtils jwtUtils; // To EXTRACT and VALIDATE.
	
	@Autowired
	private AuthenticationManager authManager;
	
	//
	// Ejecutado Cada vez que llegue un Request:	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// Get Headers for Authorization:
		final String authHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		// Extraemos TOKEN del HEADER
		// Extraemos el USERNAME usando JwtUtils.
		if (authHeader != null && authHeader.startsWith("Bearer ")) {			
			jwt = authHeader.substring(7); // 7 char before Token.
			username = jwtUtils.extractUsername(jwt); // Obtenemos Username.
		}
		
		// && If USERNAME is NOT AUTHENTICATED YET (NOT in Context)
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			// 1)
			// Obtenemos UserDetails: de dicho Username (Username y Pass):
			UserDetails userDetails = userDetailsCS.loadUserByUsername(username);
			
			// 2)
			// Validamos: Jwt y Object reicen creado (user y pass):
			if(jwtUtils.validateToken(jwt, userDetails)) {
				
				// Creamos la AuthRequest con UserDetails
				UsernamePasswordAuthenticationToken authReq =
						new UsernamePasswordAuthenticationToken(
								userDetails.getUsername(),
								userDetails.getPassword()								
								);	
				
				// Guardamos la AuthResponse para ser Setteada en el Context:
				Authentication auth = authManager.authenticate(authReq);
				
				// Setteamos la AUTH al CONTEXT
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		
		// Pasamos todo lo anterior
		// "Para el siguiente paso":
		filterChain.doFilter(request, response);		
	}

}
