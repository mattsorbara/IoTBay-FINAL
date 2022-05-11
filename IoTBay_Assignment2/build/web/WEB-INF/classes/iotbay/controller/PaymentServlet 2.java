/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package iotbay.controller;

import iotbay.model.Savedpayment;
import iotbay.model.dao.DBManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.UUID;

/**
 *
 * @author lindale
 */

public class PaymentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String paymentMethod = request.getParameter("paymentMethod");
        String email = (String) session.getAttribute("email");
        String crudPayment = (String) session.getAttribute("crudPayment");
        
        DBManager manager = (DBManager) session.getAttribute("manager");
//        Savedpayment savedPayment = null;
//        response.setContentType("text/html;charset=UTF-8");

        try {
//            savedPayment = manager.findSavedpayment(email);

            String paymentID = UUID.randomUUID().toString();
            session.setAttribute("bonk", paymentID);
            String orderID = "testingNEW";
            double amount = 69.69;
            String cardNumber = "fake";
            String cardCVC = "fake";
            String cardExpiry = "fake";

            manager.addPayment1(paymentID, orderID, amount, paymentMethod, email);

            if (paymentMethod.equals("card")) {
//                session.setAttribute("card", paymentMethod);
                request.getRequestDispatcher("cardPayment.jsp").include(request, response);
            } else if (paymentMethod.equals("savedCard")) {
                manager.addPayment2(paymentID, cardNumber, cardCVC, cardExpiry);
                request.getRequestDispatcher("home.jsp").include(request, response);
            } else if (crudPayment.equals("delete")) {
                manager.deletePayment(email);
                request.getRequestDispatcher("cardPayment.jsp").include(request, response);
            } else {
                System.out.println("No");
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

}
