package controller.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.entities.Tour;
import model.services.TourService;

/**
 * This class presents list of avalaible travels.
 * 
 * @author Yevhen Hryshchenko
 * @version 20 Jule 2016
 *
 */
public class TourListCommand implements Command {
	private TourService tourService = TourService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		HttpSession session = request.getSession();
		List<Tour> result = tourService.getAll();
		session.setAttribute(CommandConstants.TRAVELS_LIST, result);

		return CommandConstants.TRAVEL_LIST_PAGE;
	}

}
