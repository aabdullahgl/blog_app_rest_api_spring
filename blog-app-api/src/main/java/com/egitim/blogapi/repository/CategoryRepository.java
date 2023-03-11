package com.egitim.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egitim.blogapi.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
