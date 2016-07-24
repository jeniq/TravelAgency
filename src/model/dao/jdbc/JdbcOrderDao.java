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
import model.dao.OrderDao;
import model.entities.Order;

/**
 * This class performs work with MySQL table 'order' using JDBC connection. It
 * implements OrderDao and realises methods for creating, updating, deleting and
 * searching tour.
 * 
 * @author Yevhen Hryshchenko
 * @version 21 Jule 2016
 *
 */
public class JdbcOrderDao implements OrderDao{
	
	@Override
	public void create(Order order) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("INSERT INTO booking VALUES (?, ?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS)) {
			query.setInt(1, order.getId());
			query.setInt(2, order.getCustomer());
			query.setInt(3, order.gettId());
			query.setBoolean(4, order.getIsPaid());
			query.setInt(5, order.getAgent().getId());
			query.setInt(6, order.getFinalPrice());
			query.executeUpdate();
			ResultSet rs = query.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				order.setId(id);
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(LogMessageConstants.ERROR_CREATING_ORDER, e);
			throw new RuntimeException();
		}
	}

	// This method sets payment status of order
	@Override
	public boolean update(Order order) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn
						.prepareStatement("UPDATE booking set b_is_paid = ? where c_id = ? and t_id = ?")) {
			query.setBoolean(1, order.getIsPaid());			
			query.setInt(2, order.getCustomer());
			query.setInt(3, order.gettId());
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(LogMessageConstants.ERROR_CREATING_ORDER, e);
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("DELETE from booking where b_id = ?")) {
			query.setInt(1, id);
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(LogMessageConstants.ERROR_DELETING_ORDER_ID + id, e);
		}
		return false;
	}

	@Override
	public Order find(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("SELECT * from booking where b_id = ?")) {
			query.setInt(1, id);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				Order order = new Order(rs.getInt(2), rs.getInt(3), new Boolean(rs.getBoolean(4)));
				order.setId(rs.getInt(1));
				return order;
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(LogMessageConstants.ERROR_SEARCHING_ORDER_ID + id, e);
			throw new RuntimeException();
		}
		return null;
	}
	
	@Override
	public Order find(int custId, int travId) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("SELECT * from booking where c_id = ? and t_id = ?")) {
			query.setInt(1, custId);
			query.setInt(2, travId);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				Order order = new Order(rs.getInt(2), rs.getInt(3), new Boolean(rs.getBoolean(4)));
				order.setId(rs.getInt(1));
				return order;
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(LogMessageConstants.ERROR_SEARCHING_ORDER, e);
			throw new RuntimeException();
		}
		return null;
	}

	@Override
	public List<Order> findAll() {
		try (Connection connection = JdbcDaoFactory.getConnection()) {
			Statement query = connection.createStatement();
			ResultSet rs = query.executeQuery("SELECT * from booking");
			List<Order> orders = new ArrayList<>();
			while (rs.next()) {
				Order order = new Order(rs.getInt(2), rs.getInt(3), new Boolean(rs.getBoolean(4)));
				order.setId(rs.getInt(1));
				orders.add(order);
			}
			query.close();
			return orders;
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(LogMessageConstants.ERROR_SEARCHING_ORDER_LIST, e);
			throw new RuntimeException();
		}
	}

	
	// This method searches orders by user id
	@Override
	public List<Order> findAll(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("SELECT travels.t_name, travels.t_price, booking.b_is_paid, agent.a_name, agent.a_surname, booking.b_price, booking.b_id, t_start, t_end from booking, travels, agent WHERE agent.a_id = booking.a_id AND travels.t_id = booking.t_id AND booking.c_id = ?")) {
			query.setInt(1, id);
			ResultSet rs = query.executeQuery();
			List<Order> orders = new ArrayList<>();
			while (rs.next()) {
				Order order = new Order(rs.getString(1), rs.getInt(2), new Boolean(rs.getBoolean(3)), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(8), rs.getString(9));
				order.setId(rs.getInt(7));
				orders.add(order);
			}
			query.close();
			return orders;
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(LogMessageConstants.ERROR_SEARCHING_ORDER_LIST, e);
			throw new RuntimeException();
		}
	}

	@Override
	public boolean setPrice(Order order) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn
						.prepareStatement("UPDATE booking set b_price = ? where c_id = ? and t_id = ?")) {
			query.setInt(1, order.getFinalPrice());			
			query.setInt(2, order.getCustomer());
			query.setInt(3, order.gettId());
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(LogMessageConstants.ERROR_CREATING_ORDER, e);
		}
		return false;
	}

	@Override
	public boolean setAgent(Order order, int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn
						.prepareStatement("UPDATE booking set a_id = ? where b_id = ?")) {
			query.setInt(1, id);
			query.setInt(2, order.getId());
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcOrderDao.class.getName()).error(LogMessageConstants.ERROR_CREATING_ORDER, e);
		}
		return false;
	}

}
