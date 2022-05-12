///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package iotbay.controller;
//
//import iotbay.model.*;
//import iotbay.model.dao.DBManager;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// *
// * @author matthewsorbara
// */
//public class OrderCheckoutServlet extends HttpServlet {
//    
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        HttpSession session = request.getSession();
//
//        request.getRequestDispatcher("orderCheckout.jsp").include(request, response);
//
//        int id = Integer.parseInt(request.getParameter("id"));
//
////        Catalogue product = (Catalogue) session.getAttribute("testProduct");
//
//        DBManager manager = (DBManager) session.getAttribute("manager");
//
//        try {
//
//            Catalogue product = (Catalogue) manager.findProduct(id);
//
//            int quantity = Integer.parseInt(request.getParameter("quantity"));
//            int productID = product.getId();
//            double orderPrice = product.getPrice();
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
//            Timestamp orderDate = new Timestamp(new Date().getTime());
//            String orderStatus = "SAVED";  
//
//            User user = (User) session.getAttribute("user");
//
//            System.out.println("here");
//            manager.addOrder(user.getEmail(), productID, orderPrice, quantity, orderDate.toString(), orderStatus);
////            session.setAttribute("user", user);
////            request.getRequestDispatcher("welcome.jsp").include(request, response);
//            
//        } catch(SQLException ex) {
//            Logger.getLogger(OrderCheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//        
//        
//        
//            
//    }
//    
//    
//    
//}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iotbay.controller;

//Import both Catalogue and User for verification
import iotbay.model.*;

import iotbay.model.dao.*;
import java.io.IOException;
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

public class OrderCheckoutServlet extends HttpServlet {
    
    private DBManager manager;
    private DBConnector Connector;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try
        {
            Connector = new DBConnector();
        }catch (ClassNotFoundException | SQLException ex){
            java.util.logging.Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        try
        {       
            manager = new DBManager(Connector.openConnection());  
        }catch (SQLException ex){
            java.util.logging.Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        //session
        HttpSession session = request.getSession();

        try {
            String id = request.getParameter("id");
            int productid = Integer.parseInt(id);

            Catalogue product = manager.findProduct(productid);
            if (product != null) {
                session.setAttribute("product", product);
                request.getRequestDispatcher("orderCheckout.jsp").include(request, response);
                response.sendRedirect("orderCheckout.jsp");
            }
            else{
                request.getRequestDispatcher("catalogue.jsp").include(request, response);
                response.sendRedirect("catalogue.jsp");
            }
            
                            
        } catch (SQLException ex){
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //session
        HttpSession session = request.getSession();

        DBManager manager = (DBManager) session.getAttribute("manager");

        Order currentOrder = null;
        int quantity = 0;

        try {

            Catalogue product = (Catalogue) session.getAttribute("product");

            
            try {
                quantity = Integer.parseInt(request.getParameter("quantity"));
            }
            catch (NumberFormatException invalidValueError) {
                session.setAttribute("orderError", "Quantity amount invalid!");
                request.getRequestDispatcher("orderCheckout.jsp").include(request, response);
            }
            

            if (quantity <= product.getStock()) {
                int productID = product.getId();
                double orderPrice = product.getPrice();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                Timestamp orderDate = new Timestamp(new Date().getTime());
                String orderStatus = "SAVED";

                product.setStock((product.getStock() - quantity));

                User user = (User) session.getAttribute("user");

                int orderID = manager.addOrder(user.getEmail(), productID, orderPrice, quantity, orderDate, orderStatus);

                currentOrder = new Order(orderID, user.getEmail(), productID, orderPrice, quantity, orderDate.toString(), orderStatus);

                session.setAttribute("currentOrder", currentOrder);
//                request.getRequestDispatcher("payment.jsp").include(request, response);
            }
            
            else if (quantity > product.getStock()) {
                session.setAttribute("orderError", "Quantity exceeds avaliable stock!");
                request.getRequestDispatcher("orderCheckout.jsp").include(request, response);
            }

            else {
                session.setAttribute("orderError", "Quantity amount invalid!");
                request.getRequestDispatcher("orderCheckout.jsp").include(request, response);
            }


        } catch(SQLException ex) {
            Logger.getLogger(OrderCheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        


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
    }
}
