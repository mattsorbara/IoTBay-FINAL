/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iotbay.model;

/**
 *
 * @author lindale
 */
public class Payment {
    private String paymentID;
    private String orderID;
    private double amount;
    private String date;
    private String paymentMethod;
    private String cardNumber;
    private String cardExpiry;
    private String cardCVC;

    public Payment(String paymentID, String orderID, int amount, String date, String paymentMethod, String cardNumber, String cardCVC, String cardExpiry) {
        this.paymentID = paymentID;
        this.orderID = orderID;
        this.amount = amount;
        this.date = date;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
        this.cardExpiry = cardExpiry;
        this.cardCVC = cardCVC;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public String getCardCVC() {
        return cardCVC;
    }

    public void setCardCVC(String cardCVC) {
        this.cardCVC = cardCVC;
    }



}
