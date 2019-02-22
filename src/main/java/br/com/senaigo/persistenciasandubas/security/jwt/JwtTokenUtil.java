//package br.com.senaigo.persistenciasandubas.security.jwt;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class JwtTokenUtil implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//
//	public static final String CLAIM_KEY_USERNAME = "sub";
//	public static final String CLAIM_KEY_CREATED = "created";
//	public static final String CLAIM_KEY_EXPIRED = "exp";
//
//	@Value("${jwt.secret}")
//	private String secret;
//
//	@Value("${jwt.expiration}")
//	private Long expiration;
//
//	public String getUsernameFromToken(String token) {
//		String username;
//		try {
//			final Claims claims = getClaimsFromToken(token);
//			username = claims.getSubject();
//		} catch (Exception e) {
//			username = null;
//		}
//		return username;
//	}
//
//	public Date getExpirationDateFromToken(String token) {
//		Date expiration;
//		try {
//			final Claims claims = getClaimsFromToken(token);
//			expiration = claims.getExpiration();
//		} catch (Exception e) {
//			expiration = null;
//		}
//		return expiration;
//	}
//
//	private Claims getClaimsFromToken(String token) {
//		Claims claims;
//		try {
//			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//		} catch (Exception e) {
//			claims = null;
//		}
//		return claims;
//	}
//
//	private Boolean isTokenExpired(String token) {
//		final Date expiration = getExpirationDateFromToken(token);
//		return expiration.before(new Date());
//	}
//
//	public String generateToken(UserDetails userDetails) {
//		Map<String, Object> mapaClaims = new HashMap<>();
//
//		mapaClaims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
//
//		final Date createDate = new Date();
//		mapaClaims.put(CLAIM_KEY_CREATED, createDate);
//
//		return doGenerateToken(mapaClaims);
//	}
//
//	private String doGenerateToken(Map<String, Object> mapaClaims) {
//		final Date createDate = (Date) mapaClaims.get(CLAIM_KEY_CREATED);
//		final Date expirationDate = new Date(createDate.getTime() + expiration * 1000);
//		return Jwts.builder().setClaims(mapaClaims).setExpiration(expirationDate)
//				.signWith(SignatureAlgorithm.HS512, secret).compact();
//	}
//	
//	public Boolean canTokenBeRefreshed(String token) {
//		return !isTokenExpired(token);
//	}
//	
//	public String refreshToken(String token) {
//		String refreshdToken = null;
//		try {
//			final Claims claims = getClaimsFromToken(token);
//			claims.put(CLAIM_KEY_CREATED, new Date());
//			refreshdToken = doGenerateToken(claims);
//			return refreshdToken;
//		} catch (Exception e) {
//			return null;
//		}
//	}
//	
//	public Boolean validateToken(String token, UserDetails userDetails) {
//		JwtUser user = (JwtUser) userDetails;
//		final String username = getUsernameFromToken(token);
//		return (username.equals(user.getUsername()) && !isTokenExpired(token));
//	}
//}
