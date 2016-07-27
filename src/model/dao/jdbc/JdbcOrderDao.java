package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.dao.OrderDao;
import model.entities.Order;

/**
 * This class performs work with MySQL table 'order' using JDBC connection. It
 * implements OrderDao and realises methods for creating, updating, deleting and
 * searching user's order.
 * 
 * @author Yevhen Hryshchenko
 * @version 21 Jule 2016
 *
 */
public class JdbcOrderDao implements OrderDao{
	// SQL statements
	final static String CREATE = "INSERT INTO booking (c_id, t_id, b_is_paid, a_id, b_price) VALUES (?, ?, ?, ?, ?)";
	final static String DELETE_BY_ID = "DELETE FROM booking WHERE b_id = ?";
	final static String FIND_BY_ID = "SELECT * FROM booking WHERE b_id = ?";
	final static String FIND_BY_CUST_TRAV = "SELECT * FROM booking WHERE c_id = ? AND t_id = ?";
	final static String FIND_ALL = "SELECT * from booking";
	final static String FIND_ALL_BY_CUST_ID = "SELECT booking.b_id, travels.t_name, travels.t_price, booking.b_is_paid, agent.a_name, agent.a_surname, booking.b_price, t_start, t_end from booking, travels, agent WHERE agent.a_id = booking.a_id AND travels.t_id = booking.t_id AND booking.c_id = ?";
	final static String SET_PRICE = "UPDATE booking SET b_price = ? WHERE c_id = ? AND t_id = ?";
	final static String SET_AGENT = "UPDATE booking SET a_id = ? WHERE b_id = ?";
	final static String DELETE_BY_NAME = "DELETE FROM booking WHERE t_id = ? AND c_id = ?";
	final static String UPDATE_PAYMENT = "UPDATE booking SET b_is_paid = ? WHERE c_id = ? AND t_id = ?";

	@Override
	public void create(Order order) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
			query.setInt(1, order.getCustomerId());
			query.setInt(2, order.gettId());
			query.setBoolean(3, order.getIsPaid());
			query.setInt(4, order.getAgent().getId());
			query.setInt(5, order.getFinalPrice());
			query.executeUpdate();
			ResultSet rs = query.getGeneratedKeys();
			if (rs.next()) {				
				order.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(e);
			throw new RuntimeException();
		}
	}

	// This method sets payment status of order
	@Override
	public boolean update(Order order) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(UPDATE_PAYMENT)) {
			query.setBoolean(1, order.getIsPaid());
			query.setInt(2, order.getCustomerId());
			query.setInt(3, order.gettId());
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(e);
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(DELETE_BY_ID)) {
			query.setInt(1, id);
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(e);
			throw new RuntimeException();
		}
	}

	@Override
	public Order find(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(FIND_BY_ID)) {
			query.setInt(1, id);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				Order order = new Order(rs.getInt(1), rs.getInt(2), new Boolean(rs.getBoolean(4)));
				return order;
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(e);
			throw new RuntimeException();
		}
		return null;
	}
	
	@Override
	public Order find(int custId, int travId) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(FIND_BY_CUST_TRAV)) {
			query.setInt(1, custId);
			query.setInt(2, travId);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getInt(3));
				Order order = new Order(rs.getInt(1), rs.getInt(2), new Boolean(rs.getBoolean(4)));
				return order;
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(e);
			throw new RuntimeException();
		}
		return null;
	}

	@Override
	public List<Order> findAll() {
		try (Connection connection = JdbcDaoFactory.getConnection()) {
			Statement query = connection.createStatement();
			ResultSet rs = query.executeQuery(FIND_ALL);
			List<Order> orders = new ArrayList<>();
			while (rs.next()) {
				Order order = new Order(rs.getInt(1), rs.getInt(2), new Boolean(rs.getBoolean(4)));
				orders.add(order);
			}
			query.close();
			return orders;
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(e);
			throw new RuntimeException();
		}
	}

	
	// This method searches orders by user id
	@Override
	public List<Order> findAll(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(FIND_ALL_BY_CUST_ID)) {
			query.setInt(1, id);
			ResultSet rs = query.executeQuery();
			List<Order> orders = new ArrayList<>();
			while (rs.next()) {
				Order order = new Order(rs.getInt(1), rs.getString(2), rs.getInt(3), new Boolean(rs.getBoolean(4)),
						rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9));
				orders.add(order);
			}
			query.close();
			return orders;
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(e);
			throw new RuntimeException();
		}
	}

	@Override
	public boolean setPrice(Order order) {
		try (Connection cn = JdbcDaoFactory.getConnection(); PreparedStatement query = cn.prepareStatement(SET_PRICE)) {
			query.setInt(1, order.getFinalPrice());
			query.setInt(2, order.getCustomerId());
			query.setInt(3, order.gettId());
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(e);
			throw new RuntimeException();
		}
	}

	@Override
	public boolean setAgent(Order order, int id) {
		try (Connection cn = JdbcDaoFactory.getConnection(); PreparedStatement query = cn.prepareStatement(SET_AGENT)) {
			query.setInt(1, id);
			query.setInt(2, order.getId());
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(e);
			throw new RuntimeException();
		}
	}

	@Override
	public boolean delete(String name, int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(DELETE_BY_NAME)) {
			query.setString(1, name);
			query.setInt(2, id);
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(e);
			throw new RuntimeException();
		}
	}
	
}
