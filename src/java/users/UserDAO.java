/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import orders.OrderDetail;
import orders.Orders;
import products.Product;
import utils.DBUtils;

/**
 *
 * @author lequa
 */
public class UserDAO {

    private static final String LOGIN = "SELECT userID, fullName, roleID , phoneNumber, email, vipstatus, balance, useproduct FROM tblUsers "
            + "WHERE fullName=? AND password=?";

    private static final String SEARCH = "SELECT userID, fullName, roleID , phoneNumber, email, vipstatus, balance, useproduct FROM tblUsers "
            + "WHERE fullName like ?";
    
    private static final String GETUSER = "SELECT userID, fullName, roleID , phoneNumber, email, vipstatus, balance, useproduct FROM tblUsers "
            + "WHERE fullName like ?";
    
   private static final String GETLISTUSER = "SELECT userID, fullName, roleID , phoneNumber, email, vipstatus, balance, useproduct FROM tblUsers ";
   
    private static final String DELETE = "DELETE tblUsers WHERE userID=?";

    private static final String UPDATE = "UPDATE tblUsers SET userID=?, roleID=?, vipstatus=? , balance=? WHERE fullName=?";

    private static final String UPDATEMYSELF = "UPDATE tblUsers SET fullName=?, password=?, phoneNumber=?, email=? WHERE userID=?";

    private static final String UPDATEVIP = "UPDATE tblUsers SET vipstatus=?, balance=?, useproduct=? WHERE userID=?";

    private static final String CHECK_DUPLICATE = "SELECT userID FROM tblUsers WHERE userID=?";

    private static final String INSERT = "INSERT INTO tblUsers(userID, fullName, roleID, password, phoneNumber, email, vipstatus, balance, useProduct)"
            + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GETCURRENTQUANTITY = "SELECT currentQuantity FROM tblProduct "
            + "WHERE productID=?";

    private static final String INSERTORDERS = "INSERT INTO tblOrders(orderID, customerID, customerName, phoneNumber, email)"
            + " VALUES(?, ?, ?, ?, ?)";

    private static final String INSERTORDERDETAIL = "INSERT INTO tblOrderDetail(productID, orderID, quantity)"
            + " VALUES(?, ?, ?)";

    private static final String UPDATEQUANTITY = "UPDATE tblProduct SET currentQuantity=? WHERE productID=?";
    
    private static final String CHECKMONEY = "SELECT balance FROM tblUsers WHERE fullName=?";
    
    private static final String GETLISTORDERS = "SELECT orderID, customerID, customerName, phoneNumber, email FROM tblOrders";
   
    private static final String GETBALANCE = "SELECT balance FROM tblUsers WHERE userID=?";
    
    private static final String UPDATEBALANCE = "UPDATE tblUsers SET balance=? WHERE userID=?";
    
    
    
