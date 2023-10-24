package com.bigcorp.app.correction.faces;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RestaurantFormBean {

	private Long id;
	
	@NotNull
	@Size(min=3, max=20)
	private String name;
	
	private String address;

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

	@Override
	public String toString() {
		return "RestaurantFormBean [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

}
