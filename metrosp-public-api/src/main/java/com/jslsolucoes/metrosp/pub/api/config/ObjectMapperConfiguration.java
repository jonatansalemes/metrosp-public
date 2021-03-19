package com.jslsolucoes.metrosp.pub.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class ObjectMapperConfiguration {

	@Bean
	public static ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_EMPTY);
		objectMapper.setSerializationInclusion(Include.NON_DEFAULT);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
		objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
		objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper;
	}

}
