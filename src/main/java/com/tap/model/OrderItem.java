package com.tap.model;

import java.math.BigDecimal;

public class OrderItem {
	private int OrderID;
	private int OrderItemID;
	private int Quantity;
	private BigDecimal ItemTotal;
	private int MenuId;
	
	
	
	public OrderItem(int orderID, int quantity, BigDecimal itemTotal, int menuId) {
		super();
		OrderID = orderID;
		Quantity = quantity;
		ItemTotal = itemTotal;
		MenuId = menuId;
	}
	public OrderItem(int orderID, int orderItemID, int quantity, BigDecimal itemTotal, int menuId) {
		super();
		OrderID = orderID;
		OrderItemID = orderItemID;
		Quantity = quantity;
		ItemTotal = itemTotal;
		MenuId = menuId;
	}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public int getOrderItemID() {
		return OrderItemID;
	}
	public void setOrderItemID(int orderItemID) {
		OrderItemID = orderItemID;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public BigDecimal getItemTotal() {
		return ItemTotal;
	}
	public void setItemTotal(BigDecimal itemTotal) {
		ItemTotal = itemTotal;
	}
	public int getMenuId() {
		return MenuId;
	}
	public void setMenuId(int menuId) {
		MenuId = menuId;
	}
	
	
	@Override
	public String toString() {
		return "OrderItem [OrderID=" + OrderID + ", OrderItemID=" + OrderItemID + ", Quantity=" + Quantity
				+ ", ItemTotal=" + ItemTotal + ", MenuId=" + MenuId + "]";
	}
	
	
	
}
