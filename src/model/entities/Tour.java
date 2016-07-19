package model.entities;

/**
 * This class implements tour of tour agency. It contains name of tour, type and
 * price.
 * 
 * @author Yevhen Hryshchenko
 * @version 16 Jule 2016
 *
 */
public class Tour {
	private String name;
	private TourType type;
	private int price;
	private boolean isHot = false;
	private String startDate;
	private String endDate;

	public Tour(String name, String startDate, String endDate, boolean isHot, int price, TourType type) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isHot = isHot;
		this.type = type;
		this.price = price;
	}

	public TourType getType() {
		return type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public boolean isHot() {
		return isHot;
	}

	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString(){
		return "Tour " +
				name + 
				"[Type: " + type +
				"; date:" + startDate + " - " + endDate +
				"; hot tour: " + isHot +
				"; price: " + price + "]"; 
	}
}
