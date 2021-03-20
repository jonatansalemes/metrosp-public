package com.jslsolucoes.metrosp.pub.api.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "task_category_sq", allocationSize = 1, initialValue = 1, sequenceName = "task_category_sq")
public class TaskCategory {

	@Id
	@GeneratedValue(generator = "task_category_sq", strategy = GenerationType.SEQUENCE)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String alias;

	public TaskCategory(Of of) {
		this.id = of.id();
	}

	public TaskCategory() {

	}

	public TaskCategory(String name, String alias) {
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

	public static enum Of {

		UNKNOW(1L);

		private Long id;

		private Of(Long id) {
			this.id = id;
		}

		public Long id() {
			return id;
		}
	}

}
