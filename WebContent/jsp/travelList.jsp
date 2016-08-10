<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
	<head>
		<fmt:setLocale value="${lang}" />
		<fmt:setBundle basename="localization/messages" var="bundle" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="styles/travelList.css">
		<title>
			<fmt:message key="travelList.label.title" bundle="${bundle}" />
		</title>
	</head>
	<body>
		<div id="header">
			<fmt:message key="travelList.label.welcome" bundle="${bundle}" />, 
				<fmt:message key="${user.getName()}" bundle="${bundle}" />
				<fmt:message key="${user.getSurname()}" bundle="${bundle}" />
			<c:if test="${user.getPermissions() eq 'user' }"> | 
				<a href="./Controller?command=USER_ORDERS">
					<fmt:message key="travelList.label.myOrders" bundle="${bundle}" />
				</a>
			</c:if>
			| 
			<a href="./Controller?command=LOGOUT">
				<fmt:message key="travelList.label.logout" bundle="${bundle}" />
			</a>
		</div>
		<table id="travels">
			<tr id="title">
				<c:if test="${user.getPermissions() eq 'agent' }">
					<td>
						<fmt:message key="travelList.label.id" bundle="${bundle}" />
					</td>
				</c:if>
				<td id = "travelName">
					<fmt:message key="travelList.label.travelName" bundle="${bundle}" />
				</td>
				<td>
					<fmt:message key="travelList.label.originPrice" bundle="${bundle}" />
				</td>
				<td>
					<fmt:message key="travelList.label.discount" bundle="${bundle}" />
				</td>
				<td>
					<fmt:message key="travelList.label.totalPrice" bundle="${bundle}" />
				</td>
				<td></td>
			</tr>
			<c:forEach var="travel" items="${list}">
				<form method="GET" action="./Controller" >
					<tr>
						<c:if test="${user.getPermissions() eq 'agent' }">
							<td>${travel.getId() }</td>
						</c:if>
						<td>
							<c:if test="${travel.getIsHot() }">
								<label id="hotTour"><fmt:message key="travelList.label.hotTour" bundle="${bundle}" /></label>
							</c:if> 
							<label><fmt:message key="${travel.getName()}" bundle="${bundle}" /></label>
						</td>
						<c:if test="${travel.getDiscount() eq '0' }">
							<td>
								<label class="travelPrice">${travel.getOriginPrice()}$</label>
							</td>
							<td></td>
						</c:if>
						<c:if test="${travel.getDiscount() gt '0' }">
							<td>
								<label class="travelPrice" id="crossed">${travel.getOriginPrice()}$</label>
							</td>
							<td>
								<label class="travelPrice">${travel.getDiscount()}%</label>
							</td>
						</c:if>
						<td>
							<label class="travelPrice">${travel.getPrice()}$</label>
						</td>
						<td>
							<input type="hidden" name="travelId" value="${travel.getId()}" />
						</td>
						<td>
							<input type="hidden" name="command" value="NEW_ORDER" />
						</td>
						<c:if test="${user.getPermissions() eq 'user' }">
							<td>
								<input type="submit" value="<fmt:message key="travelList.button.buy" bundle="${bundle}" />">
							</td>
						</c:if>
					</tr>
				</form>
			</c:forEach>
		</table>

		<c:if test="${user.getPermissions() eq 'agent' }">
			<table id="adminPanel">
				<tr>
					<td></td><td></td>
					<td>
						<label>
							<fmt:message key="travelList.label.adminPanel" bundle="${bundle}" />
						</label>
					</td>
					<td></td><td></td>
				</tr>
				<form action="./Controller">
					<tr>
						<td>
							<label>
								<fmt:message key="travelList.label.travelId" bundle="${bundle}" />
							</label>
						</td>
						<td>
							<input type="number" name="travelId" placeholder="id">
						</td>
						<td>
							<label>
								<fmt:message key="travelList.label.discount" bundle="${bundle}" />:
							</label>
						</td>
						<td>
							<input type="number" name="discount" value="0">%
						</td>
						<td>
							<input type="hidden" name="command" value="SET_HOT" /> 
							<input type="submit" name="button" 
									value="<fmt:message key="travelList.button.setHot" bundle="${bundle}" />">
							<input type="submit" name="button"
									value="<fmt:message key="travelList.button.cancelHot" bundle="${bundle}" />">
						</td>
					</tr>
				</form>
				<form action="./Controller">
					<tr>
						<td>
							<label>
								<fmt:message key="travelList.label.customerId" bundle="${bundle}" />
							</label>
						</td>
						<td>
							<input type="number" name="customerId" placeholder="customer id">
						</td>
						<td>
							<label>
								<fmt:message key="travelList.label.discount" bundle="${bundle}" />:
							</label>
						</td>
						<td>
							<input type="number" name="discount" value="0">%
						</td>
						<td>
							<input type="hidden" name="command" value="SET_USER_DISCOUNT" /> 
							<input type="submit" value="<fmt:message key="travelList.button.setDiscount" bundle="${bundle}" />">
						</td>
					</tr>
				</form>
			</table>

			<a href="./Controller?command=FIND_ALL_CUSTOMERS">
				<fmt:message key="travelList.label.customers" bundle="${bundle}" />
			</a>
			<c:if test="${customerList ne null}">
				<p>
					<fmt:message key="travelList.label.id" bundle="${bundle}" />
					/
					<fmt:message key="travelList.label.name" bundle="${bundle}" />
					/
					<fmt:message key="travelList.label.surname" bundle="${bundle}" />
					/
					<fmt:message key="travelList.label.discount" bundle="${bundle}" />
				</p>
				<table id = "customerList">
					<c:forEach var="customer" items="${customerList}">
						<tr>
							<td>${customer.getId()}</td>
							<td>${customer.getName() }</td>
							<td>${customer.getSurname() }</td>
							<td>${customer.getDiscount() } %</td>
						</tr>
				</c:forEach>
				</table>
			</c:if>
		</c:if>
	</body>
</html>