/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String US = "US";
    private static final String USER_PAGE = "userPage.jsp";
    private static final String AD = "AD";
    private static final String ADMIN_PAGE = "adminPage.jsp";
    private static final String TEST = "shopping.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean fix = true, foundSpace = true;
            String fullName = request.getParameter("fullName");
            fullName = fullName.toLowerCase();
            char[] fixChar = fullName.toCharArray();
            for (int i = 0; i < fixChar.length; i++) {
                if (Character.isLetter(fixChar[i])) {
                    if (foundSpace) {
                        fixChar[i] = Character.toUpperCase(fixChar[i]);
                        foundSpace = false;
                    }
                } else {
                    foundSpace = true;
                }
            }
            fullName = String.valueOf(fixChar);
            if (!fullName.contains(" ")) {
                fix = false;
            }
            String password = request.getParameter("password");
            UserDAO dao = new UserDAO();
            if (fix == true) {
                UserDTO loginUser = dao.checkLogin(fullName, password);
                if (loginUser != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_USER", loginUser);
                    String roleID = loginUser.getRoleID();
                    if (AD.equals(roleID)) {
                        url = ADMIN_PAGE;
                    } else if (US.equals(roleID)) {
                        url = USER_PAGE;
                    } else {
                        request.setAttribute("ERROR", "Your role is not support");
                    }
                } else {
                    request.setAttribute("ERROR", "Incorrest User or PassWord!");
                }
            } else {
                request.setAttribute("ERROR", "Incorrest User Or PassWord Make Sure Your Name Have Space Between Surname And Name!");
            }

        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
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
