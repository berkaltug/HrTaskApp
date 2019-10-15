package com.finartz.hrtaskapp.services;

import com.finartz.hrtaskapp.model.entity.Task;

public interface TaskService {
	
	Task getTask(Integer id);
	
	Task addTask(Task task);
	
	Task updateTask(Task task);

	int deleteTask(Integer id);
}
