package model.dao;

import model.entities.Customer;

public interface CustomerDao extends GenericDao<Customer> {
	// This method searches customer by login and password
	Customer findAccount(String login, int password);
}
