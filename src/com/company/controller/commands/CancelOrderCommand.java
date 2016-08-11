package com.company.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.model.services.OrderService;

/**
 * This class executes deleting selected order from user's orders list.
 * 
 * @author Yevhen Hryshchenko
 * @version 26 Jule 2016
 *
 */
public class CancelOrderCommand implements Command {
	private OrderService orderService = OrderService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		orderService.cancelOrder(new Integer(request.getParameter(CommandConstants.ORDER_ID)));

		return CommandConstants.USER_ORDERS_COMMAND;
	}

}
