package com.jslsolucoes.metrosp.pub.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jslsolucoes.metrosp.pub.api.domain.TaskCategoryUseCase;
import com.jslsolucoes.metrosp.pub.api.domain.model.TaskCategory;

@RestController
@RequestMapping("api/v1/taskCategories")
public class TaskCategoryController {

	private TaskCategoryUseCase taskCategoryUseCase;

	public TaskCategoryController(TaskCategoryUseCase taskCategoryUseCase) {
		this.taskCategoryUseCase = taskCategoryUseCase;
	}

	@GetMapping
	public ResponseEntity<List<TaskCategoryView>> all() {
		List<TaskCategory> taskCategories = taskCategoryUseCase.all();
		if (CollectionUtils.isEmpty(taskCategories)) {
			return Responses.notFound();
		}
		return Responses.ok(toTaskCategoryView(taskCategories));
	}

	private List<TaskCategoryView> toTaskCategoryView(List<TaskCategory> taskCategories) {
		return taskCategories.stream().map(TaskCategoryView::new).collect(Collectors.toList());
	}
}
