/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import users.UserDAO;
import users.UserDTO;

/**
 *
 * @author lequa
 */
public class GetVipController extends HttpServlet {

    private static final String ERROR = "userPage.jsp";
    private static final String SUCCESS = "userPage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean check = true;
            String productKey = request.getParameter("productKey");
            int useproduct = Integer.parseInt(request.getParameter("useproduct"));
            if (productKey == "" || productKey.contains(" ")) {
                check = false;
            } else if (productKey != "" || !productKey.contains(" ")) {
                 if (useproduct > 2) {
                    check = false;
                }
            }
            String userID = request.getParameter("userID");
            float balance = Float.parseFloat(request.getParameter("balance"));
            int vipstatus = Integer.parseInt(request.getParameter("vipstatus"));
            UserDAO dao = new UserDAO();
            HttpSession session = request.getSession();
            if (check == true) {
                if (productKey.equalsIgnoreCase("riscenter") || productKey.equalsIgnoreCase("hoadnt")) {
                    UserDTO user = new UserDTO(userID, "", "", "", "", "", vipstatus, balance, useproduct);
                    boolean checkGetVip = dao.getVip(user);
                    if (checkGetVip == true) {
                        session.setAttribute("GETVIP", "Get Vip Success Your Balance Will Incresing Next Login");
                        url = SUCCESS;
                    } else {
                        session.setAttribute("GETVIP", "Get Vip Failed Check Again");
                        url = ERROR;
                    }
                } else {
                    session.setAttribute("GETVIP", "Product Key Not Valid");
                }
            } else {
                session.setAttribute("GETVIP", "Product Key Empty Or You Already Use Out ProductKey");
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
