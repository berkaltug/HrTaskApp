package com.finartz.hrtaskapp.model.dto;

public class CommentDTO {
	
	private String message;
	
	public CommentDTO(String message) {
		this.message = message;
	}

	public CommentDTO() {
	}
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CommentDTO [message=" + message + "]";
	}
	
}
