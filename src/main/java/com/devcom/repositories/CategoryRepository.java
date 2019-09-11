package com.devcom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.devcom.models.Category;

public interface CategoryRepository extends CrudRepository<Category,Long>,JpaRepository<Category, Long>{

}
