package model.entities;

public class Order {
	private int id;
	private int customer;
	private int travel;
	private boolean isPaid;
	
	// Constructor
	public Order(int id, int customer, int travel, boolean isPaid) {
		this.id = id;
		this.customer = customer;
		this.travel = travel;
		this.isPaid = isPaid;
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

	public boolean isPaid() {
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
