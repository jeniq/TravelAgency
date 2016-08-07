package controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entities.Order;
import model.entities.Tour;
import model.entities.User;
import model.services.TourService;

/**
 * This class shows detail information for user about travel. At this page user
 * can book or buy travel.
 * 
 * @author Yevhen Hryshchenko
 * @version 24 Jule 2016
 *
 */
public class OrderCommand implements Command {
	private TourService tourService = TourService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		int travId = new Integer(request.getParameter(CommandConstants.TRAVEL_ID));
		Tour tour = tourService.getTravel(travId);
		User user = (User) session.getAttribute(CommandConstants.USER);
		Order order = new Order(user, tour);

		order.setPrice(tour, user); // price sets including discounts
		
		session.setAttribute(CommandConstants.ORDER, order);
		session.setAttribute(CommandConstants.TRAVEL, tour);

		return CommandConstants.ORDER_PAGE;
	}

}
