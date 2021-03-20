package com.jslsolucoes.metrosp.pub.api.controller;

import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateTaskRequest {

	private String uuid;

	@NotBlank
	private String origin;

	@NotBlank
	private String requester;

	@NotBlank
	private String category;

	@NotNull
	private Integer priority;

	@NotBlank
	private String content;

	public String uuid() {
		return uuid;
	}

	public String origin() {
		return origin;
	}

	public String requester() {
		return requester;
	}

	public String category() {
		return category;
	}

	public Integer priority() {
		return priority;
	}

	public String content() {
		return content;
	}

	public boolean hasUiid() {
		return Optional.ofNullable(uuid).map(String::trim).filter(String::isEmpty).isPresent();
	}

	public String generateNewUuid() {
		return UUID.randomUUID().toString();
	}

}
