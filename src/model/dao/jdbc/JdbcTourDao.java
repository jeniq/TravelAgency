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
import model.dao.TourDao;
import model.entities.Tour;
import model.entities.TourType;

/**
 * This class performs work with MySQL table 'travels' using JDBC connection. It
 * implements TourDao and realises methods for creating, updating, deleting and
 * searching tour.
 * 
 * @author Yevhen Hryshchenko
 * @version 20 Jule 2016
 *
 */
public class JdbcTourDao implements TourDao {
	
	@Override
	public void create(Tour tour) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("INSERT INTO travels VALUES (?, ?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS)) {
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
				int id = rs.getInt(1);
				tour.setId(id);
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcTourDao.class.getName()).error(LogMessageConstants.ERROR_CREATING_TRAVEL, e);
			throw new RuntimeException();
		}

	}

	@Override
	public boolean update(Tour tour) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(
						"UPDATE travels set t_name=?, t_is_hot=?, t_discount = ?, t_price=?, t_type=?, t_start=?, t_end=?"
								+ "where t_id=?")) {
			query.setString(1, tour.getName());
			query.setBoolean(2, tour.getIsHot());
			query.setInt(3, tour.getDiscount());
			query.setInt(4, tour.getPrice());
			query.setString(5, tour.getType().toString());
			query.setString(6, tour.getStartDate());
			query.setString(7, tour.getEndDate());
			query.setInt(8, tour.getId());
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcTourDao.class.getName()).error(LogMessageConstants.ERROR_UPDATING_TRAVEL, e);
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("DELETE from travels where t_id = ?")) {
			query.setInt(1, id);
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcTourDao.class.getName()).error(LogMessageConstants.ERROR_DELETING_TRAVEL_ID + id, e);
		}
		return false;
	}

	@Override
	public Tour find(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("SELECT * from travels where t_id = ?")) {
			query.setInt(1, id);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				Tour tour = new Tour(rs.getString(2), // t_name
						rs.getBoolean(3), // t_isHot
						rs.getInt(4), // t_discount
						rs.getInt(5), // t_price
						TourType.valueOf(rs.getString(6).toUpperCase()), // t_type
						rs.getString(7), // t_start
						rs.getString(8));
				tour.setId(rs.getInt(1));
				return tour;
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcTourDao.class.getName()).error(LogMessageConstants.ERROR_SEARCHING_TRAVEL_ID, e);
			throw new RuntimeException();
		}
		return null;
	}

	@Override
	public List<Tour> findAll() {
		try (Connection connection = JdbcDaoFactory.getConnection()) {
			Statement query = connection.createStatement();
			ResultSet rs = query.executeQuery("SELECT * from travels");
			List<Tour> tours = new ArrayList<>();
			while (rs.next()) {
				Tour tour = new Tour(rs.getString(2), // t_name
						rs.getBoolean(3), // t_isHot
						rs.getInt(4), // t_discount
						rs.getInt(5), // t_price
						TourType.valueOf(rs.getString(6).toUpperCase()), // t_type
						rs.getString(7), // t_start
						rs.getString(8));
				tour.setId(rs.getInt(1));
				tours.add(tour);
			}
			query.close();
			return tours;
		} catch (SQLException e) {
			Logger.getLogger(JdbcTourDao.class.getName()).error(LogMessageConstants.ERROR_SEARCHING_TRAVEL_LIST, e);
			throw new RuntimeException();
		}
	}
	
	@Override
	public boolean setHot(int id, boolean isHot, int discount) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(
						"UPDATE travels set t_is_hot=?, t_discount = ? where t_id=?")) {
			query.setBoolean(1, isHot);
			query.setInt(2, discount);
			query.setInt(3, id);
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcTourDao.class.getName()).error(LogMessageConstants.ERROR_UPDATING_TRAVEL, e);
		}
		return false;
	}
}
