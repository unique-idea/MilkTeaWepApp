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
public class Orders {

    private int orderID;
    private String customerID;
    private String customerName;
    private String phoneNumber;
    private String userEmail;

    public Orders() {
    }
    
    
    public Orders(int orderID, String customerID, String customerName, String phoneNumber, String userEmail) {
        this.orderID = 0;
        this.customerID = "";
        this.customerName = "";
        this.phoneNumber = "";
        this.userEmail = "";
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

   
}
