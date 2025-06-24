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
public class Discount {
    private int discountID;
    private String discountDescription;
    private int discountValue;
    private Date startDate;
    private Date endDate;
    private boolean isActive;
    private String additionalConditions;
    
    private  DiscountStrategy discountStrategy;

    public Discount(int discountID, String discountDescription, int discountValue, Date startDate, Date endDate, boolean isActive, DiscountStrategy discountStrategy) {
        this.discountID = discountID;
        this.discountDescription = discountDescription;
        this.discountValue = discountValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.discountStrategy = discountStrategy;
    }

    public Discount(int discountID, String discountDescription, int discountValue, Date startDate, Date endDate, boolean isActive) {
        this.discountID = discountID;
        this.discountDescription = discountDescription;
        this.discountValue = discountValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }
    
   
    public Discount() {
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public DiscountStrategy getDiscountStrategy() {
        return discountStrategy;
    }
    
    

    public void setAdditionalConditions(String additionalConditions) {
        this.additionalConditions = additionalConditions;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public String getAdditionalConditions() {
        return additionalConditions;
    }
    
   

    //Getters
    public int getDiscountID() {
        return discountID;
    }

    public String getDiscountDescription() {
        return discountDescription;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    //Setters
    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public void setDiscountDescription(String discountDescription) {
        this.discountDescription = discountDescription;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void addDiscount(int discountValue,int itemID){
         discountStrategy.addDiscount(discountValue,itemID);
    }
    
    public void updateDiscount() {
        DB.getInstance().updateDiscount(this);
    }

    public void removeDiscount() {
        DB.getInstance().removeDiscount(this.discountID);
    }

    
    
}
