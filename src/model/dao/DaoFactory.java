package model.dao;

/**
 * This class contains the access mechanism that 
 * requires to work with the data source.
 * 
 * @author Yevhen Hryshchenko
 * @version 16 Jule 2016
 *
 */
public abstract class DaoFactory {
	// Creates object to access source of agent data
	public abstract AgentDao createAgentDao();
	// Creates object to access source of customer data
	public abstract CustomerDao createCustomerDao();
	// Creates object to access source of tour data 
	public abstract TourDao createTourDao();
	
	/**
	 * This method creates new instance of DAO factory
	 * @return factory of data access objects
	 */
	public static DaoFactory getInstance(){
		try {
			return (DaoFactory) Class.forName("model.dao.jdbc.JdbcDaoFactory").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
