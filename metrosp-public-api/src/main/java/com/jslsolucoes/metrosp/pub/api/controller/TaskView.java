package com.jslsolucoes.metrosp.pub.api.controller;

import java.time.LocalDateTime;

import com.jslsolucoes.metrosp.pub.api.domain.model.Task;

public class TaskView {

	private final String uuid;
	private final LocalDateTime createdAt;
	private final String requester;
	private final String content;
	private final String categoryAlias;
	private final String categoryName;
	private final String originAlias;
	private final String originName;

	public TaskView(Task task) {
		this.uuid = task.uuid();
		this.createdAt = task.createdAt();
		this.requester = task.requester();
		this.content = task.content();
		this.categoryAlias = task.taskCategoryAlias();
		this.categoryName = task.taskCategoryName();
		this.originAlias = task.taskOriginAlias();
		this.originName = task.taskOriginName();
	}

	public String originAlias() {
		return originAlias;
	}

	public String originName() {
		return originName;
	}

	public String categoryAlias() {
		return categoryAlias;
	}

	public String categoryName() {
		return categoryName;
	}

	public String uuid() {
		return uuid;
	}

	public String content() {
		return content;
	}

	public String requester() {
		return requester;
	}

	public LocalDateTime createdAt() {
		return createdAt;
	}

}
