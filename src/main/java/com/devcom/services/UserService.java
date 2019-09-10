package com.devcom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcom.models.User;
import com.devcom.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	/**
	 * get user by email 
	 * 
	 * @param email
	 * @return user
	 */
	public User getUserByEmail(String email) {
		try {
			return userRepo.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * get profile data for user
	 * 
	 * @param email
	 * @return user
	 */
	public User getProfileData(String email) {
		try {
			User user = userRepo.findByEmail(email);
			user.setPosts(null);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * get user by id
	 * 
	 * @param id
	 * @return user
	 */
	public User getUserById(long id) {
		try {
			return userRepo.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * save new user
	 * 
	 * @param user
	 * @return saved user
	 */
	public String saveNewUser(User user) {
		try {
			userRepo.save(user);
			return "success";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * get user by email and password
	 * 
	 * @param email
	 * @param password
	 * @return user
	 */
	public User login(String email, String password) {
		try {
			return userRepo.findByEmailAndPassword(email, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
