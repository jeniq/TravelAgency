package model.entities;

/**
 * This class implements customer of travel agency. Customer has name, surname
 * and middle name and id number. Customer can have several tour and personal
 * discount. It has summary price of order.
 * 
 * @author Yevhen Hryshchenko
 * @version 16 Jule 2016
 *
 */
public class Customer {
	private int id;
	private String name;
	private String surname;
	private int discount;

	// Constructor
	public Customer(int id, String name, String surname, int discount) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.discount = discount;
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

	public int getDiscount() {
		return discount;
	}
	
	@Override
	public String toString() {
		return "Customer " 
				+ name + " " 
				+ surname + ": " 
				+ "discount " + discount + "%";
	}

}
