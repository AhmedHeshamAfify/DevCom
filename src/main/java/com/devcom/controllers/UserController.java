package com.devcom.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/userController")
public class UserController {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(){
		return "runnig...";
	}
}
