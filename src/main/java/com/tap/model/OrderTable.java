package com.tap.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrderTable {
	private int UserID;
	private int OrderID;
	private Timestamp OrderDate;
	private BigDecimal TotalAmount;
	private String Status;
	private String PaymentMethod;
	private int RestaurantID;
	
	public OrderTable(int userID, BigDecimal totalAmount, String status, String paymentMethod, int restaurantID) {
		super();
		UserID = userID;
		TotalAmount = totalAmount;
		Status = status;
		PaymentMethod = paymentMethod;
		RestaurantID = restaurantID;
	}
	public OrderTable(int userID, Timestamp orderDate, BigDecimal totalAmount, String status, String paymentMethod,
			int restaurantID) {
		super();
		UserID = userID;
		OrderDate = orderDate;
		TotalAmount = totalAmount;
		Status = status;
		PaymentMethod = paymentMethod;
		RestaurantID = restaurantID;
	}
	public OrderTable(int userID, int orderID, Timestamp orderDate, BigDecimal totalAmount, String status,
			String paymentMethod, int restaurantID) {
		super();
		UserID = userID;
		OrderID = orderID;
		OrderDate = orderDate;
		TotalAmount = totalAmount;
		Status = status;
		PaymentMethod = paymentMethod;
		RestaurantID = restaurantID;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public Timestamp getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Timestamp orderDate) {
		OrderDate = orderDate;
	}
	public BigDecimal getTotalAmount() {
		return TotalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		TotalAmount = totalAmount;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getPaymentMethod() {
		return PaymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		PaymentMethod = paymentMethod;
	}
	public int getRestaurantID() {
		return RestaurantID;
	}
	public void setRestaurantID(int restaurantID) {
		RestaurantID = restaurantID;
	}
	
	@Override
	public String toString() {
		return "OrderTable [UserID=" + UserID + ", OrderID=" + OrderID + ", OrderDate=" + OrderDate + ", TotalAmount="
				+ TotalAmount + ", Status=" + Status + ", PaymentMethod=" + PaymentMethod + ", RestaurantID="
				+ RestaurantID + "]";
	}
	
	
	
	
}
