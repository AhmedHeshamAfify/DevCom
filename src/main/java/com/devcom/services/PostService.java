package com.devcom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcom.models.Answer;
import com.devcom.models.Question;
import com.devcom.repositories.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepo;

	public List<Question> getUserQuestions(long userId) {
		try {
			return postRepo.getUserQuestions(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Answer> getUserAnswers(long userId) {
		try {
			return postRepo.getUserAnswers(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
