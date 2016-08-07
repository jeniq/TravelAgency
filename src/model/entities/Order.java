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
	private int orderId;
	private TravelAgent agent;
	private Tour travel;
	private int finalPrice;
	private int customerId;
	private boolean isPaid = false;
	
	// Constructor
	public Order(int orderId, int customerId, int travelId, boolean isPaid) {
		travel = new Tour();
		travel.setId(travelId);
		this.orderId = orderId;
		this.customerId = customerId;
		this.isPaid = isPaid;
	}
	
	// Constructor
	public Order(User customer, Tour travel) {
		this.customerId = customer.getId();
		this.travel = travel;
	}

	// Constructor
	public Order(int orderId, String tName, int tPrice, boolean isPaid, String aName, String aSurname, int finalPrice,
			String start, String end) {
		this.orderId = orderId;
		travel = new Tour();
		travel.setName(tName);
		travel.setPrice(tPrice);
		travel.setStartDate(start);
		travel.setEndDate(end);
		this.isPaid = isPaid;
		agent = new TravelAgent(aName, aSurname);
		this.finalPrice = finalPrice;
	}
	
	/**
	 * This method sets total order's price after checking user/travel discount
	 * 
	 * @param travel ordered travel
	 * @param user customer
	 */
	public void setPrice(Tour travel, User user) {
		int discount = 0;
		if (travel.getDiscount() == 0) {
			if (((Customer) user).getDiscount() > 0) {
				discount = ((Customer) user).getDiscount();
			}
		}
		finalPrice = (int) (travel.getPrice() * (1 - discount / 100.));
	}

	public int getId() {
		return orderId;
	}

	public void setId(int id) {
		this.orderId = id;
	}

	public int getCustomerId() {
		return customerId;
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
		return travel.getStartDate();
	}

	public String getEnd() {
		return travel.getEndDate();
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
	
	public String getTravelName() {
		return travel.getName();
	}

	public int getTravelPrice() {
		return travel.getPrice();
	}

	public void setFinalPrice(int finalPrice) {
		this.finalPrice = finalPrice;
	}
	
}
