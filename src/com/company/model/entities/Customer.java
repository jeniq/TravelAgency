package com.company.model.entities;

/**
 * This class implements customer of travel agency. Customer has name, surname
 * and id. Customer can have personal discount.
 * 
 * @author Yevhen Hryshchenko
 * @version 16 Jule 2016
 *
 */
public class Customer extends User {
	private int discount;

	// Constructor
	public Customer(int id, String name, String surname, int discount) {
		super(id, name, surname);
		this.discount = discount;
	}

	public int getDiscount() {
		return discount;
	}

	@Override
	public String toString() {
		return "Customer " 
				+ id + " "
				+ name + " " 
				+ surname + ": " 
				+ "discount " + discount + "%";
	}

}
