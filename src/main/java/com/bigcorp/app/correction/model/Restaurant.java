package com.bigcorp.app.correction.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "RESTAURANT")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	private String name;
	
	@ManyToOne
	@JoinColumn(name="RESTAURANT_TYPE_ID")
	private RestaurantType restaurantType;

	private String address;

	private String phone;
	
	private Integer score;
	
	private Boolean active;
	
	@Enumerated(EnumType.STRING)
	private TypePrix typePrix;
	
	private LocalDate dateCreation;
	
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

	public TypePrix getTypePrix() {
		return typePrix;
	}

	public void setTypePrix(TypePrix typePrix) {
		this.typePrix = typePrix;
	}

	public LocalDate getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public RestaurantType getRestaurantType() {
		return restaurantType;
	}

	public void setRestaurantType(RestaurantType restaurantType) {
		this.restaurantType = restaurantType;
	}
	
	public void associateWith(RestaurantType restaurantType) {
		setRestaurantType(restaurantType);
		if(this.restaurantType != null) {
			this.restaurantType.getRestaurants().add(this);
		}
	}

}
