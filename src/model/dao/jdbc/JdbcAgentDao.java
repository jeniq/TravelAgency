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
public class JdbcAgentDao implements AgentDao {

	@Override
	public void create(TravelAgent agent) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("INSERT INTO agent VALUES (?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS)) {
			query.setInt(1, agent.getId());
			query.setString(2, agent.getName());
			query.setString(3, agent.getSurname());
			query.executeUpdate();

			ResultSet rs = query.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				agent.setId(id);
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcAgentDao.class.getName()).error(LogMessageConstants.ERROR_CREATING_AGENT, e);
			throw new RuntimeException();
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
			query.setInt(1, id);
			query.executeUpdate();
			return true;
		} catch (SQLException e) {
			Logger.getLogger(JdbcAgentDao.class.getName()).error(LogMessageConstants.ERROR_DELETING_AGENT, e);
		}
		return false;
	}

	@Override
	public TravelAgent find(int id) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement("SELECT * from agent where id = ?")) {
			query.setInt(1, id);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				TravelAgent agent = new TravelAgent(rs.getString(2), rs.getString(3));
				agent.setId(rs.getInt(1));
				return agent;
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcAgentDao.class.getName()).error(LogMessageConstants.ERROR_SEARCHING_AGENT_ID + id, e);
		}
		return null;
	}

	@Override
	public List<TravelAgent> findAll() {
		try (Connection cn = JdbcDaoFactory.getConnection()) {
			Statement query = cn.createStatement();
			ResultSet rs = query.executeQuery("SELECT * from agent");
			List<TravelAgent> agents = new ArrayList<>();
			while (rs.next()) {
				TravelAgent agent = new TravelAgent(rs.getString(2), rs.getString(3));
				agent.setId(rs.getInt(1));
				agents.add(agent);
			}
			query.close();
			return agents;
		} catch (SQLException e) {
			Logger.getLogger(JdbcAgentDao.class.getName()).error(LogMessageConstants.ERROR_SEARCHING_AGENT_LIST, e);
			throw new RuntimeException();
		}
	}

	@Override
	public TravelAgent findAccount(String login, int password) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn
						.prepareStatement("SELECT * from agent where a_login = ? and a_password = ?")) {
			query.setString(1, login);
			query.setInt(2, password);
			ResultSet rs = query.executeQuery();
			if (rs.next()) {
				TravelAgent agent = new TravelAgent(rs.getString(2), rs.getString(3));
				agent.setId(rs.getInt(1));
				return agent;
			}
		} catch (SQLException e) {
			Logger.getLogger(JdbcAgentDao.class.getName()).error(LogMessageConstants.ERROR_SEARCHING_AGENT_ACCOUNT, e);
			throw new RuntimeException();
		}
		return null;
	}

}
