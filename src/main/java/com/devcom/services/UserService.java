package com.devcom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcom.dao.UserDao;
import com.devcom.models.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User getUserByEmail(String email) {
		try {
			return userDao.getUserByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String register(User user) {
		try {
			userDao.register(user);
			return "success";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public User login(String email, String password) {
		try {
			return userDao.login(email, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
