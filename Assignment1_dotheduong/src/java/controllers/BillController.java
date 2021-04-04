/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.FoodDAO;
import dtos.CartDTO;
import dtos.FoodDTO;
import dtos.OrderDTO;
import dtos.OrderDetailDTO;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class BillController extends HttpServlet {
    private static final String ERROR="viewOrder.jsp";
     private static final String SUCCESS="SearchForUserController";
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
        HttpSession session = request.getSession();
        CartDTO cart = (CartDTO) session.getAttribute("CART");
        FoodDAO dao = new FoodDAO();
        try {
            String message="";
            boolean checkAvailable=true;
            for (FoodDTO dto : cart.getCart().values()) {
                boolean check=dao.checkAvailable(dto.getFoodID(), dto.getAmount());
                if(!check){
                    message+=" "+dto.getFoodName()+",";
                    checkAvailable=false;
                }
            }
            String address=request.getParameter("txtAddressDelivery");
            if(address.isEmpty() || address==null || address.equals("")){
                request.setAttribute("MESSAGE", "Address cann't empty");
                request.getRequestDispatcher(url).forward(request, response);
            } 
            else if(cart.getCart()==null){
                request.setAttribute("MESSAGE", "There isn't product in the cart");
                request.getRequestDispatcher(url).forward(request, response);
            }else if(!checkAvailable){
                request.setAttribute("MESSAGE", "There is "+message+" out of stock");
                request.getRequestDispatcher(url).forward(request, response);
            }
            else {
                Float totalPrice = Float.parseFloat(request.getParameter("txtTotal"));
                String orderDate = request.getParameter("dateOrder");
                int numOrderDetail = dao.getCountOrderDetail();
                String orderID = "O" + dao.getCountOrder();
                String userID = "" + session.getAttribute("ID_USER");
                OrderDTO order = new OrderDTO(orderID, userID, totalPrice, orderDate, address);
                dao.insertOrder(order);
                Set<String> keys = cart.getCart().keySet();
                for (String key : keys) {
                    String detailID = "OD" + numOrderDetail;
                    FoodDTO food = cart.getCart().get(key);
                    OrderDetailDTO ord = new OrderDetailDTO(detailID, orderID, food.getFoodID(), food.getAmount(), food.getPrice());
                    dao.insertOrderDetail(ord);
                    dao.updateQuanlity(food.getFoodID(), food.getAmount());
                    numOrderDetail += 1;
                    
                }
                cart.setCart(null);
                session.setAttribute("CART", cart);
                request.setAttribute("MESSAGE", "Bill successful");
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at BillServlet: "+e.toString());
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
