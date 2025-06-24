/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Noor
 */
public class PendingOrder implements OrderState{
     public void handleOrder(int orderId) {
           DB.getInstance().handleOrderPending(orderId);
    }
}
