package model.entities;

/**
 * This class implements tour of travel agency. It contains id, name of tour,
 * type and price. Tour has start date and expiration date. Also tour can be hot
 * - it means some discount for this one.
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

	// Constructor
	public Tour(String name, boolean isHot, int discount, int price, TourType type, String startDate,
			String endDate) {
		this.name = name;
		this.isHot = isHot;
		this.discount = discount;
		this.price = price;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
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

	@Override
	public String toString(){
		return "Tour " 
				+ name 
				+ "[Type: " + type 
				+ "; date:" + startDate 
				+ " - " + endDate 
				+ "; hot tour: " + isHot 
				+ "; discount: " + discount
				+ "; price: " + price + "]"; 
	}
	
}
