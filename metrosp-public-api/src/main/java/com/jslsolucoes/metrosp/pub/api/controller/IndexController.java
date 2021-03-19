package com.jslsolucoes.metrosp.pub.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class IndexController {

	@GetMapping
	public IndexResponse index() {
		return new IndexResponse("Api its running up and running");
	}

	static class IndexResponse {
		private final String message;

		public IndexResponse(final String message) {
			this.message = message;
		}

		public String message() {
			return message;
		}
	}
}
