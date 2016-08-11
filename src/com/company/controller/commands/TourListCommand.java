package com.company.controller.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.model.entities.Tour;
import com.company.model.services.TourService;

/**
 * This class shows list of avalaible travels.
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
		List<Tour> result = tourService.getAll();
		request.getSession(true).setAttribute(CommandConstants.TRAVELS_LIST, result);

		return CommandConstants.TRAVEL_LIST_PAGE;
	}

}
