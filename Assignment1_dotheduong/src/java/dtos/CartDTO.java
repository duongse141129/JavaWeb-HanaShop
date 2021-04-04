/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class CartDTO {
    private String customerName;
    private Map<String,FoodDTO> cart;

    public CartDTO() {
    }

    public CartDTO(String customerName, Map<String, FoodDTO> cart) {
        this.customerName = customerName;
        this.cart = cart;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Map<String, FoodDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, FoodDTO> cart) {
        this.cart = cart;
    }

    public void add(FoodDTO dto){
        if(cart==null){
            this.cart=new HashMap<String,FoodDTO>();
        }
        if(this.cart.containsKey(dto.getFoodID())){
            int quantity=this.cart.get(dto.getFoodID()).getAmount();
            dto.setAmount(quantity+dto.getAmount());
        }
        cart.put(dto.getFoodID(), dto);      
    }
    
    public void delete(String id){
        if(this.cart==null)
            return;
        if(this.cart.containsKey(id)){
            this.cart.remove(id);
        }
    }

    public void update(String keys,int amount){
        if(this.cart!=null){
            if(this.cart.containsKey(keys)){
                this.cart.get(keys).setAmount(amount);
            }
        }
    }
}
