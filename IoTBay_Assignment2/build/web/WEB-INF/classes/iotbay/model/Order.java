package iotbay.model;
import java.io.Serializable;

/**  
 *
 * @author rubabrahman
 */
public class Order implements Serializable {
    //the fields
    private int orderID;
    private String userEmail;
    private int productID;
    private double orderPrice;
    private int orderQuantity;
    private String orderDate;
    private String shippingType;
    private String orderStatus;    

    //initialise the variables
    public Order(int orderID, String userEmail, int productID, double orderPrice, int orderQuantity, String orderDate, String shippingType, String orderStatus) {
        this.orderID = orderID;
        this.userEmail = userEmail;
        this.productID = productID;
        this.orderPrice = orderPrice;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.shippingType = shippingType;
        this.orderStatus = orderStatus;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int id) {
        this.orderID = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void getUserEmail(String email) {
        this.userEmail = email;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int id) {
        this.productID = id;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double price) {
        this.orderPrice = price;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int quantity) {
        this.orderQuantity = quantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String date) {
        this.orderDate = date;
    }
    
    public String getShippingType(){
        return shippingType;
    }
    
    public void setShippingType(String type){
        this.shippingType = type;
    }
   
    public String getOrderStatus(){
        return orderStatus;
    }
    
    public void setOrderStatus(String status){
        this.orderStatus = status;
    } 
    
}