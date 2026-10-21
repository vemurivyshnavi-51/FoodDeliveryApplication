package com.tap.DAO;

import java.util.List;

import com.tap.model.OrderTable;

public interface OrderTableDAO {
	
	void addOrderTable(OrderTable ot);
	void updateOrderTable(OrderTable ot);
	void deleteOrderTable(int OrderID);
	List<OrderTable> getOrderTable_userID(int userID);
	OrderTable getOrderTable(int OrderID);
	List<OrderTable> getallOrderTable();
	
}
