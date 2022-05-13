/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package iotbay.controller;

import iotbay.model.Payment;
import iotbay.model.dao.DBManager;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
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

public class cardPaymentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String crudPayment = request.getParameter("crudPayment");
        String email = (String) session.getAttribute("email");
        String cardNumber = request.getParameter("cardNumber");
        String cardCVC = request.getParameter("cardCVC");
        String cardExpiry = request.getParameter("cardExpiry");
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        Validator val = new Validator();
        
//        response.setContentType("text/html;charset=UTF-8");

        try {
            int paymentID = (int) session.getAttribute("bonk");

            if(!val.validateCardNumber(cardNumber)) {
                session.setAttribute("regError", "Card Number format wrong.");
                request.getRequestDispatcher("cardPayment.jsp").include(request, response);
            }
            else if(!val.validateCardCVC(cardCVC)) {
                session.setAttribute("regError", "Card CVC format wrong.");
                request.getRequestDispatcher("cardPayment.jsp").include(request, response);
            }
            else if(!val.validateCardExpiry(cardExpiry)) {
                session.setAttribute("regError", "Card Expiry format wrong.");
                request.getRequestDispatcher("cardPayment.jsp").include(request, response);
            }
            else {
                if (crudPayment.equals("create")) {
                    manager.savePayment(email, cardNumber, cardCVC, cardExpiry);
                } else if (crudPayment.equals("update")){
                    manager.updatePayment(email, cardNumber, cardCVC, cardExpiry);
                } else if (crudPayment.equals("delete")){
                    manager.deletePayment(email);
                }
                manager.addPayment2(paymentID, cardNumber, cardCVC, cardExpiry);
                request.getRequestDispatcher("home.jsp").include(request, response);
            }
              
        } catch (SQLException ex) {
            Logger.getLogger(cardPaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

}
