package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public void create(Tour e) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("INSERT INTO travels VALUES (?, ?, ?, ?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS)) {
			query.setString(1, e.getName());
			query.setBoolean(2, e.isHot());
			query.setInt(3, e.getDiscount());
			query.setInt(4, e.getPrice());
			query.setString(5, e.getType().toString());
			query.setString(6, e.getStartDate());
			query.setString(7, e.getEndDate());
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

	@Override
	public boolean update(Tour e) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(
						"UPDATE travels set t_name=?, t_is_hot=?, t_discount = ?, t_price=?, t_type=?, t_start=?, t_end=?"
								+ "where id=?")) {
			query.setString(1, e.getName());
			query.setBoolean(2, e.isHot());
			query.setInt(3, e.getDiscount());
			query.setInt(4, e.getPrice());
			query.setString(5, e.getType().toString());
			query.setString(6, e.getStartDate());
			query.setString(7, e.getEndDate());
			query.setInt(8, e.getId());
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
				PreparedStatement query = cn.prepareStatement("DELETE from travels where id = ?")) {
			query.setInt(1, id);
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Tour find(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("SELECT * from travels where id = ?")) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				tours.add(new Tour(rs.getInt(1), // t_id
						rs.getString(2), // t_name
						rs.getBoolean(3), // t_isHot
						rs.getInt(4), // t_discount
						rs.getInt(5), // t_price
						TourType.valueOf(rs.getString(6).toUpperCase()), // t_type
						rs.getString(7), // t_start
						rs.getString(8))); // t_end
			}
			query.close();
			return tours;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
