/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author LENOVO
 */
public class BasicReceipt implements Receipt {

    private int receiptID;
  

    public BasicReceipt(int receiptID) {
        this.receiptID = receiptID;
    }
    
     // Getter method
    @Override
    public int getReceiptID() {
        return receiptID;
    }
    
    // Setter method
    public void setReceiptID(int receiptID) {
        this.receiptID = receiptID;
    }

    @Override
    public void printReceipt() {
          DB.getInstance().printReceipt(this);

    }
    }
    