/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iotbay.controller;

//Import both Catalogue and User for verification
import iotbay.model.Catalogue;

import iotbay.model.dao.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rubabrahman
 */
public class AddProductServlet extends HttpServlet {
    
    private DBManager manager;
    private DBConnector Connector;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try
        {
            Connector = new DBConnector();
        }catch (ClassNotFoundException | SQLException ex){
            java.util.logging.Logger.getLogger(AddProductServlet.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        try
        {       
            manager = new DBManager(Connector.openConnection());  
        }catch (SQLException ex){
            java.util.logging.Logger.getLogger(AddProductServlet.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        //session
        HttpSession session = request.getSession();
                
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        int stock = Integer.parseInt(request.getParameter("stock"));
        String type = request.getParameter("type");
        
        try {
            boolean exists = manager.checkProduct(id);
            //Check if the product ID already exists in the DB
            if (exists != false) {
                String error = "Product already exists";
                session.setAttribute("error", error);
                request.getRequestDispatcher("addproduct.jsp").include(request, response);
            //Check if the Imaage URL is a valid image URL (ends in png or jpg)
            }else if(!image.matches("(https?:\\/\\/.*\\.(?:png|jpg))")){
                String error = "Enter a valid image URL";
                session.setAttribute("error", error);
                request.getRequestDispatcher("addproduct.jsp").include(request, response);
            //Check if the price is above 0 or less than the max
            }else if(price > 1000000000 || price < 1){
                String error = "Enter a valid price";
                session.setAttribute("error", error);
                request.getRequestDispatcher("addproduct.jsp").include(request, response);
            //Check if the stock is above 0 or less than the max
            }else if(stock < 0 || stock > 1000000000){
                String error = "Enter a valid stock level";
                session.setAttribute("error", error);
                request.getRequestDispatcher("addproduct.jsp").include(request, response);
            } else {
                manager.addProduct(id, title, price, description, image, stock, type);
                Catalogue product = new Catalogue(id, title, price, description, image, stock, type);
                session.setAttribute("product", product);
                request.getRequestDispatcher("product.jsp").include(request, response);
                response.sendRedirect("product.jsp");
            }   
        } catch (SQLException ex){
            Logger.getLogger(AddProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
