package com.company.model.dao;

import com.company.model.entities.Customer;

public interface CustomerDao extends GenericDao<Customer> {
	// This method searches customer by login and password
	Customer findAccount(String login, int password);

	// This method sets discount for user
	boolean setDiscount(int id, int discount);
}
