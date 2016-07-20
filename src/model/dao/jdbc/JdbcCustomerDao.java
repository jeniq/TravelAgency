package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public void create(Customer e) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("INSERT INTO customer VALUES (?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS)) {
			query.setString(1, e.getName());
			query.setString(2, e.getSurname());
			query.setInt(3, e.getDiscount());
			query.executeUpdate();
			ResultSet rs = query.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				e.setId(id);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	// This method use for changing customer's discount
	@Override
	public boolean update(Customer e) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("UPDATE customer set t_discount = ? where id=?")) {
			query.setInt(1, e.getDiscount());
			query.setInt(2, e.getId());
			query.executeUpdate();
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("DELETE from customer where id = ?")) {
			query.setInt(1, id);
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Customer find(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("SELECT * from customer where id = ?")) {
			query.setInt(1, id);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				Customer customer = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
				customers.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			query.close();
			return customers;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
