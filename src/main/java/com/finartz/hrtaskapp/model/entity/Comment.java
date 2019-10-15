package com.finartz.hrtaskapp.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer commentId;
	
	private String message;
	
	@ManyToOne
	@JoinColumn(name="task_id")
	@JsonIgnoreProperties("comments")
	private Task task;

	public Comment() {
	}

	public Comment(Integer commentId, String message,Task task) {
		this.commentId = commentId;
		this.message = message;
		this.task=task;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "Comments [commentId=" + commentId + ", message=" + message + "]";
	}
	
	
}
