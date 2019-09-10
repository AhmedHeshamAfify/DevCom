package com.devcom.repositories;

import org.springframework.data.repository.CrudRepository;
import com.devcom.models.User;

public interface UserRepository extends CrudRepository<User, Long> {

	/**
	 * get user by email
	 * 
	 * @param email
	 * @return user
	 */
	User findByEmail(String email);

	/**
	 * get user by email and password
	 * 
	 * @param email
	 * @param password
	 * @return user
	 */

	User findByEmailAndPassword(String email, String password);

	/**
	 * get user by id
	 * 
	 * @param id
	 * @return user
	 */
	User findById(long id);

	/**
	 * save new user
	 * 
	 * @param user
	 * @return user
	 */
	User save(User user);

}
