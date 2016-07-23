<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/travelList.css">
<title>Available travels</title>
</head>
<body>
	<div id="header">
		Welcome, ${user.getName()} ${user.getSurname()} | <a
			href="./Controller?command=USER_ORDERS">My orders</a> | <a
			href="./Controller?command=LOGOUT">Logout</a>
	</div>
	<table>
		<c:forEach var="travel" items="${list}">
			<form action="./Controller">
				<tr>
					<td><input type="hidden" name="command" value="NEW_ORDER" /></td>
					<td><label>${travel.getName()}</label></td>
					<td><label class="travelPrice">${travel.getPrice()}</label></td>
					<td><input type="hidden" name="travelId" value="${travel.getId()}" /></td>
					<td><input type="submit" value="BUY"></td>
				</tr>
			</form>
		</c:forEach>
	</table>	
</body>
</html>