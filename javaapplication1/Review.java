/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;
import java.util.Date;
/**
 *
 * @author Noor
 */
public class Review {
    private int reviewId;
    private Customer customer;
    private Order order;  // Links to the order being reviewed
    private int rating;   // e.g., 1-5 stars
    private String comment;
    private Date reviewDate;

    public Review() {
    }

    
    public Review(int reviewId, Customer customer, Order order, int rating, String comment, Date reviewDate) {
        this.reviewId = reviewId;
        this.customer = customer;
        this.order = order;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public Review(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }
    
    //Getters

    public int getReviewId() {
        return reviewId;
    }

   
    public Customer getCustomer() {
        return customer;
    }

    public Order getOrder() {
        return order;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Date getReviewDate() {
        return reviewDate;
    }
    
    //Setters

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
    
    public void addReview(Review review) {
        DB.getInstance().addReview(review);
    }
    public void removeReview(Review review) {
        DB.getInstance().removeReview(review);
    }
    
    
    public void displayReview(){
        System.out.println("Review ID: " + this.reviewId);

        // Check if customer exists before trying to get the name
        System.out.println("Customer: " + (this.customer != null ? this.customer.getUserName() : "Unknown"));

        // Check if order exists before trying to get the ID
        System.out.println("Order ID: " + (this.order != null ? this.order.getOrderID() : "Unknown"));

        System.out.println("Rating: " + this.rating + "/5");
        System.out.println("Comment: " + this.comment);
        System.out.println("Date: " + this.reviewDate);
        System.out.println("-----------------------------------");
    
    }
}
