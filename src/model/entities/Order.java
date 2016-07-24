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
	private String tName;
	private int tPrice;
	private int orderId;
	private int customer;
	private int finalPrice;
	private boolean isPaid = false;
	private TravelAgent agent;
	private Tour travel;
	private String start;
	private String end;
	
	// Constructor
	public Order(int customer, int travel, boolean isPaid) {
		this.customer = customer;
		this.travel.setId(travel);
		this.isPaid = isPaid;
	}
	
	public Order(int customer, Tour travel){
		this.customer = customer;
		this.travel = travel;
	}
	public Order(String tName, int tPrice, boolean isPaid, String aName, String aSurname, int finalPrice, String start, String end){
		this.tName = tName;
		this.tPrice = tPrice;
		this.isPaid = isPaid;
		agent = new TravelAgent(aName, aSurname);
		this.finalPrice = finalPrice;
		this.start = start;
		this.end = end;
	}
	
	public Order(String travelName, int price, boolean isPaid){
		this.tName = travelName;
		this.tPrice = price;
		this.isPaid = isPaid;
	}
	
	public String getTravelName() {
		return tName;
	}

	public void setTravelName(String travelName) {
		this.tName = travelName;
	}

	public int gettPrice() {
		return tPrice;
	}

	
	
	public void setPrice(Tour travel, User user) {
		finalPrice = travel.getPrice();
		int discount = 0;
		// set travel's price including discount
		if (travel.getDiscount() > 0){
			discount = travel.getDiscount(); 
		}else if (((Customer)user).getDiscount() > 0){
			discount = ((Customer)user).getDiscount();
		}
		finalPrice = (int) (finalPrice * (1 - discount/100.));
	}

	public int getId() {
		return orderId;
	}

	public void setId(int id) {
		this.orderId = id;
	}

	public int getCustomer() {
		return customer;
	}

	public int gettId() {
		return travel.getId();
	}
	
	public Tour getTravel() {
		return travel;
	}

	public boolean getIsPaid() {
		return isPaid;
	}	

	public TravelAgent getAgent() {
		return agent;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public void setAgent(TravelAgent agent) {
		this.agent = agent;
	}

	
	
	public void setPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public int getFinalPrice() {
		return finalPrice;
	}
	
}
