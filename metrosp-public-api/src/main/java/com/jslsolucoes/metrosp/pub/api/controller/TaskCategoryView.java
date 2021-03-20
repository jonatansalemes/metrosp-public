package com.jslsolucoes.metrosp.pub.api.controller;

import com.jslsolucoes.metrosp.pub.api.domain.model.TaskCategory;

public class TaskCategoryView {

	private String alias;
	private String name;
	private Long id;

	public TaskCategoryView(TaskCategory taskCategory) {
		this.alias = taskCategory.alias();
		this.name = taskCategory.name();
		this.id = taskCategory.id();
	}

	public String alias() {
		return alias;
	}

	public String name() {
		return name;
	}

	public Long id() {
		return id;
	}
}
