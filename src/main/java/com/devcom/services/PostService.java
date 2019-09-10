package com.devcom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.devcom.models.Question;
import com.devcom.repositories.PostRepository;

public class PostService {

	@Autowired
	private PostRepository postRepo;

	public List<Question> getUserQuestions(long userId) {
		try {
			return postRepo.getPostsForUser(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
