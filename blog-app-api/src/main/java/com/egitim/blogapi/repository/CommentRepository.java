package com.egitim.blogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.egitim.blogapi.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
