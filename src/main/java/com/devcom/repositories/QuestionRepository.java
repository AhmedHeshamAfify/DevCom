package com.devcom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.devcom.models.Question;

public interface QuestionRepository extends CrudRepository<Question, Long>, JpaRepository<Question, Long>{

	
	List<Question> findAllByUserId(long userId);
}
