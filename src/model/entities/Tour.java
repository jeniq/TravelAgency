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

	public Tour(String name, boolean isHot, int price, TourType type) {
		this.name = name;
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

	@Override
	public String toString(){
		return "Tour " +
				name + 
				"[Type: " + type + 
				"; hot tour: " + isHot +
				"; price: " + price + "]"; 
	}
}
