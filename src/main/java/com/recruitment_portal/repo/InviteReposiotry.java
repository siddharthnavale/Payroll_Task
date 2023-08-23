package com.recruitment_portal.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recruitment_portal.entities.InviteEntity;

public interface InviteReposiotry extends JpaRepository<InviteEntity, Integer> {

	@Query(value = "select * from invite_entity where code=:id", nativeQuery = true)
	InviteEntity findbyUuid(@Param("id") UUID id);

}
