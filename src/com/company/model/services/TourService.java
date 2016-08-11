package com.company.model.services;

import java.util.List;

import com.company.model.dao.DaoFactory;
import com.company.model.dao.TourDao;
import com.company.model.entities.Tour;

/**
 * This class implements tour service. It contains methods that searches travels
 * and sets travel like hot.
 * 
 * @author Yevhen Hryshchenko
 * @version 24 Jule 2016
 */
public class TourService {
	private static TourService instance = new TourService();
	private DaoFactory factory = DaoFactory.getInstance();

	public static TourService getInstance() {
		return instance;
	}

	public List<Tour> getAll() {
		TourDao tourDao = factory.createTourDao();
		return tourDao.findAll();
	}

	// This method return travel object by travel id
	public Tour getTravel(int id) {
		TourDao tourDao = factory.createTourDao();
		return tourDao.find(id);
	}

	// This method sets travel like hot and sets discount or cancel hot status
	public boolean setHotTravel(Tour tour, boolean isHot, int discount) {
		if (discount < 0 || discount > 99) {
			return false;
		}
		TourDao tourDao = factory.createTourDao();
		
		if (tourDao.find(tour.getId()).getIsHot() && isHot){ // forbid setting hot twice
			return false;
		}
		if (isHot) {
			tour.setDiscountPrice(discount);
		} else {
			tour.cancelDiscountPrice();
		}

		return tourDao.setHot(tour, isHot, discount);
	}

}
