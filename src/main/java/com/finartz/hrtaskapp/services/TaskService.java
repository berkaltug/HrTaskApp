package com.finartz.hrtaskapp.services;

import com.finartz.hrtaskapp.model.entity.Comment;
import com.finartz.hrtaskapp.model.entity.Task;

import java.util.Optional;

public interface TaskService {
	
	Optional<Task> getTask(Integer id) ;
	Optional<Task> addTask(Task task, Integer userId,Integer processId);
	Optional<Task> updateTask(Task task,Integer taskId);
	Optional<Task> deleteTask(Integer id);
	Optional<Comment> commentTask(Comment comment,Integer taskId);

}
