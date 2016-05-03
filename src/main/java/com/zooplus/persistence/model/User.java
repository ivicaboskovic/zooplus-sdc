package com.zooplus.persistence.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "USERS", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email", name = User.USER_EMAIL_UC)
})
@NamedQueries({ 
	@NamedQuery(name = User.FIND_USER_BY_EMAIL, query = "SELECT u FROM User u WHERE u.email = :email"),

})
public class User extends BaseEntity<Long> {
			
	public static final String USER_EMAIL_UC = "USER_EMAIL_UC";

	/**
	 * 
	 */
	private static final long serialVersionUID = -1437000511403313604L;
	
	
	public enum Role {
		ROLE_USER, ROLE_ADMINISTRATOR
	}

		
	public static final String FIND_USER_BY_EMAIL = "FIND_USER_BY_EMAIL";
	
	@Column(length = 255)
	@NotEmpty
	private String firstName;
	
	@NotEmpty
	@Column(length = 255)
	private String lastName;
	
	@NotEmpty
	@Column(length = 255)
	private String password;

	@NotEmpty 
	@Email
	@Column(length = 255)
	private String email;

	
	@DateTimeFormat(pattern="dd-MM-yyyy")
    @NotNull @Past
    private Date birthday;

	@NotEmpty
	@Column(length = 255)
	private String street;
	
	@NotEmpty
	@Column(length = 255)
	private String zipCode;
	
	@NotEmpty
	@Column(length = 255)
	private String city;
	
	@NotNull
	@ManyToOne
	private Country country;

	@Enumerated(EnumType.STRING)
	@Column(length = 255)
	private Role role = Role.ROLE_USER;
		
	@Transient
	@NotEmpty
	private String passwordRetype;

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}

	public String getPasswordRetype() {
		return passwordRetype;
	}


	public void setPasswordRetype(String passwordRetype) {
		this.passwordRetype = passwordRetype;
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


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}


}
