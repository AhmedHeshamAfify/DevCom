 package com.devcom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
 import org.springframework.data.repository.CrudRepository;
 import org.springframework.data.repository.query.Param;

import com.devcom.models.Answer;
import com.devcom.models.Post;
import com.devcom.models.Question;

 
public interface PostRepository extends CrudRepository<Post,Long>{

	Post findById(long id);
	Post save(Post post);
	
	@Query("from Question where user.id =:userId")
	List<Question> getUserQuestions(@Param("userId") long userId);

	@Query("from Answer where user.id =:userId")
	List<Answer> getUserAnswers(@Param("userId") long userId);

}
