package com.training.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.training.common.Config;
import com.training.data.UserData;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 获取请求的uri
		String uri = req.getRequestURI();
		String excludeUri = Config.getStringProperty("login.exclude.uri");
		String[] excludeUriArray = StringUtils.split(excludeUri, ",");
		HttpSession session = req.getSession();

		for (String exuri : excludeUriArray) {
			if (uri.contains(exuri)) {
				chain.doFilter(req, resp);
			} else {
				UserData userData = (UserData) session.getAttribute("userData");
				if (null != userData) {
					chain.doFilter(req, resp);
				}
			}
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
