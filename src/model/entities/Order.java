package model.entities;

/**
 * This class implements customer's order for travel. It contains id, customer's
 * id, travel's id and status of payment.
 * 
 * @author Yevhen Hryshchenko
 * @version 21 Jule 2016
 *
 */
public class Order {
	private String travelName;
	private int price;
	private int id;
	private int customer;
	private int travel;
	private boolean isPaid;
	
	// Constructor
	public Order(int customer, int travel, boolean isPaid) {
		this.customer = customer;
		this.travel = travel;
		this.isPaid = isPaid;
	}
	
	public Order(String travelName, int price, boolean isPaid){
		this.travelName = travelName;
		this.price = price;
		this.isPaid = isPaid;
	}
	
	public String getTravelName() {
		return travelName;
	}

	public void setTravelName(String travelName) {
		this.travelName = travelName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomer() {
		return customer;
	}

	public int getTravel() {
		return travel;
	}

	public boolean getIsPaid() {
		return isPaid;
	}	

	@Override
	public String toString() {
		return "Order " 
				+ id
				+ ": " + travel
				+ ", " + customer 
				+ ", paid: " + isPaid;
	}
	
}
