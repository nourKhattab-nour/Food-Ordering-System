/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
 import java.text.SimpleDateFormat;

/**
 *
 * @author Noor
 */
public class DB {
            private static DB db;
            private Connection con;
            private Payment p;
            private Restuarant restuarant;
            

            private String userName = "root";
            private String password = ""; 
            private String dbName = "foodorderingsystem"; 


            private DB() {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, userName, password);
                    System.out.println("Connected to DB");
                } catch (Exception e) {
                    System.err.println("DB Connection Error: " + e.toString());
                }
            }

             public static DB getInstance() {
                if (db == null) db = new DB();
                return db;
            }

            public Connection getConnection() {
                return con;
            }
    
            //Functions
            
          public boolean signUpCustomer(String username, String password, String phone, String address) {
                try {
                    String query = "INSERT INTO User (userName, password, PhoneNumber, address, userType) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement stmt = con.prepareStatement(query);
                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    stmt.setString(3, phone);
                    stmt.setString(4, address);
                    stmt.setString(5, "CUSTOMER");
                    int rowsInserted = stmt.executeUpdate();
                    return rowsInserted > 0;
                } catch (SQLException e) {
                    System.err.println("Signup error: " + e.toString());
                    return false;
                }
            }
public User login(String username, String password) {
        try {
            String query = "SELECT * FROM User WHERE userName = ? AND password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int userID = resultSet.getInt("userID");
                String userName = resultSet.getString("userName");
                String userPassword = resultSet.getString("password");
                String phoneNumber = resultSet.getString("PhoneNumber");
                String address = resultSet.getString("address");
                String userTypeStr = resultSet.getString("userType");
                User.UserType userType = User.UserType.valueOf(userTypeStr);
                
                // Create the appropriate user type
                if (userType == User.UserType.ADMIN) {
                    // Return the singleton instance for Admin
                    return Admin.getInstance();
                } else if (userType == User.UserType.CUSTOMER) {
                   // public Customer(String customerAddress, int userID, String userName, String password, String PhoneNumber, String address, User.UserType userType) {
                    return new Customer(userID, userName, userPassword, phoneNumber, address, userType);
                } else if (userType == User.UserType.RESTAURANT_COORDINATOR) {
                    return new RestuarantCoordinator(userID, userName, userPassword, phoneNumber, address, userType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Login failed
    }
     
  // Method to prevent creating multiple Admin users in database
    public boolean createUser(User user) {
        try {
            // If trying to create an Admin, check if one already exists
            if (user.getUserType() == User.UserType.ADMIN) {
                String checkQuery = "SELECT COUNT(*) as adminCount FROM User WHERE userType = 'ADMIN'";
                PreparedStatement checkStmt = con.prepareStatement(checkQuery);
                ResultSet resultSet = checkStmt.executeQuery();
                
                if (resultSet.next() && resultSet.getInt("adminCount") > 0) {
                    System.out.println("An Admin user already exists. Cannot create another one.");
                    return false;
                }
            }
            
            // Otherwise, proceed with user creation
            String query = "INSERT INTO User (userName, password, PhoneNumber, address, userType) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getUserType().toString());
            
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
                public boolean updateUserAddress(int userID, String newAddress) {
                try {
                    PreparedStatement stmt = con.prepareStatement("UPDATE User SET address=? WHERE userID=?");
                    stmt.setString(1, newAddress);
                    stmt.setInt(2, userID);
                    stmt.executeUpdate();
                    System.out.println("Address updated.");
                    return true;
                } catch (Exception e) {
                    System.err.println("Update address error: " + e.toString());
                    return false;
                }
            }

            public boolean updateUserPhoneNumber(int userID, String newPhoneNumber) {
                try {
                    PreparedStatement stmt = con.prepareStatement("UPDATE User SET phoneNumber=? WHERE userID=?");
                    stmt.setString(1, newPhoneNumber);
                    stmt.setInt(2, userID);
                    stmt.executeUpdate();
                    System.out.println("Phone number updated.");
                    return true;
                } catch (Exception e) {
                    System.err.println("Update phone error: " + e.toString());
                    return false;
                }
            }

            public boolean resetUserPassword(String username, String newPassword) {
                try {
                    PreparedStatement stmt = con.prepareStatement("UPDATE User SET password=? WHERE userName=?");
                    stmt.setString(1, newPassword);
                    stmt.setString(2, username);
                    stmt.executeUpdate();
                    System.out.println("Password reset successfully.");
                    return true;
                } catch (Exception e) {
                    System.err.println("Reset password error: " + e.toString());
                    return false;
                }
            }

            public boolean changeUserPassword(int userID, String newPassword) {
                try {
                    PreparedStatement stmt = con.prepareStatement("UPDATE User SET password=? WHERE userID=?");
                    stmt.setString(1, newPassword);
                    stmt.setInt(2, userID);
                    stmt.executeUpdate();
                    System.out.println("Password changed successfully.");
                    return true;
                } catch (Exception e) {
                    System.err.println("Change password error: " + e.toString());
                    return false;
                }
            }
            
            
            public Admin getAdminUser() {
                    try {
                        String query = "SELECT * FROM User WHERE userType = 'ADMIN' LIMIT 1";
                        PreparedStatement statement = con.prepareStatement(query);
                        ResultSet resultSet = statement.executeQuery();

                        if (resultSet.next()) {
                            int userID = resultSet.getInt("userID");
                            String userName = resultSet.getString("userName");
                            String password = resultSet.getString("password");
                            String phoneNumber = resultSet.getString("PhoneNumber");
                            String address = resultSet.getString("address");
                            User.UserType userType = User.UserType.valueOf(resultSet.getString("userType"));

                            // Use the private constructor through reflection to maintain singleton integrity
                            Constructor<Admin> constructor = Admin.class.getDeclaredConstructor(
                                int.class, String.class, String.class, String.class, String.class, User.UserType.class);
                            constructor.setAccessible(true);
                            return constructor.newInstance(userID, userName, password, phoneNumber, address, userType);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                }

            // Review class
            
         public void addReview(Review review) {
                        if (review == null) {
                            System.err.println("Cannot add null review");
                            return;
                        }

                        // Additional validation
                        if (review.getCustomer() == null) {
                            System.err.println("Review must be associated with a customer");
                            return;
                        }
                        if (review.getOrder() == null) {
                            System.err.println("Review must be associated with an order");
                            return;
                        }

                        try (PreparedStatement stmt = con.prepareStatement(
                                "INSERT INTO Review (userID, orderID, rating, comment, reviewDate) VALUES (?, ?, ?, ?, ?)",
                                Statement.RETURN_GENERATED_KEYS)) {

                            // Set all parameters using Date
                            stmt.setInt(1, review.getCustomer().getUserID());
                            stmt.setInt(2, review.getOrder().getOrderID());
                            stmt.setInt(3, review.getRating());
                            stmt.setString(4, review.getComment());
                            stmt.setDate(5, new java.sql.Date(review.getReviewDate().getTime()));  // Using Date instead of Timestamp

                            int affectedRows = stmt.executeUpdate();

                            if (affectedRows > 0) {
                                // Get the auto-generated review ID
                                try (ResultSet rs = stmt.getGeneratedKeys()) {
                                    if (rs.next()) {
                                        int generatedId = rs.getInt(1);  // Get the first column (auto-generated ID)
                                        if (generatedId > 0) {
                                            review.setReviewId(generatedId);
                                            System.out.println("Review added successfully. ID: " + review.getReviewId());
                                        } else {
                                            System.err.println("Warning: Received invalid generated ID");
                                        }
                                    }
                                }
                            }
                        } catch (SQLException e) {
                            System.err.println("DATABASE INSERTION ERROR: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }

            
        public void removeReview(Review review) {                            //Sucess 
                    if (review == null) {
                        System.err.println("Cannot remove null review");
                        return;
                    }

                    if (review.getReviewId() <= 0) {
                        System.err.println("Invalid review ID");
                        return;
                    }

                    try (PreparedStatement stmt = con.prepareStatement("DELETE FROM Review WHERE reviewID = ?")) {
                        stmt.setInt(1, review.getReviewId());

                        int affectedRows = stmt.executeUpdate();

                        if (affectedRows > 0) {
                            System.out.println("Review removed successfully. ID: " + review.getReviewId());
                        } else {
                            System.err.println("No review found with ID: " + review.getReviewId());
                        }
                    } catch (SQLException e) {
                        System.err.println("DATABASE DELETION ERROR: " + e.getMessage());
                        e.printStackTrace();
                    }
}

            
            
           public void displayReview() {
                try (Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT * FROM Review ORDER BY reviewDate DESC")) {

                    System.out.println("\n--- All Reviews ---");
                    System.out.printf("%-10s %-10s %-10s %-7s %-30s %-15s%n", 
                                     "ReviewID", "UserID", "OrderID", "Rating", "Comment", "Date");
                    System.out.println("-------------------------------------------------------------------");

                    while (rs.next()) {
                        System.out.printf("%-10d %-10d %-10d %-7d %-30s %-15s%n",
                            rs.getInt("reviewID"),
                            rs.getInt("userID"),
                            rs.getInt("orderID"),
                            rs.getInt("rating"),
                            rs.getString("comment"),
                            rs.getDate("reviewDate"));
                    }
                } catch (SQLException e) {
                    System.err.println("DATABASE QUERY ERROR: " + e.getMessage());
                }
            }
            
           //Resturant
       /**
 * Displays all restaurants directly from the database
 * Prints results to console without storing in collection
 */
            public void viewAllRestaurants() {
                try (Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT * FROM restaurant")) {

                   

                    boolean hasResults = false;

                    while (rs.next()) {
                        hasResults = true;
                        System.out.println(
                            rs.getInt("restuarantID") + "\t" +
                            rs.getString("resturantname") + "\t" +
                            rs.getString("location")
                        );
                    }

                    if (!hasResults) {
                        System.out.println("No restaurants found in database");
                    }

                } catch (SQLException e) {
                    System.err.println("DATABASE QUERY ERROR: " + e.getMessage());
                }
            }
        public void addResturant(Restuarant restaurant) {
            try {
                Statement stmt = con.createStatement();
                stmt.executeUpdate("Insert Into Restaurant (restuarantID, resturantname) values ('" + restaurant.getRestuarantID() + "', '" + restaurant.getRestaurantName() + "')");
                System.out.println("Restaurant added");
                } catch (SQLException e) {
                    System.err.println("DATABASE INSERTION ERROR: " + e.toString());
                }
        }
        
        
        public void removeRestuarant(int restuarantID) {
            try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("Delete From Restaurant Where restuarantID = '" + restuarantID + "'");
            System.out.println("Restaurant removed");
            } catch (Exception e) {
                System.err.println("DATABASE DELETION ERROR: " + e.toString());
            }
        }
        
        //Report
//        public Report displayReport() {
//            try {
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("Select * From Report Where reportID = '" + reportID + "'");
//
//            if (rs.next()) {
//                Report report = new Report (reportID, reportDate, reportInfo, reportCreator, reportTitle);
//                report.setReportID(rs.getInt("reportID"));
//                report.setReportTitle(rs.getString("reportTitle"));
//                report.setReportDate(rs.getString("reportDate"));
//                report.setReportInfo(rs.getString("reportInfo"));
//                report.setReportCreator(rs.getString("reportCreator"));
//            
//                System.out.println("Report ID: " + report.getReportID());
//                System.out.println("Title: " + report.getReportTitle());
//                System.out.println("Date: " + report.getReportDate());
//                System.out.println("Creator: " + report.getReportCreator());
//                System.out.println("Information: " + report.getReportInfo());
//            
//                System.out.println("Report data retrieved");
//                return report;
//                } else {
//                    System.out.println("Report not found");
//                    return null;
//                }
//            } catch (Exception e) {
//                System.err.println("DATABASE QUERY ERROR: " + e.toString());
//                return null;
//            }
//        }

//       
        
                    //favorites
             public boolean addFavorite(Item item,int userID) {
                
                     int itemID = item.getItemID();
                 try {
                     // First check if the item is available
                     if (!item.getIsAvailable()) {
                         System.out.println("Item is not available and cannot be added to favorites.");
                         return false;
                     }

                     // Check if the item is already in favorites
                     PreparedStatement checkStmt = con.prepareStatement(
                         "SELECT * FROM favorite WHERE userID=? AND itemID=?");
                     checkStmt.setInt(1, userID);
                     checkStmt.setInt(2, itemID);
                     ResultSet rs = checkStmt.executeQuery();

                     if (rs.next()) {
                         System.out.println("Item is already in favorites.");
                         return false;
                     }

                     // Add item to favorites
                     PreparedStatement stmt = con.prepareStatement(
                         "INSERT INTO favorite (userID, itemID) VALUES (?, ?)");
                     stmt.setInt(1, userID);
                     stmt.setInt(2, itemID);
                     stmt.executeUpdate();

                     System.out.println("Item added to favorites successfully.");
                     return true;
                 } catch (Exception e) {
                     System.err.println("Add to favorites error: " + e.toString());
                     return false;
                 }
             }

            public boolean removeFavorite(Item item, int userID) {
                try {
                    
                    int itemID = item.getItemID();

                    // Check if the item exists in favorites before removing
                    PreparedStatement checkStmt = con.prepareStatement(
                        "SELECT * FROM favorite WHERE userID=? AND itemID=?");
                    checkStmt.setInt(1, userID);
                    checkStmt.setInt(2, itemID);
                    ResultSet rs = checkStmt.executeQuery();

                    if (!rs.next()) {
                        System.out.println("Item is not in favorites.");
                        return false;
                    }

                    // Remove item from favorites
                    PreparedStatement stmt = con.prepareStatement(
                        "DELETE FROM Favorites WHERE userID=? AND itemID=?");
                    stmt.setInt(1, userID);
                    stmt.setInt(2, itemID);
                    stmt.executeUpdate();

                    System.out.println("Item removed from favorites successfully.");
                    return true;
                } catch (Exception e) {
                    System.err.println("Remove from favorites error: " + e.toString());
                    return false;
                }
            }
        
            //Item
            public void addItem(Item item) {
                try {
                    PreparedStatement stmt = con.prepareStatement(
                        "INSERT INTO Item (itemName, Description, quantity, price, category, isAvailable) VALUES (?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);

                    stmt.setString(1, item.getItemName());
                    stmt.setString(2, item.getDescription());
                    stmt.setInt(3, item.getQuantity());
                    stmt.setDouble(4, item.getPrice());
                    stmt.setString(5, item.getCatagory());
                    stmt.setBoolean(6, item.getIsAvailable());

                    stmt.executeUpdate();

                    // Get the auto-generated itemID
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            item.setItemID(generatedKeys.getInt(1));
                            System.out.println("Item added successfully with ID: " + item.getItemID());
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Add item error: " + e.toString());
                }
            }
            
            
            // Update item details
            public void updateItem(int itemID, Item updatedDetails) {
                try {
                    PreparedStatement stmt = con.prepareStatement(
                        "UPDATE Item SET itemName=?, Description=?, quantity=?, price=?, category=?, isAvailable=? WHERE itemID=?");

                    stmt.setString(1, updatedDetails.getItemName());
                    stmt.setString(2, updatedDetails.getDescription());
                    stmt.setInt(3, updatedDetails.getQuantity());
                    stmt.setDouble(4, updatedDetails.getPrice());
                    stmt.setString(5, updatedDetails.getCatagory());
                    stmt.setBoolean(6, updatedDetails.getIsAvailable());
                    stmt.setInt(7, itemID);

                    int affectedRows = stmt.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Item updated successfully.");
                    } else {
                        System.out.println("No item found with ID: " + itemID);
                    }
                } catch (Exception e) {
                    System.err.println("Update item error: " + e.toString());
                }
            }
            
            // Delete item
        public void deleteItem(Item item) {
            try {
                PreparedStatement stmt = con.prepareStatement("DELETE FROM Item WHERE itemID=?");
                stmt.setInt(1, item.getItemID());

                int affectedRows = stmt.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Item deleted successfully.");
                } else {
                    System.out.println("No item found with ID: " + item.getItemID());
                }
            } catch (Exception e) {
                System.err.println("Delete item error: " + e.toString());
            }
        }

        public boolean checkAvailability(int itemID) {
            try {
                PreparedStatement stmt = con.prepareStatement("SELECT isAvailable FROM Item WHERE itemID = ?");
                stmt.setInt(1, itemID);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return rs.getBoolean("isAvailable");
                } else {
                    System.out.println("No item found with ID: " + itemID);
                    return false;
                }
            } catch (Exception e) {
                System.err.println("Check availability error: " + e.toString());
                return false;
            }
        }
        // Update item availability
        public boolean updateAvailability(int itemID, boolean isAvailable) {
            try {
                PreparedStatement stmt = con.prepareStatement("UPDATE Item SET isAvailable=? WHERE itemID=?");
                stmt.setBoolean(1, isAvailable);
                stmt.setInt(2, itemID);

                int affectedRows = stmt.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Item availability updated successfully.");
                    return true;
                } else {
                    System.out.println("No item found with ID: " + itemID);
                    return false;
                }
            } catch (Exception e) {
                System.err.println("Update availability error: " + e.toString());
                return false;
            }
        }
        // Search for item by name
   

        // Update item quantity
        public boolean updateItemQuantity(String itemName, int quantity) {
            try {
                PreparedStatement stmt = con.prepareStatement("UPDATE Item SET quantity=? WHERE itemName=?");
                stmt.setInt(1, quantity);
                stmt.setString(2, itemName);

                int affectedRows = stmt.executeUpdate();

                if (affectedRows > 0) {
                    // If quantity is 0, set availability to false
                    if (quantity == 0) {
                        PreparedStatement updateAvail = con.prepareStatement("UPDATE Item SET isAvailable=FALSE WHERE itemName=?");
                        updateAvail.setString(1, itemName);
                        updateAvail.executeUpdate();
                    } else {
                        // If quantity is greater than 0, ensure availability is true
                        PreparedStatement updateAvail = con.prepareStatement("UPDATE Item SET isAvailable=TRUE WHERE itemName=?");
                        updateAvail.setString(1, itemName);
                        updateAvail.executeUpdate();
                    }

                    System.out.println("Item quantity updated successfully.");
                    return true;
                } else {
                    System.out.println("No item found with name: " + itemName);
                    return false;
                }
            } catch (Exception e) {
                System.err.println("Update item quantity error: " + e.toString());
                return false;
            }
        }
        //Discount
   


public void addDiscount(Discount discount, int menuItemId) {
    try {
        Statement stmt = con.createStatement();
        
        // Format the Java Date to SQL-friendly string
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDateStr = sdf.format(discount.getStartDate());
        String endDateStr = sdf.format(discount.getEndDate());

        // Convert boolean isActive to integer
        int isActiveValue = discount.isActive() ? 1 : 0;
        
        String sql = "INSERT INTO Discount (discountID, discountDescription, discountValue, startDate, endDate, isActive) " +
                     "VALUES ('" + discount.getDiscountID() + "', '" + 
                     discount.getDiscountDescription() + "', '" + 
                     discount.getDiscountValue() + "', '" + 
                     startDateStr + "', '" + 
                     endDateStr + "', '" + 
                     isActiveValue + "')";  // <<< HERE: use integer not boolean

        stmt.executeUpdate(sql);

//        // Associate discount with menu item
        String menuItemSql = "INSERT INTO menuitem_discount (menu_item_id, discountID) " +
                             "VALUES ('" + menuItemId + "', '" + discount.getDiscountID() + "')";
        stmt.executeUpdate(menuItemSql);

        System.out.println("Discount added and applied to menu item");
    } catch (Exception e) {
        System.err.println("DATABASE DISCOUNT INSERTION ERROR: " + e.toString());
    }
}


        
       

public void updateDiscount(Discount discount) {
    try {
        Statement stmt = con.createStatement();

        // Format the Java Date to SQL-friendly string
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String startDateStr = sdf.format(discount.getStartDate());
        String endDateStr = sdf.format(discount.getEndDate());

        // Convert boolean to integer
        int isActiveValue = discount.isActive() ? 1 : 0;

        String sql = "UPDATE Discount SET " +
                     "discountDescription = '" + discount.getDiscountDescription() + "', " +
                     "discountValue = '" + discount.getDiscountValue() + "', " +
                     "startDate = '" + startDateStr + "', " +
                     "endDate = '" + endDateStr + "', " +
                     "isActive = '" + isActiveValue + "' " +
                     "WHERE discountID = '" + discount.getDiscountID() + "'";

        stmt.executeUpdate(sql);
        System.out.println("Discount updated");
    } catch (Exception e) {
        System.err.println("DATABASE DISCOUNT UPDATE ERROR: " + e.toString());
    }
}


        
        public void removeDiscount(int discountID) {
            try {
                Statement stmt = con.createStatement();

                // First remove associations
                String deleteMappingsSql = "DELETE FROM MenuItem_Discount WHERE discountID = '" + discountID + "'";
                stmt.executeUpdate(deleteMappingsSql);

                // Then remove the discount
                String sql = "DELETE FROM Discount WHERE discountID = '" + discountID + "'";
                stmt.executeUpdate(sql);

                System.out.println("Discount removed");
            } catch (Exception e) {
                System.err.println("DATABASE DISCOUNT REMOVAL ERROR: " + e.toString());
            }
        }

       

    public void printReceipt(Receipt receipt) {
    try {
        // Updated query with placeholder for receiptID
        String sql = "SELECT r.receiptID, o.orderDate, o.customerID, i.itemName, i.itemPrice, od.quantity " +
                     "FROM basicreceipt r " +
                     "JOIN orders o ON r.orderID = o.orderID " +
                     "JOIN orderdetails od ON o.orderID = od.orderID " +
                     "JOIN menuitem i ON od.itemID = i.itemID " +
                     "WHERE r.receiptID = ?;";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, receipt.getReceiptID());  // Use dynamic value here
        ResultSet rs = stmt.executeQuery();

        // Handle no results
        if (!rs.isBeforeFirst()) {
            System.out.println("No receipt found with ID: " + receipt.getReceiptID());
            return;
        }

        System.out.println("\n========== RECEIPT ==========");
        boolean headerPrinted = false;
        while (rs.next()) {
            if (!headerPrinted) {
                System.out.println("Receipt ID: " + rs.getString("receiptID"));
                System.out.println("Date: " + rs.getDate("orderDate"));
                System.out.println("Customer ID: " + rs.getString("customerID"));
                System.out.println("--------------------------------");
                headerPrinted = true;
            }

            System.out.printf("%-20s %5d x $%.2f\n",
                rs.getString("itemName"),
                rs.getInt("quantity"),
                rs.getDouble("itemPrice"));
        }
        System.out.println("================================");

    } catch (Exception e) {
        System.err.println("DATABASE ERROR: " + e.getMessage());
        e.printStackTrace();  // Helpful for debugging
    }
}
      
        //Menu
        public void viewMenu(Menu menu) {
                try {
                    Statement stmt = con.createStatement();
                    // Updated query - removed itemDescription and using Description instead
                    String sql = "SELECT m.menuID, m.menuCatagory, i.itemID, i.itemName, i.Description, i.price " +
                                 "FROM Menu m " +
                                 "JOIN MenuItem mi ON m.menuID = mi.menuID " +
                                 "JOIN Item i ON mi.itemID = i.itemID " +
                                 "WHERE m.menuID = '" + menu.getMenuID() + "'";

                    ResultSet rs = stmt.executeQuery(sql);

                    System.out.println("========== MENU: " + menu.getMenuCatagory() + " ==========");

                    while (rs.next()) {
                        System.out.println("Item ID: " + rs.getInt("itemID"));
                        System.out.println("Name: " + rs.getString("itemName"));
                        System.out.println("Description: " + rs.getString("Description")); // Changed from itemDescription
                        System.out.println("Price: $" + rs.getDouble("price")); // Changed from itemPrice
                        System.out.println("----------------------------------------");
                    }

                    System.out.println("======================================");

                } catch (Exception e) {
                    System.err.println("DATABASE MENU VIEW ERROR: " + e.toString());
                    e.printStackTrace(); // Added for better debugging
                }
            }
        public void addMenuCatagory(Menu menu) {
            try {
                Statement stmt = con.createStatement();
                String sql = "INSERT INTO Menu (menuID, menuCatagory) " +
                             "VALUES ('" + menu.getMenuID() + "', '" + menu.getMenuCatagory() + "')";

                stmt.executeUpdate(sql);
                System.out.println("Menu category added");

                // If the menu has items, add them to the MenuItem table
//                if (!menu.getItems().isEmpty()) {
//                    for (Item item : menu.getItems()) {
//                        String itemSql = "INSERT INTO MenuItem (menuID, itemID) " +
//                                        "VALUES ('" + menu.getMenuID() + "', '" + item.getItemID() + "')";
//                        stmt.executeUpdate(itemSql);
//                    }
//                    System.out.println("Menu items added");
//                }

            } catch (Exception e) {
                System.err.println("DATABASE MENU CATEGORY INSERTION ERROR: " + e.toString());
            }
        }

        public void updateMenuCatagory(Menu menu) {
            try {
                Statement stmt = con.createStatement();
                String sql = "UPDATE Menu SET menuCatagory = '" + menu.getMenuCatagory() + "' " +
                             "WHERE menuID = '" + menu.getMenuID() + "'";

                stmt.executeUpdate(sql);
                System.out.println("Menu category updated");

            } catch (Exception e) {
                System.err.println("DATABASE MENU CATEGORY UPDATE ERROR: " + e.toString());
            }
        }


    public void addToMenu(Menu menu, Item item) {
        try {
            Statement stmt = con.createStatement();

            // First check if the item exists in the Item table, if not, add it
            String checkItemSql = "SELECT * FROM Item WHERE itemID = '" + item.getItemID() + "'";
            ResultSet rs = stmt.executeQuery(checkItemSql);

            if (!rs.next()) {
                // Item doesn't exist, so add it to the Item table
                String addItemSql = "INSERT INTO item (itemID, itemName, Description, Price) " +
                                   "VALUES ('" + item.getItemID() + "', '" + 
                                   item.getItemName() + "', '" + 
                                   item.getDescription() + "', '" + 
                                   item.getPrice() + "')";
                stmt.executeUpdate(addItemSql);
                System.out.println("Item added to Item table");
            }

            // Now add the relationship between menu and item
            String addToMenuSql = "INSERT INTO menuitem (menuID, itemID, itemName, Description, itemPrice,quantity,category) " +
                                 "VALUES ('" + menu.getMenuID() + "', '" + item.getItemID() + "','" + item.getItemName()+ "','" +item.getDescription()+ "','" + item.getPrice()+ "','" + item.getQuantity()+ "','" + item.getCatagory()+ "')";
            stmt.executeUpdate(addToMenuSql);

            System.out.println("Item added to menu successfully");

        } catch (Exception e) {
            System.err.println("DATABASE ADD TO MENU ERROR: " + e.toString());
        }
    }
        
   public Restuarant searchRestaurant(String restaurantName, Customer customer) {
    try {
        // First: Search in customer's location
        PreparedStatement stmt = con.prepareStatement(
            "SELECT * FROM Restaurant WHERE resturantname LIKE ? AND location = ?"
        );
        stmt.setString(1, "%" + restaurantName + "%");
        stmt.setString(2, customer.getAddress());

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Restuarant(
                rs.getInt("restuarantID"),
                rs.getString("resturantname"), // ✅ FIXED COLUMN NAME
                rs.getString("location")
            );
        }

        // Fallback: search anywhere
        stmt = con.prepareStatement(
            "SELECT * FROM Restaurant WHERE resturantname LIKE ?"
        );
        stmt.setString(1, "%" + restaurantName + "%");

        rs = stmt.executeQuery();

        if (rs.next()) {
            System.out.println("Restaurant found outside delivery area.");
            return new Restuarant(
                rs.getInt("restuarantID"),
                rs.getString("resturantname"), // ✅ FIXED COLUMN NAME
                rs.getString("location")
            );
        } else {
            System.out.println("No matching restaurant found.");
            return null;
        }

    } catch (SQLException e) {
        System.err.println("Error searching restaurant: " + e.getMessage());
        return null;
    }
}

 public Item searchItem(String itemName) {
    try {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM Item WHERE itemName LIKE ?");
        stmt.setString(1, "%" + itemName + "%");
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Item(
                rs.getInt("itemID"),
                rs.getString("itemName"),
                rs.getString("description"),
                rs.getInt("quantity"),
                rs.getDouble("price"),
                rs.getString("category"),
                rs.getBoolean("isAvailable")
            );
        } else {
            System.out.println("No items found matching: " + itemName);
            return null;
        }

    } catch (SQLException e) {
        System.err.println("Error searching item: " + e.getMessage());
        return null;
    }
}
        //ObserverDesign pattern
        
       // Observer pattern methods

   public String registerObserver(DiscountObserver observer) {
    try {
        // First check if customer exists
        if (!idExists("user", "userID", observer.getID())) {
            return "Customer ID " + observer.getID() + " not found";
        }

        PreparedStatement stmt = con.prepareStatement(
            "INSERT INTO discount_subscriptions (customer_id, active) " +
            "VALUES (?, TRUE) " +
            "ON DUPLICATE KEY UPDATE active = TRUE");  // Update if already exists

        stmt.setInt(1, observer.getID());
        int rowsAffected = stmt.executeUpdate();
        stmt.close();

        if (rowsAffected > 0) {
            return "Successfully registered customer " + observer.getID() + " for notifications";
        }
        return "Failed to register observer";
    } catch (SQLException e) {
        return "Database error: " + e.getMessage();
    }
}

        public String removeObserver(DiscountObserver observer) {
            try {
                PreparedStatement stmt = con.prepareStatement(
                    "UPDATE discount_subscriptions SET active = 0 " +
              "WHERE customer_id = ?"
                );
                stmt.setInt(1,observer.getID() );
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    return "Successfully unsubscribed from discount notifications";
                } else {
                    return "No subscription found to unsubscribe";
                }
            } catch (SQLException e) {
                return "Error unsubscribing from discount notifications: " + e.getMessage();
            }
        }


        public String notifyObservers(Discount discount) {
                try {
                    PreparedStatement stmt = con.prepareStatement(
                        "SELECT u.userID, u.userName, u.password, u.phoneNumber, u.address, u.userType, " +
                        "c.customerAge, c.customerGender, c.customerAddress " +
                        "FROM user u " +
                        "JOIN customer c ON u.userID = c.userID " +
                        "JOIN discount_subscriptions ds ON u.userID = ds.customer_id " +
                        "WHERE ds.active = 1"
                    );

                    ResultSet rs = stmt.executeQuery();
                    int notificationCount = 0;

                    while (rs.next()) {
                        int userID = rs.getInt("userID");
                        String userName = rs.getString("userName");
                        String password = rs.getString("password");
                        String phoneNumber = rs.getString("phoneNumber");
                        String address = rs.getString("address");
                        User.UserType userType = User.UserType.valueOf(rs.getString("userType"));
                        int customerAge = rs.getInt("customerAge");
                        String customerGender = rs.getString("customerGender");
                        String customerAddress = rs.getString("customerAddress");

                        // Create customer and update them about the discount
                        Customer customer = new Customer(customerAddress, 
                                                       userID, userName, password, phoneNumber, 
                                                       address, userType);
                        customer.update(discount);

                        // Create notification record
                        displayNotifications(userID);
                        notificationCount++;
                    }

                    return "Notified " + notificationCount + " customers about the discount";
                } catch (SQLException e) {
                    return "Error notifying customer: " + e.getMessage();
                }
            }

                // Helper method for customer update
        
    
                public boolean createNotification(int customerId, int discountId, String message) {
        try {
            // Verify customer exists
            if (!idExists("user", "userID", customerId)) {
                System.out.println("Customer ID " + customerId + " not found");
                return false;
            }
            
            // Verify discount exists
            if (!idExists("discount", "discountID", discountId)) {
                System.out.println("Discount ID " + discountId + " not found");
                return false;
            }

            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO customer_notifications (customer_id, discount_id, message) " +
                "VALUES (?, ?, ?)");
            
            stmt.setInt(1, customerId);
            stmt.setInt(2, discountId);
            stmt.setString(3, message);
            
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    private boolean idExists(String table, String idColumn, int id) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(
            "SELECT 1 FROM " + table + " WHERE " + idColumn + " = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        boolean exists = rs.next();
        rs.close();
        stmt.close();
        return exists;
    }

    // Display notifications for customer
    public void displayNotifications(int customerId) {
        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT n.notification_id, d.discountDescription, n.message, n.created_at " +
                "FROM customer_notifications n " +
                "JOIN discount d ON n.discount_id = d.discountID " +
                "WHERE n.customer_id = ? " +
                "ORDER BY n.created_at DESC");
            
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            
            System.out.println("\n=== NOTIFICATIONS FOR CUSTOMER " + customerId + " ===");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("notification_id"));
                System.out.println("Discount: " + rs.getString("discountDescription"));
                System.out.println("Message: " + rs.getString("message"));
                System.out.println("Date: " + rs.getTimestamp("created_at"));
                System.out.println("---------------------");
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving notifications: " + e.getMessage());
        }
    }


  public boolean validateIdsExist(int customerId, int discountId) {
    try {
        // Check customer exists
        PreparedStatement customerStmt = con.prepareStatement(
            "SELECT 1 FROM user WHERE userID = ?");
        customerStmt.setInt(1, customerId);
        ResultSet customerRs = customerStmt.executeQuery();
        boolean customerExists = customerRs.next();
        customerRs.close();
        customerStmt.close();
        
        // Check discount exists
        PreparedStatement discountStmt = con.prepareStatement(
            "SELECT 1 FROM discount WHERE discountID = ?");
        discountStmt.setInt(1, discountId);
        ResultSet discountRs = discountStmt.executeQuery();
        boolean discountExists = discountRs.next();
        discountRs.close();
        discountStmt.close();
        
        return customerExists && discountExists;
    } catch (SQLException e) {
        System.err.println("Error validating IDs: " + e.getMessage());
        return false;
    }
}
    
    // 2. Notification Display
   

                // 3. Mark as Read
                public void markNotificationAsRead(int notificationId) {
                    try {
                        PreparedStatement stmt = con.prepareStatement(
                            "UPDATE customer_notifications SET is_read = TRUE WHERE notification_id = ?"
                        );
                        stmt.setInt(1, notificationId);
                        stmt.executeUpdate();
                        con.commit();
                        stmt.close();
                    } catch (SQLException e) {
                        System.out.println("Error marking as read: " + e.getMessage());
                    }
                }
                        // Discount operations
           public String createDiscount(String description, int value, Date startDate, Date endDate, 
                           boolean isActive, String conditions, DiscountStrategy strategy) {
                        try {
                            PreparedStatement stmt = con.prepareStatement(
                                "INSERT INTO discount (discountDescription, discountValue, startDate, endDate, isActive, additionalConditions) " +
                                "VALUES (?, ?, ?, ?, ?, ?)",
                                Statement.RETURN_GENERATED_KEYS
                            );

                            // Set parameters
                            stmt.setString(1, description);
                            stmt.setInt(2, value);
                            stmt.setDate(3, startDate != null ? new java.sql.Date(startDate.getTime()) : null);
                            stmt.setDate(4, endDate != null ? new java.sql.Date(endDate.getTime()) : null);
                            stmt.setBoolean(5, isActive);
                            stmt.setString(6, conditions != null ? conditions : "");

                            int rowsAffected = stmt.executeUpdate();

                            if (rowsAffected == 0) {
                                return "Failed to create discount";
                            }

                            // Get generated ID
                            int newDiscountId = -1;
                            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                                if (generatedKeys.next()) {
                                    newDiscountId = generatedKeys.getInt(1);
                                }
                            }

                            // Create discount object with all required fields
                            Discount discount = new Discount(
                                newDiscountId,
                                description,
                                value,
                                startDate,
                                endDate,
                                isActive,
                                strategy
                            );
                            discount.setAdditionalConditions(conditions);

                            // Notify observers
                            String notificationResult = notifyObservers(discount);

                            return "Discount created successfully. ID: " + newDiscountId + ". " + notificationResult;
                        } catch (SQLException e) {
                            return "Error creating discount: " + e.getMessage();
                        }
                    }
            
            public void executeQuery(String query) {
                    try (Statement stmt = con.createStatement();
                         ResultSet rs = stmt.executeQuery(query)) {

                        System.out.println("Executing query: " + query);

                        if (!rs.isBeforeFirst()) {
                            System.out.println("No results returned");
                            return;
                        }

                        // Simple output without metadata
                        while (rs.next()) {
                            int cols = rs.getMetaData().getColumnCount();
                            for (int i = 1; i <= cols; i++) {
                                System.out.print(rs.getString(i) + " | ");
                            }
                            System.out.println();
                        }
                    } catch (SQLException e) {
                        System.err.println("Query failed: " + e.getMessage());
                        System.err.println("SQL State: " + e.getSQLState());
                        System.err.println("Error Code: " + e.getErrorCode());
                    }
                }
            
         
            //Order
            public Order viewOrder(int orderId) {
                        Order order = null;
                        try {
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT * FROM Orders WHERE orderID = '" + orderId + "'");

                            if (rs.next()) {
                                order = new Order();
                                order.setOrderID(rs.getInt("orderID"));
                                order.setOrderDescription(rs.getString("orderDescription"));
                                order.setOrderDate(rs.getDate("orderDate"));
//                                order.setOrderStatus(rs.getString("orderStatus"));
                                order.setOrderDeliveryTime(rs.getTime("orderDeliveryTime"));
                                order.setOrderAddress(rs.getString("orderAddress"));
                                order.setOrderFromResturant(rs.getString("OrderFromRestaurant"));
                                order.setOrderItem(rs.getString("orderItem"));
                                order.setTotalAmount(rs.getDouble("totalAmount"));
                            } else {
                                System.out.println("No order found with ID: " + orderId);
                            }
                        } catch (Exception e) {
                            System.err.println("DATABASE VIEW ERROR: " + e.toString());
                        }
                        return order;
                    }

               public void viewAllOrders() {
                    try {
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM Orders");

                        while (rs.next()) {
                            System.out.println("\n========= ORDER DETAILS =========");
                            System.out.println("Order ID: " + rs.getInt("orderID"));
                            System.out.println("Order Name: " + rs.getString("orderName"));
                            System.out.println("Order Description: " + rs.getString("orderDescription"));
                            System.out.println("Order Date: " + rs.getDate("orderDate"));
                            System.out.println("Order Status: " + rs.getString("orderStatus"));
                            System.out.println("Order Delivery Time: " + rs.getTime("orderDeliveryTime"));
                            System.out.println("Order Address: " + rs.getString("orderAddress"));
//                            System.out.println("Order From Restaurant: " + rs.getString("OrderFromRestaurant"));
                            System.out.println("Order Item: " + rs.getString("orderItem"));
                            System.out.println("Customer ID: " + rs.getInt("customerId"));
                            System.out.println("Total Amount: $" + rs.getDouble("totalAmount"));
                        }
                    } catch (Exception e) {
                        System.err.println("DATABASE VIEW ALL ORDERS ERROR: " + e.toString());
                    }
                }
    
               
                  public boolean placeOrder(Order o) {
                                try {
                                    Connection con = getConnection(); // assuming you already have this method
                                    Statement stmt = con.createStatement();

                                    // Format java.util.Date to MySQL compatible format
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    String formattedDate = dateFormat.format(o.getOrderDate());

                                    // Format delivery time
                                    String formattedTime = o.getOrderDeliveryTime().toString();

                                    String query = "INSERT INTO Orders (" +
                                            "orderID,orderDescription, orderDate, orderStatus, " +
                                            "orderDeliveryTime, orderAddress, OrderFromRestaurant, " +
                                            "orderItem, customerId, totalAmount) " +
                                            "VALUES ('" + o.getOrderID() + "', '" +
                                            o.getOrderDescription() + "', '" +
                                            formattedDate + "', '" +
//                                            o.getOrderStatus() + "', '" +
                                            formattedTime + "', '" +
                                            o.getOrderAddress() + "', '" +
                                            o.getOrderFromResturant() + "', '" +
                                            o.getOrderItem() + "', '" +
                                            o.getCustomerId() + "', '" +
                                            o.getTotalAmount() + "')";

                                    stmt.executeUpdate(query);
                                    System.out.println("Order placed successfully.");
                                    return true;

                                } catch (Exception e) {
                                    System.err.println("DATABASE INSERTION ERROR: " + e.toString());
                                    return false;
                                }
                            }
                  
                public void updateOrderStatus(int orderId, String newStatus) {
                        try {
                            Statement stmt = con.createStatement();
                            stmt.executeUpdate("UPDATE Orders SET orderStatus = '" + newStatus + "' WHERE orderID = '" + orderId + "'");
                            System.out.println("Order status updated to: " + newStatus);
                        } catch (Exception e) {
                            System.err.println("DATABASE UPDATE STATUS ERROR: " + e.toString());
                        }
                    }
                
                 public Order trackOrder(int orderID) {
                        try {
                            String sql = "SELECT * FROM Orders WHERE orderID = ?";
                            PreparedStatement pstmt = con.prepareStatement(sql);
                            pstmt.setInt(1, orderID);
                            ResultSet rs = pstmt.executeQuery();

                            if (rs.next()) {
                                Order order = new Order();
                                order.setOrderID(rs.getInt("orderID"));
                                order.setOrderDescription(rs.getString("orderDescription"));
                                order.setOrderDate(rs.getTimestamp("orderDate"));
//                                order.setOrderStatus(rs.getString("orderStatus"));
                                order.setOrderDeliveryTime(rs.getTime("orderDeliveryTime"));
                                order.setOrderAddress(rs.getString("orderAddress"));
                                order.setOrderFromResturant(rs.getString("OrderFromRestaurant"));
                                order.setOrderItem(rs.getString("orderItem"));
                                order.setCustomerId(rs.getInt("customerId"));
                                order.setTotalAmount(rs.getDouble("totalAmount"));
                                
                                return order;
                            }
                        } catch (SQLException e) {
                            System.err.println("ORDER TRACKING ERROR: " + e.getMessage());
                        }
                        return null;
                    }
                  
                 public boolean cancelOrder(int orderID) {
                        try {
                            // First check if order can be cancelled
                            String checkSql = "SELECT orderStatus, paymentID FROM Orders WHERE orderID = ?";
                            PreparedStatement checkStmt = con.prepareStatement(checkSql);
                            checkStmt.setInt(1, orderID);
                            ResultSet rs = checkStmt.executeQuery();

                            if (!rs.next()) {
                                System.out.println("Order not found");
                                return false;
                            }

                            String status = rs.getString("orderStatus");
                            int paymentID = rs.getInt("paymentID");

                            // Check if order is already cancelled or completed
                            if ("Cancelled".equalsIgnoreCase(status)) {
                                System.out.println("Order is already cancelled");
                                return false;
                            }

                            if ("Delivered".equalsIgnoreCase(status)) {
                                System.out.println("Cannot cancel delivered order");
                                return false;
                            }

                            // Update order status
                            String updateSql = "UPDATE Orders SET orderStatus = 'Cancelled' WHERE orderID = ?";
                            PreparedStatement updateStmt = con.prepareStatement(updateSql);
                            updateStmt.setInt(1, orderID);
                            int rowsAffected = updateStmt.executeUpdate();

                            if (rowsAffected > 0) {
                                // If order had payment, mark it for potential refund
                                if (paymentID > 0) {
                                    String paymentSql = "UPDATE Payment SET isRefundable = TRUE WHERE paymentID = ?";
                                    PreparedStatement paymentStmt = con.prepareStatement(paymentSql);
                                    paymentStmt.setInt(1, paymentID);
                                    paymentStmt.executeUpdate();
                                }
                                return true;
                            }
                        } catch (SQLException e) {
                            System.err.println("ORDER CANCELLATION ERROR: " + e.getMessage());
                        }
                        return false;
                    }
              //Item
                  public void addItemToCart(Item item) {
                    try {
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate("INSERT INTO Cart (itemID, itemName, itemPrice, quantity) VALUES ('"
                            + item.getItemID() + "', '"
                            + item.getItemName() + "', '"
                            + item.getPrice() + "', '"
                            + item.getQuantity() + "')");
                        System.out.println("Item added to cart.");
                    } catch (Exception e) {
                        System.err.println("DATABASE INSERTION ERROR: " + e.toString());
                    }
                }

            public void removeItemFromCart(Item item) {
                try (Connection con = getConnection();
                     PreparedStatement stmt = con.prepareStatement(
                         "DELETE FROM Cart WHERE itemID = ?")) {

                    stmt.setInt(1, item.getItemID());
                    

                    int rowsAffected = stmt.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Item successfully removed from cart.");
                    } else {
                        System.out.println("No item found to remove for this user.");
                    }
                } catch (SQLException e) {
                    System.err.println("DATABASE DELETE ERROR: " + e.getMessage());
                }
            }
        
                  public boolean containsItemInCart(int itemID) {
                        try {
                            // Use PreparedStatement to prevent SQL injection
                            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Cart WHERE itemID = ?");
                            stmt.setInt(1, itemID);
                            ResultSet rs = stmt.executeQuery();

                            if (rs.next()) {
                                System.out.println("Yes, item with ID " + itemID + " exists in the cart.");
                                return true;
                            } else {
                                System.out.println("No, item with ID " + itemID + " is not in the cart.");
                                return false;
                            }
                        } catch (SQLException e) {
                            System.err.println("DATABASE SELECT ERROR while checking item " + itemID + ": " + e.getMessage());
                            return false;
                        }
                    }
                  
                  public double calculateCartTotal() {
                        double total = 0;
                        try {
                            Statement stmt = con.createStatement();
                            ResultSet rs = stmt.executeQuery("SELECT itemPrice, quantity FROM Cart");
                            while (rs.next()) {
                                double price = rs.getDouble("itemPrice");
                                int quantity = rs.getInt("quantity");
                                total += price * quantity;
                            }
                        } catch (Exception e) {
                            System.err.println("DATABASE CALCULATE TOTAL ERROR: " + e.toString());
                        }
                        return total;
                    }

              public Cart displayCart() {
    Cart cart = new Cart();  // Create a new Cart object to populate
    
    try {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Cart");
        
        while (rs.next()) {
            // Create an Item object using data from the database
            Item item = new Item();
            item.setItemID(rs.getInt("itemID"));
            item.setItemName(rs.getString("itemName"));
            item.setPrice(rs.getDouble("itemPrice"));
            
            // If your Item class has a quantity field
            item.setQuantity(rs.getInt("quantity"));
            
            // Add this item to the cart using your existing method
            cart.addItemToCart(item);
            
            // Print for debugging
            System.out.println("Added to cart - Item ID: " + item.getItemID());
            System.out.println("Item Name: " + item.getItemName());
            System.out.println("Item Price: $" + item.getPrice());
            System.out.println("Quantity: " + rs.getInt("quantity"));
            System.out.println("-------------------------------");
        }
    } catch (Exception e) {
        System.err.println("DATABASE DISPLAY CART ERROR: " + e.toString());
    }
    
    return cart;  // Return the populated Cart object
}
             public void clearCart() {
                            try {
                                Statement stmt = con.createStatement();
                                stmt.executeUpdate("DELETE FROM Cart");
                                System.out.println("Cart cleared.");
                            } catch (Exception e) {
                                System.err.println("DATABASE CLEAR CART ERROR: " + e.toString());
                            }
                        }
                        
                 public void addToCart(Item item) {
                        if (item == null) {
                            throw new IllegalArgumentException("Cannot add null item to cart");
                        }

                        try {
                            // Check if item exists
                            PreparedStatement checkStmt = con.prepareStatement("SELECT quantity FROM Cart WHERE itemID = ?");
                            checkStmt.setInt(1, item.getItemID());
                            ResultSet rs = checkStmt.executeQuery();

                            if (rs.next()) {
                                // Update quantity if item exists
                                int currentQuantity = rs.getInt("quantity");
                                PreparedStatement updateStmt = con.prepareStatement("UPDATE Cart SET quantity = ? WHERE itemID = ?");
                                updateStmt.setInt(1, currentQuantity + 1);
                                updateStmt.setInt(2, item.getItemID());
                                updateStmt.executeUpdate();
                                System.out.println("Item quantity updated in cart.");
                            } else {
                                // Insert new item if it doesn't exist
                                PreparedStatement insertStmt = con.prepareStatement(
                                    "INSERT INTO Cart (itemID, itemName, price, quantity) VALUES (?, ?, ?, ?)"); // Changed itemPrice → price
                                insertStmt.setInt(1, item.getItemID());
                                insertStmt.setString(2, item.getItemName());
                                insertStmt.setDouble(3, item.getPrice());
                                insertStmt.setInt(4, 1); // Default quantity = 1
                                insertStmt.executeUpdate();
                                System.out.println("New item added to cart.");
                            }
                        } catch (SQLException e) {
                            System.err.println("DATABASE ADD TO CART ERROR: " + e.getMessage());
                        }
                    }
                 
                 public void GenerateReport(Report report) {
                        try {
                            Statement stmt = con.createStatement();
                            String sql = "INSERT INTO Report (reportID, reportDate, reportInfo, reportCreator, reportTitle) VALUES ('" 
                                + report.getReportID() + "', '" 
                                + report.getReportDate() + "', '" 
                                + report.getReportInfo() + "', '" 
                                + report.getReportCreator() + "', '" 
                                + report.getReportTitle() + "')";
                            stmt.executeUpdate(sql);
                            System.out.println("Report added");
                        } catch (SQLException e) {
                            System.err.println("DATABASE INSERTION ERROR: " + e.toString());
                        }
                    }
        
                        //Sate pattern
                        
                        public void setOrderStatus(int orderId, String newStatus) {
                            try {
                                Connection con = getConnection(); // assuming your DB class has getConnection()
                                PreparedStatement stmt = con.prepareStatement("UPDATE Orders SET orderStatus = ? WHERE orderID = ?");
                                stmt.setString(1, newStatus);
                                stmt.setInt(2, orderId);

                                int rowsAffected = stmt.executeUpdate();

                                if (rowsAffected > 0) {
                                    System.out.println("Order #" + orderId + " status updated to: " + newStatus);
                                } else {
                                    System.out.println("No order found with ID: " + orderId);
                                }
                            } catch (Exception e) {
                                System.err.println("DATABASE SET ORDER STATUS ERROR: " + e.toString());
                            }
                        }
                        
                        public void handleOrderCancelled(int orderId) {
                                try {
                                    // Get database connection
                                    Connection con = DB.getInstance().getConnection();

                                    // Create a prepared statement to delete the order
                                    PreparedStatement stmt = con.prepareStatement("DELETE FROM Orders WHERE orderID = ?");
                                    stmt.setInt(1, orderId);

                                    // Execute the deletion
                                    int rowsAffected = stmt.executeUpdate();

                                    if (rowsAffected > 0) {
                                        System.out.println("Order #" + orderId + " has been canceled and removed from the database.");
                                    } else {
                                        System.out.println("No order found with ID: " + orderId);
                                    }
                                } catch (Exception e) {
                                    System.err.println("DATABASE DELETE ORDER ERROR: " + e.toString());
                                }
                            }
                        
                        
                         public void handleOrderPending(int orderId) {
                                    try {
                                       // Get database connection
                                       Connection con = DB.getInstance().getConnection();

                                       // Update order status to "Pending" in database
                                       PreparedStatement stmt = con.prepareStatement("UPDATE Orders SET orderStatus = ? WHERE orderID = ?");
                                       stmt.setString(1, "Pending");
                                       stmt.setInt(2, orderId);

                                       int rowsAffected = stmt.executeUpdate();

                                       if (rowsAffected > 0) {
                                           System.out.println("Order #" + orderId + " is now pending.");
                                       } else {
                                           System.out.println("No order found with ID: " + orderId);
                                       }
                                   } catch (Exception e) {
                                       System.err.println("DATABASE UPDATE ORDER STATUS ERROR: " + e.toString());
                                   }
                               }
                         
                         public void handleOrderNewOrder(int orderId) {
                                try {
                                   // Get database connection
                                   Connection con = DB.getInstance().getConnection();

                                   // First check if order exists
                                   PreparedStatement checkStmt = con.prepareStatement("SELECT * FROM Orders WHERE orderID = ?");
                                   checkStmt.setInt(1, orderId);
                                   ResultSet rs = checkStmt.executeQuery();

                                   if (rs.next()) {
                                       // Order exists, update status to "New"
                                       PreparedStatement updateStmt = con.prepareStatement("UPDATE Orders SET orderStatus = ? WHERE orderID = ?");
                                       updateStmt.setString(1, "New");
                                       updateStmt.setInt(2, orderId);
                                       updateStmt.executeUpdate();
                                       System.out.println("Order #" + orderId + " is marked as new.");
                                   } else {
                                       // Order doesn't exist yet
                                       System.out.println("Order #" + orderId + " not found in database. Please create it first.");
                                       // You might want to call a method to create a new order here
                                   }
                               } catch (Exception e) {
                                   System.err.println("DATABASE NEW ORDER ERROR: " + e.toString());
                               }
                           }
                         
                         
                        public void handleOrderDelivered(int orderId) {
                               try {
                                 // Get database connection
                                 DB dbInstance = DB.getInstance();
                                 Connection con = dbInstance.getConnection();

                                 // Update order status to "Delivered" in database
                                 PreparedStatement stmt = con.prepareStatement("UPDATE Orders SET orderStatus = ? WHERE orderID = ?");
                                 stmt.setString(1, "Delivered");
                                 stmt.setInt(2, orderId);

                                 int rowsAffected = stmt.executeUpdate();

                                 if (rowsAffected > 0) {
                                     System.out.println("Order #" + orderId + " has been delivered.");
                                 } else {
                                     System.out.println("No order found with ID: " + orderId);
                                 }
                             } catch (Exception e) {
                                 System.err.println("DATABASE UPDATE TO DELIVERED ERROR: " + e.toString());
                             }
                         }
                        
                         // Add this method to your DB class
                    public double applyFixedAmountDiscount(int amount, int itemID) {
                        try {
                            // First, get the item price from the database
                            PreparedStatement stmt = con.prepareStatement("SELECT price FROM Item WHERE itemID = ?");
                            stmt.setInt(1, itemID);
                            ResultSet rs = stmt.executeQuery();

                            if (rs.next()) {
                                double price = rs.getDouble("price");
                                double discountedPrice = Math.max(price - amount, 0); // Ensures price doesn't go below zero

                                System.out.println("Applied $" + amount + " fixed discount to item " + itemID);
                                System.out.println("Original price: $" + price + ", Discounted price: $" + discountedPrice);

                                return discountedPrice;
                            } else {
                                System.out.println("No item found with ID: " + itemID);
                                return 0.0;
                            }
                        } catch (Exception e) {
                            System.err.println("Apply fixed discount error: " + e.toString());
                            return 0.0;
                        }
                    }   
                        
            // Add this method to your DB class
            public double applyPercentageDiscount(int percentage, int itemID) {
                try {
                    // First, get the item price from the database
                    PreparedStatement stmt = con.prepareStatement("SELECT price FROM Item WHERE itemID = ?");
                    stmt.setInt(1, itemID);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        double price = rs.getDouble("price");
                        double discountedPrice = price - (price * percentage / 100.0);

                        System.out.println("Applied " + percentage + "% discount to item " + itemID);
                        System.out.println("Original price: $" + price + ", Discounted price: $" + discountedPrice);

                        return discountedPrice;
                    } else {
                        System.out.println("No item found with ID: " + itemID);
                        return 0.0;
                    }
                } catch (Exception e) {
                    System.err.println("Apply discount error: " + e.toString());
                    return 0.0;
                }
            }
                    
         //Delegation pattern
            public void processCashPayment(int paymentID, double paymentAmount, String paymentType) {
                    try {
                        String sql = "INSERT INTO Payment (paymentID, paymentAmount, paymentType) " +
                                    "VALUES (?, ?, ?)";

                        PreparedStatement pstmt = con.prepareStatement(sql);
                        pstmt.setInt(1, paymentID);
                        pstmt.setDouble(2, paymentAmount);
                        pstmt.setString(3, paymentType);
                      

                        pstmt.executeUpdate();
                        System.out.println("Cash payment recorded successfully");
                    } catch (SQLException e) {
                        System.err.println("DATABASE INSERTION ERROR (Cash): " + e.getMessage());
                        e.printStackTrace();
                    }
                }

               public void processCreditCardPayment(int paymentID, double paymentAmount,
                                     String paymentType, int cardNumber, String cardHolderName) {
                            try {
                                String sql = "INSERT INTO Payment (paymentID, paymentAmount, paymentType, cardNumber, cardHolderName) " +
                                             "VALUES (?, ?, ?, ?, ?)";

                                PreparedStatement pstmt = con.prepareStatement(sql);
                                pstmt.setInt(1, paymentID);
                                pstmt.setDouble(2, paymentAmount);
                                pstmt.setString(3, paymentType);         // now valid
                                pstmt.setInt(4, cardNumber);             // now valid
                                pstmt.setString(5, cardHolderName);      // now valid

                                pstmt.executeUpdate();
                                System.out.println("Credit card payment recorded successfully");
                            } catch (SQLException e) {
                                System.err.println("DATABASE INSERTION ERROR (Credit Card): " + e.getMessage());
                                e.printStackTrace();
                            }
                        }
                
                  public void displayPayment(int paymentID){
                        DB.getInstance().displayPayment(paymentID); 
                  }
               
                public void requestRefund(int paymentID, String reason) {
                    try {
                        // Check if payment exists and is refundable
                        String checkSql = "SELECT paymentType " +
                                          "FROM Payment WHERE paymentID = ?";
                        PreparedStatement checkStmt = con.prepareStatement(checkSql);
                        checkStmt.setInt(1, paymentID);
                        ResultSet rs = checkStmt.executeQuery();

                        if (!rs.next()) {
                            System.out.println("Payment not found.");
                            return;
                        }

                        String paymentType = rs.getString("paymentType");
                     
                       
                        // Check refund conditions
                        if ("Cash".equalsIgnoreCase(paymentType)) {
                            System.out.println("Refund denied: Cash payments are not refundable.");
                            return;
                        }

                    } catch (SQLException e) {
                        System.err.println("ERROR while checking refund eligibility: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
                 
                 //Decorator Design pattern
                 
                  public void printColoredReceipt(Receipt receipt) {
                        try {
                            Statement stmt = con.createStatement();
                            String sql = "SELECT r.receiptID, o.orderDate, U.userName, " +
                                         "i.itemName, i.itemPrice, od.quantity " +
                                         "FROM ColorDecorator r " +
                                         "JOIN Orders o ON r.orderID = o.orderID " +
                                         "JOIN User U ON o.customerID = U.userID " +  
                                         "JOIN OrderDetails od ON o.orderID = od.orderID " +
                                         "JOIN MenuItem i ON od.itemID = i.itemID " +
                                         "WHERE r.receiptID = '" + receipt.getReceiptID() + "'";

                            ResultSet rs = stmt.executeQuery(sql);

                            System.out.println("========== RECEIPT #" + receipt.getReceiptID() + " ==========");

                            if (rs.next()) {
                                System.out.println("Date: " + rs.getDate("orderDate"));
                                System.out.println("Customer: " + rs.getString("Name"));
                                System.out.println("----------------------------------------");
                                System.out.println("Items:");

                                // Print the first item
                                System.out.printf("%-20s %5d x $%.2f = $%.2f\n", 
                                    rs.getString("itemName"),
                                    rs.getInt("quantity"),
                                    rs.getDouble("itemPrice"),
                                    rs.getInt("quantity") * rs.getDouble("itemPrice"));

                                // Print remaining items
                                double total = rs.getInt("quantity") * rs.getDouble("itemPrice");

                                while (rs.next()) {
                                    System.out.printf("%-20s %5d x $%.2f = $%.2f\n", 
                                        rs.getString("itemName"),
                                        rs.getInt("quantity"),
                                        rs.getDouble("itemPrice"),
                                        rs.getInt("quantity") * rs.getDouble("itemPrice"));

                                    total += rs.getInt("quantity") * rs.getDouble("itemPrice");
                                }

                                System.out.println("----------------------------------------");
                                System.out.printf("Total: $%.2f\n", total);

                                // Check for receipt styling information from the database
                                Statement styleStmt = con.createStatement();
                                ResultSet styleRs = styleStmt.executeQuery(
                                    "SELECT textColor FROM ColorDecorator WHERE receiptID = '" + 
                                    receipt.getReceiptID() + "'"
                                );

                                if (styleRs.next()) {
                                    String textColor = styleRs.getString("textColor");


                                    System.out.println("----------------------------------------");
                                    if (textColor != null && !textColor.isEmpty()) {
                                        System.out.println("Text Color: " + textColor);
                                    }

                                }

                            } else {
                                System.out.println("No receipt found with ID: " + receipt.getReceiptID());
                            }

                            System.out.println("======================================");
                            System.out.println("Thank you for your order!");

                        } catch (Exception e) {
                            System.err.println("DATABASE RECEIPT PRINTING ERROR: " + e.toString());
                        }
                    }



                public void printStyledReceipt(Receipt receipt) {
                    try {
                        Statement stmt = con.createStatement();
                       String sql = "SELECT DISTINCT r.receiptID, o.orderDate, U.userName, " +
                            "i.itemName, i.itemPrice, od.quantity " +
                            "FROM FontDecorator r " +
                            "JOIN Orders o ON r.orderID = o.orderID " +
                            "JOIN User U ON o.customerID = U.userID " +  // Fixed: Join Orders → User
                            "JOIN OrderDetails od ON o.orderID = od.orderID " +
                            "JOIN MenuItem i ON od.itemID = i.itemID " +
                            "WHERE r.receiptID = '" + receipt.getReceiptID() + "'";

                        ResultSet rs = stmt.executeQuery(sql);

                        System.out.println("========== RECEIPT #" + receipt.getReceiptID() + " ==========");

                        if (rs.next()) {
                            System.out.println("Date: " + rs.getDate("orderDate"));
                            System.out.println("Customer: " + rs.getString("userName"));
                            System.out.println("----------------------------------------");
                            System.out.println("Items:");

                            // Print the first item
                            System.out.printf("%-20s %5d x $%.2f = $%.2f\n", 
                                rs.getString("itemName"),
                                rs.getInt("quantity"),
                                rs.getDouble("itemPrice"),
                                rs.getInt("quantity") * rs.getDouble("itemPrice"));

                            // Print remaining items
                            double total = rs.getInt("quantity") * rs.getDouble("itemPrice");

                            while (rs.next()) {
                                    System.out.printf("%-20s %5d x $%.2f = $%.2f\n", 
                                        rs.getString("itemName"),
                                        rs.getInt("quantity"),
                                        rs.getDouble("itemPrice"),
                                        rs.getInt("quantity") * rs.getDouble("itemPrice"));

                                    total += rs.getInt("quantity") * rs.getDouble("itemPrice");
                                }

                                System.out.println("----------------------------------------");
                                System.out.printf("Total: $%.2f\n", total);

                                // Check for receipt styling information from the database
                                Statement styleStmt = con.createStatement();
                                ResultSet styleRs = styleStmt.executeQuery(
                                    "SELECT fontStyle FROM FontDecorator WHERE receiptID = '" + 
                                    receipt.getReceiptID() + "'"
                                );

                                if (styleRs.next()) {
                                    String fontStyle = styleRs.getString("fontStyle");

                                    System.out.println("----------------------------------------");

                                        if (fontStyle != null && !fontStyle.isEmpty()) {
                                        System.out.println("Font Style: " + fontStyle);
                                    }
                                }

                            } else {
                                System.out.println("No receipt found with ID: " + receipt.getReceiptID());
                            }

                            System.out.println("======================================");
                            System.out.println("Thank you for your order!");

                        } catch (Exception e) {
                            System.err.println("DATABASE RECEIPT PRINTING ERROR: " + e.toString());
                        }
                    }
                
                
               public void displayFavoritesWithDetails(int userID) {
    try {
        // Modified query to match your actual schema
        String query = "SELECT f.itemID, m.menuCategory, f.dateAdded, f.isActive " +
                      "FROM favorite f JOIN menu m ON f.itemID = m.menuID " +
                      "WHERE f.userID = ? AND f.isActive = true";
        
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, userID);
        
        ResultSet rs = stmt.executeQuery();
        
        System.out.println("========== FAVORITE ITEMS ==========");
        System.out.printf("%-10s %-20s %-15s %-10s%n", 
            "Item ID", "Category", "Date Added", "Status");
        System.out.println("--------------------------------------------------");
        
        boolean hasFavorites = false;
        while (rs.next()) {
            hasFavorites = true;
            int itemID = rs.getInt("itemID");
            String menuCategory = rs.getString("menuCategory");
            Date dateAdded = rs.getDate("dateAdded");
            boolean isActive = rs.getBoolean("isActive");
            
            System.out.printf("%-10d %-20s %-15s %-10s%n", 
                itemID, 
                menuCategory, 
                dateAdded.toString(), 
                isActive ? "Active" : "Inactive");
        }
        
        if (!hasFavorites) {
            System.out.println("No favorite items found for this user.");
        }
        
        System.out.println("===============================================");
        
        rs.close();
        stmt.close();
    } catch (SQLException e) {
        System.out.println("Error displaying favorites: " + e.getMessage());
        e.printStackTrace(); // Add this for more detailed error info
    }
}
              
               
      public int executeUpdate(String sql) throws SQLException {
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
