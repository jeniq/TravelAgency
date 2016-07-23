package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.OrderDao;
import model.entities.Order;

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
		return bookTravel(o);
	}
	
	public Order bookTravel(Order o){
		OrderDao orderDao = factory.createOrderDao();
		Order order = o;
		if (orderDao.find(order.getCustomer(), order.getTravel()) == null){
			orderDao.create(order);
		}else{
			orderDao.update(order);
		}
		return order;
	}
}
