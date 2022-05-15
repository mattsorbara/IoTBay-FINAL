package iotbay.shipment.model;

import java.sql.Timestamp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tanya
 */
public class Shipment {
   private String shipMethod;
   private String unitno;
   private String streetno;
   private String streetname;
   private String suburb;
   private String postcode;
   private Timestamp shipmentDate;

    
    public Shipment(String shipMethod, String unitno, String streetno, String streetname, String suburb, String postcode) {
        this.shipMethod = shipMethod;
        this.unitno = unitno;
        this.streetno = streetno;
        this.streetname = streetname;
        this.suburb = suburb;
        this.postcode = postcode;
        
    }

public String getShipMethod() {
        return shipMethod;
    }

    public void setShipmethod(String shipMethod) {
        this.shipMethod = shipMethod;
    }


    public String getUnitno() {
        return unitno;
    }

    public void setUnitno(String unitno) {
        this.unitno = unitno;
    }

    public String getStreetno() {
        return streetno;
    }

    public void setStreetno(String streetno) {
        this.streetno = streetno;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Timestamp getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Timestamp shipmentDate) {
        this.shipmentDate = shipmentDate;
    }
    

}
