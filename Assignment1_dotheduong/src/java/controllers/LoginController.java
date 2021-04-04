/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.UserDAO;
import dtos.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {
    private static final String SUCCESS="shopping.jsp";
    private static final String SUCCESS_ADMIN="management.jsp";
    private static final String INVALID="invalid.html";
    private static final String ERROR="login.jsp";
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
            String userID=request.getParameter("txtUserID");
            String password=request.getParameter("txtPassword");
            if(userID==null || userID.isEmpty()){
                request.setAttribute("errorUserID", "UserID not empty");
                
            }
            if(password==null || password.isEmpty()){
                 request.setAttribute("errorUserPassword", "Password not empty");
            }          
            if (userID != null && password != null && !userID.isEmpty() && !password.isEmpty()) {
                UserDAO dao = new UserDAO();
                UserDTO user = dao.checkLogin(userID, password);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_USER", user);
                    session.setAttribute("ID_USER", userID);
                    if (user.getRoleID().equals("AD")) {
                        url = SUCCESS_ADMIN;
                    } else if ("US".equals(user.getRoleID())) {
                        session.setAttribute("ADDRESS_USER", user.getAddress());
                        url = SUCCESS;
                    }
                }else{
                    url=INVALID;
                }
            }
        } catch (Exception e) {
            log("Error at LoginServlet: "+e.toString());
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
