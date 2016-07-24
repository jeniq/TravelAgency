package controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.services.TourService;

public class SetHotTourCommand implements Command{
	private TourService tourService = TourService.getInstance();
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int travelId = new Integer(request.getParameter(CommandConstants.TRAVEL_ID));
		int discount = 0;
		boolean isHot = false;
		
		if (request.getParameter(CommandConstants.BUTTON).equals(CommandConstants.SET_HOT) ){
			isHot = true;
			discount = new Integer(request.getParameter(CommandConstants.TRAVEL_DISCOUNT));
		}
		tourService.setHotTravel(travelId, isHot, discount);
		
		return CommandConstants.FIND_ALL_TOURS_PAGE;
	}

}
