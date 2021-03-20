package com.jslsolucoes.metrosp.pub.api.domain;

import java.util.List;

import com.jslsolucoes.metrosp.pub.api.domain.model.TaskCategory;
import com.jslsolucoes.metrosp.pub.api.domain.repo.TaskCategoryRepo;
import com.jslsolucoes.metrosp.pub.api.stereotype.UseCase;

@UseCase
public class TaskCategoryUseCase {

	private TaskCategoryRepo taskCategoryRepo;

	public TaskCategoryUseCase(TaskCategoryRepo taskCategoryRepo) {
		this.taskCategoryRepo = taskCategoryRepo;
	}

	public List<TaskCategory> all() {
		return taskCategoryRepo.all();
	}

}
