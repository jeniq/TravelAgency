package controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entities.Customer;
import model.entities.User;
import model.services.AuthenticationService;
import model.services.TourService;

/**
 * This class shows more information about travel.
 * 
 * @author Yevhen Hryshchenko
 * @version 21 Jule 2016
 *
 */
public class OrderCommand implements Command {
	private TourService tourService = TourService.getInstance();
	private AuthenticationService accountService = AuthenticationService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		int travId = new Integer(request.getParameter(CommandConstants.TRAVEL_ID));
		User user = (User) session.getAttribute(CommandConstants.USER);
		Customer customer = accountService.findCustomer(((Customer)user).getId());
		
		session.setAttribute(CommandConstants.CUSTOMER, customer);
		session.setAttribute(CommandConstants.TRAVEL, tourService.getTravel(travId));
		return CommandConstants.ORDER_PAGE;
	}

}
