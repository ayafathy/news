package com.apps.wave.news.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration

public class SwaggerConfig {
	  @Bean
	    public Docket api() {
	        return new Docket(DocumentationType.SWAGGER_2)
	        		.select()
	                .apis(RequestHandlerSelectors.basePackage("com.apps.wave.news.controller"))
	                .paths(PathSelectors.any())
	                .build()
	                .securitySchemes(Arrays.asList(apiKey()))
	                .securityContexts(Arrays.asList(securityContext()));
	    }
	  private ApiKey apiKey() {
			return new ApiKey("Bearer", "Authorization", "header");
		}

		private SecurityContext securityContext() {
			return SecurityContext.builder().securityReferences(defaultAuth()).build();
		}

		private List<SecurityReference> defaultAuth() {
			AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
			AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
			authorizationScopes[0] = authorizationScope;
			return Arrays.asList(new SecurityReference("Bearer", authorizationScopes));
		}
}
