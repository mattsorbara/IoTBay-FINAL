/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package iotbay.shipment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ViewShipmentServlet", urlPatterns = {"/ViewShipmentServlet"})
public class ViewShipmentServlet extends HttpServlet {

    private DBManager manager;
    private DBConnector Connector;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

HttpSession session = request.getSession();


        User user = (User) session.getAttribute("user");

        String paymentMethod = request.getParameter("shipMethod");
        String email = (String) user.getEmail();
      
        Order currentOrder = (Order) session.getAttribute("currentOrder");
        
        DBManager manager = (DBManager) session.getAttribute("manager");
//        

        try {
//            
            int shipmentID = currentOrder.getshipmentID();
            

            manager.addShipment1(shipmentID, shipmethod, unitno, streetno, streetname, suburb, postcode);
             if (!"guest@guest.com".equals(currentOrder.getUserEmail())){ {
                Savedshipment savedShipment = manager.findsavedShipment(email);               
                    int shipmentID = manager.getshipmentID();
                    String unitno = savedShipment.getunitno();
                    String streetno = savedShipment.getstreetno();
                    String streetname = savedShipment.getstreetname();
                    String suburb = savedShipment.getsuburb();
                    String postcode = savedShipment.getpostcode();
                   
                    session.setAttribute("confirmedShipment", confirmedShipment);
                    request.getRequestDispatcher("orderConfirmation.jsp").include(request, response);
//                } else {
//
//                }
            } 
              
        } catch (SQLException ex) {
            Logger.getLogger(viewShipmentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
        
            
        }
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

    
   
        

   

        

//            
}
