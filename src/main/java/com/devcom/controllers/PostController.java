package com.devcom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.jwt.JwtTokenUtil;
import com.devcom.models.Answer;
import com.devcom.models.Post;
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

	@RequestMapping(value = "/votePost", method = RequestMethod.POST)
	public String votePost(@RequestHeader(name = "Authorization") String token, @RequestParam("votes") int votes,
			@RequestParam("postId") long id) {
		String result = "";
		try {
			String email = jwtTokenUtil.getEmailFromToken(token);
			User user = userService.getUserByEmail(email);
			if (user != null) {
				result = postService.votePost(votes, id);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/verifyAnswer", method = RequestMethod.POST)
	public String verifyAnswer(@RequestHeader(name = "Authorization") String token, @RequestParam("answerId") long id) {
		String result = "";
		try {
			String email = jwtTokenUtil.getEmailFromToken(token);
			User user = userService.getUserByEmail(email);
			if (user != null) {
				result = postService.verifyAnswer(id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	@RequestMapping(value = "/getUserAnswers", method = RequestMethod.POST)
	public List<Answer> getUserAnswers(@RequestHeader(name = "Authorization") String token) {
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
	}

	@RequestMapping(value = "/getQuestionWithPagination", method = RequestMethod.POST)
	public List<Question> getQuestionWithPagination(@RequestParam("limit") int limit) {
		Page<Question> questions = null;
		try {
			questions = postService.getQuestionWithPagination(limit);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questions.getContent();
	}

	@RequestMapping(value = "/getQuestionByQuestionId", method = RequestMethod.POST)
	public Question getQuestionByQuestionId(@RequestParam("questionId") long questionId) {
		try {
			return postService.getQuestionByQuestionId(questionId).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping(value = "/postQuestion", method = RequestMethod.POST)
	public String postQuestion(@RequestHeader(name = "Authorization") String token, @RequestBody Question q) {
		String result = "";
		try {
			String email = jwtTokenUtil.getEmailFromToken(token);
			User user = userService.getUserByEmail(email);
			if (user != null) {
				q.setUser(user);
				result = postService.postQuestion(q);
			}else{
				result = "please login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}

	@RequestMapping(value = "/postAnswer", method = RequestMethod.POST)
	public String postAnswer(@RequestHeader(name = "Authorization") String token, @RequestBody Answer a) {
		String result = "";
		try {
			String email = jwtTokenUtil.getEmailFromToken(token);
			User user = userService.getUserByEmail(email);
			if (user != null){
				a.setUser(user);
				result = postService.postAnswer(a);
			}else{
				result = "please login";
			}

		} catch (Exception e) {
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}

	@RequestMapping(value = "/removePost", method = RequestMethod.POST)
	public String removePost(@RequestHeader(name = "Authorization") String token, @RequestParam("postId") long postId) {
		String response = "";
		try {
			String email = jwtTokenUtil.getEmailFromToken(token);
			User user = userService.getUserByEmail(email);
			if (user != null) {
				response = postService.removePost(user.getId(), postId);
			} else {
				response = "please login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = e.getMessage();
		}
		return response;
	}
	
	@RequestMapping(value = "/getQuestionsForCategories", method = RequestMethod.POST)
	public List<Question> getQuestionsForCategories( @RequestBody List<Long> categoriesIds) {
		return postService.getQuestionForCategories(categoriesIds);
	}
	
}
