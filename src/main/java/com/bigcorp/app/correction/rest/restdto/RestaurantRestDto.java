package com.bigcorp.app.correction.rest.restdto;

import com.bigcorp.app.correction.model.Restaurant;

public class RestaurantRestDto {

	private Long id;

	private String name;

	private String address;

	private Boolean active;

	private String longName;

	public RestaurantRestDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public RestaurantRestDto(Restaurant restaurant) {
		this.id = restaurant.getId();
		this.name = restaurant.getName();
		this.address = restaurant.getAddress();
		this.active = restaurant.getActive();
		this.longName = restaurant.getName() + "long";
	}

	public Restaurant toRestaurant() {
		Restaurant newRestaurant = new Restaurant();
		newRestaurant.setId(this.id);
		newRestaurant.setName(this.name);
		newRestaurant.setAddress(this.address);
		newRestaurant.setActive(this.active);
		return newRestaurant;
	}

}
