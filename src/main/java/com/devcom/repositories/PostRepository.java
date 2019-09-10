 package com.devcom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
 import org.springframework.data.repository.CrudRepository;
 import org.springframework.data.repository.query.Param;

import com.devcom.models.Post;
import com.devcom.models.Question;

 
public interface PostRepository extends CrudRepository<Post,Long>{

	Post findById(long id);
	Post save(Post post);
	
	@Query("from Post where user.id =:userId")
	List<Question> getPostsForUser(@Param("userId") long userId);
}
