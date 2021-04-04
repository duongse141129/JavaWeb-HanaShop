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
public class OrderDetailDTO {
    String detailID;
    String orderID;
    String foodID;
    int amount;
    Float price;

    String orderDate;
    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String detailID, String orderID, String foodID, int amount, Float price) {
        this.detailID = detailID;
        this.orderID = orderID;
        this.foodID = foodID;
        this.amount = amount;
        this.price = price;
    }

    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" + "detailID=" + detailID + ", orderID=" + orderID + ", foodID=" + foodID + ", amount=" + amount + ", price=" + price + '}';
    }
    
    
    


    
}
