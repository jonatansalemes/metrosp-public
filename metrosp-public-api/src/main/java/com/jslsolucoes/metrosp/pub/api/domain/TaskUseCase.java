package com.jslsolucoes.metrosp.pub.api.domain;

import java.time.LocalDateTime;

import com.jslsolucoes.metrosp.pub.api.domain.model.Task;
import com.jslsolucoes.metrosp.pub.api.domain.model.TaskCategory;
import com.jslsolucoes.metrosp.pub.api.domain.repo.TaskCategoryRepo;
import com.jslsolucoes.metrosp.pub.api.domain.repo.TaskRepo;
import com.jslsolucoes.metrosp.pub.api.stereotype.UseCase;

@UseCase
public class TaskUseCase {

	private TaskRepo taskRepo;
	private TaskCategoryRepo taskCategoryRepo;

	public TaskUseCase(TaskRepo taskRepo, TaskCategoryRepo taskCategoryRepo) {
		this.taskRepo = taskRepo;
		this.taskCategoryRepo = taskCategoryRepo;
	}

	public Task createNewOne(String uuid, String requester, String category, Integer priority) {
		TaskCategory taskCategory = taskCategoryRepo.findByAlias(category)
				.orElseGet(() -> new TaskCategory(TaskCategory.Of.UNKNOW));
		return taskRepo.save(new Task(uuid, requester, taskCategory, LocalDateTime.now(), priority));
	}

	public boolean wasNotIndexed(String uuid) {
		return !taskRepo.findByUuid(uuid).isPresent();
	}
}
