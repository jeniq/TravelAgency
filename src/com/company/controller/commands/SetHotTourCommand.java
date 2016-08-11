package com.company.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.model.entities.Tour;
import com.company.model.services.TourService;

/**
 * This class sets tour like hot or cancels hot status and sets discount price.
 * 
 * @author Yevhen Hryshchenko
 * @version 24 Jule 2016
 * 
 */
public class SetHotTourCommand implements Command {
	private TourService tourService = TourService.getInstance();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int travelId = new Integer(request.getParameter(CommandConstants.TRAVEL_ID));
		Tour tour = tourService.getTravel(travelId);
		int discount = 0;
		boolean isHot = false;

		if (request.getParameter(CommandConstants.BUTTON).equals(CommandConstants.SET_HOT)) {
			isHot = true;
			discount = new Integer(request.getParameter(CommandConstants.DISCOUNT));
		}
		tourService.setHotTravel(tour, isHot, discount); // set or cancel
															// discount

		return CommandConstants.FIND_ALL_TOURS_COMMAND;
	}

}
