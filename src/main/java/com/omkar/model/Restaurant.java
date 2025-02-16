package com.omkar.model;

public class Restaurant {
	private int restaurantId;
	private String name;
	private String address;
	private String phone;
	private float rating;
	private String cuisineType;
	private int isAvailable;
	private String eta;
	private int adminUserId;
	private String imagePath;
	

	public Restaurant() {
	}


	public Restaurant(int restaurantId, String name, String address, String phone, float rating, String cuisineType,
			int isAvailable, String eta, int adminUserId, String imagePath) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.rating = rating;
		this.cuisineType = cuisineType;
		this.isAvailable = isAvailable;
		this.eta = eta;
		this.adminUserId = adminUserId;
		this.imagePath = imagePath;
	}


	public int getRestaurantId() {
		return restaurantId;
	}


	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
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


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public float getRating() {
		return rating;
	}


	public void setRating(float rating) {
		this.rating = rating;
	}


	public String getCuisineType() {
		return cuisineType;
	}


	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}


	public int getIsAvailable() {
		return isAvailable;
	}


	public void setIsAvailable(int isAvailable) {
		this.isAvailable = isAvailable;
	}


	public String getEta() {
		return eta;
	}


	public void setEta(String eta) {
		this.eta = eta;
	}


	public int getAdminUserId() {
		return adminUserId;
	}


	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return restaurantId+", "+name+", "+address+", "+phone+", "+rating+", "+cuisineType+", "+isAvailable+", "+eta+", "+adminUserId+", "+imagePath;
	}
	
	

	
}
