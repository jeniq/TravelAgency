package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import log.LogMessageConstants;
import model.dao.CustomerDao;
import model.entities.Customer;

/**
 * This class performs work with MySQL table 'customer' using JDBC connection.
 * It implements CustomerDao and realises methods for creating, updating,
 * deleting and searching customer.
 * 
 * @author Yevhen Hryshchenko
 * @version 16 June 2016
 */
public class JdbcCustomerDao implements CustomerDao{
	
	@Override
	public void create(Customer customer) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("INSERT INTO customer VALUES (?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS)) {
			query.setInt(1, customer.getId());
			query.setString(2, customer.getName());
			query.setString(3, customer.getSurname());
			query.setInt(4, customer.getDiscount());
			query.executeUpdate();
			ResultSet rs = query.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				customer.setId(id);
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcCustomerDao.class.getName()).error(LogMessageConstants.ERROR_CREATING_CUSTOMER, e);
			throw new RuntimeException();
		}
	}

	// This method updates customer's discount
	@Override
	public boolean update(Customer customer) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("UPDATE customer set c_discount = ? where c_id= ?")) {
			query.setInt(1, customer.getDiscount());
			query.setInt(2, customer.getId());
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcCustomerDao.class.getName()).error(LogMessageConstants.ERROR_SETTING_DISCOUNT, e);
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("DELETE from customer where c_id = ?")) {
			query.setInt(1, id);
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcCustomerDao.class.getName()).error(LogMessageConstants.ERROR_DELETING_CUSTOMER, e);
		}
		return false;
	}

	@Override
	public Customer find(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("SELECT * from customer where c_id = ?")) {
			query.setInt(1, id);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				Customer customer = new Customer(rs.getString(2), rs.getString(3), rs.getInt(4));
				customer.setId(rs.getInt(1));
				return customer;
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcCustomerDao.class.getName()).error(LogMessageConstants.ERROR_SEARCHING_CUSTOMER_ID + id, e);
			throw new RuntimeException();
		}
		return null;
	}

	@Override
	public List<Customer> findAll() {
		try (Connection connection = JdbcDaoFactory.getConnection()) {
			Statement query = connection.createStatement();
			ResultSet rs = query.executeQuery("SELECT * from customer");
			List<Customer> customers = new ArrayList<>();
			while (rs.next()) {
				Customer customer = new Customer(rs.getString(2), rs.getString(3), rs.getInt(4));
				customer.setId(rs.getInt(1));
				customers.add(customer);
			}
			query.close();
			return customers;
		} catch (SQLException e) {
			Logger.getLogger(JdbcCustomerDao.class.getName()).error(LogMessageConstants.ERROR_SEARCHING_CUSTOMER_LIST, e);
			throw new RuntimeException();
		}
	}

	@Override
	public Customer findAccount(String login, int password) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn
						.prepareStatement("SELECT * from customer where c_login = ? and c_password = ?")) {
			query.setString(1, login);
			query.setInt(2, password);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				Customer customer = new Customer(rs.getString(2), rs.getString(3), rs.getInt(4));
				customer.setId(rs.getInt(1));
				return customer;
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcCustomerDao.class.getName()).error(LogMessageConstants.ERROR_SEARCHING_CUSTOMER_ACCOUNT, e);
			throw new RuntimeException();
		}
		return null;
	}

}
