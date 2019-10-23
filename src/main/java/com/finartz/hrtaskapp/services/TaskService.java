package com.finartz.hrtaskapp.services;

import com.finartz.hrtaskapp.model.entity.Comment;
import com.finartz.hrtaskapp.model.entity.Task;

public interface TaskService {
	
	Task getTask(Integer id) throws Exception;
	Task addTask(Task task, Integer userId) throws Exception;
	Task updateTask(Task task,Integer taskId) throws Exception;
	void deleteTask(Integer id)throws Exception;
	Comment commentTask(Comment comment,Integer taskId) throws Exception;

}
