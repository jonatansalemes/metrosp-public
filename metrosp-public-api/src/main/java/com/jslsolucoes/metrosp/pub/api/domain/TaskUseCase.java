package com.jslsolucoes.metrosp.pub.api.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.jslsolucoes.metrosp.pub.api.domain.model.Task;
import com.jslsolucoes.metrosp.pub.api.domain.model.TaskCategory;
import com.jslsolucoes.metrosp.pub.api.domain.model.TaskOrigin;
import com.jslsolucoes.metrosp.pub.api.domain.repo.TaskCategoryRepo;
import com.jslsolucoes.metrosp.pub.api.domain.repo.TaskOriginRepo;
import com.jslsolucoes.metrosp.pub.api.domain.repo.TaskRepo;
import com.jslsolucoes.metrosp.pub.api.stereotype.UseCase;

@UseCase
public class TaskUseCase {

	private TaskRepo taskRepo;
	private TaskCategoryRepo taskCategoryRepo;
	private TaskOriginRepo taskOriginRepo;

	public TaskUseCase(TaskRepo taskRepo, TaskCategoryRepo taskCategoryRepo, TaskOriginRepo taskOriginRepo) {
		this.taskRepo = taskRepo;
		this.taskCategoryRepo = taskCategoryRepo;
		this.taskOriginRepo = taskOriginRepo;
	}

	public Task createNewOne(String uuid, String requester, String category, Integer priority, String content,
			String origin) {
		TaskCategory taskCategory = taskCategoryRepo.findByAlias(category)
				.orElseGet(() -> new TaskCategory(TaskCategory.Of.UNKNOW));
		TaskOrigin taskOrigin = taskOriginRepo.findByAlias(origin)
				.orElseThrow(() -> new IllegalArgumentException(origin + "its not an valid origin"));
		return taskRepo
				.save(new Task(uuid, requester, taskCategory, LocalDateTime.now(), priority, content, taskOrigin));
	}

	public boolean wasNotIndexed(String uuid) {
		return !wasIndexed(uuid);
	}

	public boolean wasIndexed(String uuid) {
		return taskRepo.findByUuid(uuid).isPresent();
	}

	public List<Task> allForCategory(String taskCategory) {
		return taskRepo.allForCategory(taskCategory);
	}
}
