package com.jslsolucoes.metrosp.pub.api.domain;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jslsolucoes.metrosp.pub.api.config.MachineLearningEnviromentProperties;
import com.jslsolucoes.metrosp.pub.api.stereotype.UseCase;

@UseCase
public class MachineLearningUseCase {

	private RestTemplate restTemplate;
	private MachineLearningEnviromentProperties machineLearningEnviromentProperties;
	private ObjectMapper objectMapper;

	@Autowired
	public MachineLearningUseCase(RestTemplate restTemplate, ObjectMapper objectMapper,
			MachineLearningEnviromentProperties machineLearningEnviromentProperties) {
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
		this.machineLearningEnviromentProperties = machineLearningEnviromentProperties;
	}

	public ClassifyResult classify(ClassifyQuery classifyQuery) throws Exception {
		String url = machineLearningEnviromentProperties.getHost() + "/classifiers/naive/bayes/classify";
		String apiKey = machineLearningEnviromentProperties.getApiKey();
		Map<String, String> headers = Map.of(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey, HttpHeaders.CONTENT_TYPE,
				MediaType.APPLICATION_JSON_VALUE);
		RequestEntity<ClassifyQuery> body = RequestEntity.post(URI.create(url)).headers(headers(headers))
				.body(classifyQuery);
		ResponseEntity<ClassifyResult> responseEntity = restTemplate.exchange(body, ClassifyResult.class);
		int statusCode = responseEntity.getStatusCodeValue();
		if (statusCode == 200) {
			return responseEntity.getBody();
		}
		throw new Exception("Could not train model, status code " + statusCode + " not expected");

	}

	public TrainResult train(TrainModel trainModel) throws Exception {
		String url = machineLearningEnviromentProperties.getHost() + "/classifiers/naive/bayes/train";
		String apiKey = machineLearningEnviromentProperties.getApiKey();
		Map<String, String> headers = Map.of(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey, HttpHeaders.CONTENT_TYPE,
				MediaType.APPLICATION_JSON_VALUE);
		RequestEntity<TrainModel> body = RequestEntity.post(URI.create(url)).headers(headers(headers)).body(trainModel);
		ResponseEntity<TrainResult> responseEntity = exchange(body, TrainResult.class);
		int statusCode = responseEntity.getStatusCodeValue();
		if (statusCode == 200 || statusCode == 409) {
			return responseEntity.getBody();
		}
		throw new Exception("Could not train model, status code " + statusCode + " not expected");
	}

	private <T, B> ResponseEntity<T> exchange(RequestEntity<B> requestEntity, Class<T> responseType)
			throws IOException {
		try {
			return restTemplate.exchange(requestEntity, responseType);
		} catch (RestClientResponseException e) {
			return errorResponseEntity(e, responseType);
		}
	}

	private <T> ResponseEntity<T> errorResponseEntity(RestClientResponseException restClientResponseException,
			Class<T> responseType) throws IOException {
		byte[] body = restClientResponseException.getResponseBodyAsByteArray();
		T response = body.length > 0 ? objectMapper.readValue(body, responseType) : null;
		int statusCode = restClientResponseException.getRawStatusCode();
		return new ResponseEntity<>(response, HttpStatus.valueOf(statusCode));
	}

	private HttpHeaders headers(Map<String, String> headers) {
		HttpHeaders httpHeaders = new HttpHeaders();
		for (Entry<String, String> entry : headers.entrySet()) {
			httpHeaders.add(entry.getKey(), entry.getValue());
		}
		return httpHeaders;
	}

	public static class ClassifyQuery {

		@NotNull
		private String identifier;

		@NotNull
		private String text;

		public ClassifyQuery() {

		}

		public ClassifyQuery(String identifier, String text) {
			this.identifier = identifier;
			this.text = text;
		}

		public String getIdentifier() {
			return identifier;
		}

		public void setIdentifier(String identifier) {
			this.identifier = identifier;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

	}

	public static class ClassifyResult {

		private String label;

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

	}

	public static class TrainResult {

		private String status;

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

	}

	public static class TrainModel {

		@NotNull
		private String identifier;

		@NotNull
		private Set<TrainModelEntry> dataset;

		public TrainModel() {

		}

		public TrainModel(String identifier, Set<TrainModelEntry> dataset) {
			this.identifier = identifier;
			this.dataset = dataset;
		}

		public String getIdentifier() {
			return identifier;
		}

		public void setIdentifier(String identifier) {
			this.identifier = identifier;
		}

		public Set<TrainModelEntry> getDataset() {
			return dataset;
		}

		public void setDataset(Set<TrainModelEntry> dataset) {
			this.dataset = dataset;
		}

	}

	public static class TrainModelEntry {

		@NotNull
		private String text;

		@NotNull
		private String label;

		public TrainModelEntry(String text, String label) {
			this.text = text;
			this.label = label;
		}

		public TrainModelEntry() {

		}

		public String getText() {
			return text;
		}

		public String getLabel() {
			return label;
		}

	}
}
