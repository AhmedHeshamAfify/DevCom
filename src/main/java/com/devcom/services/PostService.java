package com.devcom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.devcom.models.Answer;
import com.devcom.models.Post;
import com.devcom.models.Question;
import com.devcom.repositories.PostRepository;
import com.devcom.repositories.QuestionRepository;
import com.devcom.repositories.FullTextSearch;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepo;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private FullTextSearch fullTextSearch;
	
	 @Autowired
	 private Environment environment;

	public List<Question> getUserQuestions(long userId) {
		try {
			return questionRepository.findAllByUserId(userId);
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

	public Page<Question> getQuestionWithPagination(int limit) {
		try {
			return questionRepository.findAll(PageRequest.of(0, limit));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Question> searchByCategory(long categoryId, int limit) {
		try {
			return questionRepository.getQuestionsForCategory(categoryId, PageRequest.of(0, limit));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Optional<Question> getQuestionByQuestionId(long questionId) {
		try {
			questionRepository.updateNoOfViews(questionId);
			return questionRepository.findById(questionId);
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

	public String postQuestion(Question q) {
		try {
			postRepo.save(q);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public Post postAnswer(Answer a) {
		try {
			return postRepo.save(a);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public String removePost(long userId, long postId) {
		String msg = "";
		try {
			Post p = postRepo.findById(postId);
			if (p == null) {
				msg = "somthing went wrong, post not exist";
			} else if (p.getUser().getId() == userId) {
				postRepo.delete(p);
				msg = "success";
			} else {
				msg = "you don't have permission to delete this post";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		return msg;
	}

	// public List<Question> getQuestionForCategories(List<Long> categoriesIds){
	// try{
	// return questionRepository.getQuestionsForCategories(categoriesIds);
	// }catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// }
	//
	// public List<Question> getQuestionsForCategoriesToSearch(List<Long>
	// categoriesIds){
	// try{
	// return
	// questionRepository.getQuestionsForCategoriesToSearch(categoriesIds);
	// }catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// }

	public List<Question> searchByKeyword(String keyword) {
		try {
			return fullTextSearch.searchByKeyword(keyword);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	public List<Question> callSearchApi(List<Question> questions){
//		String url =  environment.getRequiredProperty("spring.datasource.url");
//	}

}
