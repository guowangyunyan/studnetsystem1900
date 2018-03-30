<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register</title>
</head>
<body>
	<h1>register</h1>
	<hr>
	<form:form action="register" method="post" commandName="userForm">
		
		<spring:message code="user.name"/>：<form:input path="name"/><br>
		
		<spring:message code="user.password"/>：<form:password path="password"/><br>
		
		<spring:message code="user.confirmPassword"/>：<form:password path="confirmPassword"/><br>
		<spring:message code="user.mobile"/>：<form:input path="mobile"/><br>
		
		
		<spring:message code="user.verifyCode" />：<form:input path="verifyCode" />
		<img alt='<spring:message code="user.wait" />' src="verifyCode"/><br>
		
		<input type="submit" value='<spring:message code="user.register" />'/>
	</form:form>
</body>
</html>