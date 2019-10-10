package com.finartz.hrtaskapp.Services;

import com.finartz.hrtaskapp.Entity.Task;

public interface TaskService {
	
	Task getTask(Integer id);
	
	void addTask(Task task);
	
	void updateTask(Task task);

	void deleteTask(Integer id);
}
