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
public class FoodErrorDTO {
    private String foodIDError;
    private String foodNameError;
    private String imgError;
    private String amountError;
    private String priceError;
    private String descriptionError;
    private String typeError;
    private String createDateError;

    public FoodErrorDTO() {
    }

    public FoodErrorDTO(String foodIDError, String foodNameError, String imgError, String amountError, String priceError, String descriptionError, String typeError, String createDateError) {
        this.foodIDError = foodIDError;
        this.foodNameError = foodNameError;
        this.imgError = imgError;
        this.amountError = amountError;
        this.priceError = priceError;
        this.descriptionError = descriptionError;
        this.typeError = typeError;
        this.createDateError = createDateError;
    }

    public String getFoodIDError() {
        return foodIDError;
    }

    public void setFoodIDError(String foodIDError) {
        this.foodIDError = foodIDError;
    }

    public String getFoodNameError() {
        return foodNameError;
    }

    public void setFoodNameError(String foodNameError) {
        this.foodNameError = foodNameError;
    }

    public String getImgError() {
        return imgError;
    }

    public void setImgError(String imgError) {
        this.imgError = imgError;
    }

    public String getAmountError() {
        return amountError;
    }

    public void setAmountError(String amountError) {
        this.amountError = amountError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getTypeError() {
        return typeError;
    }

    public void setTypeError(String typeError) {
        this.typeError = typeError;
    }

    public String getCreateDateError() {
        return createDateError;
    }

    public void setCreateDateError(String createDateError) {
        this.createDateError = createDateError;
    }

    @Override
    public String toString() {
        return "FoodErrorDTO{" + "foodIDError=" + foodIDError + ", foodNameError=" + foodNameError + ", imgError=" + imgError + ", amountError=" + amountError + ", priceError=" + priceError + ", descriptionError=" + descriptionError + ", typeError=" + typeError + ", createDateError=" + createDateError + '}';
    }

    
    
}
