package com.tap.DAO;

import java.util.List;

import com.tap.model.Menu;

public interface MenuDAO {
	void addMenu(Menu m);
	Menu getMenu(int MenuID);
	void deleteMenu(int MenuID);
	void UpdateMenu(Menu m);
	List<Menu> get_all_Menus();
	List<Menu> getMenu_restID(int RestaurantID);
	
	
}
