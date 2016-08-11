<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
	<fmt:setLocale value="${lang}" />
	<fmt:setBundle basename="com.company.localization/messages" var="bundle" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>
		<fmt:message key="userOrders.label.title" bundle="${bundle}" />
	</title>
	<link rel="stylesheet" type="text/css" href="styles/userOrders.css">
</head>
<body>
	<table>
		<c:forEach var="order" items="${orderList}">
				<tr>
					<td>
						<label><fmt:message key="${order.getTravelName()}" bundle="${bundle}" /></label>
					</td>
					<td>
						<label>${order.getStart()} - ${order.getEnd()}</label>
					</td>
					<td>
						<label>
							<fmt:message key="userOrders.label.finalPrice" bundle="${bundle}" />: 
						</label>
						${order.getFinalPrice()}$
					</td>
					<td>
						<label>
							<fmt:message key="userOrders.label.paymentStatus" bundle="${bundle}" />: 
						</label>
						<c:if test="${not order.getIsPaid() }">
							<fmt:message key="userOrders.label.unpaid" bundle="${bundle}" />
						</c:if>
						<c:if test="${ order.getIsPaid() }">
							<fmt:message key="userOrders.label.paid" bundle="${bundle}" />
						</c:if>
					</td>
					<td>
						<label>
							<fmt:message key="userOrders.label.personalManager" bundle="${bundle}" />: 
						</label>
						<fmt:message key="${order.getAgent().getName() }" bundle="${bundle}" />
						<fmt:message key="${order.getAgent().getSurname() }" bundle="${bundle}" />
					</td>
					<td>
						<form action="./Controller">
							<input type="hidden" name="command" value="CANCEL_ORDER" />
							<input type="hidden" name="orderId" value="${order.getId()}" />
							<input type="submit" value="Cancel" />
						</form>
					</td>
				</tr>
		</c:forEach>
	</table>
	<a href="./Controller?command=FIND_ALL_TOURS">
		<fmt:message key="userOrders.label.continue" bundle="${bundle}" />
	</a>
</body>
</html>