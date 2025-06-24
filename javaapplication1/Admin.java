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
public class Admin extends User{
    
    
    private static Admin instance;
    private Report report = new Report();
    private Order order=new Order();
    private Discount discount=new Discount();
    private Review review=new Review();
    private Restuarant restuarant = new Restuarant();
    
  
    private Admin(int userID, String userName, String password, String PhoneNumber, String address, UserType userType) {
        super(userID, userName, password, PhoneNumber, address, userType);
    }

   
   
    
    public static synchronized Admin getInstance(int userID, String userName, String password, 
                                               String PhoneNumber, String address, UserType userType) {
        if (instance == null) {
            instance = new Admin(userID, userName, password, PhoneNumber, address, userType);
        }
        return instance;
    }
  
    // Alternative method to get instance when admin data is retrieved from database
    public static synchronized Admin getInstance() {
        if (instance == null) {
            // Load admin data from database
            // This should handle the case where admin is already in the database
            Admin adminFromDB = DB.getInstance().getAdminUser();
            if (adminFromDB != null) {
                instance = adminFromDB;
            } else {
                throw new RuntimeException("Admin not found in database");
            }
        }
        return instance;
    }
    
//-------------------------------------Functions---------------------------------------------------------------------------------------
    public void generateReport(Report report){      // Not yet
       report.generateReport(report);
    }
    
    
    
    public void viewAllorders(){                                        //Done               
       order.viewAllOrders();
    }
    
   
    
    public void updateDiscount(Discount updateDiscount){                   //Success
       discount.updateDiscount();
    }
    
     public void displayReviews() {                                     //Success
         review.displayReview();
    
    }
     
     
    //Resturant
    public void addRestuarant(Restuarant restuarant){
     restuarant.addResturant(restuarant);
    }
    public void removeRestuarant(Restuarant restuarant){
    restuarant.removeRestuarant(restuarant.getRestuarantID());
    }
    
 
}
