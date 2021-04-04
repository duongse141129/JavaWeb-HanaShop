/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Admin
 */
public class OrderDTO {
    String orderID;
    String userID;
    Float totalPrice;
    String date;
    String addressDelivery;

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String userID, Float totalPrice, String date, String addressDelivery) {
        this.orderID = orderID;
        this.userID = userID;
        this.totalPrice = totalPrice;
        this.date = date;
        this.addressDelivery = addressDelivery;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddressDelivery() {
        return addressDelivery;
    }

    public void setAddressDelivery(String addressDelivery) {
        this.addressDelivery = addressDelivery;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "orderID=" + orderID + ", userID=" + userID + ", totalPrice=" + totalPrice + ", date=" + date + ", addressDelivery=" + addressDelivery + '}';
    }

    
    
}
