package model.entities;

public class User {
	protected String name;
	protected String surname;
	protected int id;
	private String permissions;
	
	public User(String name, String surname) {
		this.name = name;
		this.surname = surname;
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

	/**
	 * This method defines permissions for user.
	 * @param user 
	 * @return type of permission
	 */
	public String setPermissions(User user){
		if (user == null){
			return null;
		}
		if (user instanceof TravelAgent){
			return permissions = "agent";
		}else{
			return permissions = "user";
		}
	}
	
	public String getPermissions(){
		return permissions;
	}
}
