package com.finartz.hrtaskapp.model.dto;

import java.util.LinkedList;
import java.util.List;

public class TaskDTO {
	
	private String title;
	private String body;
	private String status;
	private Integer priority;
	private List<CommentDTO> comments=new LinkedList<CommentDTO>();
	private String user;
	
	public TaskDTO() {
	}

	public TaskDTO(String title, String body, String status, Integer priority, List<CommentDTO> comments) {
		this.title = title;
		this.body = body;
		this.status = status;
		this.priority = priority;
		this.comments = comments;
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

	@Override
	public String toString() {
		return "TaskDTO [title=" + title + ", body=" + body + ", status=" + status + ", priority=" + priority
				+ ", comments=" + comments + "]";
	}

}
