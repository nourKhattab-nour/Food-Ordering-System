/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Noor
 */
public class RestuarantCoordinator extends User implements DiscountSubject {
    
   

 
    private Item item = new Item();
    private Restuarant restuarant = new Restuarant();
    private Menu menu = new Menu();
    private Discount discount=new Discount();
    
    private Order order = new Order();
    
    
    public RestuarantCoordinator(int userID, String userName, String password, String PhoneNumber, String address, UserType userType) {
        super(userID, userName, password, PhoneNumber, address, userType);
    }

    
   
    //Design Pattern
    public void registerObserver(DiscountObserver observer) {
        DB.getInstance().registerObserver(observer);

        
    }
    
    public void removeObserver(DiscountObserver observer) {
       DB.getInstance().removeObserver(observer);

        
    }
    
//     public void createDiscounts(Discount newDiscount) {
//        // First add to database
//        discount.addDiscount(item.getItemID());
//        System.out.println("DEBUG: Discount created in database");
//        
//        // Then notify observers
//        this.notifyObserver(newDiscount);
//        System.out.println("DEBUG: Observers notified");
//    }
//    
    // In RestuarantCoordinator class
    
    
public void createDiscounts(Discount discount) {
    // Extract fields from the discount object
    String description = discount.getDiscountDescription();
    int value = discount.getDiscountValue();
    Date startDate = discount.getStartDate();
    Date endDate = discount.getEndDate();
    boolean isActive = discount.isActive();
    DiscountStrategy strategy = discount.getDiscountStrategy();
    
    // Call the database method with the extracted fields
    String result = DB.getInstance().createDiscount(description, value, startDate, endDate, isActive, description, strategy);
    
    System.out.println("DEBUG: " + result);
    // Rest of your code...
}


    public void notifyObserver(Discount discount) {
        DB.getInstance().notifyObservers(discount);
    }
 
    public void removeDiscount(int discountID){                            //Success
      discount.removeDiscount();
    }
    
    
   public void updateOrderStatus(int orderId, String newStatus){
        order.updateOrderStatus(orderId, newStatus);

   }
 
    public void addItem(){
        item.addItem();
    }
    
    //Menu
    public void addMenuCatagory(){
    menu.addMenuCatagory();
    
    }
    public void updateMenuCatagory(){
    menu.updateMenuCatagory();
    }

   
    
    
    
}
