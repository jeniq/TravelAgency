package model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implents customer of travel agency. Customer should have name,
 * surname and middle name and id number. Customer can have several tours,
 * personal discount. It has summary price of order.
 * 
 * @author Yevhen Hryshchenko
 * @version 16 Jule 2016
 *
 */
public class Customer {
	private int id;
	private String name;
	private String surname;
	private List<Tour> tours = new ArrayList<>();;
	private int discount;
	private double price; // currency is dollars

	// Constructor
	public Customer(String name, String surname, int discount) {
		this.name = name;
		this.surname = surname;
		this.discount = discount;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Tour> getTours() {
		return tours;
	}

	public int getDiscount() {
		return discount;
	}
	
	// Add new tour to the list of tours
	public void addTour(Tour tour) {
		tours.add(tour);
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	/**
	 * This method count price of all customer's orders.
	 * 
	 * @return price of tours
	 */
	public double getPrice() {
		int price = 0;
		for (Tour tour : tours) {
			price += tour.getPrice();
		}
		return price;
	}

	@Override
	public String toString() {
		return "Customer " 
				+ name +
				" " + surname +
				": " + "discount " + discount + "%; " + 
				tours.toString() + 
				"; summary price: " + price + "$";
	}

}
