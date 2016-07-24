<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="styles/userOrders.css">
</head>
<body>
	<table>
	<c:forEach var="order" items="${orderList}">
		<tr>
			<td><label>${order.getTravelName()}</label></td>
			<td><label>${order.getStart()} - ${order.getEnd()}</label></td>
			<td><label>Full price: </label>${order.gettPrice()}$</td>
			<td><label>Final price: </label>${order.getFinalPrice()}$</td>
			<td><label>Payment status: </label>
				<c:if test = "${not order.getIsPaid() }">unpaid</c:if>
				<c:if test = "${ order.getIsPaid() }">paid</c:if>
			</td>
			<td><label>Personal manager: </label>${order.getAgent().getName() } ${order.getAgent().getSurname() }</td>
		</tr>
	</c:forEach>
	</table>
	<a href="./Controller?command=FIND_ALL_TOURS">Continue</a>
</body>
</html>