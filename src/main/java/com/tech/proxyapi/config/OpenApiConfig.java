package com.tech.proxyapi.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI swapiProxyOpenAPI() {
		return new OpenAPI().info(new Info().title("Swapi Proxy API").description(
				"Technical test for Trileuco Solutions.\nYou can check information about all of the Star Wars characters just introducing their name.")
				.version("v0.0.1").license(new License().name("MIT License")));
	}

	@Bean
	public GroupedOpenApi api() {
		return GroupedOpenApi.builder().group("ProxyApiRestController").packagesToScan("com.tech.proxyapi.controllers")
				.build();
	}
}
