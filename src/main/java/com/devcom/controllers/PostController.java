package com.devcom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.jwt.JwtTokenUtil;
import com.devcom.models.Answer;
import com.devcom.models.Question;
import com.devcom.models.User;
import com.devcom.services.JwtUserDetailsServiceImpl;
import com.devcom.services.PostService;
import com.devcom.services.UserService;

@RestController
@CrossOrigin
public class PostController {

	@Autowired
	private JwtUserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/getUserQuestions", method = RequestMethod.POST)
	public List<Question> getUserQuestions(@RequestParam("token") String token) {
		List<Question> questions = null;
		try {
			String email = jwtTokenUtil.getEmailFromToken(token);
			User user = userService.getUserByEmail(email);
			if (user != null) {
				questions = postService.getUserQuestions(user.getId());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return questions;
	}
<<<<<<< HEAD
	
	@RequestMapping(value = "/votePost", method = RequestMethod.POST)
	public String votePost(@RequestParam("token") String token, @RequestParam("votes") int votes, @RequestParam("postId") long id) {
		String result = "";
		try {
			String email = jwtTokenUtil.getEmailFromToken(token);
			User user = userService.getUserByEmail(email);
			if(user != null) {
				result = postService.votePost(votes, id);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping(value = "/verifyAnswer", method = RequestMethod.POST)
	public String verifyAnswer(@RequestParam("token") String token, @RequestParam("answerId") long id) {
		String result = "";
		try {
			String email = jwtTokenUtil.getEmailFromToken(token);
			User user = userService.getUserByEmail(email);
			if(user != null) {
				result = postService.verifyAnswer(id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
=======

	@RequestMapping(value = "/getUserAnswers", method = RequestMethod.POST)
	public List<Answer> getUserAnswers(@RequestParam("token") String token) {
		List<Answer> answers = null;
		try {
			String email = jwtTokenUtil.getEmailFromToken(token);
			User user = userService.getUserByEmail(email);
			if (user != null) {
				answers = postService.getUserAnswers(user.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answers;
>>>>>>> e2500279fa87b73d9f5e483318a166afeba3ea07
	}
}
