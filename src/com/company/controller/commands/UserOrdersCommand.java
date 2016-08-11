package com.company.controller.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.model.entities.Order;
import com.company.model.entities.User;
import com.company.model.services.OrderService;

/**
 * This class shows list of user's orders.
 * 
 * @author Yevhen Hryshchenko
 * @version 22 Jule 2016
 *
 */
public class UserOrdersCommand implements Command {
	private OrderService orderService = OrderService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute(CommandConstants.USER);
		List<Order> orders = orderService.getAll(user.getId());

		session.setAttribute(CommandConstants.ORDER_LIST, orders);

		return CommandConstants.USER_ORDERS_PAGE;
	}

}
