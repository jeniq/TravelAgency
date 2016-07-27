package model.services;

import java.util.List;

import model.dao.AgentDao;
import model.dao.DaoFactory;
import model.dao.OrderDao;
import model.entities.Order;
import model.entities.TravelAgent;

/**
 * This class implements order service. It searches all orders in database,
 * searches by user's id, sets status for order (booked or paid/unpaid) sets
 * order's total price, select agent for order.
 * 
 * @author Yevhen Hryshchenko
 * @version 24 Jule 2016
 */
public class OrderService {
	private static OrderService instance = new OrderService();
	private DaoFactory factory = DaoFactory.getInstance();

	public static OrderService getInstance() {
		return instance;
	}
	
	// This method returns orders by user id
	public List<Order> getAll(int id) {
		OrderDao orderDao = factory.createOrderDao();
		return orderDao.findAll(id);
	}

	// This method sets paid status for order
	public Order payTour(Order order) {
		if (order != null) {
			order.setPaid(true);
			return bookTravel(order);
		}
		return null;
	}
	
	// This method sets booked status for order and add it to database
	public Order bookTravel(Order order) {
		if (order == null) {
			return null;
		}
		OrderDao orderDao = factory.createOrderDao();

		order.setAgent(selectAgent(order));
		if (orderDao.find(order.getCustomerId(), order.gettId()) == null) {
			orderDao.create(order);
		} else {
			orderDao.update(order);
		}
		return order;
	}
	
	// This method returns order object by order id 
	public Order getOrder(int id){
		OrderDao orderDao = factory.createOrderDao();
		
		return orderDao.find(id);
	}
	
	// This method sets agent that responsible for travel
	public TravelAgent selectAgent(Order order) {
		if (order.getAgent() != null) {
			return order.getAgent();
		}
		AgentDao agentDao = factory.createAgentDao();
		OrderDao orderDao = factory.createOrderDao();
		List<TravelAgent> agents = agentDao.findAll();
		TravelAgent agent;
		int aNum = (int) (Math.random() * agents.size());
		agent = agents.get(aNum);
		orderDao.setAgent(order, agent.getId());

		return agent;
	}

	// This method deletes order from customer's orders list
	public boolean cancelOrder(int id) {
		OrderDao orderDao = factory.createOrderDao();
		return orderDao.delete(id);
	}
}
