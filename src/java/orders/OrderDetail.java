/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orders;

/**
 *
 * @author lequa
 */
public class OrderDetail {
    private int id;
    private String productID;
    private int oderID;
    private int quantity;

    public OrderDetail() {
        this.id = 0;
        this.productID = "";
        this.oderID = 0;
        this.quantity = 0;
    }
    
     public OrderDetail( String productID, int oderID, int quantity) {
        this.productID = productID;
        this.oderID = oderID;
        this.quantity = quantity;
    }
     
    public OrderDetail(int id, String productID, int oderID,int quantity) {
        this.id = id;
        this.productID = productID;
        this.oderID = oderID;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getOderID() {
        return oderID;
    }

    public void setOderID(int oderID) {
        this.oderID = oderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
