/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Noor
 */
public class FixedAmountDiscount implements DiscountStrategy{
    private int amount;
    
    public FixedAmountDiscount() {
        // default constructor
    }
    
    public void setAmount(int amount) {
        this.amount = amount;
    }
   
    public void addDiscount(int discountValue, int itemID) { 
    setAmount(discountValue);  
    double discountedPrice = DB.getInstance().applyFixedAmountDiscount(this.amount, itemID);
    System.out.println("Discounted price: $" + discountedPrice);
}
}
