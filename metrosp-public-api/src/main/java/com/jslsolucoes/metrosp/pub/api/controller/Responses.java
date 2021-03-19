package com.jslsolucoes.metrosp.pub.api.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Responses {

	private Responses() {
	}

	public static <T> ResponseEntity<T> created(T body, String path, Object... uriVariables) {
		return ResponseEntity.created(uri(path, uriVariables)).body(body);
	}

	private static URI uri(String path, Object... uriVariables) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path(path).buildAndExpand(uriVariables).toUri();
	}

}
