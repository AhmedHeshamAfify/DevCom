package com.devcom.controllers;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	public String register(@RequestPart User user) {
		System.out.println("register");
		return userService.register(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, String> login(@RequestParam("email") String email, @RequestParam("password") String password) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			User user = userService.login(email, password);

			if (user != null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
				/**
				 * to generate token
				 */
				String token = jwtTokenUtil.generateToken(userDetails);

				String generatedusername = jwtTokenUtil.getEmailFromToken(token);

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

}
