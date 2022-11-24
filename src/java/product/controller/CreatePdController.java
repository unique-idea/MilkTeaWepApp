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
import products.ProductError;
import users.UserDAO;
import users.UserDTO;
import users.UserError;

/**
 *
 * @author lequa
 */
public class CreatePdController extends HttpServlet {

    private static final String ERROR = "createPd.jsp";
    private static final String SUCCESS = "productManager.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        ProductError pdError = new ProductError();
        boolean checkValidation = true, check = false;
        ProductDAO pdao = new ProductDAO();
        try {
            String productID = request.getParameter("productID");
            float price = Float.parseFloat(request.getParameter("price"));
            if (productID.contains("P")) {
                if (price != 0) {
                    check = true;
                }
            }
            String name = request.getParameter("name");
            String detail = request.getParameter("detail");
            int currentQuantity = Integer.parseInt(request.getParameter("currentQuantity"));
            HttpSession session = request.getSession();
            if (check == true) {
                boolean checkDuplicate = pdao.checkDuplicate(productID);
                if (checkDuplicate == true) {
                    pdError.setProductID("Duplicate ProductID");
                    checkValidation = false;
                }
                if (name.length() > 50 || name.length() < 3) {
                    pdError.setName("Name Lenght[3,50]");
                    checkValidation = false;
                }
                if (checkValidation == true) {
                    Product pd = new Product(productID, name, price, detail, currentQuantity);
                    boolean checkInsert = pdao.insert(pd);
                    if (checkInsert == true) {
                        session.setAttribute("CREATEPD", "New Product Has Been Added");
                        url = SUCCESS;
                    } else {
                        session.setAttribute("CREATEPDFAIL", "Oops Something Went Wrong Check Again!");
                        url = ERROR;
                    }
                }
            } else {
                pdError.setProductID("Make Sure ID Contain [P]");
                pdError.setName("Make Sure Name Length [3-50]");
                pdError.setError("Make Sure Price > 0");
            }
            request.setAttribute("PD_ERROR", pdError);
        } catch (Exception e) {
            log("Error at createPdController: " + e.toString());
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
