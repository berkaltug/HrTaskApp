package com.finartz.hrtaskapp.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finartz.hrtaskapp.model.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer>{


}
