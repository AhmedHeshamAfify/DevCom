package com.devcom.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.devcom.models.Post;
import com.devcom.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
	User findByEmailAndPassword(String email, String password);
	User findById(long id);
	User save(User user);
	

}
