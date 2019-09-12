package com.devcom.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.devcom.models.Answer;
import com.devcom.models.Question;
import com.devcom.repositories.PostRepository;
import com.devcom.repositories.QuestionRepository;

public class PostServiceTests {

	@InjectMocks
	PostService postService;
	
	@Mock
	PostRepository postRepo;
	
	@Mock
	QuestionRepository questionRepository;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getUserQuestions(){
		Question q1 = mock(Question.class);
		Question q2 = mock(Question.class);
		List<Question> questions =new ArrayList<Question>();
		questions.add(q1);
		questions.add(q2);
		
		when(questionRepository.findAllByUserId(1)).thenReturn(questions);
		Assert.assertEquals(postService.getUserQuestions(1), questions);
	}
	
	@Test
	public void getUserAnswers(){
		Answer q1 = mock(Answer.class);
		Answer q2 = mock(Answer.class);
		List<Answer> answers =new ArrayList<Answer>();
		answers .add(q1);
		answers .add(q2);
		
		when(postRepo.getUserAnswers(1)).thenReturn(answers );
		Assert.assertEquals(postService.getUserAnswers(1), answers );
	}
	
	@Test
	public void getQuestionByQuestionId() {
		Question question = mock(Question.class);
		when(questionRepository.findById((long) 1)).thenReturn(Optional.of(question));
		Assert.assertEquals(postService.getQuestionByQuestionId(1).get() , question);
	}
	
	@Test
	public void getQuestionWithPagination() {
		List<Question> questions = new ArrayList<>();
		Page<Question> pagedQuestions = new PageImpl(questions);
		when(questionRepository.findAll(pagedQuestions.getPageable())).thenReturn(pagedQuestions);
	}
}
