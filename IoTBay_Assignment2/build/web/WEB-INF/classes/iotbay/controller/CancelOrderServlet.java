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

public class CancelOrderServlet extends HttpServlet {
    
    private DBManager manager;
    private DBConnector Connector;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // grab current session      
        HttpSession session = request.getSession();
        // grab DBManager to access database         
        DBManager manager = (DBManager) session.getAttribute("manager");
        // fetch ID created from previous screen's button        
        String orderID = request.getParameter("id");
        // catch any errors if database returns invalid values        
        try {
            // sets order to cancelled and specifies what order   
            manager.setOrderStatus("CANCELLED", Integer.parseInt(orderID));
            // sends user to order search page  
            request.getRequestDispatcher("orderSearch.jsp").forward(request, response);
            
        // if database returns error            
        } catch (SQLException ex) {
            Logger.getLogger(CancelOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}