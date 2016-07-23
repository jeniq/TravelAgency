package model.entities;

public abstract class User {
	abstract void setId(int id);
	abstract int getId();
	
	protected String name;
	protected String surname;
	
	public User(String name, String surname) {
		this.name = name;
		this.surname = surname;
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
	 * @return name of permission
	 */
	public String getPermission(User user){
		if (user == null){
			return null;
		}
		if (user instanceof TravelAgent){
			return "agent";
		}else{
			return "user";
		}
	}
}
