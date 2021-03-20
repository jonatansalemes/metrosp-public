package com.jslsolucoes.metrosp.pub.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jslsolucoes.metrosp.pub.api.domain.EmailAnalyserUseCase;
import com.jslsolucoes.metrosp.pub.api.domain.TaskUseCase;
import com.jslsolucoes.metrosp.pub.api.domain.model.Task;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

	private EmailAnalyserUseCase emailAnalyserUseCase;
	private TaskUseCase taskUseCase;

	public TaskController(EmailAnalyserUseCase emailAnalyserUseCase, TaskUseCase taskUseCase) {
		this.emailAnalyserUseCase = emailAnalyserUseCase;
		this.taskUseCase = taskUseCase;
	}

	@GetMapping
	public ResponseEntity<List<TaskView>> all(@RequestParam(value = "taskCategory") String taskCategory) {
		List<Task> tasks = taskUseCase.allForCategory(taskCategory);
		if (CollectionUtils.isEmpty(tasks)) {
			return Responses.notFound();
		}
		return Responses.ok(toTaskView(tasks));
	}

	@Transactional
	@GetMapping("catalogs/emails/{identifier}")
	public ResponseEntity<List<TaskView>> catalog(@PathVariable("identifier") String identifier) throws Exception {
		List<Task> tasks = emailAnalyserUseCase.catalog(identifier);
		if (CollectionUtils.isEmpty(tasks)) {
			return Responses.notFound();
		}
		return Responses.ok(toTaskView(tasks));
	}

	@Transactional
	@PostMapping
	public ResponseEntity<TaskView> createNewOne(@RequestBody @Valid CreateTaskRequest createTaskRequest) {
		String uuid = createTaskRequest.hasUiid() ? createTaskRequest.uuid() : createTaskRequest.generateNewUuid();
		if (taskUseCase.wasIndexed(uuid)) {
			return Responses.conflict();
		}
		Task task = taskUseCase.createNewOne(uuid, createTaskRequest.requester(), createTaskRequest.category(),
				createTaskRequest.priority(), createTaskRequest.content(), createTaskRequest.origin());
		return Responses.created(new TaskView(task), "/{id}", task.id());
	}

	private List<TaskView> toTaskView(List<Task> tasks) {
		return tasks.stream().map(TaskView::new).collect(Collectors.toList());
	}
}
