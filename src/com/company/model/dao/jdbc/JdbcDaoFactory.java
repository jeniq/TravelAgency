package com.company.model.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.company.model.dao.AgentDao;
import com.company.model.dao.CustomerDao;
import com.company.model.dao.DaoFactory;
import com.company.model.dao.OrderDao;
import com.company.model.dao.TourDao;

/**
 * This class implements DAO factory for JDBC connection.
 * 
 * @author Yevhen Hryshchenko
 * @version 16 Jule 2016
 *
 */
public class JdbcDaoFactory extends DaoFactory {

	private static DataSource ds;
	private InitialContext ic;

	// Costructor creates object that provides connection pooling
	public JdbcDaoFactory() {
		try {
			ic = new InitialContext();

			ds = (DataSource) ic.lookup("java:comp/env/jdbc/travel_db");
		} catch (NamingException e) {
			Logger.getLogger(JdbcDaoFactory.class.getName()).error(e);
			new RuntimeException();
		}
	}

	@Override
	public AgentDao createAgentDao() {
		return new JdbcAgentDao();
	}

	@Override
	public CustomerDao createCustomerDao() {
		return new JdbcCustomerDao();
	}

	@Override
	public TourDao createTourDao() {
		return new JdbcTourDao();
	}

	@Override
	public OrderDao createOrderDao() {
		return new JdbcOrderDao();
	}

	/**
	 * This method returns pooled connection
	 * 
	 * @return connection to database
	 * @throws SQLException
	 */
	static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

}
