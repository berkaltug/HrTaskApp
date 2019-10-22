package com.finartz.hrtaskapp.model.dto;

import java.util.Objects;

public class CommentDto {
	
	private String message;
	
	public CommentDto(String message) {
		this.message = message;
	}

	public CommentDto() {
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
		if (!(o instanceof CommentDto)) return false;
		CommentDto that = (CommentDto) o;
		return message.equals(that.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(message);
	}
}
