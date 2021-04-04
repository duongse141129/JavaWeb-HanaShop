/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.FoodDAO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class DeleteFoodController extends HttpServlet {
    private static final String ERROR="management.jsp";//search
    private static final String SUCCESS="SearchForAdminController";
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
            String foodID;
            String selete=request.getParameter("selete");
            int size=Integer.parseInt(selete);
            Date date=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String dateDelete=sdf.format(date);
            HttpSession session = request.getSession();
            boolean check=false;
            for(int i=1;i<=size;i++){
                foodID=request.getParameter("cb"+i);
                if(foodID!=null){
                    check=true;
                }
            }      
            if (check) {
                for (int i = 1; i <= size; i++) {
                    foodID = request.getParameter("cb" + i);
                    if (foodID != null) {
                        dao.deleteFood(foodID);
                        String recordID = "RU" + dao.getCountRecordUpdate();
                        String userID = "" + session.getAttribute("ID_USER");
                        dao.insertRecordUpdate(recordID, userID, foodID, dateDelete);
                    }
                }
                request.setAttribute("MESSAGE", "Delete successful");
                url=SUCCESS;
            }
            else{
                request.setAttribute("MESSAGE", "Select foods to delete");
                url=ERROR;
            }

        } catch (Exception e) {
            log("error at DeleteFoodController: "+e.toString());
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
