<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/index.css">
<title>Travel Agency</title>
</head>
<body>
	<c:set var="lang" value="${empty lang ? 'en_US' : sessionScope.lang}"
		scope="session" />
	<fmt:setLocale value="${lang}" />
	<fmt:setBundle basename="localization/messages" var="bundle" />

	<p id="language">
		Select language: <a
			href="./Controller?command=SET_LOCALE&locale=en-US">EN</a> | <a
			href="./Controller?command=SET_LOCALE&locale=uk-UA">UA</a>
	<p>
	<div>
		<h1>
			<fmt:message key="index.label.welcome" bundle="${bundle}" />
		</h1>
		<p>
			<fmt:message key="index.label.askLoginPassword" bundle="${bundle}" />
		</p>
		<div class="loginForm">
			<form method="GET" action="./Controller">
				<input type="hidden" name="command" value="LOGIN_CHECK" />
				<p>
					<fmt:message key="index.label.login" bundle="${bundle}" />
					<input id="login" type="text" name="login" />
				</p>
				<p>
					<fmt:message key="index.label.password" bundle="${bundle}" />
					<input id="password" type="password" name="password" />
				</p>
				<input type="submit"
					value="<fmt:message key="index.button.enter" bundle="${bundle}"/>" />
			</form>
		</div>
		<br> ${wrongData}
	</div>
</body>
</html>