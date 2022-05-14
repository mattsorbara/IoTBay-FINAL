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

public class SubmitOrderServlet extends HttpServlet {
    
    private DBManager manager;
    private DBConnector Connector;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        HttpSession session = request.getSession();
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        String orderID = request.getParameter("id");
        
        try {
            manager.setOrderStatus("SUBMITTED", Integer.parseInt(orderID));
            request.getRequestDispatcher("orderSearch.jsp").forward(request, response);
            
           
        } catch (SQLException ex) {
            Logger.getLogger(SubmitOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       
}