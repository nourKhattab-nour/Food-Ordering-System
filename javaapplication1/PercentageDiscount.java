/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Noor
 */
public class PercentageDiscount implements DiscountStrategy {
    private int percentage;
    
    public PercentageDiscount() {
        // default constructor
    }
    
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
    
 public void addDiscount(int discountValue, int itemID) { 
    setPercentage(discountValue);  
    double discountedPrice = DB.getInstance().applyPercentageDiscount(this.percentage, itemID);
    System.out.println("Discounted price: $" + discountedPrice);
}
}
