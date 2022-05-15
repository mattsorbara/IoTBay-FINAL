package iotbay.controller;

//Import both Catalogue and User for verification
import iotbay.model.*;

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

public class OrderCheckoutServlet extends HttpServlet {
    
    private DBManager manager;
    private DBConnector Connector;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Intialise dbConnector
        try
        {
            Connector = new DBConnector();
        } catch (ClassNotFoundException | SQLException ex){
            java.util.logging.Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE,null,ex);
        }
        //Intialise dbManager
        try
        {       
            manager = new DBManager(Connector.openConnection());  
        } catch (SQLException ex){
            java.util.logging.Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        //grabs current session
        HttpSession session = request.getSession();
        //attempts to grab parameter "id" which contains productID for product
        try {
            String id = request.getParameter("id");
            int productid = Integer.parseInt(id);

            //search database for product
            Catalogue product = manager.findProduct(productid);
            //check if product is null
            if (product != null) {
                //set product to an attribute, then direct user to orderCheckout.jsp
                session.setAttribute("product", product);
                request.getRequestDispatcher("orderCheckout.jsp").include(request, response);
                response.sendRedirect("orderCheckout.jsp");
            }
            //sends user back to catalogue if the product is null
            else{
                request.getRequestDispatcher("catalogue.jsp").include(request, response);
                response.sendRedirect("catalogue.jsp");
            }
            
        //catches any SQL errors that dbmanager may throw                    
        } catch (SQLException ex){
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //current session
        HttpSession session = request.getSession();
        //Intialise dbManager
        DBManager manager = (DBManager) session.getAttribute("manager");
        //Set order and quantity to intialise
        Order currentOrder = null;
        int quantity = 0;

        try {
            //assign product to a variable called product
            Catalogue product = (Catalogue) session.getAttribute("product");
            
            try {
                //checks if the quantity is a valid number (within stock and numerical)
                quantity = Integer.parseInt(request.getParameter("quantity"));
            }
            //if the quantity is not valid, outputs an error
            catch (NumberFormatException invalidValueError) {
                session.setAttribute("orderError", "Quantity amount invalid!");
                request.getRequestDispatcher("orderCheckout.jsp").include(request, response);
            }
            
            // checks if quantity is less than or equal to product's stock
            if (quantity <= product.getStock()) {
                //Intialising fields necessary for data to be added to the database
                int productID = product.getId();
                double orderPrice = product.getPrice();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                Timestamp orderDate = new Timestamp(new Date().getTime());
                String orderStatus = "SAVED";
                
                //sets stock of object product
                product.setStock((product.getStock() - quantity));
                //fetch current user in session
                User user = (User) session.getAttribute("user");
                //add order to database whilst returning auto-generated orderID
                int orderID = manager.addOrder(user.getEmail(), productID, orderPrice, quantity, orderDate, orderStatus);
                //calculate totalPrice
                double totalPrice = manager.fetchOrderAmount(orderID);
                //add order to model object Order
                currentOrder = new Order(orderID, user.getEmail(), productID, totalPrice, quantity, orderDate.toString(), orderStatus);
                //sets attribute currentOrder with currentOrder
                session.setAttribute("currentOrder", currentOrder);
                //finds savedpayment for user
                Savedpayment savedPayment = manager.findSavedpayment(user.getEmail());
                // checks if savedPayment is null, then sets attribute for savedPayment
                if (savedPayment != null) {
                    session.setAttribute("savedPayment", savedPayment);
                    session.setAttribute("anythingSaved", "true");
                    System.out.print(session.getAttribute("anythingSaved"));
                //if null, creates dummy savedPayment
                } else {
                    Savedpayment nullPayment = new Savedpayment("N/A", "N/A", "N/A", "N/A");
                    session.setAttribute("savedPayment", nullPayment);
                    session.setAttribute("anythingSaved", "false");
                }
         //       request.getRequestDispatcher("payment.jsp").include(request, response);

/* SAVED SHIPMENT STUFF BELOW - TANYA */
                Savedshipment savedShipment = manager.findSavedshipment(user.getEmail());

                if (savedShipment != null) {
                    session.setAttribute("savedShipment", savedShipment);
                    session.setAttribute("anythingSavedShipment", "true");

                } else {
                    Savedshipment nullShipment = new Savedshipment("N/A", "N/A", "N/A", "N/A","N/A", "N/A");
                    session.setAttribute("savedShipment", nullShipment);
                    session.setAttribute("anythingSavedShipment", "false");
                }
                request.getRequestDispatcher("viewShipment.jsp").include(request, response);
            

/* FIN SAVED SHIPMENT */
            }
          

            
            else if (quantity > product.getStock()) {
                session.setAttribute("orderError", "Quantity exceeds avaliable stock!");
                request.getRequestDispatcher("orderCheckout.jsp").include(request, response);
            }
           //if quantity is in wrong format, returns error
            else {
                session.setAttribute("orderError", "Quantity amount invalid!");
                request.getRequestDispatcher("orderCheckout.jsp").include(request, response);
            }

        //if dbmanager fails an SQL search, returns error
        } catch(SQLException ex) {
            Logger.getLogger(OrderCheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }


       
    }
}