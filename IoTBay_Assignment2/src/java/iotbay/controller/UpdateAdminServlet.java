/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iotbay.controller;

import iotbay.model.User;
import iotbay.model.dao.DBManager;
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
 * @author saniyakhanna
 */
class UpdateAdminServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String type = request.getParameter("type");
        
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        User user = new User (name, email, password, phone, type);

        try {
                manager.updateUser(email, name, phone, password);

                session.setAttribute("user", user);

                ArrayList<User> users = manager.fetchUsers();
                request.setAttribute("users", users);

                request.getRequestDispatcher("EditUser.jsp").include(request, response);
 

            } catch (SQLException ex) {
                session.setAttribute("updateMsg", "Update was not successful");
                request.getRequestDispatcher("HomeAdmin.jsp").include(request, response);
            }

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
