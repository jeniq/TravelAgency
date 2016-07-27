package controller.commands;

/**
 * This class contains set of string values for command classes.
 * 
 * @author Yevhen Hryshchenko
 * @version 23 Jule 2016
 *
 */
public interface CommandConstants {
	// Constants
	String LOGIN = "login";
	String PASSWORD = "password";
	String WRONG_DATA = "wrongData";
	String USER = "user";
	String PERMISSIONS = "permissions";
	String TRAVEL_ID = "travelId";
	String TRAVEL = "travel";
	String ORDER = "order";
	String TRAVELS_LIST = "list";
	String LOCALE = "locale";
	String ORDER_LIST = "orderList";
	String SET_HOT = "Set hot";
	String BUTTON = "button";
	String CUSTOMER_LIST = "customerList";
	String CUSTOMER_ID = "customerId";
	String LANG = "lang";
	String ORDER_ID = "orderId";
	String TRUE = "true";
	String DISCOUNT = "discount";

	// Pages
	String INDEX_PAGE = "/index.jsp";
	String FIND_ALL_TOURS_COMMAND = "./Controller?command=FIND_ALL_TOURS";
	String USER_ORDERS_COMMAND = "./Controller?command=USER_ORDERS";
	String ORDER_PAGE = "/jsp/order.jsp";
	String TRAVEL_LIST_PAGE = "/jsp/travelList.jsp";
	String USER_ORDERS_PAGE = "/jsp/userOrders.jsp";
}
