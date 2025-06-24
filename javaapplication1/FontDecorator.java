/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Noor
 */
public class FontDecorator extends ReceiptDecorator{
     private String fontStyle;

    public FontDecorator(Receipt component,int ID, String fontStyle) {
        super(component,ID);
        this.fontStyle = fontStyle;
    }

    public void setFontStyle(String style) {
        this.fontStyle = style;
    }

    @Override
    public void printReceipt() {
         DB.getInstance().printStyledReceipt(this);
         
    }
}
