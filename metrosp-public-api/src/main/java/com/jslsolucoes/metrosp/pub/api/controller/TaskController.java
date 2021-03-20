package com.jslsolucoes.metrosp.pub.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jslsolucoes.metrosp.pub.api.domain.EmailAnalyserUseCase;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

	private EmailAnalyserUseCase emailAnalyserUseCase;

	public TaskController(EmailAnalyserUseCase emailAnalyserUseCase) {
		this.emailAnalyserUseCase = emailAnalyserUseCase;
	}

	@GetMapping("catalogs/emails/{identifier}")
	public void catalog(@PathVariable("identifier") String identifier) throws Exception {
		emailAnalyserUseCase.catalog(identifier);
	}
}
