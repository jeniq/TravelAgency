package controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entities.Customer;
import model.entities.Order;
import model.entities.Tour;
import model.entities.User;
import model.services.AuthenticationService;
import model.services.OrderService;

/**
 * This class performes user's payment for travel and add it to database using
 * order service.
 * 
 * @author Yevhen Hryshchenko
 * @version 22 Jule 2016
 *
 */
public class PayTourCommand implements Command{
	private OrderService orderService = OrderService.getInstance();
	private AuthenticationService accountService = AuthenticationService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Tour tour = (Tour) session.getAttribute(CommandConstants.TRAVEL);
		User user = (User) session.getAttribute(CommandConstants.USER);
		Customer customer = accountService.findCustomer(((Customer)user).getId());
		Order order = new Order(customer.getId(), tour.getId(), true);
		
		orderService.payTour(order);
		request.setAttribute(CommandConstants.ORDER, order);
		
		return CommandConstants.ORDER_PAGE;
	}

}
