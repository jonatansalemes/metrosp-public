package com.jslsolucoes.metrosp.pub.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration {

	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry corsRegistry) {
				corsRegistry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*")
						.exposedHeaders("Location");
			}

		};
	}
}
