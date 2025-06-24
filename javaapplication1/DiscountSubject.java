/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Noor
 */
public interface DiscountSubject {
    public void registerObserver(DiscountObserver observer);
    public void removeObserver(DiscountObserver observer);
    public void notifyObserver(Discount discount);
}
