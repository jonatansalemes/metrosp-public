package com.jslsolucoes.metrosp.pub.api.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "task_sq", allocationSize = 1, initialValue = 1, sequenceName = "task_sq")
public class Task {

	@Id
	@GeneratedValue(generator = "task_sq", strategy = GenerationType.SEQUENCE)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private String requester;
	
	@Column(nullable = false, unique = true)
	private String uuid;
	
	@Column(nullable = false)
	@Lob
	private String content;

	@ManyToOne
	@JoinColumn(name = "id_task_category", nullable = false)
	private TaskCategory taskCategory;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private Integer priority;

	public Task() {

	}

	public Task(String uuid, String requester, TaskCategory taskCategory, LocalDateTime createdAt, Integer priority, String content) {
		this.uuid = uuid;
		this.requester = requester;
		this.taskCategory = taskCategory;
		this.createdAt = createdAt;
		this.priority = priority;
		this.content = content;
	}

	public Long id() {
		return id;
	}
	
	public String uuid() {
		return uuid;
	}
	
	public String content() {
		return content;
	}
	
	public String taskCategoryName() {
		return taskCategory.name();
	}
	
	public String taskCategoryAlias() {
		return taskCategory.alias();
	}

	public TaskCategory department() {
		return taskCategory;
	}

	public String requester() {
		return requester;
	}

	public LocalDateTime createdAt() {
		return createdAt;
	}

	public Integer priority() {
		return priority;
	}

}
