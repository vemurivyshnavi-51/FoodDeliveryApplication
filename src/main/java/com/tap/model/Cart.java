package com.tap.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	static Map<Integer,CartItem> items;
	
	
	public Cart() {
		items=new HashMap<Integer,CartItem>();
	}
	
	
	public Map<Integer, CartItem> getItems() {
		return items;
	}
	
	
	public static void addItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		int menuId = cartItem.getMenuId();
		
		if(items.containsKey(menuId)) {
			
			CartItem exisitingCartItem = items.get(menuId);
			exisitingCartItem.setQuantity(exisitingCartItem.getQuantity()+1);
			
		}
		else {
			items.put(menuId,cartItem);
		}
		
		System.out.print(items.size());
	}
	
	
	
	
	
}
