package model.services;

import java.util.List;

import model.dao.AgentDao;
import model.dao.DaoFactory;
import model.dao.OrderDao;
import model.entities.Customer;
import model.entities.Order;
import model.entities.Tour;
import model.entities.TravelAgent;
import model.entities.User;

public class OrderService {
	private static OrderService instance = new OrderService();
	private DaoFactory factory = DaoFactory.getInstance();
	
	public static OrderService getInstance(){ return instance; }
	
	public List<Order> getAll(){
		OrderDao orderDao = factory.createOrderDao();
		return orderDao.findAll();
	}
	
	public List<Order> getAll(int id){
		OrderDao orderDao = factory.createOrderDao();
		return orderDao.findAll(id);
	}
	
	public Order payTour(Order o) {
		o.setPaid(true);
		return bookTravel(o);
	}
	
	public Order bookTravel(Order o){
		OrderDao orderDao = factory.createOrderDao();
		Order order = o;
		if (orderDao.find(order.getCustomer(), order.gettId()) == null){
			orderDao.create(order);
		}else{
			orderDao.update(order);
		}
		return order;
	}
	
	public boolean setPrice(Order order, Tour travel, User user){
		OrderDao orderDao = factory.createOrderDao();
		int price = travel.getPrice();
		int discount = 0;
		// set travel's price including discount
		if (travel.getDiscount() > 0){
			discount = travel.getDiscount(); 
		}else if (((Customer)user).getDiscount() > 0){
			discount = ((Customer)user).getDiscount();
		}
		price = price * (1 - discount/100);
		
		return orderDao.setPrice(order);
	}
	
	public Order getOrder(int id){
		OrderDao orderDao = factory.createOrderDao();
		
		return orderDao.find(id);
	}
	
	public TravelAgent selectAgent(Order order){
		if (order.getAgent() != null){
			return order.getAgent();
		}
		AgentDao agentDao = factory.createAgentDao();
		OrderDao orderDao = factory.createOrderDao();
		List<TravelAgent> agents = agentDao.findAll();
		TravelAgent agent;
		int aNum = (int)(Math.random()*agents.size());
		agent = agents.get(aNum);
		orderDao.setAgent(order, agent.getId());
		
		return agent;
	}
}
