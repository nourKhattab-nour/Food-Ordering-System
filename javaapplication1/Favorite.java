/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Noor
 */
public class Favorite {
    private Customer customer;

    public Favorite(Customer customer) {
        this.customer = customer;
    }

    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
     public boolean addFavorite(Item item) {
        int userID = this.customer.getUserID();
        return DB.getInstance().addFavorite(item, userID);
    }
    
    // Remove from favorites
    public boolean removeFavorite(Item item) {
        int userID = this.customer.getUserID();
        return DB.getInstance().removeFavorite(item, userID);
    }
    
     public void displayFavorites() {
     DB.getInstance().displayFavoritesWithDetails(0);
     }
}
