package com.jslsolucoes.metrosp.pub.api.controller;

import javax.validation.constraints.NotNull;

public class CreateTicketRequest {
	
	@NotNull
	private String uuid;
	
	@NotNull
	private String requester;
	
	@NotNull
	private String category;
	
	@NotNull
	private Integer priority;
	
	@NotNull
	private String content;

	public String uuid() {
		return uuid;
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

}
