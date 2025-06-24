/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Noor
 */
public class Customer extends User implements DiscountObserver {
    
    private String customerAddress;
    private Discount discount = new Discount();
    private Order order = new Order();
    private Restuarant restuarant = new Restuarant();
    private Favorite favorite = new Favorite(this);
    private Cart cart = new Cart();
    private Review reviews = new Review();
    private Menu menu = new Menu();
    private Payment payment = new Payment();
    
    private Restuarant selectedRestaurant = new Restuarant();

    
    public Customer(String customerAddress, int userID, String userName, String password, String PhoneNumber, String address, UserType userType) {
        super(userID, userName, password, PhoneNumber, address, userType);
        this.customerAddress = customerAddress;
    }

    public Customer(int userID, String userName, String password, String PhoneNumber, String address, UserType userType) {
        super(userID, userName, password, PhoneNumber, address, userType);
    }

    public Discount getDiscount() {
        return discount;
    }

    public Favorite getFavorite() {
        return favorite;
    }

    public Review getReviews() {
        return reviews;
    }

    public Menu getMenu() {
        return menu;
    }

    public Restuarant getSelectedRestaurant() {
        return selectedRestaurant;
    }

  
    //Getters
   
 

    public String getCustomerAddress() {
        return customerAddress;
    }

  

    public Order getOrder() {
        return order;
    }

    public Restuarant getRestuarant() {
        return restuarant;
    }

    public Cart getCart() {
        return cart;
    }
    
    //Setters
  

 
    
    
    //Pattern-------------------------------------------------------------------------------------------------------------------------------
 public void update(Discount discount) {
        if (discount == null) return;
        
        String message = "New discount: " + discount.getDiscountDescription() + 
                       " (" + discount.getDiscountValue() + "% off)";
        
        boolean success = DB.getInstance().createNotification(
            this.getUserID(),
            discount.getDiscountID(),
            message
        );
        
        if (!success) {
            System.out.println("Failed to notify customer " + this.getUserID());
        }
    }
    
    public void viewNotifications() {
        DB.getInstance().displayNotifications(this.getUserID());
    }

    
    public int getID() {
        return super.getUserID(); // Assuming this returns the user ID
    }
   
    //-----------------------------------------------------------------------------------------------------------------------------
    //Done
     public void viewAllRestaurants(){                  //Success
       restuarant.viewRestuarant();
    }
    
   
//    
     public void addFavorite(Item item) {               //Not yet forign key error
        favorite.addFavorite(item);
    }

    // Remove from favorites
    public void removeFavorite(Item item) {                  //Success
        favorite.removeFavorite(item);
    }

    // Display favorites
    public void displayFavorites() {                      //Work not seeing display
     favorite.displayFavorites();
    }
    
    

        public void addReview(Order order, int rating, String comment) {          //Success
         // Validate input
         if (order == null) {
             System.err.println("Cannot review a null order");
             return;
         }
         if (rating < 1 || rating > 5) {
             System.err.println("Rating must be between 1 and 5");
             return;
         }
         if (comment == null || comment.trim().isEmpty()) {
             System.err.println("Comment cannot be empty");
             return;
         }

         // Create new review associated with this customer
         Review newReview = new Review(
             0,               // reviewId (let DB generate)
             this,           // current customer
             order,          // the order being reviewed
             rating,         // rating (1-5)
             comment,        // review comment
             new Date()      // current date
         );

         // Save to database
         reviews.addReview(newReview);
     }
    
        public void removeReview(Review review) {                              // Success
            if (review == null) {
                System.err.println("Cannot remove null review");
                return;
            }

         reviews.removeReview(review);
        }

    
  
    public void hasItemInCart(Item item) {
      cart.containsItemInCart(item.getItemID());          //Success
    }
    
    public void addToCart(Item item) {                    //Success
        cart.addItemToCart(item);
    }
   
    
    public void removeFromCart(Item item) {              //Success
        cart.removeItemFromCart(item.getItemID());
       
    }
   
    public void viewCart() {
       cart.displayCart();                  //Success
    }
    
    public void selectRestaurant(Restuarant restaurant) {            //Success
                if (restaurant == null) {
                    System.out.println("No restaurant selected.");
                    return;
                }
                this.selectedRestaurant = restaurant;
                System.out.println("Restaurant selected: " + restaurant.getRestaurantName() +
                                   " at " + restaurant.getLocation());
   }
     
    public Restuarant searchRestaurant(String name) {                //Success
        return restuarant.searchRestaurant(name, this);
    }
     
      public  void createOrder(Order order){                                         //Sucess
        this.order = order; // Store order for future reference
        order.placeOrder();
    }
    
       public void selectPaymentMethod(String methodType) {                   //Success
            payment = new Payment();
            payment.setPaymentID(1001); // example ID
            payment.setPaymentAmount(order.getTotalAmount());

            payment.selectPaymentMethod(methodType);

            if (methodType.equalsIgnoreCase("card")) {
                CreditCardPaymentMethod method = (CreditCardPaymentMethod) payment.paymentMethod;
                method.setCardNumber(12345678);
                method.setCardHolderName("John Doe");
                method.setExpiryDate("12/25");
                method.setCvv(123);
            }

            System.out.println("Payment method selected: " + methodType);
        }
       
         public void processPayment() {                      //Success
            if (payment == null) {
                System.out.println("Payment not initialized.");
                return;
            }
    
    // Ensure all required fields are set
    if (payment.getPaymentID() == 0) {
        System.out.println("Payment ID not set.");
        return;
    }
    
    if (payment.getPaymentAmount() <= 0) {
        System.out.println("Invalid payment amount.");
        return;
    }
    
    if (payment.getPaymentType() == null || payment.getPaymentType().isEmpty()) {
        System.out.println("Payment type not specified.");
        return;
    }

    payment.processPayment(
        payment.getPaymentID(),
        payment.getPaymentAmount(),
        payment.getPaymentType()
      
    );
}
         
             
  public void requestRefund() {                                      //Success
    if (payment != null) {
        // Check if payment was by credit card
        if (!(payment.paymentMethod instanceof CreditCardPaymentMethod)) {
            System.out.println("Refunds only available for credit card payments");
            return;
        }
        
        payment.requestRefund(payment.getPaymentType());
    } else {
        System.out.println("No payment to refund.");
    }
}

    public void cancelMyOrder(int orderID) {                            
        order.cancelOrder(orderID);
        
    }

        public void trackMyOrder(int orderID) {                             
            order.trackStatus(orderID);
            if (order != null) {
                System.out.println("\n--- Order Details ---");
                System.out.println("Order ID: " + order.getOrderID());
                System.out.println("Status: " + order.getOrderStatus());
                System.out.println("Restaurant: " + order.getOrderFromResturant());
                System.out.println("Items: " + order.getOrderItem());
                System.out.println("Total: $" + order.getTotalAmount());
                System.out.println("Order Date: " + order.getOrderDate());
                System.out.println("Delivery Time: " + order.getOrderDeliveryTime());

                if ("Delivered".equalsIgnoreCase(order.getOrderStatus())) {
                    System.out.println("\nYour order has been delivered!");
                } else if ("Cancelled".equalsIgnoreCase(order.getOrderStatus())) {
                    System.out.println("\nThis order has been cancelled");
                } else {
                    System.out.println("\nYour order is being prepared");
                }
            } else {
                System.out.println("Order not found");
            }
        }

       public void viewmenu(){
       menu.viewMenu();
     }
         


     
     
   


   



    
    
    
    
    
    
    
    
  
    
    
    
 
    
      
      
    
  
}
