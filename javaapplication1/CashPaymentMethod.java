/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Noor
 */
public class CashPaymentMethod implements PaymentMethod {
    
    public CashPaymentMethod() {
    }
    //processPayment
 
  public void processPayment(int paymentID, double amount, String paymentType) {
        System.out.println("Processing cash payment for payment ID: " + paymentID);
        DB.getInstance().processCashPayment(paymentID, amount, paymentType);
    }
}
