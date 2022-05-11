/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iotbay.controller;


import iotbay.model.User;
import iotbay.model.dao.DBManager;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author saniyakhanna
 */
public class DeleteAdminServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        DBManager manager = (DBManager)session.getAttribute("manager");
        User user = (User)session.getAttribute("user");
        
        
        try {
            manager.deleteUser(user.getEmail());
            request.getRequestDispatcher("Delete.jsp").include(request, response);
        
            
        } catch (SQLException ex) {
            System.out.println("Error: user not deleted.");
        }
       
    }
    
}
