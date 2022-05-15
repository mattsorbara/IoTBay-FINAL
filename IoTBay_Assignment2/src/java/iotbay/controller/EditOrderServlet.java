package iotbay.controller;

//Import both Catalogue and User for verification
import iotbay.model.*;

import iotbay.model.dao.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;

public class EditOrderServlet extends HttpServlet {
    
    private DBManager manager;
    private DBConnector Connector;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // catching any errors when intializing DBCONNECTER
        try {
            Connector = new DBConnector();
        } catch (ClassNotFoundException | SQLException ex){
            java.util.logging.Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE,null,ex);
        }
        // catching any errors when intializing DBMANAGER        
        try {       
            manager = new DBManager(Connector.openConnection());  
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        //fetches recent session
        HttpSession session = request.getSession();

        //catches any error when accessing database
        try {
            //fetch value from button from previous webpage
            String orderID = request.getParameter("id");
            //search for order with orderID
            Order targetOrder = (Order) manager.fetchOrder(Integer.parseInt(orderID));
            //fetch productID from order
            int productID = targetOrder.getProductID();
            //fetch product from database with productID
            Catalogue product = manager.findProduct(productID);
            //check if product is null
            if (product != null) {
                //sets product, order, and orderID to respective attributes to be used by the page
                session.setAttribute("product", product);
                session.setAttribute("targetOrder", targetOrder);
                session.setAttribute("selectedOrderID", orderID);
                
                //sends user to directed pages
                request.getRequestDispatcher("editOrder.jsp").include(request, response);
                response.sendRedirect("editOrder.jsp");
            }
            
        //catches any SQL errors from dbmanager                    
        } catch (SQLException ex){
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //current session
        HttpSession session = request.getSession();
        //initalise dbmanager
        DBManager manager = (DBManager) session.getAttribute("manager");
        //fetch order from session and grab orderID
        Order targetOrder = (Order) session.getAttribute("targetOrder");
        int orderID = targetOrder.getOrderID();
        
        int quantity = 0;

        try {
            //check if product is null, if so, catches error
            Catalogue product = (Catalogue) session.getAttribute("product");
            
            try {
                //check if quantity is null/correct format, if so, catches error
                quantity = Integer.parseInt(request.getParameter("quantity"));
                
                //check if quantity is the same
                if (quantity != targetOrder.getOrderQuantity()) {
                    //check if quantity is larger than product's stock
                    if (quantity <= product.getStock()) {
                        //calculates order price
                        double totalPrice = quantity * product.getPrice();
                        //updates order with new changes
                        manager.updateOrder(totalPrice, quantity, orderID);
                        //sends user back ot order search screen
                        request.getRequestDispatcher("orderSearch.jsp").include(request, response);
                    }
                    //if the quantity is larger than stock, output error
                    else {
                        session.setAttribute("orderError", "Quantity amount invalid!");
                        request.getRequestDispatcher("editOrder.jsp").include(request, response);
                    }
                }
                //redirect user back to search page, update is not necessary
                else {
                   request.getRequestDispatcher("orderSearch.jsp").include(request, response);
                }
            }
            //catches if quantity is not a number or is larger than stock
            catch (NumberFormatException invalidValueError) {
                session.setAttribute("orderError", "Quantity amount invalid!");
                request.getRequestDispatcher("editOrder.jsp").include(request, response);
            }
        //catches if dbmanager throws a SQL error
        } catch(SQLException ex) {
            Logger.getLogger(OrderCheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
        
}








//            try {
//
//               int orderIDSearch = Integer.parseInt(request.getParameter("orderIDSearch"));
//
//
//                    System.out.println("I AM HERE");
//                    try {
//                        ResultSet filteredLogs = manager.filterOrdersID(user.getEmail(), orderIDSearch);
//                        session.setAttribute("orderIDSearch", orderIDSearch);
//                        session.setAttribute("orderLogRows", filteredLogs);
//
//                        request.getRequestDispatcher("viewOrders.jsp").forward(request, response);
//
//
//                    } catch (SQLException ex) {
//                        System.out.println("SQL Error.");
//                    }
//
//            } catch (NumberFormatException ex) {
//                session.setAttribute("orderViewError", "OrderID not valid!");
//                request.getRequestDispatcher("orderSearch.jsp").include(request, response); 
//            }
//        }
//        else {
//            Timestamp fromTime = Timestamp.valueOf(fromString.replace("T"," "));
//            Timestamp toTime = Timestamp.valueOf(toString.replace("T"," "));
//
//            if (toTime.after(fromTime) && fromTime.before(toTime)) {
//
//                try {
//                   ResultSet filteredLogs = manager.filterOrdersDate(fromTime, toTime, user.getEmail());
//
//                   session.setAttribute("orderLogRows", filteredLogs);
//                   session.setAttribute("from", fromString.replace("T"," "));
//                   session.setAttribute("to", toString.replace("T"," "));
//
//                   request.getRequestDispatcher("viewOrders.jsp").forward(request, response);
//
//                } catch (SQLException ex) {
//                    System.out.println("SQL Error.");
//                }
//            } else {
//                session.setAttribute("orderViewError", "Date not valid!");
//                request.getRequestDispatcher("orderSearch.jsp").include(request, response); 
//            }
//        }