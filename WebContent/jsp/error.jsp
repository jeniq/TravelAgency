<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<fmt:setLocale value="${lang}" />
	<fmt:setBundle basename="localization/messages" var="bundle" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><fmt:message key="error.label.title" bundle="${bundle}" /></title>
</head>
	<body>
		<fmt:message key="error.label.message" bundle="${bundle}" />:
		<fmt:message key="error.label.text" bundle="${bundle}" />
	</body>
</html>