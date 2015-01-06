package com.bdcyclists.bdcbook.dto;

public class RegistrationForm {
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String passwordConfirmed;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmed() {
		return passwordConfirmed;
	}

	public void setPasswordConfirmed(String passwordConfirmed) {
		this.passwordConfirmed = passwordConfirmed;
	}

	@Override
	public String toString() {
		return "RegistrationForm [email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", password=" + password
				+ ", passwordConfirmed=" + passwordConfirmed + "]";
	}

	
	
	
}
