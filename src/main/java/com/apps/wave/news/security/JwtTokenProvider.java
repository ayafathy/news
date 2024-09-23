package com.apps.wave.news.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;


import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import org.apache.commons.codec.*; 
@Component
public class JwtTokenProvider {
	@Value("${lifeTimeToken.in.milliseconds}")
	Integer validityInMs;
	@Autowired
	JwtProperties jwtProperties;



	private PrivateKey privateKey;
	private PublicKey publicKey;

	@PostConstruct
	protected void init() throws NoSuchAlgorithmException, DecoderException, InvalidKeySpecException {

		KeyFactory kf = KeyFactory.getInstance("RSA");

		byte[] privateKeyBinary = Hex.decode(jwtProperties.getPrivateKey());
		PKCS8EncodedKeySpec privateSpecs = new PKCS8EncodedKeySpec(privateKeyBinary);
		privateKey = kf.generatePrivate(privateSpecs);

		byte[] publicKeyBinary = Hex.decode(jwtProperties.getPublicKey());

		X509EncodedKeySpec pubSpecs = new X509EncodedKeySpec(publicKeyBinary);
		publicKey = kf.generatePublic(pubSpecs);

	}
	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody().getSubject();
	}
	 public boolean validate(String token) {
	        if (getUsername(token) != null && isExpired(token)) {
	            return true;
	        }
	        return false;
	    }
	 

	 public String generate(String username ,String role ) {
			Date now = new Date();
			Map<String, Object> claims = new HashMap<>();
	   //     claims.put("role", role);
			
			Date validity = new Date(now.getTime() + validityInMs);
	        return	 Jwts.builder()//
	        		 .setSubject(username)
				//	.setClaims(claims)//
					.setIssuedAt(new Date(System.currentTimeMillis()))//
					.setExpiration(validity)//
					.signWith(SignatureAlgorithm.RS256, privateKey)//
					.compact();
	    }
	 
	  public boolean isExpired(String token) {
	        Claims claims = getClaims(token);
	        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
	    }
	   private Claims getClaims(String token) {
	        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
	    }
}
