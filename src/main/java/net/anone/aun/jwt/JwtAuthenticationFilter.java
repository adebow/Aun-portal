package net.anone.aun.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.anone.aun.service.Impl.StudentUserDetailsService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final StudentUserDetailsService userDetailsService;

	public JwtAuthenticationFilter(JwtUtil jwtUtil, StudentUserDetailsService userDetailsService) {
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");

		String matricNumber = null;
		String jwt = null;

		if (authHeader != null && authHeader.startsWith("Bearer")) {
			jwt = authHeader.substring(7);
			try {
				matricNumber = jwtUtil.extractUsername(jwt);
			} catch (ExpiredJwtException e) {
				logger.warn("JWT token has expired");
			}catch (Exception e) {
				logger.error("JWT extraction failed: " + e.getMessage());
			}
		}
		
		if(matricNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(matricNumber);
			
			if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {
				UsernamePasswordAuthenticationToken authToken =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
				
			}
		}

		filterChain.doFilter(request, response);

	}

}
