package com.hotel.bean;


public class Billing {
    private String BillingId;
    private double roomCharges;
    private double additionalServices;
    private double totalCharges;

    
    public String getBillingId() {
        return BillingId;
    }

    public void setBillingId(String BillingId) {
        this.BillingId = BillingId;
    }

    public double getRoomCharges() {
        return roomCharges;
    }

    public void setRoomCharges(double roomCharges) {
        this.roomCharges = roomCharges;
    }

    public double getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(double additionalServices) {
        this.additionalServices = additionalServices;
    }

    public double getTotalCharges() {
        return totalCharges;
    }

    public void setTotalCharges(double totalCharges) {
        this.totalCharges = totalCharges;
    }
}
