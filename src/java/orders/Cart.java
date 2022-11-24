/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

import java.util.HashMap;
import java.util.Map;
import products.Product;

/**
 *
 * @author lequa
 */
public class Cart {
    private Map<String, Product> cart;

    public Cart() {
    }

    public Cart(Map<String, Product> cart) {
        this.cart = cart;
    }

    public Map<String, Product> getCart() {
        return cart;
    }

    public void setCart(Map<String, Product> cart) {
        this.cart = cart;
    }
    
    public boolean add(Product pd){
        boolean check = false;
        if(this.cart == null){
            this.cart = new HashMap<>();          
        }
        if(this.cart.containsKey(pd.getProductID())){
            int nowQuantity = this.cart.get(pd.getProductID()).getCurrentQuantity();
            pd.setCurrentQuantity(nowQuantity + pd.getCurrentQuantity());
        }
        this.cart.put(pd.getProductID(), pd);
        check = true;
        return check;
    }
    
    public boolean remove(String productID){
        boolean check = false;
        if(this.cart != null){
            if(this.cart.containsKey(productID)){
                this.cart.remove(productID);
                check = true;
            }
        }
        return check;
    }
    
     public boolean update(String productID, Product pd){
        boolean check = false;
        if(this.cart != null){
            if(this.cart.containsKey(productID)){
                this.cart.replace(productID, pd);
                check = true;
            }
        }
        return check;
    }

}
