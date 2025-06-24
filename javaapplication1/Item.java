/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Noor
 */
public class Item {
    private int itemID;
    private String itemName;
    private String Description;
    private int quantity;
    private double price;
    private String catagory;
    private boolean isAvailable;

    public Item() {
    }
    

    public Item(int itemID, String itemName, String Description, int quantity, double price, String catagory, boolean isAvailable) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.Description = Description;
        this.quantity = quantity;
        this.price = price;
        this.catagory = catagory;
        this.isAvailable = isAvailable;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return Description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getCatagory() {
        return catagory;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void addItem() {
        DB.getInstance().addItem(this);
    }
    
    // Update this item in database
    public void updateItem() {
        DB.getInstance().updateItem(this.itemID, this);
    }
    
    // Delete this item from database
    public void deleteItem() {
        DB.getInstance().deleteItem(this);
    }
    // Check availability through database
    public boolean checkAvailability() {
        return DB.getInstance().checkAvailability(this.itemID);
    }
    
    // Update availability
    public boolean updateAvailability(boolean isAvailable) {
        boolean result = DB.getInstance().updateAvailability(this.itemID, isAvailable);
        if (result) {
            this.isAvailable = isAvailable;
        }
        return result;
    }
    
    // Search for items by name (static method)
    public Item searchItem(String itemName) {
        return DB.getInstance().searchItem(itemName);
    }
    
    // Update quantity
    public boolean updateItemQuantity(int newQuantity) {
        boolean result = DB.getInstance().updateItemQuantity(this.itemName, newQuantity);
        if (result) {
            this.quantity = newQuantity;
            this.isAvailable = (newQuantity > 0);
        }
        return result;
    }
    

}
