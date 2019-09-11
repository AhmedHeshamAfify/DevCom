package com.devcom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.devcom.models.Answer;
import com.devcom.models.Post;
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
	

	public Page<Post> getQuestionWithLimit(int limit){
		return postRepo.findAll(PageRequest.of(0, limit));
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
