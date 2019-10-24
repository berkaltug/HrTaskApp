package com.finartz.hrtaskapp.services.impl;

import com.finartz.hrtaskapp.db.repository.ProcessRepository;
import com.finartz.hrtaskapp.db.repository.TaskRepository;
import com.finartz.hrtaskapp.model.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finartz.hrtaskapp.db.repository.CommentRepository;
import com.finartz.hrtaskapp.model.entity.Comment;
import com.finartz.hrtaskapp.model.entity.Task;
import com.finartz.hrtaskapp.services.TaskService;
import com.finartz.hrtaskapp.services.UserService;

import java.util.Date;
import java.util.Optional;


@Service
public class TaskServiceImpl implements TaskService{

	Logger logger=LoggerFactory.getLogger(TaskServiceImpl.class);
	
	private UserService userService; //findLoggedInUsername fonk.nuna ihtiyacımız var
	private TaskRepository taskRepository;
	private CommentRepository commentRepository;
	private ProcessRepository processRepository;

	@Autowired
	public TaskServiceImpl(UserService userService, TaskRepository taskRepository, CommentRepository commentRepository, ProcessRepository processRepository) {
		this.userService = userService;
		this.taskRepository = taskRepository;
		this.commentRepository = commentRepository;
		this.processRepository = processRepository;
	}

	@Override
	public Optional<Task> getTask(Integer id) {
		return taskRepository.findById(id);
	}

	@Override
	public Optional<Task> addTask(Task task, Integer userId,Integer processId){
		userService.getUser(userId).ifPresent(task::setUser);
		processRepository.findById(processId).ifPresent(task::setProcess);
		task.setCreationDate(new Date());
		if(task.getUser()!=null) {
			return Optional.ofNullable(taskRepository.save(task));
		}else {
			return Optional.empty();
		}	
	}
	
	//Persistence Exceptionları ayrı yakalamaya gerek var mı bak !
	@Override
	public Optional<Task> updateTask(Task task,Integer taskId) {
			Optional<Task> optionalTask= taskRepository.findById(taskId);
			if(optionalTask.isPresent()) {
				//DTO'nun içinde id olmadığı için id set ediyoruz ki düzgün güncellesin
				task.setTaskId(optionalTask.get().getTaskId());
				task.setUpdateDate(new Date());
				//done yapıldıysa kapama tarihi ata
				if (task.getStatus() == TaskStatus.DONE.get())
					task.setCloseDate(new Date());
				//kendi taskını mı güncelliyor ?
				if (userService.findLoggedInUsername().equals(task.getUser().getUsername()) || userService.isAdmin())
					return Optional.ofNullable(taskRepository.save(task));
			}
			return Optional.empty();
	}

	@Override
	public Optional<Task> deleteTask(Integer id){
		Optional<Task> optionalProcess=taskRepository.findById(id);
		optionalProcess.ifPresent(t->taskRepository.delete(t));
		return optionalProcess;
	}

	@Override
	public Optional<Comment> commentTask(Comment comment, Integer taskId) {
		taskRepository.findById(taskId).ifPresent(comment::setTask);
		comment.setSender(userService.findLoggedInUsername());
		return Optional.ofNullable(commentRepository.save(comment));
	}
}
