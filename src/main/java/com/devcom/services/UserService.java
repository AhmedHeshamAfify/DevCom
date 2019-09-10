package com.devcom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcom.models.User;
import com.devcom.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public User getUserByEmail(String email) {
		try {
			return userRepo.findByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
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
	
	public User getUserById(long id) {
		try {
			return userRepo.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String saveNewUser(User user) {
		try {
			userRepo.save(user);
			return "success";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public User login(String email, String password) {
		try {
			return userRepo.findByEmailAndPassword(email, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
