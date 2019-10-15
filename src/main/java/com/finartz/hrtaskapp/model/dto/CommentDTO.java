package com.finartz.hrtaskapp.model.dto;

public class CommentDTO {
	
	private String message;
	
	public CommentDTO(String message) {
		this.message = message;
	}

	public CommentDTO() {
	}

	@Override
	public String toString() {
		return "CommentDTO [message=" + message + "]";
	}

	
	
	
	
}
