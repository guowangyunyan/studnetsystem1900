package com.training.service;

import com.training.data.UserData;
import com.training.form.UserForm;
import com.training.model.UserModel;

public interface UserService {

	UserData findUser(UserForm userForm);

	UserModel findUserByName(UserForm userForm);

	void saveUser(UserForm userForm);

	UserData queryUserByNameAndPassword(String name, String password);

}
