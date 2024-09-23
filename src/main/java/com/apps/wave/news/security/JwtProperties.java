package com.apps.wave.news.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtProperties {

	private String privateKey ;
	private String publicKey ;


}
