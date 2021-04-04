/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import daos.FoodDAO;
import dtos.FoodDTO;
import dtos.FoodErrorDTO;
import dtos.TypeDTO;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class AddNewFoodController extends HttpServlet {
    private static final String ERROR="addFood.jsp";
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
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String url=ERROR;
        FoodErrorDTO errorFood=new FoodErrorDTO("", "", "", "", "", "", "","");
        FoodDAO dao=new FoodDAO();
        try {
            List<TypeDTO> listType=dao.getListType();
            request.setAttribute("LIST_TYPE",listType);  
            String foodId=request.getParameter("txtFoodId");
            String fullName=request.getParameter("txtFoodName");         
            Float price = null;
            String p=request.getParameter("txtPrice");
            String description=request.getParameter("description"); 
            String type=request.getParameter("cmbType"); 
            String img=request.getParameter("img");
            Date d=new Date();
            boolean check=true;
            if(foodId.isEmpty()){
                check=false;
                errorFood.setFoodIDError("FoodID is not empty !");
            }
            if(fullName.isEmpty()){
                check=false;
                errorFood.setFoodNameError("Fullname is not empty !");
            }
            if(request.getParameter("txtAmount").isEmpty()){
                check=false;
                errorFood.setAmountError("amount isn't empty");
            }
            if(p.isEmpty()){
                check=false;
                errorFood.setPriceError("price isn't empty");
            }else{
                boolean correct = p.matches("[+]?[0-9]*\\.?[0-9]+");
                if(correct){
                    price=Float.parseFloat(p);
                }else{
                    check=false;
                    errorFood.setPriceError("price is number >=0");
                }
            }
            if(check){
                String imgPath="image/"+img;
                int amount=Integer.parseInt(request.getParameter("txtAmount"));
                FoodDTO food=new FoodDTO(foodId, fullName, imgPath, amount, price, description, type, new java.sql.Date((d.getTime())),true);
                dao.createRoom(food);
                url=SUCCESS;
            }else{
                request.setAttribute("ERROR", errorFood);
            }
            
            
        } catch (Exception e) {
            if(e.toString().contains("duplicate")){
                errorFood.setFoodIDError("FoodID is duplicate !");
                request.setAttribute("ERROR", errorFood);
            }
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
