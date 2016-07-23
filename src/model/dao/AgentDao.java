package model.dao;

import model.entities.TravelAgent;

public interface AgentDao extends GenericDao<TravelAgent> {
	TravelAgent findAccount(String login, int password);
}
