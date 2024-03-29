package com.devcom.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.devcom.models.Question;

public interface QuestionRepository extends CrudRepository<Question, Long>, JpaRepository<Question, Long>{

	
	List<Question> findAllByUserId(long userId);
	
//	@Query("from Question q ,Category c where c.id in :categoryIds order by q.date desc")
//	List<Question> getQuestionsForCategories(@Param("categoryIds") List<Long> categoriesIds);

//	@Query("select new com.devcom.models.Question(q.id,q.text) from Question q ,Category c where c.id in :categoryIds order by q.date desc")
//	List<Question> getQuestionsForCategoriesToSearch(@Param("categoryIds") List<Long> categoriesIds);

	@Query("Select q from Question q inner join q.categories c where c.id  = :categoryId order by q.date desc")
	List<Question> getQuestionsForCategory(@Param("categoryId") Long categoriesIds,Pageable pageable);
	
	@Query("UPDATE Question q SET q.viewsNumber = viewsNumber + 1 WHERE q.id = :questionId")
	@Transactional
	@Modifying
	void updateNoOfViews(@Param("questionId") Long questionId);

	
}
