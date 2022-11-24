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
@WebServlet(name = "DeleteUsController", urlPatterns = {"/DeleteUsController"})
public class DeleteUsController extends HttpServlet {

    private static final String ERROR = "SearchUsController";
    private static final String SUCCESS = "SearchUsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //search, userID, Action, Session->Atribute->huy khi sess time out, tat URL, Scope-> para//
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            UserDAO dao = new UserDAO();
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            boolean checkDelete = true;
            if (loginUser.getFullName().equals(fullName)) {
                checkDelete = false;
            }else{
                 checkDelete = dao.delete(userID);
            }
            if (checkDelete == true) {
                session.setAttribute("DELETESUCCSESS", "Detele Successfull!");
                url = SUCCESS;
            } else {
                session.setAttribute("ERROR", "Oops Look Like You Can Not Remove YourSelf");
                url = ERROR;
            }
        } catch (Exception e) {
            log("Error at DeleteController: " + e.toString());
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
