package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.dao.TourDao;
import model.dao.jdbc.exceptions.ErrorsConstants;
import model.dao.jdbc.exceptions.JdbcException;
import model.entities.Tour;
import model.entities.TourType;

/**
 * This class performs work with MySQL table 'travels' using JDBC connection. It
 * implements TourDao and realises methods for creating, updating, setting hot
 * and searching tour.
 * 
 * @author Yevhen Hryshchenko
 * @version 20 Jule 2016
 *
 */
public class JdbcTourDao implements TourDao {
	// SQL statements
	final static String CREATE = "INSERT INTO travels (t_name, t_is_hot, t_discount, t_price, t_type, t_start, t_end) VALUES (?, ?, ?, ?, ?, ?)";
	final static String DELETE = "DELETE FROM travels WHERE t_id = ?";
	final static String FIND_BY_ID = "SELECT * FROM travels WHERE t_id = ?";
	final static String FIND_ALL = "SELECT * FROM travels";
	final static String SET_HOT = "UPDATE travels SET t_is_hot=?, t_discount = ?, t_price = ? WHERE t_id=?";
	final static String FIND_BY_NAME = "SELECT * FROM travels WHERE t_name = ?";
	
	@Override
	public void create(Tour tour) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
			query.setString(1, tour.getName());
			query.setBoolean(2, tour.getIsHot());
			query.setInt(3, tour.getDiscount());
			query.setInt(4, tour.getPrice());
			query.setString(5, tour.getType().toString());
			query.setString(6, tour.getStartDate());
			query.setString(7, tour.getEndDate());
			query.executeUpdate();
			ResultSet rs = query.getGeneratedKeys();
			if (rs.next()) {
				tour.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcTourDao.class.getName()).error(e);
			throw new JdbcException(ErrorsConstants.CREATING, ErrorsConstants.TRAVEL);
		}

	}

	@Override
	public boolean delete(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection(); PreparedStatement query = cn.prepareStatement(DELETE)) {
			query.setInt(1, id);
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcTourDao.class.getName()).error(e);
			throw new JdbcException(ErrorsConstants.DELETING, ErrorsConstants.TRAVEL);
		}
	}

	@Override
	public Tour find(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(FIND_BY_ID)) {
			query.setInt(1, id);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				Tour tour = new Tour(rs.getInt(1), // t_id 
						rs.getString(2), // t_name
						rs.getBoolean(3), // t_isHot
						rs.getInt(4), // t_discount
						rs.getInt(5), // t_price
						TourType.valueOf(rs.getString(6).toUpperCase()), // t_type
						rs.getString(7), // t_start
						rs.getString(8)); // t_end
				return tour;
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcTourDao.class.getName()).error(e);
			throw new JdbcException(ErrorsConstants.SEARCHING, ErrorsConstants.TRAVEL);
		}
		return null;
	}

	@Override
	public List<Tour> findAll() {
		try (Connection connection = JdbcDaoFactory.getConnection()) {
			Statement query = connection.createStatement();
			ResultSet rs = query.executeQuery(FIND_ALL);
			List<Tour> tours = new ArrayList<>();
			while (rs.next()) {
				Tour tour = new Tour(rs.getInt(1), // t_id
						rs.getString(2), // t_name
						rs.getBoolean(3), // t_isHot
						rs.getInt(4), // t_discount
						rs.getInt(5), // t_price
						TourType.valueOf(rs.getString(6).toUpperCase()), // t_type
						rs.getString(7), // t_start
						rs.getString(8)); // t_end
				tour.setId(rs.getInt(1));
				tours.add(tour);
			}
			query.close();
			return tours;
		} catch (SQLException e) {
			Logger.getLogger(JdbcTourDao.class.getName()).error(e);
			throw new JdbcException(ErrorsConstants.SEARCHING, ErrorsConstants.TRAVEL);
		}
	}
	
	@Override
	public boolean setHot(Tour tour, boolean isHot, int discount) {
		try (Connection cn = JdbcDaoFactory.getConnection(); PreparedStatement query = cn.prepareStatement(SET_HOT)) {
			query.setBoolean(1, isHot);
			query.setInt(2, discount);
			query.setInt(3, tour.getPrice());
			query.setInt(4, tour.getId());
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcTourDao.class.getName()).error(e);
			throw new JdbcException(ErrorsConstants.UPDATING, ErrorsConstants.TRAVEL);
		}
	}

	@Override
	public Tour find(String name) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(FIND_BY_NAME)) {
			query.setString(1, name);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				Tour tour = new Tour(rs.getInt(1), // t_id
						rs.getString(2), // t_name
						rs.getBoolean(3), // t_isHot
						rs.getInt(4), // t_discount
						rs.getInt(5), // t_price
						TourType.valueOf(rs.getString(6).toUpperCase()), // t_type
						rs.getString(7), // t_start
						rs.getString(8));
				return tour;
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcTourDao.class.getName()).error(e);
			throw new JdbcException(ErrorsConstants.SEARCHING, ErrorsConstants.TRAVEL);
		}
		return null;
	}
}
