package com.company.model.services;

import com.company.model.dao.AgentDao;
import com.company.model.dao.CustomerDao;
import com.company.model.dao.DaoFactory;
import com.company.model.entities.Customer;
import com.company.model.entities.TravelAgent;
import com.company.model.entities.User;

/**
 * This class implements authentication service. It checks user existence in
 * database.
 *
 * @author Yevhen Hryshchenko
 * @version 21 Jule 2016
 */
public class AuthenticationService {
	private static AuthenticationService instance = new AuthenticationService();
	private DaoFactory factory = DaoFactory.getInstance();

	public static AuthenticationService getInstance() {
		return instance;
	}

	/**
	 * This method checks entered login and password data in database
	 * 
	 * @param login
	 * @param password
	 * @return {@see User}
	 */
	public User checkLogin(String login, String password) {
		AgentDao agentDao = factory.createAgentDao();
		CustomerDao customerDao = factory.createCustomerDao();
		TravelAgent agent;
		Customer customer;

		if ((agent = agentDao.findAccount(login, password.hashCode())) != null) {
			return agent;
		}
		if ((customer = customerDao.findAccount(login, password.hashCode())) != null) {
			return customer;
		}
		return null;
	}

}
