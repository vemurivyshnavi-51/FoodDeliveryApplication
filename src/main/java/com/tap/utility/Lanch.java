package com.tap.utility;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import com.tap.DAOimpl.MenuDAOimpl;
import com.tap.DAOimpl.OrderItemDAOimpl;
//import com.tap.DAOimpl.OrderItemDAOimpl;
import com.tap.DAOimpl.OrderTableDAOimpl;
import com.tap.DAOimpl.RestaurantDAOimpl;
import com.tap.DAOimpl.UserDAOimpl;
import com.tap.model.Menu;
import com.tap.model.OrderItem;
import com.tap.model.OrderTable;
import com.tap.model.Restaurant;
import com.tap.model.User;

public class Lanch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		System.out.println("1.User 2.Restaurant Owners 3.Menu Items 4.OrderTable 5.OrderItem ");
		int client=scan.nextInt();
		
		if(client==1) 
		{
			System.out.println("Enter the option: 1.addUser 2.getUser 3.Delete 4.Update 5.All Users");
			int option=scan.nextInt();
		    UserDAOimpl udao=new UserDAOimpl();
		    
		    if(option==1) 
		    {
		    	System.out.println("Name: ");
				String userName=scan.nextLine();
				System.out.println("password: ");
				String password=scan.nextLine();
				System.out.println("email: ");
				String email=scan.nextLine();
				System.out.println("address: ");
				String address=scan.nextLine();
				System.out.println("role: ");
				String role=scan.nextLine();
				Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
			    User  u=new User(userName, password, email, address, role,timestamp ,timestamp);
		    	udao.addUser(u);
		    }
		    
		    else if(option==2) 
		    {
		    	System.out.println("Enter id to get user:");
		    	int userId=scan.nextInt();
		    	User u=udao.getUser(userId);
		    	System.out.println(u);
		    }
		    
		    
		    else if(option==3) 
		    {
		    	System.out.println("Enter the userid:");
		    	int userId=scan.nextInt();
		    	udao.deleteUser(userId);
		    }
		    
		    
		    else if(option==4) 
		    {
		    	
		    	//System.out.print("enter details to update:");
		    	
		    	System.out.println("enter userId");
		    	int userId=scan.nextInt();
		    	User u=udao.getUser(userId);
		    	scan.nextLine();
		    	System.out.println("Enter the column name: ");
		    	
//		    	String field=scan.nextLine();
		    	System.out.print("Enter the value to update");
		    	scan.nextLine();
		    	String updateField=scan.nextLine();
		
	        	u.setUserName(updateField);
	        	udao.updateUser(u);
	        	System.out.println("Updated user");
		        
		     }
		   
		   
		    else if(option==5) 
		    {
		    	 List<User> li=udao.getAllUsers();
		 	    
		 	    for(User x:li) {
		 	    	System.out.println(x);
		 	    }
		 	    scan.close();
		    }
		}
		
		else if(client==2) {
			
			System.out.println("Enter the option: 1.addRestaurant 2.getRestaurant 3.Delete Restaurant 4.Update restaurant 5.All restaurants");
			int option=scan.nextInt();
			RestaurantDAOimpl rest =new RestaurantDAOimpl();
			scan.nextLine();
			if(option==1) {
				System.out.println("Enter name:");
				String Name=scan.nextLine();
				System.out.println("Enter CuisineType:");
				String CuisineType=scan.nextLine();
				System.out.println("Enter DeliveryTime:");
				int DeliveryTime=scan.nextInt();
				scan.nextLine();
				System.out.println("Enter Address:");
				String Address=scan.nextLine();
				
				System.out.println("Enter Rating:");
				BigDecimal Rating=scan.nextBigDecimal();
				scan.nextLine();
				System.out.println("Enter IsActive:");
				String isactive=scan.nextLine();
				boolean IsActive=isactive.equalsIgnoreCase("Yes");
				System.out.println("Enter ImagePath:");
				String ImagePath=scan.nextLine();
				
				Restaurant r=new Restaurant(Name, CuisineType, DeliveryTime, Address, Rating, IsActive, ImagePath);
				rest.addRestaurant(r);
			}
			else if(option==2) {
				System.out.print("Enter restuarantid");
				int id=scan.nextInt();
				Restaurant r=rest.getRestaurant(id);
				System.out.print(r);
			}
			else if(option==3) {
				System.out.println("To delete the restaurant enter the id:");
				int id=scan.nextInt();
				rest.deleteRestaurant(id);
				
			}
			
			
			
			else if(option==4) {
				
				System.out.println("Enter the details to update");
				
				System.out.println("Enter the Restaurant ID");
				
				int RestaurantID=scan.nextInt();
				
				Restaurant r=rest.getRestaurant(RestaurantID);
				
				
				
				System.out.println("Enter the fields u want to update");
				
				System.out.println("1.Name 2.Cuisine Type 3.Delivery Type 4.Address 5.IsActive 6.Image path 7.update all");
				int choice=scan.nextInt();
				
				if(choice==1) {
					scan.nextLine();
					System.out.println("Enter the Name:");
					String Name=scan.nextLine();
					r.setName(Name);
					
					
				}
				else if(choice==2) {
					scan.nextLine();
					System.out.println("Enter the CuisineType:");
					String CuisineType=scan.nextLine();

					r.setCuisineType(CuisineType);
					
					
				}
				else if(choice==3) {
					System.out.println("Enter the DeliveryTime:");
					int DeliveryTime=scan.nextInt();
					r.setDeliveryTime(DeliveryTime);
					
				}
				else if(choice==4) {
					scan.nextLine();
					System.out.println("Enter the Address:");
					String Address=scan.nextLine();
					r.setAddress(Address);
				}
				else if(choice==5) {
					scan.nextLine();
					System.out.println("Enter the IsActive:");
					String isActive=scan.nextLine();
					boolean IsActive=isActive.equalsIgnoreCase("Yes");
					r.setIsActive(IsActive);
				}
				else if(choice==6) {
					scan.nextLine();
					System.out.println("Enter the ImagePath:");
					String ImagePath=scan.nextLine();
					r.setImagePath(ImagePath);
				}
				
				
				else if(choice==7) {
					scan.nextLine();
					System.out.println("Enter the Name:");
					String Name=scan.nextLine();
					r.setName(Name);
					
					System.out.println("Enter the CuisineType:");
					String CuisineType=scan.nextLine();
					r.setCuisineType(CuisineType);
					
					System.out.println("Enter the DeliveryTime:");
					int DeliveryTime=scan.nextInt();
					r.setDeliveryTime(DeliveryTime);
					scan.nextLine();
					
					System.out.println("Enter the Address:");
					String Address=scan.nextLine();
					r.setAddress(Address);
					
					System.out.println("Enter the IsActive: Yes OR No");
					String isActive=scan.nextLine();
					boolean IsActive=isActive.equalsIgnoreCase("Yes");
					r.setIsActive(IsActive);
					
					System.out.println("Enter the ImagePath:");
					String ImagePath=scan.nextLine();
					r.setImagePath(ImagePath);
					
					
				}
				
				
				
				rest.updateRestaurant(r);

			}
			
			else if(option==5) {
				System.out.println("All restaurant details:");
				List<Restaurant> allRestaurant = rest.getAllRestaurant();
				
				for(Restaurant r:allRestaurant) {
					System.out.println(r);
				}
			}
			
		}
		
		else if(client==3) {
			
			System.out.println("Enter the option: 1.add Menuitem 2.get Menuitem with menuID 3.get Menuitem with restaurant ID 4.Delete Menuitem 5.Update Menuitem 6.Menu");
			int option=scan.nextInt();
			
			MenuDAOimpl menu = new MenuDAOimpl();
			
			if(option==1) {
				
				System.out.println("Enter the Restaurant ID:");
				int ResturantID=scan.nextInt();
				scan.nextLine();
				
				System.out.println("Enter the ItemName:");
				String ItemName=scan.nextLine();
				
				System.out.println("Enter the Description:");
				String Description=scan.nextLine();
				
				System.out.println("Enter the Price:");
				BigDecimal Price=scan.nextBigDecimal();
				
				scan.nextLine();
				
				System.out.println("Enter the IsAvailable:");
				String isAvailable=scan.nextLine();
				boolean IsAvailable=isAvailable.equalsIgnoreCase("Yes");
				
				System.out.println("Enter the ImagePath:");
				String ImagePath=scan.nextLine();
				
				Menu m = new Menu(ResturantID, ItemName, Description, Price, IsAvailable, ImagePath);
				
				menu.addMenu(m);
				
			}
			
			else if(option==2) {
				System.out.println("Enter the MenuID");
				int MenuID=scan.nextInt();
				Menu m = menu.getMenu(MenuID);
				System.out.println("Menu Item with the above Menu ID: ");
				System.out.print(m);
			}
			
			else if(option==3) {
				
				System.out.println("Enter the Restaurant ID: ");
				
				int RestaurantId=scan.nextInt();
				
				List<Menu> li=menu.getMenu_restID(RestaurantId);
				
				System.out.println("Menu Items with the above restaurant ID: ");
				for(Menu m:li) {
					System.out.println(m);
				}
				
				
			}
			
			
			
			else if(option==4) {
				System.out.println("Enter the Menu ID to delete the Menu Item: ");
				int MenuID=scan.nextInt();
			    menu.deleteMenu(MenuID);
			}
			
			else if(option==5){
				System.out.println("Enter the details to update");
				
				System.out.println("Enter the Menu ID");
				
				int MenutID=scan.nextInt();
				Menu m = menu.getMenu(MenutID);
				
				
				
				
				
				System.out.println("Enter the fields u want to update");
				
				System.out.println("1.Item Name 2.Description 3.Price 4.IsAvailable 5.Image path 6.update all");
				int choice=scan.nextInt();
				
				if(choice==1) {
					scan.nextLine();
					System.out.println("Enter the Item Name:");
					String ItemName=scan.nextLine();
					m.setItemName(ItemName);
					
					
				}
				else if(choice==2) {
					scan.nextLine();
					System.out.println("Enter the Description:");
					String Description=scan.nextLine();

					m.setDescription(Description);
					
					
				}
				else if(choice==3) {
					System.out.println("Enter the Price:");
					BigDecimal Price=scan.nextBigDecimal();
					m.setPrice(Price);
					
				}
				else if(choice==4) {
					scan.nextLine();
					System.out.println("Enter the IsAvailable:");
					String isAvailable=scan.nextLine();
					boolean IsAvailable=isAvailable.equalsIgnoreCase("Yes");
					m.setIsAvailable(IsAvailable);
				}
				
				else if(choice==5) {
					scan.nextLine();
					System.out.println("Enter the ImagePath:");
					String ImagePath=scan.nextLine();
					m.setImagePath(ImagePath);
				}
				
				
				else if(choice==6) {
					
					scan.nextLine();
					System.out.println("Enter the Item Name:");
					String ItemName=scan.nextLine();
					m.setItemName(ItemName);	
					
					scan.nextLine();
					System.out.println("Enter the Description:");
					String Description=scan.nextLine();

					m.setDescription(Description);
					
					
					
					System.out.println("Enter the Price:");
					BigDecimal Price=scan.nextBigDecimal();
					m.setPrice(Price);
					
					scan.nextLine();
					System.out.println("Enter the IsAvailable:");
					String isAvailable=scan.nextLine();
					boolean IsAvailable=isAvailable.equalsIgnoreCase("Yes");
					m.setIsAvailable(IsAvailable);
					
					scan.nextLine();
					System.out.println("Enter the ImagePath:");
					String ImagePath=scan.nextLine();
					m.setImagePath(ImagePath);
				}
				
				menu.UpdateMenu(m);
				
			}
			
			
			
			
			else if(option==6) {
				
				List<Menu> li=menu.get_all_Menus();
				System.out.println("Menu Items: ");
				for(Menu m:li) {
					System.out.println(m);
				}
				
				
			}
			
			
			
		}
		
		
		else if(client==4) {
			System.out.println("Enter the option:1.add Orders 2.get orders with OrderID 3.get orders with userID ( pending 4.)get Menuitem with restaurantID 5.Delete Order with OrderID 6.Update OrderID 7.All Order ");
			OrderTableDAOimpl ol=new OrderTableDAOimpl();
			
			int option=scan.nextInt();
			
			if(option==1) {
				
				System.out.println("Enter the UserId: ");
				int UserID=scan.nextInt();
				
				System.out.println("Enter the TotalAmount: ");
				BigDecimal TotalAmount=scan.nextBigDecimal();
				scan.nextLine();
				
				System.out.println("Enter the Status ('Completed','Pending','Cancelled'): ");
				String Status=scan.nextLine();
				
				System.out.println("Enter the PaymentMethod ('Online' or 'Hand-cash') : ");
				String PaymentMethod=scan.nextLine(); 
				
				System.out.println("Enter the RestaurantID: ");
				int RestaurantID=scan.nextInt();
				OrderTable o = new OrderTable(UserID, TotalAmount, Status, PaymentMethod, RestaurantID);
				
				ol.addOrderTable(o);
				
			}
			
			
			else if(option==2) {
				System.out.print("Enter the OrderID to get the details:" );
				int OrderID=scan.nextInt();
				OrderTable o = ol.getOrderTable(OrderID);
				System.out.println(o);
						
			}
			else if(option==3) {
				System.out.print("Enter the userID to get the details:" );
				int userID=scan.nextInt();
				List<OrderTable> li = ol.getOrderTable_userID(userID);
				
				for(OrderTable x:li) {
					System.out.println(x);
				}
						
			}
			
			
			
			
			
			else if(option==5) {
				System.out.println("Enter the orderId to delete the Order");
				int OrderId=scan.nextInt();
				ol.deleteOrderTable(OrderId);
			}
			
			else if(option ==6) {
				
				System.out.println("Enter the details to update");
				System.out.println("Enter the OrderID:");
				int OrderId=scan.nextInt();
				OrderTable o=ol.getOrderTable(OrderId);
				System.out.println("Enter the choice 1.Status 2.PaymentMethod, if both enter 3.both");
				int choice=scan.nextInt();
				if(choice==1) {
					scan.nextLine();
					System.out.println("Enter the status 'Completed','Pending','Cancelled':");
					String Status=scan.nextLine();
					o.setStatus(Status);
					
				}
				else if(choice==2) {
					scan.nextLine();
					System.out.println("Enter the method 'Online','Hand-cash' :");
					String PaymentMethod=scan.nextLine();
					o.setPaymentMethod(PaymentMethod);
				}
				
				else if(choice ==3) {
					scan.nextLine();
					String Status=scan.nextLine();
					String PaymentMethod=scan.nextLine();
					o.setStatus(Status);
					o.setPaymentMethod(PaymentMethod);
					
				}
				ol.updateOrderTable(o);
				
			}
			
			
			
			
			
			else if(option==7) {
				List<OrderTable> li = ol.getallOrderTable();
				
				for(OrderTable x:li) {
					System.out.println(x);
				}
			}
		}
		
		
		else if(client==5) {
			OrderItemDAOimpl oi = new OrderItemDAOimpl();
			
			System.out.println("Enter the option 1.addOrderItem 2.getOrderItem 3.updateOrderItem 4.deleteOrderItem 5.getAllOrderItem");
			int choice=scan.nextInt();
			if(choice==1) {
				System.out.println("Enter the OrderID:");
				int OrderID=scan.nextInt();
				
				System.out.println("Enter the Quantity:");
				int Quantity=scan.nextInt();
				
				
				System.out.println("Enter the ItemTotal:");
				BigDecimal ItemTotal=scan.nextBigDecimal();
				
				System.out.println("Enter the MenuId:");
				int MenuId=scan.nextInt();
				
				OrderItem o = new OrderItem(OrderID, Quantity, ItemTotal, MenuId);
				oi.addOrderItem(o);
				
			}
			
			
			else if(choice==2) {
				System.out.println("Enter the OrderItemID:");
				int OrderItemID=scan.nextInt();
				OrderItem orderItem = oi.getOrderItem(OrderItemID);
				System.out.println(orderItem);
			}
			
			else if(choice==3) {
				
				System.out.println("Enter the OrderItemID:");
				int OrderItemID=scan.nextInt();
				OrderItem o = oi.getOrderItem(OrderItemID);
				
				System.out.println("Enter the quantity(int):");
				int Quantity=scan.nextInt();
				o.setQuantity(Quantity);
				
				oi.updateOrderItem(o);
				
			}
			
			
			else if(choice==4) {
				System.out.println("Enter the OrderItemID");
				int OrderItemID= scan.nextInt();
				oi.deleteOrderItem(OrderItemID);
			}
			
			else if(choice==5) {
				
				List<OrderItem> list = oi.getallOrderItem();
				
				for(OrderItem x:list) {
					System.out.println(x);
				}
				
				
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		scan.close();
	} 
}
