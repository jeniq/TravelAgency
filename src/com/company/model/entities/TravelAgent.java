package com.company.model.entities;

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
	
	// Constructor
	public TravelAgent(String name, String surname) {
		super(name, surname);
	}
	
	// Constructor
	public TravelAgent(int id, String name, String surname) {
		super(id, name, surname);
	}

	@Override
	public String toString(){
		return "Travel agent "
				+ id + " "
				+ name + " " 
				+ surname;
	}
	
}
