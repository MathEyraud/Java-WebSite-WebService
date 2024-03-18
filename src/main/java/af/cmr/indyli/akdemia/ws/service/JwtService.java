package af.cmr.indyli.akdemia.ws.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import af.cmr.indyli.akdemia.business.dto.full.UserFullDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * Service class for handling JSON Web Tokens (JWT) operations.
 */
@Component
public class JwtService {

	@Value("${akdemia.application.jwt.key.secret}")
	private String keySecret;

	/**
	 * Generates a JWT token for the specified user and roles.
	 *
	 * @param user  UserFullDTO representing the user details.
	 * @param roles List of roles associated with the user.
	 * @return Generated JWT token.
	 */
	public String generateToken(UserFullDTO user, List<String> roles) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("roles", roles);
		claims.put("id", user.getId());
		return createToken(claims, user.getEmail());
	}

	private String createToken(Map<String, Object> claims, String userName) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(keySecret);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	/**
	 * Extracts the username from the given JWT token.
	 *
	 * @param token JWT token to extract the username from.
	 * @return Username extracted from the token.
	 */
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	/**
	 * Extracts the expiration date from the given JWT token.
	 *
	 * @param token JWT token to extract the expiration date from.
	 * @return Expiration date extracted from the token.
	 */
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	/**
	 * Extracts a claim from the given JWT token using the provided claims resolver.
	 *
	 * @param token          JWT token to extract the claim from.
	 * @param claimsResolver Function to resolve the desired claim.
	 * @param <T>            Type of the extracted claim.
	 * @return Extracted claim value.
	 */
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	/**
	 * Validates the given JWT token against the provided UserDetails.
	 *
	 * @param token       JWT token to validate.
	 * @param userDetails UserDetails representing the user details.
	 * @return True if the token is valid for the specified user, otherwise false.
	 */
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
