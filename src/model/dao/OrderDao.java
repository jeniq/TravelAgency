package model.dao;

import java.util.List;

import model.entities.Order;

public interface OrderDao extends GenericDao<Order> {
	// This method searches order by customer and travel
	Order find(int custId, int travId);

	// This method searches all user's orders
	List<Order> findAll(int userId);

	// This method sets price for order
	boolean setPrice(Order order);

	// This method sets responsible travel agent for user's order
	boolean setAgent(Order order, int id);

	// This method deletes order by travel name and user's id
	boolean delete(String name, int userId);
	
	// This method updates order's information
	boolean update(Order order);
}
