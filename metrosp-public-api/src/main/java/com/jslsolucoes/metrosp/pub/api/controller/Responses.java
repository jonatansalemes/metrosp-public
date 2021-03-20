package com.jslsolucoes.metrosp.pub.api.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

	public static <T> ResponseEntity<T> ok(T value) {
		return ok(value, new HttpHeaders());
	}

	public static <T> ResponseEntity<T> ok(T value, HttpHeaders httpHeaders) {
		return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(value);
	}

	public static <T> ResponseEntity<T> conflict() {
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	public static <T> ResponseEntity<T> notFound() {
		return ResponseEntity.notFound().build();
	}

	public static <T> ResponseEntity<T> ok() {
		return ResponseEntity.ok().build();
	}

}
