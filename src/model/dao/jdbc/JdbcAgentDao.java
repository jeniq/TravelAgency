package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dao.AgentDao;
import model.entities.TravelAgent;

/**
 * This class performs work with MySQL table 'agent' using JDBC connection. It
 * implements AgentDao and realises methods for creating, deleting and searching
 * customer.
 * 
 * @author Yevhen Hryshchenko
 * @version 16 June 2016
 */
public class JdbcAgentDao implements AgentDao{
	
	@Override
	public void create(TravelAgent e) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("INSERT INTO agent VALUES (?, ?)",
						Statement.RETURN_GENERATED_KEYS)) {
			query.setString(1, e.getName());
			query.setString(2, e.getSurname());
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
	public boolean update(TravelAgent e) {
		return false;
	}

	@Override
	public boolean delete(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("DELETE from agent where id = ?")) {
			query.setLong(1, id);
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public TravelAgent find(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("SELECT * from agent where id = ?")) {
			query.setLong(1, id);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				TravelAgent agent = new TravelAgent(rs.getInt(1), rs.getString(2), rs.getString(3));
				return agent;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<TravelAgent> findAll() {
		try (Connection connection = JdbcDaoFactory.getConnection()) {
			Statement query = connection.createStatement();
			ResultSet rs = query.executeQuery("SELECT * from agent");
			List<TravelAgent> agents = new ArrayList<>();
			while (rs.next()) {
				agents.add(new TravelAgent(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			query.close();
			return agents;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
