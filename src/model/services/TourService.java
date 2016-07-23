package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.TourDao;
import model.entities.Tour;

public class TourService {
	private static TourService instance = new TourService();
	public static TourService getInstance(){ return instance; }
	
	public List<Tour> getAll(){
		DaoFactory factory = DaoFactory.getInstance();
		TourDao tourDao = factory.createTourDao();
		return tourDao.findAll();
	}
	
	public Tour getTravel(int id){
		DaoFactory factory = DaoFactory.getInstance();
		TourDao tourDao = factory.createTourDao();
		return tourDao.find(id);
	}
}
