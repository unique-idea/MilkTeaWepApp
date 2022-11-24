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
//III//
//V//

/**
 *
 * @author lequa
 */
@WebServlet(name = "UpdateUsController", urlPatterns = {"/UpdateUsController"})
public class UpdateUsController extends HttpServlet {

    private static final String ERROR = "userManager.jsp";
    private static final String SUCCESS = "userManager.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean check = false;
            String userID = request.getParameter("userID");
            String roleID = request.getParameter("roleID");
            if (userID.contains("U") || userID.contains("A")) {
               if(roleID.equals("AD") || roleID.equals("US")){
                   check = true;
               }
            }      
            String fullName = request.getParameter("fullName");
            int vipstatus = Integer.parseInt(request.getParameter("vipstatus"));
            float balance = Float.parseFloat(request.getParameter("balance"));
            UserDAO dao = new UserDAO();
            HttpSession session = request.getSession();
            if (check == true) {
                UserDTO updateUser = (UserDTO) session.getAttribute("LOGIN_USER");
                if (updateUser.getFullName().equalsIgnoreCase(fullName)) {
                    session.setAttribute("UPDATEFAIL", "You Can't Update Youself Because Your Account Is Provide By Developer");
                    url = SUCCESS;
                } else {
                    UserDTO user = new UserDTO(userID, fullName, "", roleID, "", "", vipstatus, balance, 0);
                    boolean checkUpdate = dao.update(user);
                    if (checkUpdate == true) {
                        session.setAttribute("UPDATESUCCESS", "Customer Update Success");
                        url = SUCCESS;
                    } else {
                        session.setAttribute("UPDATESUCCESS", "Same ID With Other Users");
                        url = ERROR;
                    }
                }
            } else {
                session.setAttribute("UPDATEFAIL", "Update Failed Make Sure The Id Update Must Contain " + "[A] Or [U]" + " And Role Follow [AD] Or [US]!");
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
