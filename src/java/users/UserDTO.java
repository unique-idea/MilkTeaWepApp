/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

/**
 *
 * @author lequa
 */
public class UserDTO {

    private String userID;
    private String fullName;
     private String password;
    private String roleID;
    private String phoneNumber;
    private String email;
    private int vipsatus;
    private float balance;
    private int useproduct;

    public UserDTO() {
        this.userID = "";
        this.fullName = "";
        this.password = "";
        this.roleID = "";
        this.phoneNumber = "";
        this.email = "";
        this.vipsatus = 0;
        this.balance = 0;
        this.useproduct = 0;
    }

    public UserDTO(String userID, String fullName, String password, String roleID, String phoneNumber, String email, int vipsatus, float balance, int useproduct) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.roleID = roleID;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.vipsatus = vipsatus;
        this.balance = balance;
        this.useproduct = useproduct;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVipsatus() {
        return vipsatus;
    }

    public void setVipsatus(int vipsatus) {
        this.vipsatus = vipsatus;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getUseproduct() {
        return useproduct;
    }

    public void setUseproduct(int useproduct) {
        this.useproduct = useproduct;
    }
    
    
   
    
}
