/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Noor
 */
public abstract class ReceiptDecorator implements Receipt{
    protected Receipt component;  // The receipt being decorated
     private int ReceiptID;
    public ReceiptDecorator(Receipt component,int receiptID) {
        this.component = component;
        this.ReceiptID=receiptID;
    }
@Override
    public int getReceiptID() {
        return component.getReceiptID();
    }
    
    @Override
    public void printReceipt() {
          DB.getInstance().printReceipt(this);
     

    }
    
    //Arwa code
//    public interface Receipt {
//    
//    String printReceipt();
//}
}
