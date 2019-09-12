package com.devcom.controllers;

import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.devcom.models.Category;
import com.devcom.services.CategoryService;

public class CategoryControllerTest {

	@Mock
	CategoryService categoryService;
	
	@InjectMocks
	CategoryController categoryController;
	
	@Mock
	Category category;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllCategoriesWithQuestionsNo() {
		Map<Category, Integer> categoriesAndQuestionsNo = new HashMap<Category, Integer>();
		
		categoriesAndQuestionsNo.put(category, 1);
		when(categoryService.getAllCategoriesWithQuestionsNo()).thenReturn(categoriesAndQuestionsNo);
		Assert.assertEquals(categoryController.getAllCategoriesWithQuestionsNo(),categoriesAndQuestionsNo);
	}
}
