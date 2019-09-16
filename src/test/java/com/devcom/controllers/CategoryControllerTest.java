package com.devcom.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
	public void getAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		categories.add(category);
		when(categoryService.getAllCategories()).thenReturn(categories);
		Assert.assertEquals(categoryController.getAllCategory(),categories);
	}
}
