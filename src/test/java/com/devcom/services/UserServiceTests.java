package com.devcom.services;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.devcom.models.User;
import com.devcom.repositories.PostRepository;
import com.devcom.repositories.UserRepository;


public class UserServiceTests {

	@InjectMocks
	UserService userService;
	
	@Mock
	UserRepository userRepo;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getUserByEmail(){
		User user = mock(User.class);
		when(userRepo.findByEmail("email")).thenReturn(user);
		Assert.assertEquals(userService.getUserByEmail("email"), user);
	}
	
	@Test
	public void getProfileData(){
		User user = mock(User.class);
		when(userRepo.findByEmail("email")).thenReturn(user);
		Assert.assertEquals(userService.getUserByEmail("email"), user);
	}
	
	@Test
	public void getUserById(){
		User user = mock(User.class);
		when(userRepo.findById(101)).thenReturn(user);
		Assert.assertEquals(userService.getUserById(101), user);
	}
	
	@Test
	public void saveNewUser(){
		User user = mock(User.class);
		when(userRepo.save(user)).thenReturn(user);
		Assert.assertEquals(userService.saveNewUser(user), "success");
	}
	
	@Test
	public void login(){
		User user = mock(User.class);
		when(userRepo.findByEmailAndPassword("userName","password")).thenReturn(user);
		Assert.assertEquals(userService.login("userName","password"), user);
	}
	
	@Test
	public void updateUserData(){
		User user = mock(User.class);
		when(userRepo.save(user)).thenReturn(user);
		Assert.assertEquals(userService.updateUser(user), user);
	}
}
