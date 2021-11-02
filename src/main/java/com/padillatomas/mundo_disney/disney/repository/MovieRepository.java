package com.padillatomas.mundo_disney.disney.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.padillatomas.mundo_disney.disney.entity.MovieEntity;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long>, JpaSpecificationExecutor<MovieEntity> {

	// For Filtering:
	List<MovieEntity> findAll(Specification<MovieEntity> specs);
}
