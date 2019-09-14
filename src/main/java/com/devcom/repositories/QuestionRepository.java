package com.devcom.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.devcom.models.Question;

public interface QuestionRepository extends CrudRepository<Question, Long>, JpaRepository<Question, Long>{

	
	List<Question> findAllByUserId(long userId);
	
	@Query("from Question q ,Category c where c.id in :categoryIds order by q.date desc")
	List<Question> getQuestionsForCategories(@Param("categoryIds") List<Long> categoriesIds);
	
	@Query("UPDATE Question q SET q.viewsNumber = viewsNumber + 1 WHERE q.id = :questionId")
	@Transactional
	@Modifying
	void updateNoOfViews(@Param("questionId") Long questionId);

}
