package com.devcom.dao;

import com.devcom.models.User;

public interface UserDao {
	
	
	public User getUserByEmail(String email);

	public User saveNewUser(User user);

	public User login(String email, String password);

}
