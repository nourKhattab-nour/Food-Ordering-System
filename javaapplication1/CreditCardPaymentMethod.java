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
public class CreditCardPaymentMethod implements PaymentMethod {
     private int cardNumber; 
    private String cardHolderName;
    private String expiryDate;
    private int cvv;

    public CreditCardPaymentMethod() {
    }

    public CreditCardPaymentMethod(int cardNumber, String cardHolderName, String expiryDate, int cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
    
    
   public void processPayment(int paymentId, double amount, String paymentType) {
        System.out.println("Processing credit card payment for payment ID: " + paymentId);
        System.out.println("Card holder: " + cardHolderName);
        System.out.println("Card ending with: " + String.valueOf(cardNumber).substring(Math.max(0, String.valueOf(cardNumber).length() - 4)));
        
        DB.getInstance().processCreditCardPayment(
            paymentId, 
            amount, 

            paymentType, 
          
            cardNumber, 
            cardHolderName
        );
    }
   
    public void enterPaymentInfo(int cardNumber, int cvv) {
        this.cardNumber = cardNumber; 
        this.cvv = cvv; 
        System.out.println("Payment information entered successfully"); 
    }

    
}
