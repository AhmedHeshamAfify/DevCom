package com.devcom.controllers;

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

import com.devcom.jwt.JwtTokenUtil;
import com.devcom.models.Answer;
import com.devcom.models.Question;
import com.devcom.models.User;
import com.devcom.services.PostService;
import com.devcom.services.UserService;


public class PostControllerTests {

	@InjectMocks
	PostController postController;
	
	@Mock
	PostService postService;
	
	@Mock
	UserService userService; 
	
	@Mock
	JwtTokenUtil jwtTokenUtil;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getUserQuestions(){
		String token = "token";
		String email = "devcom@devcom.com";
		
		when(jwtTokenUtil.getEmailFromToken("token")).thenReturn(email);
		User user = mock(User.class);
		
		Question q1 = mock(Question.class);
		Question q2 = mock(Question.class);
		Question q3 = mock(Question.class);
		List<Question> questions =new ArrayList<Question>();
		questions.add(q1);
		questions.add(q2);
		questions.add(q3);
		when(userService.getUserByEmail(email)).thenReturn(user);
		when(postService.getUserQuestions(user.getId())).thenReturn(questions);
		
		Assert.assertEquals(postController.getUserQuestions(token), questions);
	}	
	
	@Test
	public void getUserAnswers(){
		String token = "token";
		String email = "devcom@devcom.com";
		
		when(jwtTokenUtil.getEmailFromToken("token")).thenReturn(email);
		User user = mock(User.class);
		
		Answer a1 = mock(Answer.class);
		Answer a2 = mock(Answer.class);
		Answer a3 = mock(Answer.class);
		List<Answer> answers =new ArrayList<Answer>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		
		when(userService.getUserByEmail(email)).thenReturn(user);
		when(postService.getUserAnswers(user.getId())).thenReturn(answers);
		
		Assert.assertEquals(postController.getUserAnswers(token), answers);
	}	
	
	@Test
	public void votePost() {
		String token = "token";
		String email = "test@devcom.com";
		
		when(jwtTokenUtil.getEmailFromToken(token)).thenReturn(email);
		
		User user = mock(User.class);
		
		when(userService.getUserByEmail(email)).thenReturn(user);
		when(postService.votePost(1, 12)).thenReturn("Success");
		Assert.assertEquals(postController.votePost(token, 1, 12), "Success");
	}
	
}
