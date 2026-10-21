package com.tap.model;

import java.math.BigDecimal;

public class Menu {
	private int MenuID;
	private int RestaurantID;
	private String ItemName;
	private String Description;
	private BigDecimal Price;
	private boolean IsAvailable;
	private String ImagePath;
	public Menu(int restaurantID, String itemName, String description, BigDecimal price,
			boolean isAvailable, String imagePath) {
		super();
		RestaurantID = restaurantID;
		ItemName = itemName;
		Description = description;
		Price = price;
		IsAvailable = isAvailable;
		ImagePath = imagePath;
	}
	public Menu(int menuID, int restaurantID, String itemName, String description, BigDecimal price,
			boolean isAvailable, String imagePath) {
		super();
		MenuID = menuID;
		RestaurantID = restaurantID;
		ItemName = itemName;
		Description = description;
		Price = price;
		IsAvailable = isAvailable;
		ImagePath = imagePath;
	}
	public int getMenuID() {
		return MenuID;
	}
	public void setMenuID(int menuID) {
		MenuID = menuID;
	}
	public int getRestaurantID() {
		return RestaurantID;
	}
	public void setRestaurantID(int restaurantID) {
		RestaurantID = restaurantID;
	}
	public String getItemName() {
		return ItemName;
	}
	public void setItemName(String itemName) {
		ItemName = itemName;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public BigDecimal getPrice() {
		return Price;
	}
	public void setPrice(BigDecimal price) {
		Price = price;
	}
	public boolean getIsAvailable() {
		return IsAvailable;
	}
	public void setIsAvailable(boolean isAvailable) {
		IsAvailable = isAvailable;
	}
	public String getImagePath() {
		return ImagePath;
	}
	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}
	@Override
	public String toString() {
		return "Menu [MenuID=" + MenuID + ", RestaurantID=" + RestaurantID + ", ItemName=" + ItemName + ", Description="
				+ Description + ", Price=" + Price + ", IsAvailable=" + IsAvailable + ", ImagePath=" + ImagePath + "]";
	}
	
	
	
	
	
}
