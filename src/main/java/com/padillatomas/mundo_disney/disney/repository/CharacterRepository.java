package com.padillatomas.mundo_disney.disney.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.padillatomas.mundo_disney.disney.entity.CharacterEntity;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long>, JpaSpecificationExecutor<CharacterEntity> {

	// For Filtering:
	List<CharacterEntity> findAll(Specification<CharacterEntity> specs);
}
