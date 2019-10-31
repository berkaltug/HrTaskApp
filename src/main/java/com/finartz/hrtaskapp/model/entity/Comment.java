package com.finartz.hrtaskapp.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer commentId;
	
	private String message;
	
	
	private String senderUsername;
	
	@ManyToOne
	@JoinColumn(name="task_id")
	private Task task;

	public Comment() {
	}

	public Comment(Integer commentId, String message, String senderUsername, Task task) {
		this.commentId = commentId;
		this.message = message;
		this.senderUsername = senderUsername;
		this.task = task;
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

	public String getSender() {
		return senderUsername;
	}

	public void setSender(String sender) {
		this.senderUsername = sender;
	}

	@Override
	public String toString() {
		return "Comment{" +
				"commentId=" + commentId +
				", message='" + message + '\'' +
				", senderUsername='" + senderUsername + '\'' +
				", task=" + task +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Comment)) return false;
		Comment comment = (Comment) o;
		return commentId.equals(comment.commentId) &&
				message.equals(comment.message) &&
				senderUsername.equals(comment.senderUsername) &&
				task.equals(comment.task);
	}

	@Override
	public int hashCode() {
		return Objects.hash(commentId, message, senderUsername, task);
	}
}
