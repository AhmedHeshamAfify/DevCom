package com.devcom.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.jwt.JwtTokenUtil;
import com.devcom.models.User;
import com.devcom.services.JwtUserDetailsServiceImpl;
import com.devcom.services.UserService;

@RestController
@CrossOrigin
@RequestMapping
public class UserController {

	@Autowired
	private JwtUserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "runnig...";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestBody User user) {

		return userService.saveNewUser(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, String> login(@RequestParam("email") String email, @RequestParam("password") String password) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			User user = userService.login(email, password);

			if (user != null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
				String token = jwtTokenUtil.generateToken(userDetails);

				response.put("message", "success");
				response.put("token", token);
				response.put("type", user.getType().toString());
			} else {
				response.put("message", "user name or password is incorrect");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", e.getLocalizedMessage());
		}
		return response;
	}

	@RequestMapping(value = "/getUserProfile", method = RequestMethod.POST)
	public User getUserProfile(@RequestParam("token") String token) {
		User user = null;
		try {
			String email = jwtTokenUtil.getEmailFromToken(token);
			user = userService.getProfileData(email);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return user;
	}

	@RequestMapping(value = "/updateUserData", method = RequestMethod.POST)
	public User updateUserData(@RequestHeader(name = "Authorization") String token, @RequestBody User user) {
		User response = null;
		System.out.println(">>> update user Data <<< ");
		System.out.println("token: "+token);
		System.out.println("user: "+user.getId());
		try {
//			String tokenEmail = jwtTokenUtil.getEmailFromToken(token);
//			User checkedUser = userService.getUserByEmail(tokenEmail);
//			if (checkedUser != null) {
//				response = userService.updateUser(user);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
