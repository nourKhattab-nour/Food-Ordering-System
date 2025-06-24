/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.util.Date;

/**
 *
 * @author Noor
 */
public class Payment {
    private int paymentID;
    private double paymentAmount;
    private Date paymentDate;
    private String paymentType ;
    private String paymentStatus;
    public PaymentMethod paymentMethod;

    public Payment() {
    }
    
    //Getters

    public int getPaymentID() {
        return paymentID;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    
    //Setters

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
   public void displayPayment(int paymentID){
        DB.getInstance().displayPayment(paymentID); 
    }
    
    
    public void processPayment(int paymentID, double amount, String paymentType) {
        if (paymentMethod == null) {
            throw new IllegalStateException("No payment method selected");
        }
        paymentMethod.processPayment(paymentID, amount, paymentType);
      
        this.paymentDate = new Date();
        this.paymentAmount = amount;
    }
    
    public void requestRefund(String paymentType) {
        System.out.println("Refund requested for payment ID: " + paymentID);
        DB.getInstance().requestRefund(paymentID, paymentType);
        
    }
    
    public void selectPaymentMethod(String paymentType){
        if (paymentType.equalsIgnoreCase("card")) {
            CreditCardPaymentMethod creditMethod = new CreditCardPaymentMethod();
            this.paymentMethod = creditMethod;
            this.paymentType = "Credit Card";
        } else if (paymentType.equalsIgnoreCase("cash")) {
            CashPaymentMethod cashMethod = new CashPaymentMethod();
            this.paymentMethod = cashMethod;
            this.paymentType = "Cash";
        } else {
            System.out.println("Invalid payment method");
        }
    }
}

    

