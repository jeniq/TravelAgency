package model.services;

import java.util.List;

import model.dao.CustomerDao;
import model.dao.DaoFactory;
import model.entities.Customer;

/**
 * This class implements customer service. It has methods that searches all
 * customers and sets discount for user.
 * 
 * @author Yevhen Hryshchenko
 * @version 23 Jule 2016
 */
public class CustomerService {
	private static CustomerService instance = new CustomerService();
	private DaoFactory factory = DaoFactory.getInstance();

	public static CustomerService getInstance() {
		return instance;
	}

	// This method returns all customers
	public List<Customer> getAll() {
		CustomerDao customerDao = factory.createCustomerDao();
		return customerDao.findAll();
	}
	
	/**
	 * This method sets discount for customer
	 * 
	 * @param id customer's id
	 * @param discount percent
	 * @return boolean
	 */
	public boolean setDiscount(int id, int discount) {
		if (discount >= 0 && discount < 100) {
			CustomerDao customerDao = factory.createCustomerDao();
			return customerDao.setDiscount(id, discount);
		}
		return false;
	}
}
