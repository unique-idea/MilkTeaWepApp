/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lequa
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";

    //Done!
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";

    private static final String SEARCH = "Search";
    private static final String SEARCH_CONTROLLER = "SearchUsController";
    private static final String SEARCHPD = "SearchPd";
    private static final String SEARCHPD_CONTROLLER = "SearchPdController";

    private static final String DELETE = "Delete";
    private static final String DELETE_CONTROLLER = "DeleteUsController";
    private static final String DELETEPD = "DeletePd";
    private static final String DELETEPD_CONTROLLER = "DeletePdController";

    private static final String UPDATE = "Update";
    private static final String UPDATE_CONTROLLER = "UpdateUsController";
    private static final String UPDATEPD = "UpdatePd";
    private static final String UPDATEPD_CONTROLLER = "UpdatePdController";
    private static final String UPDATEMYSELF = "UpdateMyself";
    private static final String UPDATEMYSELF_CONTROLLER = "UpdateMsController";

    private static final String CREATE = "Create";
    private static final String CREATE_CONTROLLER = "CreateUsController";
    private static final String CREATEPD = "CreatePd";
    private static final String CREATEPD_CONTROLLER = "CreatePdController";

    private static final String LOGOUTAD = "LogoutAd";
    private static final String LOGOUTAD_CONTROLLER = "LogoutAdController";
    private static final String LOGOUTUS = "LogoutUs";
    private static final String LOGOUTUS_CONTROLLER = "LogoutUsController";

    private static final String ADD = "Add";
    private static final String ADD_CONTROLLER = "AddController";

    private static final String VIEW = "View";
    private static final String VIEW_PAGE = "viewCart.jsp";

    private static final String REMOVEPD = "Remove";
    private static final String REMOVEPD_CONTROLLER = "RemovePdController";

    private static final String EDITPD = "Edit";
    private static final String EDITPD_CONTROLLER = "EditPdController";

    private static final String SHOWALL = "showAll";
    private static final String SHOWALL_CONTROLLER = "GetListPdController";

    private static final String CHECKOUT = "checkOut";
    private static final String CHECKOUT_CONTROLLER = "CheckOutController";

    private static final String GETVIP = "GetVip";
    private static final String GETVIP_CONTROLLER = "GetVipController";
    
    private static final String LOGINGOOLE = "loginGoogle";
    private static final String LOGINGOOLECONTROLLER = "LoginGoogleController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (SEARCH.equals(action)) {
                url = SEARCH_CONTROLLER;
            } else if (SEARCHPD.equals(action)) {
                url = SEARCHPD_CONTROLLER;
            } else if (DELETE.equals(action)) {
                url = DELETE_CONTROLLER;
            } else if (DELETEPD.equals(action)) {
                url = DELETEPD_CONTROLLER;
            } else if (UPDATE.equals(action)) {
                url = UPDATE_CONTROLLER;
            } else if (UPDATEPD.equals(action)) {
                url = UPDATEPD_CONTROLLER;
            } else if (UPDATEMYSELF.equals(action)) {
                url = UPDATEMYSELF_CONTROLLER;
            } else if (CREATE.equals(action)) {
                url = CREATE_CONTROLLER;
            } else if (CREATEPD.equals(action)) {
                url = CREATEPD_CONTROLLER;
            } else if (LOGOUTAD.equals(action)) {
                url = LOGOUTAD_CONTROLLER;
            } else if (LOGOUTUS.equals(action)) {
                url = LOGOUTUS_CONTROLLER;
            } else if (ADD.equals(action)) {
                url = ADD_CONTROLLER;
            } else if (VIEW.equals(action)) {
                url = VIEW_PAGE;
            } else if (REMOVEPD.equals(action)) {
                url = REMOVEPD_CONTROLLER;
            } else if (EDITPD.equals(action)) {
                url = EDITPD_CONTROLLER;
            } else if (SHOWALL.equals(action)) {
                url = SHOWALL_CONTROLLER;
            } else if (CHECKOUT.equals(action)) {
                url = CHECKOUT_CONTROLLER;
            } else if (GETVIP.equals(action)) {
                url = GETVIP_CONTROLLER;
            } else if(LOGINGOOLE.equals(action)){
                url = LOGINGOOLECONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
