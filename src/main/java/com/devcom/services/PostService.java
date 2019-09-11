package com.devcom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcom.models.Question;
import com.devcom.repositories.PostRepository;
@Service
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
	
	public String votePost(int votes, long postId) {
		try {
			 postRepo.votePost(votes, postId);
			 return "Success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public String verifyAnswer(long answerId) {
		try {
			postRepo.verifyAnswer(answerId);
			return "Success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
