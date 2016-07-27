package model.dao;

import model.entities.TravelAgent;

public interface AgentDao extends GenericDao<TravelAgent> {
	// This method searches travel agent by login and password
	TravelAgent findAccount(String login, int password);
}
