/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iotbay.controller;

import iotbay.model.User;
import iotbay.model.dao.DBManager;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lindale
 */

public class PaymentSearchServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Grab current session, intialise necessary fields from webpage
        HttpSession session = request.getSession();
        DBManager manager = (DBManager)session.getAttribute("manager");
        User user = (User) session.getAttribute("user");

        String fromString = request.getParameter("from")+":00";
        String toString = request.getParameter("to")+":00";

        String requestType = (String) request.getParameter("id");

        //checks if the id field is equal to 2, which triggers a paymentIDSearch
        if ("2".equals(requestType)) {
            try {
               //checks if paymentIDSearch is null or incorrect format
               int paymentIDSearch = Integer.parseInt(request.getParameter("paymentIDSearch"));
                    //attempts to search database to find orders through filtered list
                    try {
                        ResultSet filteredLogs = manager.filterPaymentID(user.getEmail(), paymentIDSearch);
                        session.setAttribute("paymentIDSearch", paymentIDSearch);
                        session.setAttribute("paymentLogRows", filteredLogs);

                        request.getRequestDispatcher("viewPayments.jsp").forward(request, response);

                    //catches any errors from the dbmanager
                    } catch (SQLException ex) {
                        System.out.println("SQL Error.");
                    }
            //sets error if the paymentIDSearch is invalid
            } catch (NumberFormatException ex) {
                session.setAttribute("paymentViewError", "PaymentID not valid!");
                request.getRequestDispatcher("paymentSearch.jsp").include(request, response); 
            }
        }
        //Timestamp search
        else {
            Timestamp fromTime = Timestamp.valueOf(fromString.replace("T"," "));
            Timestamp toTime = Timestamp.valueOf(toString.replace("T"," "));
            //Checks if the time period is correct
            if (toTime.after(fromTime) && fromTime.before(toTime)) {
                //attempts to search database to find payments through filtered list
                try {
                   ResultSet filteredLogs = manager.filterPaymentDate(fromTime, toTime, user.getEmail());

                   session.setAttribute("paymentLogRows", filteredLogs);
                   session.setAttribute("from", fromString.replace("T"," "));
                   session.setAttribute("to", toString.replace("T"," "));

                   request.getRequestDispatcher("viewPayments.jsp").forward(request, response);
                //catches any errors from the dbmanager
                } catch (SQLException ex) {
                    System.out.println("SQL Error.");
                }
            //returns error is date not valid
            } else {
                session.setAttribute("paymentViewError", "Date not valid!");
                request.getRequestDispatcher("paymentSearch.jsp").include(request, response); 
            }
        }
    }
}  
