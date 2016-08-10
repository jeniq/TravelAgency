<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<fmt:setLocale value="${lang}" />
		<fmt:setBundle basename="localization/messages" var="bundle" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>
			<fmt:message key="order.label.title" bundle="${bundle}" />
		</title>
	</head>
	<body>
		<div>
			<p>
				<fmt:message key="${user.getName() }" bundle="${bundle}" />
				<fmt:message key="${user.getSurname() }" bundle="${bundle}" />,
				<fmt:message key="order.label.yourTravel" bundle="${bundle}" />
				<fmt:message key="${travel.getName() }" bundle="${bundle}" />,
			</p>
			<p>
				<fmt:message key="order.label.travelId" bundle="${bundle}" />
				: ${travel.getId() }
			</p>
			<p>
				<fmt:message key="order.label.date" bundle="${bundle}" />
				: ${travel.getStartDate() } - ${travel.getEndDate() }
			</p>
			<p>
				<fmt:message key="order.label.travelType" bundle="${bundle}" />
				: <fmt:message key="${ travel.getType().toString() }" bundle="${bundle}" />
			</p>
			<p>
				<fmt:message key="order.label.price" bundle="${bundle}" />
				: ${travel.getOriginPrice() }$
			</p>
			<c:if test="${travel.getIsHot() }">
				<p>
					<fmt:message key="order.label.travelDiscount" bundle="${bundle}" />
					: ${travel.getDiscount() }%
				</p>
			</c:if>
			<c:if test="${user.getDiscount() gt '0' }">
				<p>
					<fmt:message key="order.label.userDiscount" bundle="${bundle}" />
					: ${user.getDiscount() }%
				</p>
			</c:if>
			<p>
				<fmt:message key="order.label.totalPrice" bundle="${bundle}" />
				: ${order.getFinalPrice() }$
			</p>
			<p>
				<fmt:message key="order.label.paymentStatus" bundle="${bundle}" />:
				<c:if test="${order.getIsPaid()}">
					<fmt:message key="order.label.paid" bundle="${bundle}" />
				</c:if>
				<c:if test="${not order.getIsPaid()}">
					<fmt:message key="order.label.unpaid" bundle="${bundle}" />
				</c:if>
			</p>
			<p>
				<a href="./Controller?command=FIND_ALL_TOURS">
					<fmt:message key="order.link.continue" bundle="${bundle}" />
				</a> 
				<a href="./Controller?command=PAY_TOUR">
					<fmt:message key="order.link.payTour" bundle="${bundle}" />
				</a>
				<c:if test="${not order.getIsPaid()}">
					<a href="./Controller?command=BOOK_TOUR">
						<fmt:message key="order.label.book" bundle="${bundle}" />
					</a>
				</c:if>
			</p>
			<p>
				<c:if test="${order.getId() ne 0 }">
					<fmt:message key="order.label.travelHas" bundle="${bundle}" />
					<c:if test="${order.getIsPaid() }">
						<fmt:message key="order.label.paid" bundle="${bundle}" />
					</c:if>
					<c:if test="${not order.getIsPaid() }">
						<fmt:message key="order.label.booked" bundle="${bundle}" />
					</c:if>
					<fmt:message key="order.label.succesful" bundle="${bundle}" />!
		 		</c:if>
			</p>
		</div>
	</body>
</html>