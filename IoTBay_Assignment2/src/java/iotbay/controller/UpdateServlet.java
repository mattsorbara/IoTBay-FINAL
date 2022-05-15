/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iotbay.controller;

import iotbay.model.User;
import iotbay.model.dao.DBManager;
import java.io.IOException;
import java.sql.SQLException;
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
public class UpdateServlet extends HttpServlet  {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // initialise required fields
        HttpSession session = request.getSession();
        
        // get data from form
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String type = request.getParameter("type");
        
        session.setAttribute("updateError", "");
        
        User user = new User(name, email, password, phone, type);
        DBManager manager = (DBManager) session.getAttribute("manager");
        Validator val = new Validator();
        
        try {
            // if user is not null

            if (!val.validateName(name)) {
                session.setAttribute("updateError", "Incorrect name format.");
                request.getRequestDispatcher("edit.jsp").include(request, response);
            }
            else if (!val.validatePassword(password)) {
                session.setAttribute("updateError", "Incorrect password format.");
                request.getRequestDispatcher("edit.jsp").include(request, response);
            }
            else if (user != null) {
                session.setAttribute("user", user);
                session.setAttribute("updated", "Update successful.");
                manager.updateUser(name, email, password, phone);
                request.getRequestDispatcher("welcome.jsp").include(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        // redirect to edit.jsp
        response.sendRedirect("edit.jsp");
    }
  
}
