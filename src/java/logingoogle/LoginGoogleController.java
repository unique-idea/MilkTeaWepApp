/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logingoogle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "LoginGoogleController", urlPatterns = {"/LoginGoogleController"})
public class LoginGoogleController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "createUser.jsp";
    private static final String SUCCESS_ALREADY = "userPage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String code = request.getParameter("code");
            UserDAO dao = new UserDAO();
            List<UserDTO> listUser = dao.getListUser();
            UserDTO loginUser = new UserDTO();
            if (!code.isEmpty()) {
                HttpSession session = request.getSession();
                int flag = 0;
                String accessToken = GoogleUtils.getToken(code);
                UserGoogleDto user = GoogleUtils.getUserInfo(accessToken);
                String a = user.getName();
                String b = user.getEmail();
                for (UserDTO userDTO : listUser) {
                    if (userDTO.getEmail().equals(user.getEmail())) {
                        loginUser = dao.getUserByFullName(userDTO.getFullName());
                        flag = 1;
                        break;
                    }
                }
                if (flag == 1) {
                    session.setAttribute("LOGIN_USER", loginUser);
                    url = SUCCESS_ALREADY;
                } else if (flag == 0) {
                    request.setAttribute("GOOGLE_USER", user);
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at LoginGoogleController: " + e.toString());
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
        doGet(request, response);
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
