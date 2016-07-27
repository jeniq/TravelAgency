package model.entities;

/**
 * This class implements tour of travel agency. It contains id, name of tour,
 * type, discount and price. Tour has start date and expiration date. Also tour
 * can be hot - it means some discount for this one.
 * 
 * @author Yevhen Hryshchenko
 * @version 16 Jule 2016
 *
 */
public class Tour {
	private int id;
	private String name;
	private boolean isHot = false;
	private int discount;
	private int price;
	private TourType type;
	private String startDate;
	private String endDate;

	//Constructor
	public Tour() {
		super();
	}
	
	// Constructor
	public Tour(int id, String name, boolean isHot, int discount, int price, TourType type, String startDate,
			String endDate) {
		this.id = id;
		this.name = name;
		this.isHot = isHot;
		this.discount = discount;
		this.price = price;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	/**
	 * This method sets travel's price with discount.
	 * 
	 * @param discount in percent
	 */
	public void setDiscountPrice(int discount) {
		price = (int) (price * (1 - discount / 100.0));
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// This method return travel's price to primary state without discount
	public void cancelDiscountPrice() {
		price = price * 100 / (100 - discount);
		discount = 0;
	}
	
	public int getOriginPrice() {
		return price * 100 / (100 - discount);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getIsHot() {
		return isHot;
	}

	public int getDiscount() {
		return discount;
	}

	public int getPrice() {
		return price;
	}

	public TourType getType() {
		return type;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString(){
		return "Tour " 
				+ id + " "
				+ name + " [" 
				+ "Type: " + type 
				+ "; date:" + startDate + " - " + endDate 
				+ "; hot tour: " + isHot 
				+ "; discount: " + discount
				+ "; price: " + price + "]"; 
	}
	
}
