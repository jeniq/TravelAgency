package controller.commands;

/**
 * This enumaration class contains list of commands.
 * 
 * @author Yevhen Hryshchenko
 * @version 24 Jule 2016
 * 
 */
public enum CommandList {
	FIND_ALL_TOURS(new TourListCommand()), 
	LOGIN_CHECK(new LoginCheckCommand()), 
	LOGOUT(new LogoutCommand()), 
	NEW_ORDER(new OrderCommand()), 
	PAY_TOUR(new PayTourCommand()), 
	BOOK_TOUR(new BookTravelCommand()),
	SET_LOCALE(new SetLocaleCommand()),
	USER_ORDERS(new UserOrdersCommand()),
	SET_HOT(new SetHotTourCommand()),
	FIND_ALL_CUSTOMERS(new CustomerListCommand()),
	SET_USER_DISCOUNT(new SetUserDiscount()),
	CANCEL_ORDER(new CancelOrderCommand());

	private Command command;

	private CommandList(Command command) {
		this.command = command;
	}

	public Command getCommand() {
		return command;
	}
}
