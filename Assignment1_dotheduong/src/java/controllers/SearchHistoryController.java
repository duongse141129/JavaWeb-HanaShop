/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.FoodDAO;
import dtos.OrderDTO;
import dtos.OrderDetailDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class SearchHistoryController extends HttpServlet {
    private static final String ERROR="viewHistoryShopping.jsp";
    private static final String SUCCESS="viewHistoryShopping.jsp";
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
        String url=ERROR;
        try {
             FoodDAO dao=new FoodDAO();
            HttpSession session=request.getSession();
            String search=request.getParameter("txtSearch");
            String date=request.getParameter("txtDate");
            if(date.isEmpty()){
                request.setAttribute("MESSAGE", "select date");
            }
            else{
                String userID = "" + session.getAttribute("ID_USER");
                List<OrderDTO> list = dao.searchOrderShoppingHistory(search, date, userID);
                request.setAttribute("txtDate", date);
                request.setAttribute("HISTORY_SHOPPING", list);
                List<OrderDetailDTO> listDetail=dao.searchShoppingHistory(search, date, userID);
                request.setAttribute("HISTORY_SHOPPING_DETAIL", listDetail);
            }

            url=SUCCESS;  

            url=SUCCESS;        
        } catch (Exception e) {
            log("Error at ViewShoppingController: "+e.toString());
        }
        finally{
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
