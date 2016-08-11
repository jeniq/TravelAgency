package com.company.model.entities;

/**
 * This enumaration class contains type of tours that travel agency can offer.
 * 
 * @author Yevhen Hryshchenko
 * @version 16 Jule 2016
 *
 */
public enum TourType {
	VACATION("Vacation"), EXCURSION("Excursion"), SHOPPING("Shopping");

	private final String name;

	private TourType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
