package com.devcom.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.devcom.controllers.UserController;
import com.devcom.jwt.JwtTokenUtil;
import com.devcom.models.User;
import com.devcom.models.UserType;
import com.devcom.services.JwtUserDetailsServiceImpl;
import com.devcom.services.UserService;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class UserControllerTests {
	
	@InjectMocks
	UserController userController;

	@Mock
	private JwtUserDetailsServiceImpl userDetailsService;

	@Mock
	private JwtTokenUtil jwtTokenUtil;

	@Mock
	UserService userService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void login() {

		String email = "deccom@devcom.com";
		String password = "devcom";
		User u = new User();
		u.setEmail(email);
		u.setPassword(password);
		u.setType(UserType.user);

		when(userService.login(email, password)).thenReturn(u);
		UserDetails userDetails = mock(UserDetails.class);
		when(userDetailsService.loadUserByUsername(email)).thenReturn(userDetails);
		when(jwtTokenUtil.generateToken(userDetails)).thenReturn("genratedToken");
		when(jwtTokenUtil.getEmailFromToken("genratedToken")).thenReturn(email);

		when(userController.login(email, password).get("message")).thenReturn("success");
		Assert.assertEquals("success", userController.login(email, password).get("message"));
	}

	@Test
	public void register() {
		User user = mock(User.class);
		when(userService.saveNewUser(user)).thenReturn("success");
		String response = userController.register(user);
		Assert.assertTrue(response.equals("success"));
	}
	
	@Test
	public void getUserProfile(){
		String token = "token";
		String email = "devcom@devcom.com";
		User user = mock(User.class);
		when(jwtTokenUtil.getEmailFromToken("token")).thenReturn(email);
		when(userService.getProfileData(email)).thenReturn(user);
		Assert.assertEquals(userController.getUserProfile(token), user);
	}

	// @Test
	// public void register() {
	// User user = new User();
	// user.setEmail("admin2@gmail.com");
	// user.setName("admin");
	// user.setPassword("admin");
	// user.setScore(0);
	// user.setType(UserType.admin);
	// // User user = mock(User.class);
	// // UserController c = mock(UserController.class);
	// String response = userController.register(user);
	// Assert.assertTrue(response.equals("success"));
	//
	// }
	//
	// @Test
	// public void login() {
	//
	// Assert.assertEquals("success", userController.login("admin@gmail.com",
	// "admin").get("message"));
	//
	// }

}
