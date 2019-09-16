package com.devcom.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.devcom.controllers.CategoryController;
import com.devcom.models.Category;
import com.devcom.repositories.CategoryRepository;

public class CategoryServiceTest {
	
	@Mock
	CategoryRepository categoryRepository;
	
	@InjectMocks
	CategoryService categoryService;
	
	@Mock
	Category category;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllCategories(){
		List<Category> categories = new ArrayList<Category>();
		categories.add(category);
		when(categoryRepository.findAll()).thenReturn(categories);
		Assert.assertEquals(categoryService.getAllCategories(),categories);
	}
}
