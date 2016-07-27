<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<c:choose>
			<c:when test = "${nullSession}">
				<fmt:message key="error.label.nullSession" bundle="${bundle}" />
				<p>
					<a href = "/TravelAgency/index.jsp">
						<fmt:message key="error.link.enter" bundle="${bundle}" />
					</a>
				</p>
			</c:when>
			<c:otherwise>
				<fmt:message key="error.label.text" bundle="${bundle}" />
			</c:otherwise>		
		</c:choose>
	</body>
</html>