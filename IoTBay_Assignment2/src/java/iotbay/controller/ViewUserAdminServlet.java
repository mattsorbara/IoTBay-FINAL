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
public class ViewUserAdminServlet extends HttpServlet {
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
 
        DBManager manager = (DBManager) session.getAttribute("manager");

        try {
     
            ArrayList<User> customer = manager.fetchCustomers();
            ArrayList<User> staff = manager.fetchStaff();

            request.setAttribute("customers", customer);
            request.setAttribute("staffs", staff);

            request.getRequestDispatcher("ViewUser.jsp").include(request, response);

        } 

            catch (SQLException ex) {
            Logger.getLogger(ViewUserAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}