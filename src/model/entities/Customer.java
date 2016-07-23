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
public class Customer extends User{
	private int id;
	private int discount;

	// Constructor
	public Customer(String name, String surname, int discount) {
		super(name, surname);
		this.discount = discount;
	}
	
	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
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
