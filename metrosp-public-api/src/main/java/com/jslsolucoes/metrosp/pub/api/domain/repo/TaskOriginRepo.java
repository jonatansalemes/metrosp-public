package com.jslsolucoes.metrosp.pub.api.domain.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jslsolucoes.metrosp.pub.api.domain.model.TaskOrigin;

@Repository
public interface TaskOriginRepo extends CrudRepository<TaskOrigin, Long> {

	@Query("select to from TaskOrigin to where to.alias = :alias")
	public Optional<TaskOrigin> findByAlias(@Param("alias") String alias);

}
