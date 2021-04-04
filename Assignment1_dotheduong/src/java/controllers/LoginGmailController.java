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
public class LoginGmailController extends HttpServlet {
    private static final String SUCCESS="shopping.jsp";
    private static final String ERROR="invalid.html";
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
            String fullName=request.getParameter("txtFullName");
            String email=request.getParameter("txtEmail");
            userID=userID.substring(0, 9);
            UserDAO dao=new UserDAO();
            if(!userID.isEmpty() && !fullName.isEmpty() && !email.isEmpty()){
               UserDTO user=dao.checkLogin(userID, "");
               HttpSession session = request.getSession();
               if(user==null){
                   user=new UserDTO(userID, fullName, "", "UG","");
                   dao.createNew(user);
                   session.setAttribute("LOGIN_USER", user);
                    session.setAttribute("ID_USER", userID);
                    session.setAttribute("ADDRESS_USER", user.getAddress());
                    url = SUCCESS;
               }
               else{
                    session.setAttribute("LOGIN_USER", user);
                    session.setAttribute("ID_USER", userID);
                    session.setAttribute("ADDRESS_USER", user.getAddress());
                    url = SUCCESS;
               }
            }

        } catch (Exception e) {
            log("Error at LoginGmailServlet: "+e.toString());
        }finally{
            response.sendRedirect(url);
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
