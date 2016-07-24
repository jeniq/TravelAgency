package controller.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entities.Customer;
import model.services.CustomerService;

/**
 * This class implements command that displays list of customers.
 * 
 * @author Yevhen Hryshchenko
 * @version 24 Jule 2016
 *
 */
public class CustomerListCommand implements Command{
	private CustomerService customerService = CustomerService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Customer> result = customerService.getAll();
		request.setAttribute(CommandConstants.CUSTOMERS_LIST, result);

		return CommandConstants.TRAVEL_LIST_PAGE;
	}

}