package com.devcom.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devcom.models.Category;
import com.devcom.services.CategoryService;

@RestController
@CrossOrigin
public class CategoryController {
	@Autowired
	CategoryService  categoryService;

	@RequestMapping(value = "/getAllCategoriesWithQuestionsNo", method = RequestMethod.POST)
	public Map<Category,Integer> getAllCategoriesWithQuestionsNo() {
		try {
			return categoryService.getAllCategoriesWithQuestionsNo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/getAllCategories", method = RequestMethod.GET)
	public List<Category> getAllCategory(){
		List<Category> response = categoryService.getAllCategories();
		System.out.println(" get all categories: "+ response.size());
		return response;
	}
}
