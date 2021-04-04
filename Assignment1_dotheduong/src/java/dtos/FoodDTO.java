/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class FoodDTO {
    private String foodID;
    private String foodName;
    private String img;
    private int amount;
    private float price;
    private String description;
    private String type;
    private Date createDate;
    private boolean status;

    public FoodDTO(String foodID, String foodName, String img, int amount, float price, String description, String type, Date createDate, boolean status) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.img = img;
        this.amount = amount;
        this.price = price;
        this.description = description;
        this.type = type;
        this.createDate = createDate;
        this.status = status;
    }

    

    public FoodDTO() {
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FoodDTO{" + "foodID=" + foodID + ", foodName=" + foodName + ", img=" + img + ", amount=" + amount + ", price=" + price + ", description=" + description + ", type=" + type + ", createDate=" + createDate + ", status=" + status + '}';
    }
    
    
    
}
