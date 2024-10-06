package com.kusoduck.account.model;

import java.util.List;

import com.kusoduck.account.validation.CourseCode;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Student {

//	@NotBlank(message = "is required")
	@NotNull(message = "is required")
	private String firstName;
//	@NotBlank(message = "is required")
	@NotNull(message = "is required")
	private String lastName;
	@NotNull(message = "is required")
	@Min(value = 1, message = "must be greater than or equal to one")
	@Max(value = 6, message = "must be less than or equal to six")
//	private int grade; >> "Failed to convert property value of type java.lang.String to required type int" when set not null
	private Integer grade;
	@NotNull(message = "is required")
	@Pattern(regexp = "^[a-zA-Z0-9]{3}", message = "only 3 chars/digits")
	private String postalCode;
//	@CourseCode //default
	@CourseCode(value = "TOPS", message = "must start with TOPS")
	private String courseCode;
	private String country;
	private String favoriteLanguage;
	private List<String> favoriteSystems;

	public Student() {
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

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFavoriteLanguage() {
		return favoriteLanguage;
	}

	public void setFavoriteLanguage(String favoriteLanguage) {
		this.favoriteLanguage = favoriteLanguage;
	}

	public List<String> getFavoriteSystems() {
		return favoriteSystems;
	}

	public void setFavoriteSystems(List<String> favoriteSystems) {
		this.favoriteSystems = favoriteSystems;
	}

}
