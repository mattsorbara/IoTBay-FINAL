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
public class CatalogueSearch extends HttpServlet {
    
    private DBManager manager;
    private DBConnector Connector;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try
        {
            Connector = new DBConnector();
        }catch (ClassNotFoundException | SQLException ex){
            java.util.logging.Logger.getLogger(CatalogueSearch.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        try
        {       
            manager = new DBManager(Connector.openConnection());  
        }catch (SQLException ex){
            java.util.logging.Logger.getLogger(CatalogueSearch.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        //session
        HttpSession session = request.getSession();
        
        try {
            String query = request.getParameter("query");
            ArrayList<Catalogue> products = manager.searchProducts(query);
            System.out.println(!query.isEmpty());
            if (!query.isEmpty()) {
                session.setAttribute("catalogue", products);
                request.getRequestDispatcher("catalogue.jsp").include(request, response);
                response.sendRedirect("catalogue.jsp");
            }
            else{
                response.sendRedirect("CatalogueServlet");
            }
            
                            
        } catch (SQLException ex){
            Logger.getLogger(CatalogueSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
