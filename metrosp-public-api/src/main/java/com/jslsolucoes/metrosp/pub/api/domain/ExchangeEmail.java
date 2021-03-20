package com.jslsolucoes.metrosp.pub.api.domain;

public class ExchangeEmail {

	public final String from;
	public final String message;
	public final String id;

	public ExchangeEmail(final String id, final String from, final String message) {
		this.id = id;
		this.from = from;
		this.message = message;
	}

	public String from() {
		return from;
	}

	public String id() {
		return id;
	}

	public String message() {
		return message;
	}

}
