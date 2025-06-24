/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;
import java.util.ArrayList;
/**
 *
 * @author Noor
 */
public class Restuarant {
    private int restuarantID;
    private String restaurantName;
    private String location;
    private Menu menu = new Menu();
    
    

    public Restuarant() {
    }

    public Restuarant(int restuarantID, String restaurantName, String location) {
        this.restuarantID = restuarantID;
        this.restaurantName = restaurantName;
        this.location = location;
    }

    Restuarant(int id, String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setRestuarantID(int restuarantID) {
        this.restuarantID = restuarantID;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getRestuarantID() {
        return restuarantID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getLocation() {
        return location;
    }

    public Menu getMenu() {
        return menu;
    }

    
    
    
   
   
    
    
  
    
    public void viewRestuarant(){
        DB.getInstance().viewAllRestaurants();
    }
    
    /*public void addToMenu(Item item) {
    } */ 
    
    public Restuarant searchRestaurant(String restaurantName, Customer customer) { 
//        Customer customer=order.getCustomer();
        return DB.getInstance().searchRestaurant(restaurantName, customer);
    }
     public void addResturant(Restuarant restaurant) {
         DB.getInstance().addResturant(this);
    }
     
     public void removeRestuarant(int restuarantID){
         DB.getInstance().removeRestuarant(this.restuarantID);
    }


}
