package com.jslsolucoes.metrosp.pub.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "task_origin_sq", allocationSize = 1, initialValue = 1, sequenceName = "task_origin_sq")
public class TaskOrigin {

	@Id
	@GeneratedValue(generator = "task_origin_sq", strategy = GenerationType.SEQUENCE)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String alias;

	public TaskOrigin() {

	}

	public TaskOrigin(String name, String alias) {
		this.name = name;
		this.alias = alias;
	}

	public Long id() {
		return id;
	}

	public String name() {
		return name;
	}

	public String alias() {
		return alias;
	}
}
