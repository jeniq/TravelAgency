package controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entities.Customer;
import model.entities.Order;
import model.entities.Tour;
import model.services.OrderService;

/**
 * This class executes booking of travel and add it to database using service.
 * 
 * @author Yevhen Hryshchenko
 * @version 22 Jule 2016
 *
 */
public class BookTravelCommand implements Command {
	private OrderService orderService = OrderService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Customer customer = (Customer) session.getAttribute(CommandConstants.CUSTOMER);
		Tour tour = (Tour) session.getAttribute(CommandConstants.TRAVEL);

		Order order = new Order(customer.getId(), tour.getId(), false); 
		orderService.bookTravel(order);
		session.setAttribute(CommandConstants.ORDER, order);
		
		return CommandConstants.ORDER_PAGE;
	}
	
}
