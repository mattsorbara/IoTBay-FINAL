/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iotbay.model.dao;

import iotbay.model.*;
import java.sql.*;
import java.util.ArrayList;




public class DBManager {
    
    private Statement st;
    
    public DBManager(Connection conn) throws SQLException {
        st = conn.createStatement();
    }

public void addShipment(String shipmethod, String unitno, String streetno, String streetname, String suburb, String postcode) throws SQLException {
        st.executeUpdate("INSERT INTO IOTADMIN.Shipment " + "VALUES ('" + shipmethod + "', '" + unitno +"', '" + streetno + "', '" + streetname +"','" + suburb + "', '" + postcode);
    }
    
    public void updateShipment(String shipmethod, String unitno, String streetno, String streetname, String suburb, String postcode, String email) throws SQLException {
        st.executeUpdate("UPDATE IOTADMIN.Shipment SET SHIPMETHOD='" + shipmethod + "',UNITNUMBER='" + unitno + "',STREETNUMBER='" + streetno + "',STREETNAME='" + streetname + "',SUBURB='" + suburb + "',POSTCODE='" + postcode + "' WHERE EMAIL='" + email + "'");
        
    }

    
    
    public void deleteShipment(String email) throws SQLException {
        st.executeUpdate("DELETE FROM IOTADMIN.Shipment WHERE EMAIL='" + email + "'");
    }
    
    
    public User findUser(String email, String password) throws SQLException {
        String fetch = "SELECT * FROM IOTADMIN.USERS WHERE USEREMAIL='" + email + "' AND PASSWORD='" + password + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
            String userEmail = rs.getString(1);
            String userPass = rs.getString(3);
            if (userEmail.equals(email) && userPass.equals(password)) {
                String userName = rs.getString(2);
                String userPhone = rs.getString(4);
                String userType = rs.getString(5);
                return new User(userName, userEmail, password, userPhone, userType);
                
            }
        }
        return null;
    }
    
    public void addUser(String name, String email, String password, String phone, String type) throws SQLException {
        st.executeUpdate("INSERT INTO IOTADMIN.USERS " + "VALUES ('" + name + "', '" + email + "', '" + password +"','" + phone + "', '" + type + "', true)");
    }

    public void adminAddUser(String name, String email, String password, String phone, String type) throws SQLException {
        st.executeUpdate("INSERT INTO IOTADMIN.Users " + "VALUES ('" + name + "', '" + email + "', '" + password +"','" + phone + "', '" + type + "', true");
    }
    
    public void updateUser(String name, String email, String password, String phone) throws SQLException {
        st.executeUpdate("UPDATE IOTADMIN.Users SET FULLNAME='" + name + "',PASSWORD='" + password + "',PHONE='" + phone + "' WHERE USEREMAIL='" + email + "'");
        
    }
    
    public void deleteUser(String email) throws SQLException {
        st.executeUpdate("DELETE FROM IOTADMIN.USERS WHERE USEREMAIL='" + email + "'");
    }
    
    public ArrayList<User> fetchUsers() throws SQLException {
        String fetch = "SELECT * FROM IOTADMIN.USERS";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<User> temp = new ArrayList<User>();
        
        while (rs.next()) {
            String name = rs.getString(2);
            String email = rs.getString(1);
            String password = rs.getString(3);
            String phone = rs.getString(4);
            String type = rs.getString(5);
            
            temp.add(new User(name,email,password,phone,type));
        }
        return temp;
    }
    
    public boolean checkStudent(String email, String password) throws SQLException {
        String fetch = "SELECT * FROM IOTADMIN.Users where EMAIL='" + email + "' AND PASSWORD='" + password + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
            String userEmail = rs.getString(2);
            String userPass = rs.getString(3);
            if (userEmail.equals(email) && userPass.equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    public void addLog(Timestamp login, Timestamp logout, String email) throws SQLException {
        st.executeUpdate("INSERT INTO IOTADMIN.Logs (USEREMAIL, LOGIN, LOGOUT) " + "VALUES ('" + email + "', '" + login + "', '" + logout + "')");
    }
    
    public ResultSet fetchAllLogs(String email) throws SQLException {
        String fetch = "SELECT * FROM IOTADMIN.Logs WHERE EMAIL='" + email + "'";
        ResultSet rs = st.executeQuery(fetch);
        return rs;
    }
    
    public ResultSet filterLogs(Timestamp from, Timestamp to, String email) throws SQLException {
        String filter = "SELECT * FROM IOTADMIN.Logs WHERE USEREMAIL='" + email + "' AND LOGIN >= '" + from + "' AND LOGIN <= '" + to + "'";
        ResultSet rs = st.executeQuery(filter);
        return rs;
    }
    
    public void submitFinalOrder(String orderID, String userEmail, String productID, double orderPrice, int quantity, String orderDate, String shippingType) throws SQLException {
        st.executeUpdate("INSERT INTO IOTADMIN.ORDERS " + "VALUES ('" + orderID + "', '" + userEmail + "', '" + productID + "', '" + orderPrice + "', '" + quantity + "', '" + orderDate + "', '" + shippingType + ", 'SUBMITTED')");

    }

    public Catalogue testGetDevice() throws SQLException {
        String fetch = "SELECT * FROM IOTADMIN.PRODUCTS WHERE PRODUCTID = 1";
        ResultSet rs = st.executeQuery(fetch);

        while(rs.next()) {
            int id = rs.getInt(1);
            String title = rs.getString(2);
            double price = rs.getDouble(3);
            String description = rs.getString(4);
            int stock = rs.getInt(5);
            String type = rs.getString(6);
            String image = rs.getString(7);
            return new Catalogue(id, title, price, description, image, stock, type);
        }


        return null;
    }

    public void addOrder(String userEmail, int productID, double productPrice, int quantity, String orderDate, String status) throws SQLException {
        double amount = productPrice * quantity;

        st.executeUpdate("INSERT INTO IOTADMIN.ORDER (userEmail, productID, amount, orderQuantity, orderDate, orderStatus) VALUES ('" + userEmail + "', '" + productID + "', '" + amount + "', '" + quantity + "', '" + orderDate + "', 'SAVED')");

        System.out.println("test");
    }

     
    public void addPayment1(String paymentID, String orderID, double amount, String paymentMethod, String email) throws SQLException {
        st.executeUpdate("INSERT INTO IOTADMIN.payment " + "VALUES ('" + paymentID + "', '" + orderID + "', '" + paymentMethod + "', " + amount + ", '" + email + "', ' ', ' ', ' ')");
    } 
    
    public void addPayment2(String paymentID, String cardNumber, String cardCVC, String cardExpiry) throws SQLException {
        st.executeUpdate("UPDATE IOTADMIN.payment " + "SET cardNumber ='" + cardNumber + "', cardCVC ='" + cardCVC + "', cardExpiry='" + cardExpiry + "' WHERE paymentID ='" + paymentID + "'");
    }

    public void savePayment(String email, String cardNumber, String cardCVC, String cardExpiry) throws SQLException {
        st.executeUpdate("INSERT INTO IOTADMIN.savedPayment " + "VALUES ('" + email + "', '" + cardNumber + "', '" + cardCVC + "', '" + cardExpiry + "')");
    } 

    public void updatePayment(String email, String cardNumber, String cardCVC, String cardExpiry) throws SQLException {
        st.executeUpdate("UPDATE IOTADMIN.SAVEDPAYMENT SET cardNumber = '" + cardNumber + "', cardCVC = '" + cardCVC + "', cardExpiry ='" +cardExpiry + "' WHERE email = '" + email +"'");
    } 

    public void deletePayment(String email) throws SQLException {
        st.executeUpdate("DELETE FROM IOTADMIN.SAVEDPAYMENT WHERE USEREMAIL = '" + email + "'");
    } 

    public Savedpayment findSavedpayment(String email) throws SQLException {
        String fetch = "SELECT * FROM IOTADMIN.Savedpayment WHERE USEREMAIL='" + email + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
            String userEmail = rs.getString(1);
            String cardNumber = rs.getString(2);
            String cardCVC = rs.getString(3);
            String cardExpiry = rs.getString(4);
            return new Savedpayment(userEmail, cardNumber, cardCVC, cardExpiry);
        }
        return null;
    }

    
    public ArrayList<Catalogue> fetchProducts() throws SQLException {
            String fetch = "SELECT * FROM IOTADMIN.PRODUCTS";
            ResultSet rs = st.executeQuery(fetch);
            ArrayList<Catalogue> products = new ArrayList();

            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                double price = rs.getDouble(3);
                String description = rs.getString(4);
                String image = rs.getString(5);
                int stock = rs.getInt(6);
                String type = rs.getString(7);

                products.add(new Catalogue(id, title, price, description , image, stock, type));
            }
            return products;
    }
    
    public Catalogue findProduct(int productid) throws SQLException {
        String fetch = "SELECT * FROM IOTADMIN.PRODUCTS WHERE PRODUCTID="+productid;
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                double price = rs.getDouble(3);
                String description = rs.getString(4);
                String image = rs.getString(5);
                int stock = rs.getInt(6);
                String type = rs.getString(7);
                
                return new Catalogue(id, title, price, description , image, stock, type);
        }
        return null;
    }
    
    public ArrayList<Catalogue> searchProducts(String query) throws SQLException {
            String cleanQuery = query.replaceAll("'", "");
            String fetch = "SELECT DISTINCT * FROM IOTADMIN.PRODUCTS WHERE UPPER(PRODUCTTITLE) LIKE UPPER('%"+cleanQuery+"%') OR UPPER(PRODUCTTYPE) LIKE UPPER('%"+cleanQuery+"%')";
            ResultSet rs = st.executeQuery(fetch);
            ArrayList<Catalogue> products = new ArrayList();

            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                double price = rs.getDouble(3);
                String description = rs.getString(4);
                String image = rs.getString(5);
                int stock = rs.getInt(6);
                String type = rs.getString(7);

                products.add(new Catalogue(id, title, price, description , image, stock, type));
            }
            return products;
    }
    
    public void addProduct(int id, String title, double price, String description, String image, int stock, String type) throws SQLException {
        st.executeUpdate("INSERT INTO IOTADMIN.PRODUCTS (PRODUCTID, PRODUCTTITLE, PRODUCTTITLE, PRODUCTDESCRIPTION, PRODUCTIMAGE, PRODUCTSTOCK, PRODUCTTYPE) VALUES (" + id + ", '" + title + "', " + price +",'" + description + "','" + image + "'," + stock + ", '" + type + "')");
    }
    
    public void deleteProduct(int id) throws SQLException {
        st.executeUpdate("DELETE FROM IOTADMIN.PRODUCTS WHERE PRODUCTID="+id);
    }
    
    public void editProduct(int id, double price, int stock) throws SQLException {
        st.executeUpdate("UPDATE IOTADMIN.PRODUCT SET PRODUCTPRICE="+price+", PRODUCTSTOCK="+stock+" WHERE PRODUCTID="+id);
    }
    
    public boolean checkProduct(int id) throws SQLException {
        String fetch = "SELECT * FROM IOTADMIN.PRODUCTS WHERE PRODUCTID=" + id;
        ResultSet rs = st.executeQuery(fetch);
        
        while (rs.next()) {
            int productid = rs.getInt(1);
            if (productid == id) {
                return true;
            }
        }
        return false;
    }
     

}