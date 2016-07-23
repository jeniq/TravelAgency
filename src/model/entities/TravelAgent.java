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
public class TravelAgent extends User{
	private int id;

	// Constructor
	public TravelAgent(String name, String surname) {
		super(name, surname);
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String toString(){
		return "Travel agent " 
				+ name + " " 
				+ surname;
	}
	
}
