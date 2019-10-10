package com.finartz.hrtaskapp.Services;

import com.finartz.hrtaskapp.Entity.Task;

public interface TaskService {
	
	Task getTask(Integer id);
	
	Task addTask(Task task);
	
	Task updateTask(Task task);

	int deleteTask(Integer id);
}
