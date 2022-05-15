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
        //Fetches session, dbmanager, and orderID from previous webpage
        HttpSession session = request.getSession();
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        String orderID = request.getParameter("id");

        try {
            //Sets selected order to be "SUBMITTED"
            manager.setOrderStatus("SUBMITTED", Integer.parseInt(orderID));
            //fetches order, assigns to new variable, grab productID, then intialise new Catalogue object
            Order order = (Order) manager.fetchOrder(Integer.parseInt(orderID));
            int productID = order.getProductID();
            Catalogue product = (Catalogue) manager.findProduct(productID);

            //calculate final amount to insert into the order
            int finalAmount = product.getStock() - order.getOrderQuantity();
            //deduct stock from database and redirect user to orderSearch
            manager.setProductStock(finalAmount, productID);
            request.getRequestDispatcher("orderSearch.jsp").forward(request, response);
            
        //catches any SQL errors that may occur from dbmanager
        } catch (SQLException ex) {
            Logger.getLogger(SubmitOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }       
}