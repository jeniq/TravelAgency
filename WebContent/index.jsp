<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<c:set var="lang" value="${empty lang ? 'en_US' : lang}" scope="session" />
		<fmt:setLocale value="${lang}" />
		<fmt:setBundle basename="localization/messages" var="bundle" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="styles/index.css">
		<title>
			<fmt:message key="index.label.title" bundle="${bundle}" />
		</title>
	</head>
	<body>
		<p id="language">
			<fmt:message key="index.label.selectLanguage" bundle="${bundle}" />:
			<a href="./Controller?command=SET_LOCALE&locale=en-US">EN</a> | 
			<a href="./Controller?command=SET_LOCALE&locale=uk-UA">UA</a>
		<p>
		<div>
			<h1>
				<fmt:message key="index.label.welcome" bundle="${bundle}" />!
			</h1>
			<p>
				<fmt:message key="index.label.askLoginPassword" bundle="${bundle}" />
			</p>
			<div class="loginForm">
				<form method="POST" action="./Controller">
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
			<br>
			<c:if test="${wrongData}">
				<fmt:message key="index.label.password" bundle="${bundle}" />:
			</c:if>
		</div>
	</body>
</html>