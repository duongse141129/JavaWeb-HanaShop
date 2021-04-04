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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class SaveUpdateFoodController extends HttpServlet {
    private static final String ERROR="updateFood.jsp";
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
        FoodErrorDTO errorFood=new FoodErrorDTO("", "", "", "", "", "", "","");
        FoodDAO dao=new FoodDAO();
        try {
            String imgFile=request.getParameter("imgFile");
            String foodId=request.getParameter("txtFoodId");
            String fullName=request.getParameter("txtFoodName");
            String price=request.getParameter("txtPrice");
            String amount=request.getParameter("txtAmount");
            String description=request.getParameter("description").trim(); 
            String type=request.getParameter("cmbType");
            String date=request.getParameter("txtCreateDate");
            date=date.replace("-","/");
            Date createDate=new Date(date);
            String img;
            if(imgFile==null || imgFile.equals("")){    
                img=request.getParameter("img");
            }else{
                img="image/"+imgFile;
            }
            String statusName=request.getParameter("cmbStatus");
            boolean status;
            if(statusName.equals("true")){
                status=true;
            }
            else
                status=false;
            List<TypeDTO> listType=dao.getListType();
            request.setAttribute("LIST_TYPE", listType);
            boolean check=true;
            if(fullName.isEmpty()){
                check=false;
                errorFood.setFoodNameError("Fullname is not empty !");
            }
            if(amount.isEmpty()){
                check=false;
                errorFood.setAmountError("amount isn't empty");
            }
            if(price.isEmpty()){
                check=false;
                errorFood.setPriceError("price isn't empty");
            }else{
                boolean correct = price.matches("[+]?[0-9]*\\.?[0-9]+");
                if(!correct){
                    check=false;
                    errorFood.setPriceError("price is number >=0");
                }
            }
            if(check){
                FoodDTO food=new FoodDTO(foodId, fullName, img, Integer.parseInt(amount), Float.parseFloat(price), description, type, new java.sql.Date((createDate.getTime())),status);
                dao.updateFood(food);
                String recordID = "RU" + dao.getCountRecordUpdate();
                HttpSession session = request.getSession();
                String userID = "" + session.getAttribute("ID_USER");
                dao.insertRecordUpdate(recordID, userID, foodId, date);

                request.setAttribute("MESSAGE", "Update successful");
                url=SUCCESS;
            }else{
                request.setAttribute("ERROR", errorFood);
            }
            
            
        } catch (Exception e) {
             log("Error at SaveUpdateFoodController: "+e.toString());
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
