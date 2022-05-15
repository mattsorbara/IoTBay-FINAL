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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author oliverguerreiro
 */

public class OrderSearchServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Grab current session, intialise necessary fields from webpage
        HttpSession session = request.getSession();
        DBManager manager = (DBManager)session.getAttribute("manager");
        User user = (User) session.getAttribute("user");

        String fromString = request.getParameter("from")+":00";
        String toString = request.getParameter("to")+":00";

        String requestType = (String) request.getParameter("id");

        //checks if the id field is equal to 2, which triggers a orderIDSearch
        if ("2".equals(requestType)) {
            try {
               //checks if orderIDSearch is null or incorrect format
               int orderIDSearch = Integer.parseInt(request.getParameter("orderIDSearch"));
                    //attempts to search database to find orders through filtered list
                    try {
                        ResultSet filteredLogs = manager.filterOrdersID(user.getEmail(), orderIDSearch);
                        session.setAttribute("orderIDSearch", orderIDSearch);
                        session.setAttribute("orderLogRows", filteredLogs);

                        request.getRequestDispatcher("viewOrders.jsp").forward(request, response);

                    //catches any errors from the dbmanager
                    } catch (SQLException ex) {
                        System.out.println("SQL Error.");
                    }
            //sets error if the orderIDSearch is invalid
            } catch (NumberFormatException ex) {
                session.setAttribute("orderViewError", "OrderID not valid!");
                request.getRequestDispatcher("orderSearch.jsp").include(request, response); 
            }
        }
        //Timestamp search
        else {
            Timestamp fromTime = Timestamp.valueOf(fromString.replace("T"," "));
            Timestamp toTime = Timestamp.valueOf(toString.replace("T"," "));
            //Checks if the time period is correct
            if (toTime.after(fromTime) && fromTime.before(toTime)) {
                //attempts to search database to find orders through filtered list
                try {
                   ResultSet filteredLogs = manager.filterOrdersDate(fromTime, toTime, user.getEmail());

                   session.setAttribute("orderLogRows", filteredLogs);
                   session.setAttribute("from", fromString.replace("T"," "));
                   session.setAttribute("to", toString.replace("T"," "));

                   request.getRequestDispatcher("viewOrders.jsp").forward(request, response);
                //catches any errors from the dbmanager
                } catch (SQLException ex) {
                    System.out.println("SQL Error.");
                }
            //returns error is date not valid
            } else {
                session.setAttribute("orderViewError", "Date not valid!");
                request.getRequestDispatcher("orderSearch.jsp").include(request, response); 
            }
        }
    }
}  
