/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class MainController extends HttpServlet {
    private final static String DEFAULT="login.jsp";
    private final static String LOGIN="LoginController";
    private final static String LOGIN_GMAIL="LoginGmailController";
    private final static String LOGOUT="LogoutController";
    private final static String SEARCH="SearchForUserController";
    private final static String SEARCH_ADMIN="SearchForAdminController";
    private final static String CREATE_FOOD="CreateNewFoodController";
    private final static String ADD_FOOD="AddNewFoodController";
    private final static String UPDATE_FOOD="UpdateFoodController";//UpdateFoodController
    private final static String ADD_TO_CREATE="AddToCartController";
    private final static String VIEW="ViewOrderController";
    private final static String UPDATE_BILL="UpdateBillController";
    private final static String DELETE_BILL="DeleteBillController";
    private final static String BILL="BillController";
    private final static String VIEW_SHOPPING="ViewShoppingController";
    private final static String SEARCH_HISTORY="SearchHistoryController";
    private final static String DELETE_FOOD="DeleteFoodController";
    private final static String UPDATE_INFORMATION="UpdateInformationController";
    private final static String SAVE_UPDATE_FOOD="SaveUpdateFoodController";
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url=DEFAULT;
        try {           
            String action=request.getParameter("btnAction");
            if("Login".equals(action)){
                url=LOGIN;
            }else if("Logout".equals(action)){
                url=LOGOUT;
            }else if("Search".equals(action)){
                url=SEARCH;
            }else if("SearchForAdmin".equals(action)){
                url=SEARCH_ADMIN;
            }else if("NewFood".equals(action)){
                url=CREATE_FOOD;
            }else if("SaveNewFood".equals(action)){
                url=ADD_FOOD;
            }else if("UpdateFood".equals(action)){
                url=UPDATE_FOOD;
            }else if("AddToCart".equals(action)){
                url=ADD_TO_CREATE;
            }else if("ViewOrder".equals(action)){
                url=VIEW;
            }else if("UpdateBill".equals(action)){
                url=UPDATE_BILL;
            }else if("DeleteBill".equals(action)){
                url=DELETE_BILL;
            }else if("Bill".equals(action)){
                url=BILL;
            }else if("ViewHistoryShopping".equals(action)){
                url=VIEW_SHOPPING;
            }else if("SearchHistory".equals(action)){
                url=SEARCH_HISTORY;
            }else if("DeleteFood".equals(action)){
                url=DELETE_FOOD;
            }else if("UpdateInformation".equals(action)){
                url=UPDATE_INFORMATION;
            }else if("SaveUpdateFood".equals(action)){
                url=SAVE_UPDATE_FOOD;
            }else if("LoginGmail".equals(action)){
                url=LOGIN_GMAIL;
            }
            
        } catch (Exception e) {
            log("Error at MainContrller"+e.toString());
        }finally{
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
