package com.bdcyclists.bdcbook.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.bdcyclists.bdcbook.validation.PasswordNotEqual;

@PasswordNotEqual(passwordFieldName = "password", passwordConfirmedFieldName = "passwordConfirmed")
public class RegistrationForm {

	public static final String FIELD_NAME_EMAIL = "email";
	
	@Email
	@NotEmpty
	@Size(max = 100)
	private String email;
	
	@NotEmpty
	@Size(max = 100)
	private String firstName;
	
	@NotEmpty
	@Size(max = 100)
	private String lastName;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
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
