package model.dao;

import java.util.List;

import model.entities.Order;

public interface OrderDao extends GenericDao<Order>{
//	boolean payTour(int custId, int travId);
	// This method searches order by customer and travel
	Order find(int custId, int travId);
	List<Order> findAll(int id);
	boolean setPrice(Order order);
	boolean setAgent(Order order, int id);
}
