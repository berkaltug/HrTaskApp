package com.finartz.hrtaskapp.model.dto;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CommentDTO)) return false;
		CommentDTO that = (CommentDTO) o;
		return message.equals(that.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(message);
	}
}
