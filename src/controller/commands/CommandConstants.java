package controller.commands;

/**
 * This class contains set of string values for command classes.
 * 
 * @author Yevhen Hryshchenko
 * @version 23 Jule 2016
 *
 */
public interface CommandConstants {
	String LOGIN = "login";
	String PASSWORD = "password";
	String WRONG_DATA = "wrongData";
	String USER = "user";
	String PERMISSIONS = "permissions";
	String TRAVEL_ID = "travelId"; 
	String CUSTOMER = "Customer";
	String TRAVEL = "travel";
	String ORDER = "order";
	String ORDER_STATUS = "orderStatus";
	String TRAVELS_LIST = "list";
	String LOCALE = "locale";
	String ORDER_LIST = "orderList";
	String AGENT = "agent";
	String TRAVEL_DISCOUNT = "travelDiscount";
	String SET_HOT = "Set hot";
	String BUTTON = "button";
	String CUSTOMERS_LIST = "customerList";
	String CUSTOMER_ID = "customerId";
	String CUSTOMER_DISCOUNT = "customerDiscount";
	
	// pages
	String INDEX_PAGE = "/index.jsp";
	String FIND_ALL_TOURS_PAGE  = "./Controller?command=FIND_ALL_TOURS";
	String ORDER_PAGE = "/jsp/order.jsp";
	String TRAVEL_LIST_PAGE = "/jsp/travelList.jsp";
	String USER_ORDERS_PAGE = "/jsp/userOrders.jsp";
	
	// messages
	String WRONG_LOGIN_DATA = "Entered login/password is incorrect!";
	String BOOKED = "booked";
	String PAID = "paid";
}
