/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package iotbay.controller;

import iotbay.model.Order;
import iotbay.model.Savedpayment;
import iotbay.model.User;
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


import java.util.UUID;

/**
 *
 * @author lindale
 */

public class PaymentServlet extends HttpServlet {
    
    private DBManager manager;
    private DBConnector Connector;
        

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        try
//        {
//            Connector = new DBConnector();
//        }catch (ClassNotFoundException | SQLException ex){
//            java.util.logging.Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE,null,ex);
//        }
//        
//        try
//        {       
//            manager = new DBManager(Connector.openConnection());  
//        }catch (SQLException ex){
//            java.util.logging.Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE,null,ex);
//        }
//        
//        //session
//        HttpSession session = request.getSession();
//
//        User user = (User) session.getAttribute("user");
//        
//        String email = user.getEmail();
//        Savedpayment savedPayment = null;
//
//        try {
//            savedPayment = manager.findSavedpayment(email);
//            System.out.print(savedPayment);
//
//            if (savedPayment != null) {
//                System.out.print("IM HERE");
//                session.setAttribute("savedPayment", savedPayment);
//                request.getRequestDispatcher("payment.jsp").include(request, response);
//            } else {
//                System.out.print("HERE I AM");
//                Savedpayment nullPayment = new Savedpayment("N/A", "N/A", "N/A", "N/A");
//                session.setAttribute("savedPayment", nullPayment);
//                request.getRequestDispatcher("payment.jsp").include(request, response);
//            }
//            
//                            
//        } catch (SQLException ex){
//            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();


        User user = (User) session.getAttribute("user");

        String paymentMethod = request.getParameter("paymentMethod");
        String email = (String) user.getEmail();
        String crudPayment = (String) session.getAttribute("crudPayment");
        Order currentOrder = (Order) session.getAttribute("currentOrder");
        
        DBManager manager = (DBManager) session.getAttribute("manager");
//        Savedpayment savedPayment = null;
//        response.setContentType("text/html;charset=UTF-8");

        try {
//            savedPayment = manager.findSavedpayment(email);
//            session.setAttribute("bonk", paymentID);
            int orderID = currentOrder.getOrderID();
            double amount = currentOrder.getOrderPrice();


            manager.addPayment1(orderID, amount, paymentMethod, email);
            if (paymentMethod.equals("card")) {
                System.out.println("Empty");
                request.getRequestDispatcher("cardPayment.jsp").include(request, response);
            } else if (paymentMethod.equals("savedCard")) {
                Savedpayment savedPayment = manager.findSavedpayment(email);
//                if (savedPayment != null) {
                    int paymentID = manager.getPaymentID(orderID);
                    String cardNumber = savedPayment.getCardNumber();
                    String cardCVC = savedPayment.getCardCVC();
                    String cardExpiry = savedPayment.getCardExpiry();                     
                    manager.addPayment2(paymentID, cardNumber, cardCVC, cardExpiry);
                    request.getRequestDispatcher("home.jsp").include(request, response);
//                } else {
//
//                }
            } else {
                System.out.println("No");
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

//            savedPayment = manager.findSavedpayment(email);
//
//            if (savedPayment != null) {
//                session.setAttribute("savedPayment", savedPayment);
//            } else {
//                Savedpayment nullPayment = new Savedpayment("N/A", "N/A", "N/A", "N/A");
//                session.setAttribute("savedPayment", nullPayment);
//            }

}
