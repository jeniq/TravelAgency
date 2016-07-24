package model.services;

import java.util.List;

import model.dao.CustomerDao;
import model.dao.DaoFactory;
import model.entities.Customer;

public class CustomerService {
	private static CustomerService instance = new CustomerService();
	private DaoFactory factory = DaoFactory.getInstance();
	
	public static CustomerService getInstance(){ return instance; }

	public List<Customer> getAll(){
		CustomerDao customerDao = factory.createCustomerDao();
		return customerDao.findAll();
	}
	
	public boolean setDiscount(int id, int discount){
		CustomerDao customerDao = factory.createCustomerDao();
		return customerDao.setDiscount(id, discount);
	}
}
