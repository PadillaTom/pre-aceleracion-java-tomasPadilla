package com.padillatomas.mundo_disney.disney.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
	
	private String username;
	private String password;
	
}
