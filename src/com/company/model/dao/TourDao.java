package com.company.model.dao;

import com.company.model.entities.Tour;

public interface TourDao extends GenericDao<Tour> {
	// This method sets tour like hot and sets discount
	public boolean setHot(Tour tour, boolean isHot, int discount);

	// This method searches travel by it's name
	public Tour find(String name);
}
