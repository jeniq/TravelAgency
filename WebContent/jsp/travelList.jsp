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
	<table id = "travels">
		<c:forEach var="travel" items="${list}">
			<form action="./Controller">
				<tr>
					<c:if test = "${user.getPermissions() eq 'agent' }"><td>${travel.getId() }</td></c:if>
					<td><input type="hidden" name="command" value="NEW_ORDER" /></td>
					<td>
						<c:if test = "${travel.getIsHot() }">
						<label id = "hotTour">HOT!!!</label> 
						</c:if> 
						<label> ${travel.getName()}</label>
					</td>
					<td><label class="travelPrice">${travel.getPrice()}</label></td>
					<td><input type="hidden" name="travelId" value="${travel.getId()}" /></td>
					<td><input type="submit" value="BUY"></td>
				</tr>
			</form>
		</c:forEach>
	</table>	
	
	<c:if test = "${user.getPermissions() eq 'agent' }">
		<table id = "adminPanel">
			<tr>
				<td></td><td></td>
				<td><label>Admin panel</label></td>
				<td></td><td></td>
			</tr>
			<tr>
				<form action="./Controller">
					<td><label>Travel id:</label></td>
					<td><input type = "number" name = "travelId"></td>
					<td><label>discount:</label></td>
					<td><input type = "number" name = "travelDiscount">%</td>
					<td>
						<input type="hidden" name="command" value="SET_HOT" />
						<input type="submit" name="button" value="Set hot">
						<input type="submit" name="button" value="Cancel hot">
					</td>
				</form>
			</tr>
			<tr>
				<form action="./Controller">
					<td><label>Customer id:</label></td>
					<td><input type = "number" name = "customerId"></td>
					<td><label>discount:</label>
					<td><input type = "number" name = "customerDiscount">%</td>
					<td>
						<input type="hidden" name="command" value="SET_USER_DISCOUNT" />
						<input type="submit" value="Set discount">
					</td>
				</form>
			</tr>
		</table>
		
		<a href="./Controller?command=FIND_ALL_CUSTOMERS">Customers</a>
		<c:if test="${customerList ne null}">
			<p>ID/name/surname/discount</p>
			<c:forEach var="customer" items="${customerList}">
				<p>${customer.getId()} ${customer.getName() } ${customer.getSurname() } ${customer.getDiscount() }</p>
			</c:forEach>
		</c:if>
		
	</c:if>
	
</body>
</html>