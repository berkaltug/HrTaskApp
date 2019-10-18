package com.finartz.hrtaskapp.services;

import com.finartz.hrtaskapp.model.entity.Comment;
import com.finartz.hrtaskapp.model.entity.Task;

public interface TaskService {
	
	Task getTask(Integer id);
	
	Task addTask(Task task, Integer userId);
	
	Task updateTask(Task task,Integer taskId);

	int deleteTask(Integer id);
	
	int commentTask(Comment comment,Integer taskId);
}
