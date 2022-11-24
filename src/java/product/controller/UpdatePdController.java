/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import products.ProductDAO;
import products.Product;
import users.UserDAO;
import users.UserDTO;

/**
 *
 * @author lequa
 */
public class UpdatePdController extends HttpServlet {

    private static final String ERROR = "SearchPdController";
    private static final String SUCCESS = "SearchPdController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean check = false;
            String productID = request.getParameter("productID");
            String name = request.getParameter("name");
            float price = Float.parseFloat(request.getParameter("price"));
            if (price != 0) {
                check = true;
            }
            String detail = request.getParameter("detail");
            int currentQuantity = Integer.parseInt(request.getParameter("currentQuantity"));
            HttpSession session = request.getSession();
            ProductDAO pdao = new ProductDAO();
            if (check == true) {
                Product pd = new Product(productID, name, price, detail, currentQuantity);
                boolean checkUpdate = pdao.update(pd);
                if (checkUpdate == true) {
                    session.setAttribute("UPPD", "Product Has Been Update");
                    url = SUCCESS;
                } else {
                    session.setAttribute("UPPD", "Same Product ID");
                    url = ERROR;
                }
            } else {
                session.setAttribute("UPPD", "Check Again Make Sure Price > 0");
                url = ERROR;
            }

        } catch (Exception e) {
            log("Error at UpdateController: " + e.toString());
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
