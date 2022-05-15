/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iotbay.controller;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpSession;

/**
 *
 * @author matthewsorbara
 */
public class Validator {
    
   private String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";      
   private String namePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";       

   private String passwordPattern = "[A-Za-z0-9]{4,}";       
              
    private String cardNumberPattern = "[0-9]{16}|[0-9]{4}[\\s]?[0-9]{4}[\\s]?[0-9]{4}[\\s]?[0-9]{4}";
    private String cardCVCPattern = "^[0-9]{3}$";
    private String cardExpiryPattern = "^[0-9]{2}/[0-9]{2}$";   

   public Validator(){ }       
                                  

   public boolean validate(String pattern, String input){       
      Pattern regEx = Pattern.compile(pattern);       
      Matcher match = regEx.matcher(input);       

      return match.matches(); 

   }       

 

   public boolean checkEmpty(String email, String password){       

      return  email.isEmpty() || password.isEmpty();   

   }

   
   public boolean validateEmail(String email){                       

      return validate(emailPattern,email);   

   }

       
   public boolean validateName(String name){

      return validate(namePattern,name); 

   }       
   

   public boolean validatePassword(String password){

      return validate(passwordPattern,password); 

   }          


    public boolean checkPaymentDetailsEmpty(String paymentMethod, String cardNumber, String cardCVC, String cardExpiry) {
        return paymentMethod.isEmpty() || cardNumber.isEmpty() || cardCVC.isEmpty() || cardExpiry.isEmpty();
    }

    public boolean validateCardNumber (String cardNumber) {
        return validate(cardNumberPattern, cardNumber);
    }

    public boolean validateCardCVC (String cardCVC) {
        return validate(cardCVCPattern, cardCVC);
    }

    public boolean validateCardExpiry (String cardExpiry){
        return validate(cardExpiryPattern, cardExpiry);
    }

}
    
