package log;

public interface LogMessageConstants {
	// AgentDao
	String ERROR_CREATING_AGENT = "Error creating new travel agent";
	String ERROR_DELETING_AGENT = "Error deleting travel agent";
	String ERROR_SEARCHING_AGENT_ID = "Error searching agent by ID ";
	String ERROR_SEARCHING_AGENT_LIST = "Error searching agents";
	String ERROR_SEARCHING_AGENT_ACCOUNT = "Error searching agent account";
	
	// CustomerDao
	String ERROR_CREATING_CUSTOMER = "Error creating new customer";
	String ERROR_SETTING_DISCOUNT = "Error setting customer's discount";
	String ERROR_DELETING_CUSTOMER = "Error deleting discount";
	String ERROR_SEARCHING_CUSTOMER_ID = "Error searching customer by ID ";
	String ERROR_SEARCHING_CUSTOMER_LIST = "Error searching customers";
	String ERROR_SEARCHING_CUSTOMER_ACCOUNT = "Error searching customer account";
	
	// OrderDao
	String ERROR_CREATING_ORDER = "Error creating new order";
	String ERROR_DELETING_ORDER_ID = "Error deleting order by ID ";
	String ERROR_SEARCHING_ORDER_ID = "Error searching order by ID ";
	String ERROR_SEARCHING_ORDER = "Error searching order";
	String ERROR_SEARCHING_ORDER_LIST = "Error searching order list";
	
	// TourDao
	String ERROR_CREATING_TRAVEL = "Error creating new travel";
	String ERROR_UPDATING_TRAVEL = "Error updating travel";
	String ERROR_DELETING_TRAVEL_ID = "Error deleting travel by ID ";
	String ERROR_SEARCHING_TRAVEL_ID = "Error searching travel by ID ";
	String ERROR_SEARCHING_TRAVEL_LIST = "Error searching travel list";
	
	// LoginCheckCommand
	String USER_LOGIN = "User entered to site:";
}
