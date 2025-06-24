/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Noor
 */
public class Order {
    
    private int orderID;
    private String orderDescription;
    private Date orderDate;
    private String orderStatus;
    private Time orderDeliveryTime;
    private String orderAddress;
    private String OrderFromResturant;
    private String orderItem;
    private int customerId;
    private double totalAmount;
    private Customer customer ;
    private OrderState state;   //State Pattern
    
    private Payment payment;
   
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Order() {
    }

    
    public Order(int orderID, String orderName, String orderDescription, Date orderDate, String orderStatus, Time orderDeliveryTime, String orderAddress, String OrderFromResturant, String orderItem, int customerId, double totalAmount, Customer customer, OrderState state) {
        this.orderID = orderID;
      
        this.orderDescription = orderDescription;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderDeliveryTime = orderDeliveryTime;
        this.orderAddress = orderAddress;
        this.OrderFromResturant = OrderFromResturant;
        this.orderItem = orderItem;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.customer = customer;
        this.state = state;
    }

    public Order(int orderID,String orderDescription, Date orderDate, String orderStatus, Time orderDeliveryTime, String orderAddress, String OrderFromResturant, String orderItem, int customerId, double totalAmount) {
        this.orderID = orderID;
       
        this.orderDescription = orderDescription;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderDeliveryTime = orderDeliveryTime;
        this.orderAddress = orderAddress;
        this.OrderFromResturant = OrderFromResturant;
        this.orderItem = orderItem;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
      
    }
    
    

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

   
    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderDeliveryTime(Time orderDeliveryTime) {
        this.orderDeliveryTime = orderDeliveryTime;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public void setOrderFromResturant(String OrderFromResturant) {
        this.OrderFromResturant = OrderFromResturant;
    }

    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    
    //Getters
    public int getOrderID() {
        return orderID;
    }

   

    public String getOrderDescription() {
        return orderDescription;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Time getOrderDeliveryTime() {
        return orderDeliveryTime;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public String getOrderFromResturant() {
        return OrderFromResturant;
    }

    public String getOrderItem() {
        return orderItem;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderState getState() {
        return state;
    }
    
   
    //Functions
    
    
    public void updateOrderStatus(int orderId, String newStatus) {
        DB.getInstance().setOrderStatus(orderId, newStatus);
    }
    
    public void trackStatus(int orderId) {
       DB.getInstance().trackOrder(orderId);
   
    }                                                  
    public void placeOrder(){
        DB.getInstance().placeOrder(this);
    }
    
    public void cancelOrder(int orderID){
        DB.getInstance().cancelOrder(orderID);
    }
    
   
    public void viewOrder(int orderId) {
        DB.getInstance().viewOrder(orderId);
    }
    
    public void viewAllOrders() {
        DB.getInstance().viewAllOrders();
    }

    
    public Order(int orderID) {
        this.orderID = orderID;
        this.state = new NewOrder(); 
    }


    public void handleOrder(int orderID) {
        state.handleOrder(orderID);  
    }
    
}
