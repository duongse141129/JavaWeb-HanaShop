/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.FoodDAO;
import dtos.FoodDTO;
import dtos.TypeDTO;
import dtos.UserDTO;
import java.io.IOException;
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
public class SearchForAdminController extends HttpServlet {
    private static final String ERROR="management.jsp";
    private static final String SUCCESS="management.jsp";
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
            Float price=Float.parseFloat(request.getParameter("price"));
            String type=request.getParameter("txtType");
            String name=request.getParameter("txtSearch");
            int count = dao.getCountFoodForAdmin(name, price, type);
            int totalPage=0;
            if (count % 20 != 0) {
                totalPage = (count / 20) + 1;
            } else {
                totalPage = (count / 20);
            }
            request.setAttribute("PAGE", totalPage);
            String pageS = request.getParameter("pageNum");
            int page = 1;
            if (pageS != null) {
                page = Integer.parseInt(pageS);
            }
            List<FoodDTO> list=dao.getListFoodForSearchInAdminLimit(name, price, type,page);
            List<TypeDTO> listType=dao.getListType();
            if(list!=null){
                HttpSession session = request.getSession();
                UserDTO user=(UserDTO) session.getAttribute("LOGIN_USER");
                
                if(user.getRoleID().equals("AD")){
                    url=SUCCESS;
                    request.setAttribute("LIST", list);
                    request.setAttribute("LIST_TYPE", listType);
                }
            }
            else{               
                request.setAttribute("MESSAGE", "Not found");
            }
        } catch (Exception e) {
            log("Error at SearchAdminServlet: "+e.toString());
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
