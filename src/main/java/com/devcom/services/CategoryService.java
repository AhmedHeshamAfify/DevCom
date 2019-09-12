package com.devcom.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcom.models.Category;
import com.devcom.models.Post;
import com.devcom.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public Map<Category,Integer> getAllCategoriesWithQuestionsNo(){
		Map<Category,Integer> categoriesAndQuestionsNo = new HashMap<Category, Integer>();
		try {
			List<Category> categories = categoryRepository.findAll();
			for (Category category : categories) {
				if (category != null && category.getQuestions() != null) {
					categoriesAndQuestionsNo.put(category,category.getQuestions().size());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return categoriesAndQuestionsNo;
	}
}
