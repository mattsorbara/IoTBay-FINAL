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
public class EditProductServlet extends HttpServlet {
    
    private DBManager manager;
    private DBConnector Connector;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try
        {
            Connector = new DBConnector();
        }catch (ClassNotFoundException | SQLException ex){
            java.util.logging.Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        try
        {       
            manager = new DBManager(Connector.openConnection());  
        }catch (SQLException ex){
            java.util.logging.Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        //session
        HttpSession session = request.getSession();
        
        session.setAttribute("error", "");
        
        try {
            String id = request.getParameter("id");
            int productid = Integer.parseInt(id);

            Catalogue product = manager.findProduct(productid);
            if (product != null) {
                session.setAttribute("product", product);
                request.getRequestDispatcher("editproduct.jsp").include(request, response);
                response.sendRedirect("editproduct.jsp");
            }
            else{
                request.getRequestDispatcher("editproduct.jsp").include(request, response);
                response.sendRedirect("editproduct.jsp");
            }
            
                            
        } catch (SQLException ex){
            Logger.getLogger(ProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try
        {
            Connector = new DBConnector();
        }catch (ClassNotFoundException | SQLException ex){
            java.util.logging.Logger.getLogger(EditProductServlet.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        try
        {       
            manager = new DBManager(Connector.openConnection());  
        }catch (SQLException ex){
            java.util.logging.Logger.getLogger(EditProductServlet.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        //session
        HttpSession session = request.getSession();
                
        int id = Integer.parseInt(request.getParameter("id"));
        double price = Double.parseDouble(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        
        try {
            if(price > 1000000000 || price < 1){
                String error = "Enter a valid price";
                session.setAttribute("error", error);
                request.getRequestDispatcher("editproduct.jsp").include(request, response);
            }else if(stock < 0 || stock > 1000000000){
                String error = "Enter a valid stock level";
                session.setAttribute("error", error);
                request.getRequestDispatcher("editproduct.jsp").include(request, response);
            }else{
                manager.editProduct(id, price, stock);
                response.sendRedirect("ProductServlet?id="+id); 
            }
        } catch (SQLException ex){
            Logger.getLogger(EditProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
