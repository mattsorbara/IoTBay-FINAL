/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iotbay.controller;

import iotbay.model.*;
import iotbay.model.dao.DBManager;
import java.io.IOException;
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
 * @author matthewsorbara
 */
public class OrderCheckoutServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();

        Catalogue product = (Catalogue) session.getAttribute("testProduct");

        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int productID = product.getId();
        double orderPrice = product.getPrice();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Timestamp orderDate = new Timestamp(new Date().getTime());
        String orderStatus = "SAVED";  

        DBManager manager = (DBManager) session.getAttribute("manager");

        try {

            User user = (User) session.getAttribute("user");

            System.out.println("here");
            manager.addOrder(user.getEmail(), productID, orderPrice, quantity, orderDate.toString(), orderStatus);
            session.setAttribute("user", user);
            request.getRequestDispatcher("welcome.jsp").include(request, response);
            
        } catch(SQLException ex) {
            Logger.getLogger(OrderCheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
            
    }
    
    
    
}
