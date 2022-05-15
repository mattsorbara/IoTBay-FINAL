/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package iotbay.controller;

import iotbay.model.User;
import iotbay.model.dao.DBManager;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tanya
 */
@WebServlet(name = "DeleteShipmentServlet", urlPatterns = {"/DeleteShipmentServlet"})
public class DeleteShipmentServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

         HttpSession session = request.getSession();

        DBManager manager = (DBManager)session.getAttribute("manager");
          User user = (User)session.getAttribute("user"); 


        response.setContentType("text/html;charset=UTF-8");


       try {
            manager.deleteShipment(user.getEmail());
           
            request.getRequestDispatcher("home.jsp").include(request, response);
        
            
        } catch (SQLException ex) {
            System.out.println("Error: Shipment details not deleted.");
        }

       // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

}
}
