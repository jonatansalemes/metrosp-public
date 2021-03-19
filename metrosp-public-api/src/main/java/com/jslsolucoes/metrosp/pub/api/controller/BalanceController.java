package com.jslsolucoes.metrosp.pub.api.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/balances")
public class BalanceController {

	@GetMapping("{cardNumber}")
	public BalanceView all(@PathVariable("cardNumber") Long cardNumber) {
		return new BalanceView(cardNumber, BigDecimal.valueOf(10), "jonatan@jslsolucoes.com", "(11) 94970-3490", "Jonatan de Sá Lemes");
	}
}
