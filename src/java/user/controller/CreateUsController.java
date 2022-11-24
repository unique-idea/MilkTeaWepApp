/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import users.UserDAO;
import users.UserDTO;
import users.UserError;

/**
 *
 * @author lequa
 */
@WebServlet(name = "CreateUsController", urlPatterns = {"/CreateUsController"})
public class CreateUsController extends HttpServlet {

    private static final String ERROR = "createUser.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        boolean checkValidation = true;
        UserDAO dao = new UserDAO();
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            if (userID.length() > 10 || userID.length() < 2 || !userID.contains("U")) {
                userError.setUserID("User ID Lenght [3-10] Start With [U]");
                checkValidation = false;
            }
            boolean checkDuplicate = dao.checkDuplicate(userID);
            if (checkDuplicate) {
                userError.setUserID("Duplicate UserID");
                checkValidation = false;
            }
            if (fullName.length() > 50 || fullName.length() < 5) {
                userError.setFullName("full name Length [5-50]!");
                checkValidation = false;
            }
            if (!confirm.equals(password)) {
                userError.setConfirm("Password not the same!");
                checkValidation = false;
            }
            if (checkValidation == true) {
                UserDTO user = new UserDTO(userID, fullName, password, roleID, phoneNumber, email, 0, 1000, 0);
                boolean checkInsert = dao.insert(user);
                if (checkInsert == true) {
                    request.setAttribute("CREATESUCCESS", "Create Successfull You Can Login Right Now");
                    url = SUCCESS;
                } else {
                    userError.setError("Some things went wrong :<<");
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            log("Error at createController: " + e.toString());
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
