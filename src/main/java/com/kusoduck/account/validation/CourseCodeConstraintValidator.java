package com.kusoduck.account.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String>{

	private String coursePrefix;
	
	@Override
	public void initialize(CourseCode theCourseCode) {
		coursePrefix = theCourseCode.value();
	}
	
	@Override
	/**
	 * value	html form data entered by user
	 * context	can place additional error messages here
	 */
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value==null) {
			return true;
		}
		return value.startsWith(coursePrefix);
	}

}
