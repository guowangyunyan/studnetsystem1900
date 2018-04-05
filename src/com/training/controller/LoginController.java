package com.training.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.training.data.UserData;
import com.training.form.UserForm;
import com.training.service.UserService;
import com.training.validator.LoginValidator;

@Controller
public class LoginController {
	// Validator Spring框架里的一个接口
	@Resource
	private LoginValidator loginValidator;

	@Resource
	private UserService userService;

	// 登录主页
	@RequestMapping("/login")
	public String login(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "login";
	}

	// 登录功能
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, UserForm userForm, BindingResult bindingResult, HttpSession session) {
		loginValidator.validate(userForm, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("userForm", userForm);
			return "login";
		}
		String verifyCode = (String) session.getAttribute("verifyCode");
		if (!verifyCode.equals(userForm.getVerifyCode())) {
			return "login";
		}

		UserData userData = userService.findUser(userForm);
		if (null == userData) {
			return "login";
		}
		session.setAttribute("userData", userData);

		return "redirect:loadStudentsByFields";
	}

}
