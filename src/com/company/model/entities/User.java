package com.company.model.entities;

/**
 * This implements user of travel agent. It has name, surname and permissions.
 * This class sets permissions depend on child class.
 * 
 * @author Yevhen Hryshchenko
 * @version 25 Jule 2016
 * 
 */
public class User {
	protected int id;
	protected String name;
	protected String surname;
	private String permissions;
	
	// Constructor
	public User(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	// Constructor
	public User(int id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
	}
	
	/**
	 * This method defines permissions for user.
	 * 
	 * @param user
	 * @return type of permission
	 */
	public String setPermissions(User user) {
		if (user == null) {
			return null;
		}
		if (user instanceof TravelAgent) {
			return permissions = "agent";
		} else {
			return permissions = "user";
		}
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}
	
	public String getPermissions(){
		return permissions;
	}
}
