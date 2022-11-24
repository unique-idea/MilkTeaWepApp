/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import orders.Cart;
import products.Product;

/**
 *
 * @author lequa
 */
public class AddController extends HttpServlet {

    private static final String ERROR = "shopping.jsp";
    private static final String SUCCESS = "shopping.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String productID = request.getParameter("productID");
            String name = request.getParameter("name");
            float price = Float.parseFloat(request.getParameter("price"));
            String detail = request.getParameter("detail");
            int currentQuantity = Integer.parseInt(request.getParameter("currentQuantity"));
            int AddNumber = Integer.parseInt(request.getParameter("AddNumber"));
            HttpSession session = request.getSession();
            if (session != null) {
                Cart cart = (Cart) session.getAttribute("CART");
                Product pd = new Product(productID, name, price, detail, AddNumber);
                if (cart == null) {
                    cart = new Cart();
                }
                boolean checkAdd = cart.add(pd);
                if (checkAdd == true) {
                    session.setAttribute("CART", cart);
                    request.setAttribute("MESSAGE", "Added" + " " + name + " " + AddNumber + " Cup");
                    url = SUCCESS;
                }else{
                    request.setAttribute("MESSAGE", "Add Failed Try Again!");
                    url = ERROR;
                }
            }
        } catch (Exception e) {
            log("Error at shopping: " + e.toString());
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
        processRequest(request, response);
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
        processRequest(request, response);
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
