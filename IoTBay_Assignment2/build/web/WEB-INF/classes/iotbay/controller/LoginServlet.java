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
import java.util.Set;

/**
 *
 * @author matthewsorbara
 */
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // get session data
        HttpSession session = request.getSession();
        
        // get user info
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        session.setAttribute("email", email);
        
        // initialise manager
        DBManager manager = (DBManager) session.getAttribute("manager");
        User user = null;
        Validator val = new Validator();
        
        try {
            // validate email format
            if (!val.validateEmail(email)) {
                session.setAttribute("logError", "Email format wrong.");
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
            // validate password format
            else if (!val.validatePassword(password)) {
                session.setAttribute("logError", "Password format wrong.");
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
            // run code if email + password valid
            else {
                user = manager.findUser(email, password);
                
                // if user is null
                if (user == null) {
                    session.setAttribute("logError", "User does not exist.");
                    request.getRequestDispatcher("login.jsp").include(request, response);
                } 
                
                // if user is disabled
                else if (user.getUserActive() == false) {
                    session.setAttribute("logError", "User registration has been cancelled.");
                    request.getRequestDispatcher("login.jsp").include(request, response);
                } 
                else if (user.getEmail().equals("sysadmin@iotbay.com")) {
                    session.setAttribute("sysadmin", true);
                    Timestamp login = new Timestamp(new Date().getTime());  
                    session.setAttribute("loginTimestamp", login);  
                    session.setAttribute("user", user);
                    request.getRequestDispatcher("HomeAdmin.jsp").include(request, response);
                }
                // if user is sysadmin redirect them to the admin page
                else if (user.getEmail().equals("sysadmin@iotbay.com")) {
                    session.setAttribute("sysadmin", "true");
                    Timestamp login = new Timestamp(new Date().getTime());  
                    session.setAttribute("loginTimestamp", login);  
                    session.setAttribute("user", user);
                    request.getRequestDispatcher("HomeAdmin.jsp").include(request, response);
                }
                // if ok, create new log and send user to welcome page
                else { 
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                    Timestamp login = new Timestamp(new Date().getTime());  
                    session.setAttribute("loginTimestamp", login);  
                    session.setAttribute("user", user);
                    request.getRequestDispatcher("welcome.jsp").include(request, response);  
                }
            }
          
          // catch SQL error
        } catch (SQLException | NullPointerException ex) {
            System.out.println(ex.getMessage() == null ? "User does not exist" : "welcome");
        }       
        
    }
    
    
}
