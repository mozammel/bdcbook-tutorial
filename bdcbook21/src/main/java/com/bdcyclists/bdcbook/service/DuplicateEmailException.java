package com.bdcyclists.bdcbook.service;

public class DuplicateEmailException extends Exception {
	public DuplicateEmailException(String message) {
		super(message);
	}
}
