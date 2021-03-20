package com.jslsolucoes.metrosp.pub.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jslsolucoes.metrosp.pub.api.domain.MachineLearningUseCase;
import com.jslsolucoes.metrosp.pub.api.domain.MachineLearningUseCase.ClassifyQuery;
import com.jslsolucoes.metrosp.pub.api.domain.MachineLearningUseCase.ClassifyResult;
import com.jslsolucoes.metrosp.pub.api.domain.MachineLearningUseCase.TrainModel;
import com.jslsolucoes.metrosp.pub.api.domain.MachineLearningUseCase.TrainResult;

@RestController
@RequestMapping("api/v1/mls")
public class MachineLearningController {

	private MachineLearningUseCase machineLearningUseCase;

	@Autowired
	public MachineLearningController(MachineLearningUseCase machineLearningUseCase) {
		this.machineLearningUseCase = machineLearningUseCase;
	}

	@PostMapping
	public ResponseEntity<TrainResult> train(@RequestBody @Valid TrainModel trainModel) throws Exception {
		return Responses.created(machineLearningUseCase.train(trainModel), trainModel.getIdentifier());
	}

	@GetMapping("{identifier}")
	public ResponseEntity<ClassifyResult> classify(@PathVariable("identifier") String identifier,
			@RequestParam(value = "q", required = true) String query) throws Exception {
		return Responses.ok(machineLearningUseCase.classify(new ClassifyQuery(identifier, query)));
	}
}
