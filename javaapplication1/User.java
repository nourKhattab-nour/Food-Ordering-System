/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.sql.*;
/**
 *
 * @author Noor
 */
public class User {
    
    public enum UserType {
        CUSTOMER,
        ADMIN,
        RESTAURANT_COORDINATOR
    }
       
    private int userID;
    private String userName;
    private String password;
    private String PhoneNumber;
    private String address;
    private UserType userType;

    public User(int userID, String userName, String password, String PhoneNumber, String address, UserType userType) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.PhoneNumber = PhoneNumber;
        this.address = address;
        this.userType = userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserType getUserType() {
        return userType;
    }
    
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   
    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getAddress() {
        return address;
    }
  
//----------------------------------------------------------------------------------------------------------------------------------------------
    public static User login(String username, String password) {                // Problem in if user is Customer. request to enter age
        return DB.getInstance().login(username, password);
    }
    
   public void viewProfile() {
        System.out.println("User ID: " + userID);
        System.out.println("Username: " + userName);
        System.out.println("Phone: " + PhoneNumber);
        System.out.println("Address: " + address);
    }
    
    public void updateAddress(String newAddress) {                                 //Success
        boolean success = DB.getInstance().updateUserAddress(userID, newAddress);
        if (success) {
            this.address = newAddress;
        }
    }
    
    public void updatePhonenumber(String newPhoneNumber) {                         //Sucess
        boolean success = DB.getInstance().updateUserPhoneNumber(userID, newPhoneNumber);
        if (success) {
            this.PhoneNumber = newPhoneNumber;
        }
    }
    
    public void forgetPassword(String newPassword) {                              //Success
        boolean success = DB.getInstance().resetUserPassword(userName, newPassword);
        if (success) {
            this.password = newPassword;
        }
    }
    
    public void changePassword(String oldPassword, String newPassword) {               //Sucess
        if (!this.password.equals(oldPassword)) {
            System.out.println("Old password is incorrect.");
            return;
        }
        
        boolean success = DB.getInstance().changeUserPassword(userID, newPassword);
        if (success) {
            this.password = newPassword;
        }
    }
    
}
