/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import products.Product;
import users.UserDTO;
import utils.DBUtils;

/**
 *
 * @author lequa
 */
public class ProductDAO {

    private static final String GET = "SELECT productID,name, price, detail, currentQuantity FROM tblProduct  ";
    private static final String SEARCH = "SELECT productID, name, price, detail, currentQuantity FROM tblProduct  "
            + "WHERE name like ?";
    private static final String DELETE = "DELETE tblProduct WHERE productID=?";
    private static final String UPDATE = "UPDATE tblProduct SET productID=?, name=?, price=?, detail=? , currentQuantity=? WHERE productID=?";
    private static final String INSERT = "INSERT INTO tblProduct(productID, name, price, detail, currentQuantity)" + " VALUES(?, ?, ?, ?, ?)";
    private static final String CHECK_DUPLICATE = "SELECT productID FROM tblProduct WHERE productID=?";

    public List<Product> getListProduct(String searchPd) throws SQLException {
        List<Product> ProductList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pt = conn.prepareStatement(SEARCH);
                pt.setString(1, "%" + searchPd + "%");
                rs = pt.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    String detail = rs.getString("detail");
                    int currentQuantity = rs.getInt("currentQuantity");
                    ProductList.add(new Product(productID, name, price, detail, currentQuantity));
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
        return ProductList;
    }

    public List<Product> getListProductUs() throws SQLException {
        List<Product> ProductList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pt = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pt = conn.prepareStatement(GET);
                rs = pt.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("productID");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    String detail = rs.getString("detail");
                    int currentQuantity = rs.getInt("currentQuantity");
                    ProductList.add(new Product(id, name, price, detail, currentQuantity));
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
        return ProductList;
    }

    public boolean delete(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, productID);
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

    public boolean update(Product pd) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, pd.getProductID());
                ptm.setString(2, pd.getName());
                ptm.setFloat(3, pd.getPrice());
                ptm.setString(4, pd.getDetail());
                ptm.setInt(5, pd.getCurrentQuantity());
                ptm.setString(6, pd.getProductID());
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

    public boolean checkDuplicate(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, productID);
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

    public boolean insert(Product pd) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT);
                ptm.setString(1, pd.getProductID());
                ptm.setString(2, pd.getName());
                ptm.setFloat(3, pd.getPrice());
                ptm.setString(4, pd.getDetail());
                ptm.setInt(5, pd.getCurrentQuantity());
                check = ptm.executeUpdate() > 0 ? true : false; // if ptm > 0 => true//
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
}
