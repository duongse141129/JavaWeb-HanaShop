/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dtos.CartDTO;
import dtos.FoodDTO;
import dtos.UserDTO;
import java.io.IOException;
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
public class AddToCartController extends HttpServlet {
    private final static String ERROR="shopping.jsp";
    private final static String SUCCESS="SearchForUserController";
    private final static String LOGIN="login.jsp";
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
        HttpSession session=request.getSession();
        UserDTO user=(UserDTO) session.getAttribute("LOGIN_USER");
        if(user==null){
            url=LOGIN;
            request.getRequestDispatcher(url).forward(request, response);
        }
        try {                  
            String foodId=request.getParameter("txtFoodID");
            String fullName=request.getParameter("txtFoodName");
            String price=request.getParameter("txtPrice");
            String amount=request.getParameter("numToCart");
            String description=request.getParameter("description"); 
            String type=request.getParameter("txtTypeID");
            String date=request.getParameter("txtCreateDate");
            date=date.replace("-","/");
            Date createDate=new Date(date);
            String img=request.getParameter("img");                 
            CartDTO cart=(CartDTO) session.getAttribute("CART");
            FoodDTO food=new FoodDTO(foodId, fullName, img, Integer.parseInt(amount), Float.parseFloat(price), description, type, new java.sql.Date((createDate.getTime())),true);
            String userName=""+session.getAttribute("LOGIN_USER");
            if(cart==null){
                cart=new CartDTO(userName, null);              
            }
            cart.add(food);
            session.setAttribute("CART", cart);
            session.setAttribute("CART_DETAIL", cart.getCart().values());
            request.setAttribute("MESSAGE", "you have bought :"+fullName+" successful. You have "+cart.getCart().values().size() +" Food");
            url=SUCCESS;
            
        } catch (Exception e) {
            log("Error at AddToCartServlet "+e.toString());
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
