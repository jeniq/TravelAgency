package model.entities;

/**
 * This class implements tour agent of travel agency. Agent has id, name,
 * surname. It has permission set tour like "hot tour", set discounts for
 * customers.
 * 
 * @author Yevhen Hryshchenko
 * @version 16 Jule 2016
 *
 */
public class TravelAgent {
	private int id;
	private String name;
	private String surname;

	// Constructor
	public TravelAgent(int id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
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

	@Override
	public String toString(){
		return "Travel agent " 
				+ name + " " 
				+ surname;
	}
	
}
