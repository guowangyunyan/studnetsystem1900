package com.training.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.training.common.service.CommonService;
import com.training.convert.UserModelConvert;
import com.training.data.UserData;
import com.training.form.UserForm;
import com.training.model.UserModel;
import com.training.service.UserService;

public class UserServiceImpl implements UserService {
	private CommonService commonService;
	private UserModelConvert userModelConvert;
	private Md5PasswordEncoder md5Encoder;

	// 登录
	@Override
	public UserData findUser(UserForm userForm) {
		UserData userData = new UserData();
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put(UserModel.NAME, userForm.getName());
		String password = md5Encoder.encodePassword(userForm.getPassword(), userForm.getName());
		fields.put(UserModel.PASSWORD, password);
		List<UserModel> userModels = commonService.getEntitiesByFields(UserModel.class, fields);
		List<UserData> userDatas = new ArrayList<UserData>();
		if (null != userModels) {
			for (UserModel model : userModels) {
				UserData data = new UserData();
				data.setId(model.getId());
				data.setName(model.getName());
				data.setMobile(model.getMobile());
				data.setCreateDate(model.getCreateDate());
				data.setModifyDate(model.getModifyDate());
				userDatas.add(data);
			}
			userData = userDatas.get(0);
		}
		return userData;
	}

	// 验证用户名的唯一性
	@Override
	public UserModel findUserByName(UserForm userForm) {
		UserModel userModel = new UserModel();
		List<UserModel> userModels = commonService.getEntitiesByField(UserModel.class, UserModel.NAME,
				userForm.getName());
		if (userModels.size() > 0) {
			userModel = userModels.get(0);
			return userModel;
		}
		return null;
	}

	// 注册用户
	@Override
	public void saveUser(UserForm userForm) {
		UserModel userModel = userModelConvert.convert(userForm);
		commonService.saveOrUpdateEntity(userModel);

	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public UserModelConvert getUserModelConvert() {
		return userModelConvert;
	}

	public void setUserModelConvert(UserModelConvert userModelConvert) {
		this.userModelConvert = userModelConvert;
	}

	public Md5PasswordEncoder getMd5Encoder() {
		return md5Encoder;
	}

	public void setMd5Encoder(Md5PasswordEncoder md5Encoder) {
		this.md5Encoder = md5Encoder;
	}

}
