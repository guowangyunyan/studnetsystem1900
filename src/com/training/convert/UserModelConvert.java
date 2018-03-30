package com.training.convert;

import java.util.Date;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.training.form.UserForm;
import com.training.model.UserModel;

public class UserModelConvert implements Convert<UserForm, UserModel> {
	private Md5PasswordEncoder md5Encoder;

	@Override
	public UserModel createTarget() {

		return new UserModel();
	}

	@Override
	public UserModel convert(UserForm userForm) {
		String password = md5Encoder.encodePassword(userForm.getPassword(), userForm.getName());
		UserModel userModel = createTarget();
		userModel.setName(userForm.getName());
		userModel.setPassword(password);
		userModel.setMobile(userForm.getMobile());
		userModel.setCreateDate(new Date());
		userModel.setModifyDate(new Date());
		userModel.setAvailable(true);
		return userModel;
	}

	public Md5PasswordEncoder getMd5Encoder() {
		return md5Encoder;
	}

	public void setMd5Encoder(Md5PasswordEncoder md5Encoder) {
		this.md5Encoder = md5Encoder;
	}

}
