package com.devcom.controllers;

//import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.jwt.JwtTokenUtil;
import com.devcom.models.User;
import com.devcom.services.UserService;

@RestController
@CrossOrigin
@RequestMapping
public class UserController {

	@Autowired
	private JwtTokenUtil jwtUserTokenUtil;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "runnig...";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestPart User user) {
		return userService.saveNewUser(user);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
		User user =  userService.login(email, password);
		String token = "";
		
		return token;
		

	}

}
