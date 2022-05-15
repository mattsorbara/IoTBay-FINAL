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
public class RegisterServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String type = request.getParameter("type");
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        Validator val = new Validator();
        
        try {
            User exist = manager.findUser(email, password);
            if (exist != null) {
                session.setAttribute("regError", "User already exists.");
                request.getRequestDispatcher("register.jsp").include(request, response);
            }
            else if(!val.validateName(name)) {
                session.setAttribute("regError", "Name format wrong.");
                request.getRequestDispatcher("register.jsp").include(request, response);
            }
            else if(!val.validateEmail(email)) {
                session.setAttribute("regError", "Email format wrong.");
                request.getRequestDispatcher("register.jsp").include(request, response);
            }
            else if(!val.validatePassword(password)) {
                session.setAttribute("regError", "Password format wrong.");
                request.getRequestDispatcher("register.jsp").include(request, response);
            }
            else {
                manager.addUser(name, email, password, phone, type);
                User user = new User(name, email, password, phone, type);
                session.setAttribute("user", user);
                request.getRequestDispatcher("welcome.jsp").include(request, response);
            }
            
        } catch(SQLException ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
            
    }
    
    
    
}
