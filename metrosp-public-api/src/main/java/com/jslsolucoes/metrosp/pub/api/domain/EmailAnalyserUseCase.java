package com.jslsolucoes.metrosp.pub.api.domain;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jslsolucoes.metrosp.pub.api.domain.MachineLearningUseCase.ClassifyQuery;
import com.jslsolucoes.metrosp.pub.api.domain.MachineLearningUseCase.ClassifyResult;
import com.jslsolucoes.metrosp.pub.api.domain.model.Task;
import com.jslsolucoes.metrosp.pub.api.stereotype.UseCase;

@UseCase
public class EmailAnalyserUseCase {

	private static final Logger logger = LoggerFactory.getLogger(EmailAnalyserUseCase.class);

	private ExchangeConnector emailConnector;
	private MachineLearningUseCase machineLearningUseCase;
	private TaskUseCase taskUseCase;

	public EmailAnalyserUseCase(ExchangeConnector emailConnector, MachineLearningUseCase machineLearningUseCase,
			TaskUseCase taskUseCase) {
		this.emailConnector = emailConnector;
		this.machineLearningUseCase = machineLearningUseCase;
		this.taskUseCase = taskUseCase;
	}

	public List<Task> catalog(String identifier) throws Exception {
		List<Task> tasks = new ArrayList<>();
		for (ExchangeEmail exchangeEmail : emailConnector.messages()) {
			String message = exchangeEmail.message();
			String from = exchangeEmail.from();
			String id = exchangeEmail.id();
			if (taskUseCase.wasNotIndexed(id)) {
				ClassifyResult classifyResult = machineLearningUseCase.classify(new ClassifyQuery(identifier, message));
				String label = classifyResult.getLabel();
				logger.info("New email from ({}) {} with message {} was created as {} with priority 0", id, from,
						message, label);
				tasks.add(taskUseCase.createNewOne(id, from, label, 0 , message));
			} else {
				logger.info("Email id {} was already indexed", id);
			}
		}
		return tasks;
	}
}
