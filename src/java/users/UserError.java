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
public class UserError {
    private String userID;
    private String fullName;
    private String roleID;
    private String password;
    private String confirm;
    private String phoneNumner;
    private String email;
    private int vipstatus;
    private float balance;
    private int useproduct;
    private String error;

    public UserError() {
        this.userID = "";
        this.fullName = "";
        this.roleID = "US";
        this.password = "";
        this.confirm = "";
        this.phoneNumner = "";
        this.email = "";
        this.vipstatus = 0;
        this.balance = 0;
        this.useproduct = 0;
        this.error = "";
    }

    public UserError(String userID, String fullName, String roleID, String password, String confirm, String phoneNumner, String email, int vipstatus, float balance, int useproduct, String error) {
        this.userID = userID;
        this.fullName = fullName;
        this.roleID = roleID;
        this.password = password;
        this.confirm = confirm;
        this.phoneNumner = phoneNumner;
        this.email = email;
        this.vipstatus = vipstatus;
        this.balance = balance;
        this.useproduct = useproduct;
        this.error = error;
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

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getPhoneNumner() {
        return phoneNumner;
    }

    public void setPhoneNumner(String phoneNumner) {
        this.phoneNumner = phoneNumner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVipstatus() {
        return vipstatus;
    }

    public void setVipstatus(int vipstatus) {
        this.vipstatus = vipstatus;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    
    
    
}
