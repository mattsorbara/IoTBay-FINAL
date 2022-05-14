/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iotbay.controller;

import iotbay.model.Savedpayment;
import iotbay.model.User;
import iotbay.model.dao.DBManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;  
import java.util.Date;

/**
 *
 * @author matthewsorbara
 */
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
//        Validator validator = new Validator();
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        session.setAttribute("email", email);
        
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        User user = null;
        Validator val = new Validator();
        
        try {
            if (!val.validateEmail(email)) {
                session.setAttribute("logError", "Email format wrong.");
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
            else if (!val.validatePassword(password)) {
                session.setAttribute("logError", "Password format wrong.");
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
            else {
                user = manager.findUser(email, password);
                if (user == null) {
                    System.out.println("here1");
                    session.setAttribute("logError", "User does not exist.");
                    request.getRequestDispatcher("login.jsp").include(request, response);
                } else if (user.getUserActive() == false) {
                    System.out.println("here");
                    session.setAttribute("logError", "User registration has been cancelled.");
                    request.getRequestDispatcher("login.jsp").include(request, response);
                }
                else { 
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Timestamp login = new Timestamp(new Date().getTime());  
                    session.setAttribute("loginTimestamp", login);  
                    session.setAttribute("user", user);
                    request.getRequestDispatcher("welcome.jsp").include(request, response);  
                }
            }
            
        } catch (SQLException | NullPointerException ex) {
            System.out.println(ex.getMessage() == null ? "User does not exist" : "welcome");
        }
//        
        
        
        
    }
    
    
}
