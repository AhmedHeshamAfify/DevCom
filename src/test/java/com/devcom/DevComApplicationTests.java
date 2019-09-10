package com.devcom;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.devcom.controllers.UserController;
import com.devcom.models.User;
import com.devcom.models.UserType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DevComApplicationTests {

	@Autowired
	UserController userController;

	@Test
	public void contextLoads() {
	}

	@Test
	public void register() {
		 User user = new User();
		 user.setEmail("admin2@gmail.com");
		 user.setName("admin");
		 user.setPassword("admin");
		 user.setScore(0);
		 user.setType(UserType.admin);
		// User user = mock(User.class);
		// UserController c = mock(UserController.class);
		String response = userController.register(user);
		Assert.assertTrue(response.equals("success"));

	}

	@Test
	public void login() {

		Assert.assertEquals("success", userController.login("admin@gmail.com", "admin").get("message"));
		
	}

}
