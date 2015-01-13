package com.bdcyclists.bdcbook.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

@Entity
public class User extends BaseEntity<Long> implements UserDetails {

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

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<UserRole> userRoles = new HashSet<>();
	
	@Column(nullable = false)
	private boolean enabled;
	
	@Column(nullable = false)
	private boolean locked;
	
	@Transient
	private List<SimpleGrantedAuthority> simpleGrantedAuthorityList;
	
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
	
	private boolean isLocked() {
		return locked;
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

        public Builder role(Role role) {
            user.userRoles.add(new UserRole(this.user, role));
            return this;
        }

        public Builder enabled(boolean enabled) {
            user.enabled = enabled;
            return this;
        }

        public Builder locked(boolean locked) {
            user.locked = locked;
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

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	       if (simpleGrantedAuthorityList == null && !CollectionUtils.isEmpty(getUserRoles())) {
	            simpleGrantedAuthorityList = new ArrayList<>();

	            simpleGrantedAuthorityList
	                    .addAll(getUserRoles()
	                    		.stream()
	                            .map(userRole -> new SimpleGrantedAuthority(userRole
	                            	.getRole().name())).collect(Collectors.toList()));
	        }

	        return simpleGrantedAuthorityList;
	}

	@Override
	public String getUsername() {
		return getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;	// Not implemented
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isLocked();
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;	// Not Implemented
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
