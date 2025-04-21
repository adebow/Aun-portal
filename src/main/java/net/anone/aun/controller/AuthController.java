package net.anone.aun.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.anone.aun.jwt.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	

	public AuthController (AuthenticationManager authenticationManager,JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	public ResponseEntity<?> login(@RequestBody Map<String, String>body) {
		String matricNumber = body.get("matricNo");
		String password = body.get("password");
		
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(matricNumber, password));
			
			String token = jwtUtil.generateToken(matricNumber);
			return ResponseEntity.ok(Collections.singletonMap("token", token));
		}catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Inavalid credentials");
		
		}
	}
}
