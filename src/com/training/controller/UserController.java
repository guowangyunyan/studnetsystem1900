package com.training.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.training.form.UserForm;
import com.training.model.UserModel;
import com.training.service.UserService;
import com.training.validator.UserValidator;

@Controller
public class UserController {
	@Resource
	private UserService userService;

	@Resource
	private UserValidator userValidator;

	// 注册页面
	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "user/register";
	}

	// 注册用户
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Model model, UserForm userForm, BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("userForm", userForm);
			return "user/register";
		}
		String verifyCode = (String) session.getAttribute("verifyCode");
		if (!verifyCode.equals(userForm.getVerifyCode())) {
			return "user/register";
		}

		UserModel userModel = userService.findUserByName(userForm);
		if (null == userModel) {
			userService.saveUser(userForm);
		}
		return "login";
	}
}
