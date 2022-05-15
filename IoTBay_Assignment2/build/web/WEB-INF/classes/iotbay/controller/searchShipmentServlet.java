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
 * @author otanya
 */

public class searchShipmentServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        DBManager manager = (DBManager)session.getAttribute("manager");
        User user = (User) session.getAttribute("user");

        String fromString = request.getParameter("from")+":00";
        String toString = request.getParameter("to")+":00";

        String requestType = (String) request.getParameter("id");


        if ("2".equals(requestType)) {
            try {

               int shipmentIDSearch = Integer.parseInt(request.getParameter("shipmentIDSearch"));


                    
                    try {
                        ResultSet filteredLogs = manager.filterOrdersID(user.getEmail(), shipmentIDSearch);
                        session.setAttribute("shipmentIDSearch", shipmentIDSearch);
/* how do i change this to shipment */
                        session.setAttribute("orderLogRows", filteredLogs);

                        request.getRequestDispatcher("viewOrders.jsp").forward(request, response);
/*till here */

                    } catch (SQLException ex) {
                        System.out.println("SQL Error.");
                    }

            } catch (NumberFormatException ex) {
                session.setAttribute("shipmentViewError", "Shipment ID not valid!");
                request.getRequestDispatcher("shipmentSearch.jsp").include(request, response); 
            }
        }
        else {
            Timestamp fromTime = Timestamp.valueOf(fromString.replace("T"," "));
            Timestamp toTime = Timestamp.valueOf(toString.replace("T"," "));

            if (toTime.after(fromTime) && fromTime.before(toTime)) {

                try {
                   ResultSet filteredLogs = manager.filterOrdersDate(fromTime, toTime, user.getEmail());

                   session.setAttribute("orderLogRows", filteredLogs);
                   session.setAttribute("from", fromString.replace("T"," "));
                   session.setAttribute("to", toString.replace("T"," "));

                   request.getRequestDispatcher("viewOrders.jsp").forward(request, response);

                } catch (SQLException ex) {
                    System.out.println("SQL Error.");
                }
            } else {
                session.setAttribute("shipmentViewError", "Date not valid!");
                request.getRequestDispatcher("shipmentSearch.jsp").include(request, response); 
            }
        }
    }
}  
