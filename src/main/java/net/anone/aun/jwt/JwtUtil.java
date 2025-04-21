package net.anone.aun.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String SECRET_KEY;

	private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(String matricNumber) {

		return Jwts.builder().setSubject(matricNumber).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token, String username) {
		try {
			Claims claims = Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
			return username.equals(claims.getSubject()) && !claims.getExpiration().before(new Date());
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}

	}

	/*
	 * private boolean isTokenExpired(String token) { try { Claims claims =
	 * Jwts.parserBuilder() .setSigningKey(getSignInKey()) .build()
	 * .parseClaimsJws(token) .getBody(); return claims.getExpiration().before(new
	 * Date()); } catch (JwtException | IllegalArgumentException e) { return true;
	 * 
	 * }
	 */

}

