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
        try {
            Connector = new DBConnector();
        } catch (ClassNotFoundException | SQLException ex){
            java.util.logging.Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        try {       
            manager = new DBManager(Connector.openConnection());  
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        //session
        HttpSession session = request.getSession();

        try {
            String orderID = request.getParameter("id");
            Order targetOrder = (Order) manager.fetchOrder(Integer.parseInt(orderID));
            
            int productID = targetOrder.getProductID();

            Catalogue product = manager.findProduct(productID);
            
            if (product != null) {
                session.setAttribute("product", product);
                session.setAttribute("targetOrder", targetOrder);
                session.setAttribute("selectedOrderID", orderID);
                
                request.getRequestDispatcher("editOrder.jsp").include(request, response);
                response.sendRedirect("editOrder.jsp");
            }
            
                            
        } catch (SQLException ex){
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//        try
//        {
//            Connector = new DBConnector();
//        } catch (ClassNotFoundException | SQLException ex){
//            java.util.logging.Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE,null,ex);
//        }
//        
//        try
//        {       
//            manager = new DBManager(Connector.openConnection());  
//        } catch (SQLException ex){
//            java.util.logging.Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE,null,ex);
//        }
//        
//        //session
//        HttpSession session = request.getSession();
//
//        try {
//            String id = request.getParameter("id");
//            int productid = Integer.parseInt(id);
//
//            Catalogue product = manager.findProduct(productid);
//            if (product != null) {
//                session.setAttribute("product", product);
//                request.getRequestDispatcher("orderCheckout.jsp").include(request, response);
//                response.sendRedirect("orderCheckout.jsp");
//            }
//            else{
//                request.getRequestDispatcher("catalogue.jsp").include(request, response);
//                response.sendRedirect("catalogue.jsp");
//            }
//            
//                            
//        } catch (SQLException ex){
//            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        DBManager manager = (DBManager) session.getAttribute("manager");

        Order targetOrder = (Order) session.getAttribute("targetOrder");
        int orderID = targetOrder.getOrderID();
        
        int quantity = 0;

        try {

            Catalogue product = (Catalogue) session.getAttribute("product");
            
            try {
                quantity = Integer.parseInt(request.getParameter("quantity"));
                
                if (quantity != targetOrder.getOrderQuantity()) {
                    if (quantity <= product.getStock()) {

                        product.setStock((product.getStock() - quantity));

                        double totalPrice = manager.fetchOrderAmount(orderID);

                        manager.updateOrder(totalPrice, quantity, orderID);

                        request.getRequestDispatcher("orderSearch.jsp").include(request, response);
                    }
                    else {
                        session.setAttribute("orderError", "Quantity amount invalid!");
                        request.getRequestDispatcher("editOrder.jsp").include(request, response);
                    }
                }
                else {
                   request.getRequestDispatcher("orderSearch.jsp").include(request, response);
                }
            }
            catch (NumberFormatException invalidValueError) {
                session.setAttribute("orderError", "Quantity amount invalid!");
                request.getRequestDispatcher("editOrder.jsp").include(request, response);
            }
            
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