/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

/**
 *
 * @author lequa
 */
public class ProductError {
    private String productID;
    private String name;
    private float price;
    private String detail;
    private int currentQuantity;
    private String error;

    public ProductError() {
         this.productID = "";
        this.name = "";
        this.price = 0;
        this.detail = "";
        this.currentQuantity = 0;
        this.error = "";
    }

    public ProductError(String productID, String name, float price, String detail, int currentQuantity, String error) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.detail = detail;
        this.currentQuantity = currentQuantity;
        this.error = error;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(int currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
}
