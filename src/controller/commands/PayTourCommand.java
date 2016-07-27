package controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entities.Order;
import model.services.OrderService;

/**
 * This class performs user's payment for travel and add it to database using
 * order service.
 * 
 * @author Yevhen Hryshchenko
 * @version 22 Jule 2016
 *
 */
public class PayTourCommand implements Command{
	private OrderService orderService = OrderService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Order order = (Order) session.getAttribute(CommandConstants.ORDER);
		
		orderService.payTour(order);
		session.setAttribute(CommandConstants.ORDER, order);
		
		return CommandConstants.ORDER_PAGE;
	}

}
