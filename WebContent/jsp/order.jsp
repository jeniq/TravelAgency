<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order info</title>
</head>
<body>
	<div>
		<p>${user.getName() } ${user.getSurname() }, your travel is
		<p>
		<p>${travel.getName() }</p>
		<p>travel id: ${travel.getId() }</p>
		<p>Date: ${travel.getStartDate() } - ${travel.getEndDate() }</p>
		<p>Price: ${travel.getPrice() }</p>
		<p>Travel type: ${ travel.getType() }</p>
		<p>
			Payment status:
			<c:if test="${order.getIsPaid()}">paid</c:if>
			<c:if test="${not order.getIsPaid()}">unpaid</c:if>
		</p>
		<p>
			<a href="./Controller?command=FIND_ALL_TOURS">Continue</a> 
			<a href="./Controller?command=PAY_TOUR">Pay tour</a>
			<c:if test = "${not order.getIsPaid()}">
				<a href="./Controller?command=BOOK_TOUR">Book</a>
			</c:if>
		</p>
		<p>
			<c:if test="${order.getTravel() eq travel.getId()  }"> Travel has 
				<c:if test="${order.getIsPaid() }">paid</c:if>
				<c:if test="${not order.getIsPaid() }">booked</c:if>
		 		succesfull!
		 	</c:if>
		</p>
	</div>
</body>
</html>