package model.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.dao.AgentDao;
import model.entities.TravelAgent;

/**
 * This class performs work with MySQL table 'agent' using JDBC connection. It
 * implements AgentDao and realises methods for creating and searching travel
 * agent.
 * 
 * @author Yevhen Hryshchenko
 * @version 16 June 2016
 */
public class JdbcAgentDao implements AgentDao {
	// SQL statements
	final static String CREATE = "INSERT INTO agent (a_name, a_surname) VALUES (?, ?)";
	final static String FIND_BY_ID = "SELECT * FROM agent WHERE id = ?";
	final static String FIND_ALL = "SELECT * FROM agent";
	final static String FIND_ACCOUNT = "SELECT * FROM agent WHERE a_login = ? AND a_password = ?";

	@Override
	public void create(TravelAgent agent) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
			query.setString(1, agent.getName());
			query.setString(2, agent.getSurname());
			query.executeUpdate();

			ResultSet rs = query.getGeneratedKeys();
			if (rs.next()) {
				agent.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcAgentDao.class.getName()).error(e);
			throw new RuntimeException();
		}
	}

	@Override
	public boolean delete(int id) {
		return false;
	}

	@Override
	public TravelAgent find(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(FIND_BY_ID)) {
			query.setInt(1, id);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				TravelAgent agent = new TravelAgent(rs.getInt(1), rs.getString(2), rs.getString(3));
				return agent;
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcAgentDao.class.getName()).error(e);
			throw new RuntimeException();
		}
		return null;
	}

	@Override
	public List<TravelAgent> findAll() {
		try (Connection cn = JdbcDaoFactory.getConnection()) {
			Statement query = cn.createStatement();
			ResultSet rs = query.executeQuery(FIND_ALL);
			List<TravelAgent> agents = new ArrayList<>();
			while (rs.next()) {
				TravelAgent agent = new TravelAgent(rs.getInt(1), rs.getString(2), rs.getString(3));
				agents.add(agent);
			}
			query.close();
			return agents;
		} catch (SQLException e) {
			Logger.getLogger(JdbcAgentDao.class.getName()).error(e);
			throw new RuntimeException();
		}
	}

	@Override
	public TravelAgent findAccount(String login, int password) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(FIND_ACCOUNT)) {
			query.setString(1, login);
			query.setInt(2, password);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				TravelAgent agent = new TravelAgent(rs.getInt(1), rs.getString(2), rs.getString(3));
				return agent;
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcAgentDao.class.getName()).error(e);
			throw new RuntimeException();
		}
		return null;
	}

}
