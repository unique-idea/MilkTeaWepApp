/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import orders.OrderDetail;
import orders.Orders;
import orders.Cart;
import products.Product;
import users.UserDAO;
import users.UserDTO;

/**
 *
 * @author lequa
 */
public class CheckOutController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean checkQuantity = true, checkUpdateBalance = true, checkUpdateQuantity = true,
                    checkInsertOrders = false , checkInsertOrderDetail = false;
            int orderID = 1;
            String customerID = request.getParameter("customerID");
            String fullName = request.getParameter("fullName");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            float cost =  Float.parseFloat(request. getParameter("cost"));
            UserDAO dao = new UserDAO();
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            List<Orders> listOrders = dao.getListOrders();
            if (session != null) {
                if (cart != null) {
                    for (Product pd : cart.getCart().values()) {
                        checkQuantity = dao.CompareCurentQuantiy(pd.getProductID(), pd.getCurrentQuantity());
                        if (checkQuantity == false) {
                            session.setAttribute("NOTENOUGHT", "Sorry we not have enought " + pd.getName() + "quantity for you!");
                            break;
                        }
                    }
                    if(checkQuantity == true){
                        boolean checkMoney = dao.checkMoney(fullName, cost);
                        if(checkMoney == true){
                            if(listOrders.size() == 0){
                                checkInsertOrders = dao.insertOrders(orderID, customerID, fullName, phone, email); 
                            }else if(listOrders.size() > 0){
                                orderID = listOrders.size() + 1;
                                checkInsertOrders = dao.insertOrders(orderID, customerID, fullName, phone, email); 
                            }                    
                        }else{
                             session.setAttribute("NOTENOUGHT", "Sorry look like you have not enough money!");
                        }
                    }
                    
                    if(checkInsertOrders == true){
                        for (Product pd : cart.getCart().values()){
                              OrderDetail ord = new OrderDetail(0, pd.getProductID(), orderID, pd.getCurrentQuantity());
                              checkInsertOrderDetail  = dao.insertOrderDetail(ord);
                        }
                    }
                    
                    if(checkInsertOrderDetail == true){
                          for (Product pd : cart.getCart().values()){
                              checkUpdateQuantity = dao.updateQuantity(pd.getProductID(), pd.getCurrentQuantity());
                              if(checkUpdateQuantity == false){
                                 url = ERROR;
                                 break;
                              }
                          }
                          
                          if(checkUpdateQuantity == true){
                              checkUpdateBalance = dao.updateBalance(cost, customerID);
                              if(checkUpdateBalance == false){
                                  url = ERROR;
                              }else{
                                  session.setAttribute("PAYMENTSUCCESS", "You have payment successfull!");
                                  cart = null;
                                  session.setAttribute("CART", cart);
                                  url = SUCCESS;
                              }
                          }
                    }
                    
                }
            }              
        } catch (Exception e) {
            log("Error at CheckOutController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CheckOutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
