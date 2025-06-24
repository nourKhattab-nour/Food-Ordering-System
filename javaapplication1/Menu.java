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
public class Menu {
    private int menuID;
    private String menuCatagory;
    //private int itemID; //database
    private Item items = new Item();

    public Menu() {
    }

    public Menu(int menuID, String menuCatagory) {
        this.menuID = menuID;
        this.menuCatagory = menuCatagory;
    }
    
    

    //Getters
    public int getMenuID() {
        return menuID;
    }

    public String getMenuCatagory() {
        return menuCatagory;
    }

    public Item getItems() {
        return items;
    }

    public void setItems(Item items) {
        this.items = items;
    }
    

    
    
    
    //Setters
    public void setMenuID(int menuID) {
        this.menuID = menuID;
    }

    public void setMenuCatagory(String menuCatagory) {
        this.menuCatagory = menuCatagory;
    }
    
    public void viewMenu() {
          DB.getInstance().viewMenu(this);
      }

      public void addMenuCatagory() {
          DB.getInstance().addMenuCatagory(this);
      }

      public void updateMenuCatagory() {
          DB.getInstance().updateMenuCatagory(this);
      }

      public void addToMenu(Item item) {
               DB.getInstance().addToMenu(this, item);

       }
}