    public boolean updateBalance (float cost, String customerID) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETBALANCE);
                ptm.setString(1, customerID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    float balance = rs.getFloat("balance");
                    float newSalary = balance - cost;
                    ptm = conn.prepareStatement(UPDATEBALANCE);
                    ptm.setFloat(1, newSalary);
                    ptm.setString(2, customerID);
                    if (ptm.executeUpdate() > 0) {
                        check = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }
    
     public List<Orders> getListOrders() throws SQLException {
        List<Orders> listOrders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pt = conn.prepareStatement(GETLISTORDERS);
                rs = pt.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    String customerID = rs.getString("customerID");
                    String customerName = rs.getString("customerName");
                    String phone = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    listOrders.add(new Orders(orderID, customerID, customerName, phone, email));
                }
            }
        } catch (Exception e) {

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pt != null) {
                pt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listOrders;
    }
     
    public boolean checkMoney(String fullName, float cost) throws SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECKMONEY);
                ptm.setString(1, fullName);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    float balance = rs.getFloat("balance");
                    if(balance > cost){
                        check = true;
                    }
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }
    public boolean updateQuantity(String productID, int quantity) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETCURRENTQUANTITY);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int currentQuantity = rs.getInt("currentQuantity");
                    int newQuantity = currentQuantity - quantity;
                    ptm = conn.prepareStatement(UPDATEQUANTITY);
                    ptm.setInt(1, newQuantity);
                    ptm.setString(2, productID);
                    if (ptm.executeUpdate() > 0) {
                        check = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }

    public boolean insertOrders(int orderID, String customerID, String fullName, String phone, String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERTORDERS);
                ptm.setInt(1, orderID);
                ptm.setString(2,customerID);
                ptm.setString(3, fullName);
                ptm.setString(4, phone);
                ptm.setString(5, email);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean insertOrderDetail(OrderDetail ord) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERTORDERDETAIL);
                ptm.setString(1, ord.getProductID());
                ptm.setInt(2, ord.getOderID());
                ptm.setInt(3, ord.getQuantity());
                if (check = ptm.executeUpdate() > 0) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean CompareCurentQuantiy(String productID, int quantity) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GETCURRENTQUANTITY);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int currentQuantity = rs.getInt("currentQuantity");
                    if (currentQuantity < quantity) {
                        check = false;
                    } else if (currentQuantity > quantity) {
                        check = true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public UserDTO checkLogin(String fullName, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, fullName);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String roleID = rs.getString("roleID");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    int vipstatus = rs.getInt("vipstatus");
                    float balance = rs.getFloat("balance");
                    int useproduct = rs.getInt("useproduct");
                    user = new UserDTO(userID, fullName, password, roleID, phoneNumber, email, vipstatus, balance, useproduct);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }
    
      public UserDTO getUserByFullName(String fullName) throws SQLException {
         UserDTO user = new UserDTO();
        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pt = conn.prepareStatement(GETUSER);
                pt.setString(1, fullName);
                rs = pt.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String name = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    int vipstatus = rs.getInt("vipstatus");
                    float balance = rs.getFloat("balance");
                    int useproduct = rs.getInt("useproduct");
                     user = new UserDTO(userID, name,"******", roleID, phoneNumber, email, vipstatus, balance, useproduct);
                }
            }
        } catch (Exception e) {

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pt != null) {
                pt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public List<UserDTO> getListUser() throws SQLException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pt = conn.prepareStatement(GETLISTUSER);
                rs = pt.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    int vipstatus = rs.getInt("vipstatus");
                    float balance = rs.getFloat("balance");
                    int useproduct = rs.getInt("useproduct");
                    listUser.add(new UserDTO(userID, fullName, "*******", roleID, phoneNumber, email, vipstatus, balance, useproduct));
                }
            }
        } catch (Exception e) {

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pt != null) {
                pt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listUser;
    }
    
    public List<UserDTO> getListUser(String search) throws SQLException {
        List<UserDTO> listUser = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pt = conn.prepareStatement(SEARCH);
                pt.setString(1, "%" + search + "%");
                rs = pt.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    String phoneNumber = rs.getString("phoneNumber");
                    String email = rs.getString("email");
                    int vipstatus = rs.getInt("vipstatus");
                    float balance = rs.getFloat("balance");
                    int useproduct = rs.getInt("useproduct");
                    listUser.add(new UserDTO(userID, fullName, "*******", roleID, phoneNumber, email, vipstatus, balance, useproduct));
                }
            }
        } catch (Exception e) {

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pt != null) {
                pt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listUser;
    }

    public boolean delete(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, userID);
                check = ptm.executeUpdate() > 0 ? true : false; // if ptm > 0 => true//
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean update(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getRoleID());
                ptm.setInt(3, user.getVipsatus());
                ptm.setFloat(4, user.getBalance());
                ptm.setString(5, user.getFullName());
                if (ptm.executeUpdate() > 0) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateMs(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATEMYSELF);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getPassword());
                ptm.setString(3, user.getPhoneNumber());
                ptm.setString(4, user.getEmail());
                ptm.setString(5, user.getUserID());
                check = ptm.executeUpdate() > 0 ? true : false; // if ptm > 0 => true//
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean insert(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getRoleID());
                ptm.setString(4, user.getPassword());
                ptm.setString(5, user.getPhoneNumber());
                ptm.setString(6, user.getEmail());
                ptm.setInt(7, user.getVipsatus());
                ptm.setFloat(8, user.getBalance());
                ptm.setInt(9, user.getUseproduct());
                if (check = ptm.executeUpdate() > 0) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean getVip(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATEVIP);
                ptm.setInt(1, user.getVipsatus() + 1);
                ptm.setFloat(2, user.getBalance() + 10000);
                ptm.setInt(3, user.getUseproduct() + 1);
                ptm.setString(4, user.getUserID());
                if (check = ptm.executeUpdate() > 0) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
