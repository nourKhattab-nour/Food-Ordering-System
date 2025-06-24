/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Noor
 */
public class ColorDecorator extends ReceiptDecorator{
    private String textColor;

    public ColorDecorator(Receipt component,int ID, String textColor) {
        super(component,ID);
        this.textColor = textColor;
    }

    public void setTextColor(String color) {
        this.textColor = color;
    }

    @Override
    public void printReceipt() {
         DB.getInstance().printColoredReceipt(this);
         
    }
}
