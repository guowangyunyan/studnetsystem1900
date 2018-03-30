package com.training.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.training.form.UserForm;

public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object taget, Errors errors) {
		UserForm userForm = (UserForm) taget;
		if (userForm.isNew()) {

		}
		String name = userForm.getName();
		String password = userForm.getPassword();
		String verifyCode = userForm.getVerifyCode();
		if (StringUtils.isBlank(name)) {
			errors.rejectValue(name, "user.name.empty");
		}
		if (StringUtils.isBlank(password)) {
			errors.rejectValue(password, "user.password.empty");
		}
		if (StringUtils.isBlank(verifyCode)) {
			errors.rejectValue(verifyCode, "user.verifyCode.empty");
		}
	}

}
