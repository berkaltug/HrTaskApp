package com.finartz.hrtaskapp.model.dto;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TaskDTO {
	
	private String title;
	private String body;
	private String status;
	private Integer priority;
	private List<CommentDTO> comments=new LinkedList<CommentDTO>();
	private Integer userId;
	
	public TaskDTO() {
	}

	public TaskDTO(String title, String body, String status, Integer priority, List<CommentDTO> comments,
			Integer userId) {
		this.title = title;
		this.body = body;
		this.status = status;
		this.priority = priority;
		this.comments = comments;
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "TaskDTO [title=" + title + ", body=" + body + ", status=" + status + ", priority=" + priority
				+ ", comments=" + comments + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TaskDTO)) return false;
		TaskDTO taskDTO = (TaskDTO) o;
		return Objects.equals(title, taskDTO.title) &&
				Objects.equals(body, taskDTO.body) &&
				Objects.equals(status, taskDTO.status) &&
				Objects.equals(priority, taskDTO.priority) &&
				Objects.equals(comments, taskDTO.comments) &&
				Objects.equals(userId, taskDTO.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, body, status, priority, comments, userId);
	}
}
