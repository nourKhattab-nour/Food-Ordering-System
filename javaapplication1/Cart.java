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
public class Cart {  
    
    private int cartId;
    private Item item = new Item();

    public Cart(int cartId) {
        this.cartId = cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getCartId() {
        return cartId;
    }

    public Cart() {
    }
    
    
    
    public void addItemToCart(Item item) {
        DB.getInstance().addItemToCart(item);
    }
    
    public void removeItemFromCart(int itemID) {
        DB.getInstance().removeItemFromCart(item);
    }
    public boolean containsItemInCart(int itemID) {
        return DB.getInstance().containsItemInCart(itemID);
    }
    public double calculateCartTotal() {
        return DB.getInstance().calculateCartTotal();
    }
    public void displayCart() {
        DB.getInstance().displayCart();
    }
    
    public void clearCart() {
        DB.getInstance().clearCart();
    }

}
