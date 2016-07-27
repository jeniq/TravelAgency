package controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.services.CustomerService;

/**
 * This class sets user's discount.
 * 
 * @author Yevhen Hryshchenko
 * @version 24 Jule 2016
 *
 */
public class SetUserDiscount implements Command {
	private CustomerService customerService = CustomerService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int customerId = new Integer(request.getParameter(CommandConstants.CUSTOMER_ID));
		int discount = new Integer(request.getParameter(CommandConstants.DISCOUNT));

		customerService.setDiscount(customerId, discount);

		return CommandConstants.FIND_ALL_TOURS_COMMAND;
	}

}
