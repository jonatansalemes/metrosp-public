package com.jslsolucoes.metrosp.pub.api.controller;

import java.math.BigDecimal;

public class BalanceView {

	private final Long cardNumber;
	private final BigDecimal balance;
	private final String email;
	private final String phone;
	private final String fullName;

	public BalanceView(final Long cardNumber, final BigDecimal balance, final String email, final String phone,
			final String fullName) {
		this.cardNumber = cardNumber;
		this.balance = balance;
		this.email = email;
		this.phone = phone;
		this.fullName = fullName;
	}

	public Long cardNumber() {
		return cardNumber;
	}

	public BigDecimal balance() {
		return balance;
	}

	public String email() {
		return email;
	}

	public String phone() {
		return phone;
	}

	public String fullName() {
		return fullName;
	}

}
