package com.jslsolucoes.metrosp.pub.api.domain.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jslsolucoes.metrosp.pub.api.domain.model.TaskCategory;

@Repository
public interface TaskCategoryRepo extends CrudRepository<TaskCategory, Long> {

	@Query("select tc from TaskCategory tc where tc.alias = :alias")
	public Optional<TaskCategory> findByAlias(@Param("alias") String alias);

}
