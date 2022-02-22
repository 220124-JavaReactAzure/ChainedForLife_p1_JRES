package com.revature.wedding_planner.exceptions;

public class InvalidRequestException extends RuntimeException{

	public InvalidRequestException(String message) {
		super(message);
	}
}
