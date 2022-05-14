/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iotbay.controller;

import iotbay.model.Catalogue;
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
        
       if (email.equals("sysadmin@iotbay.com") && password.equals("sysadmin")) {
            session.setAttribute("sysadmin", true);
            request.getRequestDispatcher("HomeAdmin.jsp").include(request, response);
        }
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        User user = null;
        Catalogue testProduct = null;
        Savedpayment savedPayment = null;
//        validator.clear(session);
        
        try {
            user = manager.findUser(email, password);
            testProduct = manager.testGetDevice();
////            
            session.setAttribute("testProduct", testProduct);

            savedPayment = manager.findSavedpayment(email);
//
            if (savedPayment != null) {
                session.setAttribute("savedPayment", savedPayment);
            } else {
                Savedpayment nullPayment = new Savedpayment("N/A", "N/A", "N/A", "N/A");
                session.setAttribute("savedPayment", nullPayment);
            }

            if (user != null) {
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
                Timestamp login = new Timestamp(new Date().getTime());  
                session.setAttribute("loginTimestamp", login);   

                session.setAttribute("user", user);
                request.getRequestDispatcher("welcome.jsp").include(request, response);
            } else {
                session.setAttribute("existErr", "Student does not exist in the Database!");
                request.getRequestDispatcher("login.jsp").include(request, response);
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println(ex.getMessage() == null ? "Student does not exist" : "welcome");
        }
//        
//        if (!validator.validateEmail(email)) {
//            session.setAttribute("emailErr", "Error: Email format incorrect");
//            request.getRequestDispatcher("login.jsp").include(request, response);
//        } else if (!validator.validatePassword(password)) {
//           session.setAttribute("passErr", "Error: Password format incorrect");
//           request.getRequestDispatcher("login.jsp").include(request, response);
//        } else {
//            try {
//                user = manager.findUser(email, password);
//                
//                if (user != null) {
//                    session.setAttribute("student", user);
//                    request.getRequestDispatcher("main.jsp").include(request, response);
//                } else {
//                    session.setAttribute("existErr", "Student does not exist in the Database!");
//                    request.getRequestDispatcher("login.jsp").include(request, response);
//                }
//            } catch (SQLException | NullPointerException ex) {
//                System.out.println(ex.getMessage() == null ? "Student does not exist" : "welcome");
//            }
//        }
        
        
        
        
    }
    
    
}
