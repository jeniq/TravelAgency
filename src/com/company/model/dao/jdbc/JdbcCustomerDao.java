package com.company.model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.company.model.dao.CustomerDao;
import com.company.model.dao.jdbc.exceptions.ErrorsConstants;
import com.company.model.dao.jdbc.exceptions.JdbcException;
import com.company.model.entities.Customer;

/**
 * This class performs work with MySQL table 'customer' using JDBC connection.
 * It implements CustomerDao and realises methods for creating, searching and
 * setting customer's discount.
 * 
 * @author Yevhen Hryshchenko
 * @version 16 June 2016
 */
public class JdbcCustomerDao implements CustomerDao {
	// SQL statements
	final static String CREATE = "INSERT INTO customer (c_name, c_surname, c_discount) VALUES (?, ?, ?)";
	final static String FIND_BY_ID = "SELECT * FROM customer WHERE c_id = ?";
	final static String FIND_ALL = "SELECT * FROM customer";
	final static String FIND_BY_ACCOUNT = "SELECT * FROM customer WHERE c_login = ? AND c_password = ?";
	final static String SET_DISCOUNT = "UPDATE customer set c_discount = ? where c_id= ?";

	@Override
	public void create(Customer customer) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
			query.setString(1, customer.getName());
			query.setString(2, customer.getSurname());
			query.setInt(3, customer.getDiscount());
			query.executeUpdate();
			ResultSet rs = query.getGeneratedKeys();
			if (rs.next()) {
				customer.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcCustomerDao.class.getName()).error(e);
			throw new JdbcException(ErrorsConstants.CREATING, ErrorsConstants.CUSTOMER);
		}
	}

	@Override
	public boolean delete(int id) {
		return false;
	}

	@Override
	public Customer find(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(FIND_BY_ID)) {
			query.setInt(1, id);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				return customer;
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcCustomerDao.class.getName()).error(e);
			throw new JdbcException(ErrorsConstants.SEARCHING, ErrorsConstants.CUSTOMER);
		}
		return null;
	}

	@Override
	public List<Customer> findAll() {
		try (Connection connection = JdbcDaoFactory.getConnection()) {
			Statement query = connection.createStatement();
			ResultSet rs = query.executeQuery(FIND_ALL);
			List<Customer> customers = new ArrayList<>();
			while (rs.next()) {
				Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				customers.add(customer);
			}
			query.close();
			return customers;
		} catch (SQLException e) {
			Logger.getLogger(JdbcCustomerDao.class.getName()).error(e);
			throw new JdbcException(ErrorsConstants.SEARCHING, ErrorsConstants.CUSTOMER);
		}
	}

	@Override
	public Customer findAccount(String login, int password) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(FIND_BY_ACCOUNT)) {
			query.setString(1, login);
			query.setInt(2, password);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				return customer;
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcCustomerDao.class.getName()).error(e);
			throw new JdbcException(ErrorsConstants.SEARCHING, ErrorsConstants.CUSTOMER);
		}
		return null;
	}

	@Override
	public boolean setDiscount(int id, int discount) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(SET_DISCOUNT)) {
			query.setInt(1, discount);
			query.setInt(2, id);
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcCustomerDao.class.getName()).error(e);
			throw new JdbcException(ErrorsConstants.UPDATING, ErrorsConstants.DISCOUNT);
		}
	}

}
