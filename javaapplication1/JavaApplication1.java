/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import java.sql.Time;  
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 *
 * @author Noor
 */
public class JavaApplication1 {
        
    public static void main(String[] args) {
               //Observer
//        RestuarantCoordinator coordinator = new RestuarantCoordinator(
//            1, "RestaurantAdmin", "password123", "555-123-4567", "123 Main St", User.UserType.RESTAURANT_COORDINATOR
//        );
//        
//        // Create customers who will observe discounts
//        Customer customer1 = new Customer(
//            "Delivery Address 1", 99, "John", "pass123", "555-111-2222", "456 Oak St", User.UserType.CUSTOMER
//        );
//        
//        Customer customer2 = new Customer(
//            "Delivery Address 2",100, "Sarah", "pass456", "555-333-4444", "789 Pine St", User.UserType.CUSTOMER
//        );
//        
//        // Register customers as observers
//        coordinator.registerObserver(customer1);
//        coordinator.registerObserver(customer2);
//        
//        System.out.println("Customers registered as observers");
//        
//        // Create a new discount
//        Calendar startCal = Calendar.getInstance();
//        startCal.add(Calendar.DATE, 0); // Today
//        
//        Calendar endCal = Calendar.getInstance();
//        endCal.add(Calendar.DATE, 7); // 7 days from now
//        
//        Discount newDiscount = new Discount(
//            0, // ID will be set by the database
//            "test: 900% off on all desserts",
//            20, // 70% discount
//            startCal.getTime(), // start date
//            endCal.getTime(),   // end date
//            true // active
//        );
//        
//        // Coordinator creates the discount - this should notify all observers
//        System.out.println("\nCreating a new discount...");
//        coordinator.createDiscounts(newDiscount);
//        
//        // Check if notifications were created
//        System.out.println("\nChecking notifications for customer 1:");
//        customer1.viewNotifications();
//        
//        System.out.println("\nChecking notifications for customer 2:");
//        customer2.viewNotifications();
//        
////        // Remove one observer
////        System.out.println("\nRemoving customer 2 from observers...");
////        coordinator.removeObserver(customer2);
//        
//        // Create another discount
//        startCal.add(Calendar.DATE, 1); // Tomorrow
//        endCal.add(Calendar.DATE, 14);  // 14 days from tomorrow
//        
//        Discount anotherDiscount = new Discount(
//            0,
//            "Weekend Flash Sale: 30% off on all beverages",
//            30,
//            startCal.getTime(),
//            endCal.getTime(),
//            true
//        );
//        
//        // Coordinator creates another discount
//        System.out.println("\nCreating another discount...");
//        coordinator.createDiscounts(anotherDiscount);
//        
//        // Check notifications again - customer1 should get the notification, but not customer2
//        System.out.println("\nChecking notifications for customer 1 again:");
//        customer1.viewNotifications();
//        
//        System.out.println("\nChecking notifications for customer 2 again:");
//        customer2.viewNotifications();
//    }
//    }
    
//    private static void testLogin() {
//        System.out.println("Testing Login Function:");
//        
//        // Successful admin login
//        User adminUser = DB.getInstance().login("Dima", "pass5555");
//        if (adminUser != null && adminUser instanceof Admin) {
//            System.out.println("Admin login successful: " + adminUser.getUserName());
//            
//            // Test admin functions
//            Admin admin = (Admin) adminUser;
//            admin.displayReviews();
//        } else {
//            System.out.println("Admin login failed");
//        }
//        
//        // Successful customer login
//        User customerUser = DB.getInstance().login("Hala", "pass230");
//        if (customerUser != null && customerUser instanceof Customer) {
//            System.out.println("Customer login successful: " + customerUser.getUserName());
//        } else {
//            System.out.println("Customer login failed");
//        }
//        
//        // Failed login
//        User failedUser = DB.getInstance().login("wrongUsername", "wrongPassword");
//        if (failedUser == null) {
//            System.out.println("Login correctly failed for invalid credentials");
//        }
//    }

//    //Singlton
//    private static void testSingleton() {
//        System.out.println("\nTesting Singleton Pattern:");
//        
//        // Get admin instance
//        Admin admin1 = Admin.getInstance();
//        System.out.println("First Admin instance: " + admin1.getUserName());
//        
//        // Get admin instance again (should be the same object)
//        Admin admin2 = Admin.getInstance();
//        System.out.println("Second Admin instance: " + admin2.getUserName());
//        
//        // Check if they are the same object
//        if (admin1 == admin2) {
//            System.out.println("Singleton is working correctly - both references point to the same object");
//        } else {
//            System.out.println("Singleton is NOT working correctly");
//        }
//        
//        // Try creating a new admin in the database (this should be prevented)
//        User newAdmin = new User(100, "AnotherAdmin", "adminPass", "0123456789", "Cairo", User.UserType.ADMIN);
//        boolean created = DB.getInstance().createUser(newAdmin);
//        System.out.println("Attempt to create second admin: " + (created ? "Succeeded (WRONG)" : "Prevented (CORRECT)"));
//    }
        
//  try {
//            // Get the admin instance from the database
//            Admin admin = Admin.getInstance();
//            System.out.println("Admin user retrieved: " + admin.getUserName());
//            
//            // All operations will use the same admin instance
//            admin.viewAllorders();
//            admin.displayReviews();
//            
//            // This will retrieve the same instance, not create a new one
//            Admin sameAdmin = Admin.getInstance();
//            if (admin == sameAdmin) {
//                System.out.println("Singleton is working correctly - both references point to the same instance");
//            } else {
//                System.out.println("Singleton is NOT working correctly");
//            }
//            
//            // Using specific parameters (should still return the existing instance)
//            Admin specificAdmin = Admin.getInstance(3, "Dima", "pass5555", "010157895", "Giza", User.UserType.ADMIN);
//            if (admin == specificAdmin) {
//                System.out.println("Singleton is working correctly with specific parameters");
//            } else {
//                System.out.println("Singleton is NOT working correctly with specific parameters");
//            }
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error: " + e.getMessage());
//        }
// 
    
//              Arwa Pattern
//        System.out.println("\n--- TESTING BASIC RECEIPT ---");
//        Receipt basicReceipt = new BasicReceipt(1001);
//        basicReceipt.printReceipt();
        
//        // Test a font-decorated receipt
//        System.out.println("\n--- TESTING FONT DECORATOR ---");
//        Receipt fontReceipt = new FontDecorator(new BasicReceipt(1001), 1001, "Arial Bold");
//        fontReceipt.printReceipt();
//        
//        // Test a color-decorated receipt
//        System.out.println("\n--- TESTING COLOR DECORATOR ---");
//        Receipt colorReceipt = new ColorDecorator(new BasicReceipt(1001), 1001, "Blue");
//        colorReceipt.printReceipt();


//       Nour ahmed Pattern
//        // Test data
//        int testItemID = 10; // Use an existing itemID from your database
//        int fixedDiscountAmount = 5; // $5 discount
//        int percentageDiscount = 20; // 20% discount
//        
//        // Test fixed amount discount
//        System.out.println("\n=== Testing Fixed Amount Discount ===");
//        double fixedDiscountedPrice = db.applyFixedAmountDiscount(fixedDiscountAmount, testItemID);
//        System.out.println("Final discounted price: $" + fixedDiscountedPrice);
//        
//        // Test percentage discount
//        System.out.println("\n=== Testing Percentage Discount ===");
//        double percentageDiscountedPrice = db.applyPercentageDiscount(percentageDiscount, testItemID);
//        System.out.println("Final discounted price: $" + percentageDiscountedPrice);
//        
//        // Test adding a new discount
//        System.out.println("\n=== Testing Add Discount ===");
//        try {
//            // Create test discount
//            Discount testDiscount = new Discount();
//            testDiscount.setDiscountID(1001); // Use a unique ID
//            testDiscount.setDiscountDescription("Summer Sale 2023");
//            testDiscount.setDiscountValue(15.0); // $15 or 15% depending on your implementation
//            
//            // Set dates (today + 30 days)
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date startDate = new Date();
//            Date endDate = new Date(startDate.getTime() + (30L * 24 * 60 * 60 * 1000)); // 30 days later
//            
//            testDiscount.setStartDate(startDate);
//            testDiscount.setEndDate(endDate);
//            testDiscount.(true);
//            
//            // Add to database and associate with menu item
//            db.addDiscount(testDiscount, testItemID);
//            System.out.println("Discount added successfully!");
//            
//        } catch (Exception e) {
//            System.err.println("Error creating test discount: " + e.getMessage());
//        }
        
        //Hala Pattern
////  Create a new order
//        Order order = new Order();
//        int orderId = 1001; 
//
//        // Set state to NewOrder
//        order.setState(new NewOrder());
//
//        // Process the order
//        order.handleOrder(orderId);
//------------------------------
//      Order order = new Order();
//        int orderId = 1194; // Assume this order already exists in DB
//
//        // Set state to NewOrder
//        order.setState(new CancelOrder());
//
//        // Process the order
//        order.handleOrder(orderId);


         //Dima Patern
//        System.out.println("==== Testing Credit Card Payment ====");
//        Payment creditPayment = new Payment();
//        creditPayment.setPaymentID(1);
//        creditPayment.selectPaymentMethod("card");
//
//        // Cast to access CreditCard-specific methods
//        CreditCardPaymentMethod ccMethod = (CreditCardPaymentMethod) creditPayment.getPaymentMethod();
//        ccMethod.setCardHolderName("John Doe");
//        ccMethod.setCardNumber(123456789);
//        ccMethod.setCvv(123);
//        ccMethod.enterPaymentInfo(123456789, 123);
//
//        creditPayment.processPayment(1, 150.75, "Card");
////        creditPayment.requestRefund("Online");

//        // Test Cash Payment
//        System.out.println("\n==== Testing Cash Payment ====");
//        Payment cashPayment = new Payment();
//        cashPayment.setPaymentID(6);
//        cashPayment.selectPaymentMethod("cash");
//
//        cashPayment.processPayment(7, 75.50, "Cash");
//        cashPayment.requestRefund("In Store");



// NourAhmed Pattern

//        int itemID = 10; // Replace with a valid item ID from your DB
//        int discountValue = 20; // This could be a percentage or fixed amount depending on strategy
//
//        // Choose discount strategy
//        DiscountStrategy strategy;
//
//        // Let's say we want to test a Fixed discount
//        strategy = new FixedAmountDiscount();
//        // Alternatively:
//        // strategy = new PercentageDiscount();
//
//        // Apply the discount using the strategy
//        strategy.addDiscount(discountValue, itemID);
//
//        System.out.println("Discount applied successfully using " + strategy.getClass().getSimpleName());
//// ------------------------------------------------------------------------
//    PercentageDiscount percentStrategy = new PercentageDiscount();
//        
//        // Create a discount with the percentage strategy
//        Discount discount1 = new Discount(10, "Summer Sale", 20, new Date(), new Date(), true, percentStrategy);
//        
//        // Apply the discount to item #101
//        discount1.addDiscount(20, 10);
//        
//        // Change to fixed amount strategy
//        FixedAmountDiscount fixedStrategy = new FixedAmountDiscount();
//        discount1.setDiscountStrategy(fixedStrategy);
//        discount1.setDiscountValue(10);
//        
//        // Apply the new strategy to the same item
//        discount1.addDiscount(10, 10);
        
    }
}













        
 
        
    
  
        

        
       



       


 

            
        





    
    

