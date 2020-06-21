package com.sob.identity.service;

import java.util.Date;

import com.sob.identity.model.AuthToken;
import com.sob.identity.model.JwtUser;
import com.sob.identity.repo.models.Identity;
import com.sob.identity.repo.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	public static final String BEARER_TOKEN = "Bearer ";

	@Value("${jwt.client.secret}")
	private String secret;

	@Value("${jwt.client.expire}")
	public long expire;


	@Autowired
	private IdentityRepository repo;

	public AuthToken generate(Identity user, String subject) {
		if(user!=null && user.getRole().equals(user.getRole())) {
			Claims claims = Jwts.claims().setSubject(subject);
			claims.setIssuedAt(new Date(System.currentTimeMillis()));
			claims.setExpiration(new Date(System.currentTimeMillis() + expire * 1000));
			claims.put("user_id", user.getIdentity_id());
			claims.put("user_role", user.getRole());

			return AuthToken.builder()
					.token(Jwts.builder()
							.setClaims(claims)
							.signWith(SignatureAlgorithm.HS512, secret)
							.compact())
					.expires(expire)
					.build();
		}
		throw new RuntimeException("User details are not valid");
	}

	/**
	 * Validate the JWT Token
	 * 
	 * @param token
	 * @return
	 */
	public JwtUser validate(String token) {
		JwtUser jwtUser = null;
		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			Date expiration = body.getExpiration();

			if (expiration.before(new Date())) {
				throw new RuntimeException("JWT token is not valid");
			}
			Identity user = repo.findUserByEmail(body.getSubject());
			if(user!=null && user.getRole().equals((String) body.get("role"))) {
				jwtUser = new JwtUser();
				jwtUser.setSubject(body.getSubject());
				jwtUser.setId((String) body.get("user_id"));
				jwtUser.setRole((String) body.get("user_role"));
				return jwtUser;
			}
			throw new RuntimeException("User details are not valid");
		} catch (Exception e) {
			throw new RuntimeException("JWT token is not valid");
		}
	}

	public String extractUserId(String token) {
		JwtUser jwtUser = null;
		try {
			Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			Date expiration = body.getExpiration();

			if (expiration.before(new Date())) {
				throw new RuntimeException("JWT token is not valid");
			}
			return String.valueOf(body.get("user_id"));
		} catch (Exception e) {
			throw new RuntimeException("JWT token is not valid");
		}
	}

}
