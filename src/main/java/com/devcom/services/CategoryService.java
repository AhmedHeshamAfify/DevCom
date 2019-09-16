package com.devcom.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcom.models.Category;
import com.devcom.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	
	public List<Category> getAllCategories(){
		try{
			return categoryRepository.findAll();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
