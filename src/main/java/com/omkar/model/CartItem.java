package com.omkar.model;

public class CartItem {
	
	private int itemId;
	private int menuId;
	private int restaurantId;
	private String itemname;
	private int quantity;
	private float price;
	private String imagePath;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public CartItem() {
	}

	public CartItem(int menuId,int restaurantId, String itemname, int quantity, float price, String imString) {
//		super();
		this.menuId = menuId;
		this.restaurantId = restaurantId;
//		this.itemId = itemId;
		this.itemname = itemname;
		this.quantity = quantity;
		this.price = price;
		this.imagePath=imString;
		
	}
//
	public int getItemId() {
		return itemId;
	}
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
