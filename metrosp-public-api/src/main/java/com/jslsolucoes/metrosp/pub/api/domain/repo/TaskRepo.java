package com.jslsolucoes.metrosp.pub.api.domain.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jslsolucoes.metrosp.pub.api.domain.model.Task;

@Repository
public interface TaskRepo extends CrudRepository<Task, Long> {

	@Query("select t from Task t where t.uuid = :uuid")
	public Optional<Task> findByUuid(@Param("uuid") String uuid);
	
}
