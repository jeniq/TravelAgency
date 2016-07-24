package model.dao;

import model.entities.Tour;

public interface TourDao extends GenericDao<Tour> {
	public boolean setHot(int id, boolean isHot, int discount);
}
