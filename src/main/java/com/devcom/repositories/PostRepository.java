 package com.devcom.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
 import org.springframework.data.repository.CrudRepository;
 import org.springframework.data.repository.query.Param;

import com.devcom.models.Answer;
import com.devcom.models.Post;
import com.devcom.models.Question;


public interface PostRepository extends CrudRepository<Post,Long>{

	Post findById(long id);
	Post save(Post post);
	
<<<<<<< HEAD
	@Query("from Post where user.id =:userId")
	List<Question> getPostsForUser(@Param("userId") long userId);
	
	@Transactional
	@Modifying
	@Query("UPDATE Post p SET p.votesNumber =:votes where p.id =:postId")
	void votePost(@Param("votes") int votes, @Param("postId") long postId);
	
	@Transactional
	@Modifying
	@Query("UPDATE Answer a set a.verified = true where a.id =:postId")
	void verifyAnswer(@Param("postId") long postId);
=======
	@Query("from Question where user.id =:userId")
	List<Question> getUserQuestions(@Param("userId") long userId);

	@Query("from Answer where user.id =:userId")
	List<Answer> getUserAnswers(@Param("userId") long userId);

>>>>>>> e2500279fa87b73d9f5e483318a166afeba3ea07
}
