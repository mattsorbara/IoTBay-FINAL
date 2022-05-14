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
 * @author saniyakhanna
 */
class UpdateAdminServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        User user = null;

        try {
                user = manager.findUser(email, password);
                session.setAttribute("user", user);
                request.getRequestDispatcher("EditUser.jsp").include(request, response);


                ArrayList<User> staff = manager.fetchStaff();
                request.setAttribute("staff", staff);

                request.getRequestDispatcher("EditUser.jsp").include(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(EditServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.getRequestDispatcher("main.jsp").include(request, response);
            }

        try {
                user = manager.findUser(email, password);
                session.setAttribute("user", user);
                request.getRequestDispatcher("EditUser.jsp").include(request, response);


                ArrayList<User> customer = manager.fetchCustomers();
                request.setAttribute("staff", customer);

                request.getRequestDispatcher("EditUser.jsp").include(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(EditServlet.class.getName()).log(Level.SEVERE, null, ex);
                request.getRequestDispatcher("main.jsp").include(request, response);
            }


                }
//        try {
//            user = manager.findUser(email, password);
//            if (user != null) {
//                session.setAttribute("user", user);
//                request.getRequestDispatcher("EditUser.jsp").include(request, response);
//            } else {
//                System.out.println("user does not exist");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println(ex.getErrorCode() + " and "  + ex.getMessage());
//        }
        
//        request.getRequestDispatcher("UpdateUser.jsp").include(request, response);
//    }
//}
