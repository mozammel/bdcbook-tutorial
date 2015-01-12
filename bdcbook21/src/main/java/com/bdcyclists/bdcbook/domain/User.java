package com.bdcyclists.bdcbook.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User extends BaseEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "email", length = 100, nullable = false, unique = true)
	private String email;

	@Column(name = "first_name", length = 100, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 100, nullable = false)
	private String lastName;

	@Column(name = "password", length = 255)
	private String password;

	public User() {

	}

	public User(Long id, String email, String firstName, String lastName,
			String password) {
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName="
				+ firstName + ", lastName=" + lastName + ", password="
				+ password + "]";
	}

	public static Builder getBuilder() {
		return new Builder();
	}

	public static class Builder {

		private User user;

		public Builder() {
			user = new User();
		}

		public Builder email(String email) {
			user.email = email;
			return this;
		}

		public Builder firstName(String firstName) {
			user.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			user.lastName = lastName;
			return this;
		}

		public Builder password(String password) {
			user.password = password;
			return this;
		}

		public User build() {
			return user;
		}

		public Builder id(Long id) {
			user.id = id;
			return this;
		}
	}

}
