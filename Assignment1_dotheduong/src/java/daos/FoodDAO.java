/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.FoodDTO;
import dtos.OrderDTO;
import dtos.OrderDetailDTO;
import dtos.TypeDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class FoodDAO {
    public List<FoodDTO> getListFoodForSearchInUserLimit(String roomNameForSearch,Float price,String typeForSearch,int page) throws SQLException{
        List<FoodDTO> result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select foodID,foodName,img,amount,price,description,F.typeID,createDate\n" +
                            "from tblFood F,tblCategory C\n" +
                            "where F.typeID=C.typeID and F.foodName like ? and amount>=1 \n" +
                            "	and F.status=1 and price >=? and typeName like ? order by createDate\n"
                            +"offset (? - 1)*20 rows fetch next 20 rows only";
                stm=conn.prepareStatement(sql);
                stm.setString(1, "%"+roomNameForSearch+"%");
                stm.setFloat(2, price);
                stm.setString(3, "%"+typeForSearch+"%");
                stm.setInt(4, page);
                rs=stm.executeQuery();
                while(rs.next()){
                    String foodID=rs.getString("foodID");
                    String foodName=rs.getString("foodName");
                    String img=rs.getString("img");
                    Float foodPrice=rs.getFloat("price");
                    int amount=rs.getInt("amount");
                    String description=rs.getString("description");
                    Date createDate=rs.getDate("createDate");
                    String type=rs.getString("typeID");
                    if(result==null){
                        result=new ArrayList<>();
                    }
                    result.add(new FoodDTO(foodID, foodName, img, amount, foodPrice, description, type, createDate,true));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public List<FoodDTO> getListFoodForSearchInAdminLimit(String roomNameForSearch,Float price,String typeForSearch,int page) throws SQLException{
        List<FoodDTO> result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select foodID,foodName,img,amount,price,description,F.typeID,createDate,F.status\n" +
                        "from tblFood F,tblCategory C\n" +
                        "where F.typeID=C.typeID and F.foodName like ? and price >=? and typeName like ? order by createDate\n"
                            +"offset (? - 1)*20 rows fetch next 20 rows only";
                stm=conn.prepareStatement(sql);
                stm.setString(1, "%"+roomNameForSearch+"%");
                stm.setFloat(2, price);
                stm.setString(3, "%"+typeForSearch+"%");
                stm.setInt(4, page);
                rs=stm.executeQuery();
                while(rs.next()){
                  String foodID=rs.getString("foodID");
                    String foodName=rs.getString("foodName");
                    String img=rs.getString("img");
                    int amount=Integer.parseInt(rs.getString("amount"));
                    Float foodPrice=Float.parseFloat(rs.getString("price"));
                    String description=rs.getString("description");
                    Date createDate=rs.getDate("createDate");
                    String type=rs.getString("typeID");
                    Boolean status=rs.getBoolean("status");
                    if(result==null){
                        result=new ArrayList<>();
                    }
                    result.add(new FoodDTO(foodID, foodName, img, amount, foodPrice, description, type, createDate,status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public List<TypeDTO> getListType() throws SQLException{
        List<TypeDTO> result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select typeID,typeName\n" +
                            "from tblCategory";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                while(rs.next()){
                    if(result==null){
                        result=new ArrayList<>();
                    }
                    String typeID=rs.getString("typeID");
                    String typeName=rs.getString("typeName");
                    result.add(new TypeDTO(typeID, typeName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public void createRoom(FoodDTO food) throws SQLException, ClassNotFoundException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="insert into tblFood(foodID,foodName,img,amount,price,description,createDate,typeID,status)\n" +
                            "values (?,?,?,?,?,?,?,?,1)";
                stm=conn.prepareStatement(sql);
                stm.setString(1, food.getFoodID());
                stm.setString(2, food.getFoodName());
                stm.setString(3, food.getImg());
                stm.setInt(4, food.getAmount());
                stm.setFloat(5, food.getPrice());
                stm.setString(6, food.getDescription());
                stm.setDate(7, food.getCreateDate());
                stm.setString(8, food.getType());
                stm.executeUpdate();
            }  
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    public void updateFood(FoodDTO food) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="update tblFood\n" +
                            "set foodName=?,amount=?,price=?,description=?,typeID=?,createDate=?,status=?,img=?\n" +
                            "where foodID=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, food.getFoodName());
                stm.setInt(2, food.getAmount());
                stm.setFloat(3, food.getPrice());
                stm.setString(4, food.getDescription());
                stm.setString(5, food.getType());
                stm.setDate(6, food.getCreateDate());
                stm.setBoolean(7, food.isStatus());
                stm.setString(8, food.getImg());
                stm.setString(9, food.getFoodID());
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    public void deleteFood(String foodID) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="update tblFood\n" +
                            "set status=0\n" +
                            "where foodID=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1,foodID);
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    public int getCountFoodForAdmin(String roomNameForSearch,Float price,String typeForSearch) throws SQLException {
        int result = -1;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select COUNT(foodID) AS num\n" +
                            "from tblFood F,tblCategory C\n" +
                            "where F.typeID=C.typeID and F.foodName like ?\n" +
                            "and price >=? and typeName like ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%"+roomNameForSearch+"%");
                stm.setFloat(2, price);
                stm.setString(3, "%"+typeForSearch+"%");
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("num");
                    if (result >= 0) {
                        return result;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public int getCountFoodForUser(String roomNameForSearch,Float price,String typeForSearch) throws SQLException {
        int result = -1;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select COUNT(foodID) AS num\n" +
                        "from tblFood F,tblCategory C\n" +
                        "where F.typeID=C.typeID and F.foodName like ? and amount>=1\n" +
                        "	and F.status=1 and price >=? and typeName like ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%"+roomNameForSearch+"%");
                stm.setFloat(2, price);
                stm.setString(3, "%"+typeForSearch+"%");
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("num");
                    if (result >= 0) {
                        return result;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public int getCountOrder() throws SQLException {
        int result = -1;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "select COUNT(orderID) AS num\n"
                        + "from tblOrder ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("num");
                    if (result >= 0) {
                        return result + 1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public int getCountOrderDetail() throws SQLException{
        int result=-1;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select COUNT(detailID) AS num\n" +
                            "from tblOrderDetail ";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                if(rs.next()){
                    result=rs.getInt("num");
                    if(result>=0){
                        return result+1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public int getCountRecordUpdate() throws SQLException{
        int result=-1;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select COUNT(recordID) AS num\n" +
                            "from tblRecordUpdate";
                stm=conn.prepareStatement(sql);
                rs=stm.executeQuery();
                if(rs.next()){
                    result=rs.getInt("num");
                    if(result>=0){
                        return result+1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public void insertOrder(OrderDTO dto) throws SQLException, ClassNotFoundException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="insert into tblOrder\n" +
                            "values (?,?,?,1,?,?)";
                stm=conn.prepareStatement(sql);
                stm.setString(1, dto.getOrderID());
                stm.setString(2, dto.getUserID());
                stm.setFloat(3, dto.getTotalPrice());
                stm.setString(4, dto.getDate());
                stm.setString(5, dto.getAddressDelivery());
                stm.executeUpdate();
            }  
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    public void insertRecordUpdate(String recordID,String userID,String foodID,String dateUpdate) throws SQLException, ClassNotFoundException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="insert into tblRecordUpdate\n" +
                            "values (?,?,?,?)";
                stm=conn.prepareStatement(sql);
                stm.setString(1, recordID);
                stm.setString(2, userID);
                stm.setString(3, foodID);
                stm.setString(4, dateUpdate);
                stm.executeUpdate();
            }  
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    public void insertOrderDetail(OrderDetailDTO dto) throws SQLException, ClassNotFoundException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="insert into tblOrderDetail\n" +
                            "values (?,?,?,?,?,1)";
                stm=conn.prepareStatement(sql);
                stm.setString(1, dto.getDetailID());
                stm.setString(2, dto.getOrderID());
                stm.setString(3, dto.getFoodID());
                stm.setFloat(4, dto.getAmount());
                stm.setFloat(5, dto.getPrice());
                stm.executeUpdate();
            }  
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    public void updateQuanlity(String foodID,int amount) throws SQLException, ClassNotFoundException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="update tblFood\n" +
                            "set amount=amount-?\n" +
                            "where foodID=?";
                stm=conn.prepareStatement(sql);
                stm.setInt(1, amount);
                stm.setString(2, foodID);
                stm.executeUpdate();
            }  
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    public boolean checkAvailable(String foodID,int amount) throws SQLException, ClassNotFoundException{
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select foodName\n" +
                        "from tblFood\n" +
                        "where foodID=? and amount>=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, foodID);
                stm.setInt(2, amount);
             
                rs=stm.executeQuery();
                if(rs.next()){
                   
                    return true;
                }
            }  
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return false;
    }
    /*public List<OrderDetailDTO> searchShoppingHistory(String foodNameForSearch,String date,String userID) throws SQLException{
        List<OrderDetailDTO> result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select F.foodName,OD.quantity,OD.price,O.orderDate\n" +
                        "from tblUser U,tblOrder O,tblOrderDetail OD,tblFood F\n" +
                        "where U.userId=O.userID and O.orderID=OD.orderID and OD.foodID=F.foodID\n" +
                        "	and F.foodName like ? and O.orderDate=? and U.userId=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, "%"+foodNameForSearch+"%");
                stm.setString(2, date);
                stm.setString(3, userID);
                rs=stm.executeQuery();
                while(rs.next()){
                    if(result==null){
                        result=new ArrayList<>();
                    }
                    String foodName=rs.getString("foodName");
                    String amount=rs.getString("quantity");
                    String price=rs.getString("price");
                    String orderDate=rs.getString("orderDate");
                    OrderDetailDTO ord=new OrderDetailDTO("", "", foodName, Integer.parseInt(amount), Float.parseFloat(price));
                    ord.setOrderDate(orderDate);
                    result.add(ord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public List<OrderDetailDTO> getShoppingHistory(String userID) throws SQLException{
        List<OrderDetailDTO> result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select F.foodName,OD.quantity,OD.price,O.orderDate\n" +
                        "from tblUser U,tblOrder O,tblOrderDetail OD,tblFood F\n" +
                        "where U.userId=O.userID and O.orderID=OD.orderID and OD.foodID=F.foodID \n" +
                        "	and U.userId=? order by O.orderDate";
                stm=conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs=stm.executeQuery();
                while(rs.next()){
                    if(result==null){
                        result=new ArrayList<>();
                    }
                    String foodName=rs.getString("foodName");
                    String amount=rs.getString("quantity");
                    String price=rs.getString("price");
                    String orderDate=rs.getString("orderDate");
                    OrderDetailDTO ord=new OrderDetailDTO("", "", foodName, Integer.parseInt(amount), Float.parseFloat(price));
                    ord.setOrderDate(orderDate);
                    result.add(ord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }*/
    public List<OrderDTO> searchOrderShoppingHistory(String foodNameForSearch,String date,String userID) throws SQLException{
        List<OrderDTO> result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select distinct O.orderID,O.userID,O.totalPrice,O.orderDate,O.addressDelivery\n" +
                            "from tblUser U,tblOrder O,tblOrderDetail OD,tblFood F\n" +
                            "where U.userId=O.userID and O.orderID=OD.orderID and OD.foodID=F.foodID and O.status=1\n" +
                            "and F.foodName like ? and O.orderDate=? and U.userId=?\n" +
                            "order by O.orderDate ";
                stm=conn.prepareStatement(sql);
                stm.setString(1, "%"+foodNameForSearch+"%");
                stm.setString(2, date);
                stm.setString(3, userID);
                rs=stm.executeQuery();
                while(rs.next()){
                    if(result==null){
                        result=new ArrayList<>();
                    }
                    String orderID=rs.getString("orderID");
                    String userIDOfOrder=rs.getString("userID");
                    String addressDelivery=rs.getString("addressDelivery");
                    String totalPrice=rs.getString("totalPrice");
                    String orderDate=rs.getString("orderDate");
                    OrderDTO ord=new OrderDTO(orderID, userID, Float.parseFloat(totalPrice), orderDate, addressDelivery);
                   
                    result.add(ord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public List<OrderDTO> getOrderShoppingHistory(String userID) throws SQLException{
        List<OrderDTO> result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select O.orderID,O.userID,O.totalPrice,O.orderDate,O.addressDelivery\n" +
                            "from tblUser U,tblOrder O\n" +
                            "where U.userId = ?  and U.userId=O.userID and O.status=1\n" +
                            "order by O.orderDate ";
                stm=conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs=stm.executeQuery();
                while(rs.next()){
                    if(result==null){
                        result=new ArrayList<>();
                    }
                    String orderID=rs.getString("orderID");
                    String userIDOfOrder=rs.getString("userID");
                    String addressDelivery=rs.getString("addressDelivery");
                    String totalPrice=rs.getString("totalPrice");
                    String orderDate=rs.getString("orderDate");
                    OrderDTO ord=new OrderDTO(orderID, userID, Float.parseFloat(totalPrice), orderDate, addressDelivery);
                    result.add(ord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public List<OrderDetailDTO> searchShoppingHistory(String foodNameForSearch,String date,String userID) throws SQLException{
        List<OrderDetailDTO> result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select F.foodName,OD.quantity,OD.price,O.orderDate,OD.detailID,OD.orderID\n" +
                        "from tblUser U,tblOrder O,tblOrderDetail OD,tblFood F\n" +
                        "where U.userId=O.userID and O.orderID=OD.orderID and OD.foodID=F.foodID\n" +
                        "	and F.foodName like ? and O.orderDate=? and U.userId=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1, "%"+foodNameForSearch+"%");
                stm.setString(2, date);
                stm.setString(3, userID);
                rs=stm.executeQuery();
                while(rs.next()){
                    if(result==null){
                        result=new ArrayList<>();
                    }
                    String foodName=rs.getString("foodName");
                    String detailID=rs.getString("detailID");
                    String orderID=rs.getString("orderID");
                    String amount=rs.getString("quantity");
                    String price=rs.getString("price");
                    String orderDate=rs.getString("orderDate");
                    OrderDetailDTO ord=new OrderDetailDTO(detailID, orderID, foodName, Integer.parseInt(amount), Float.parseFloat(price));
                    ord.setOrderDate(orderDate);
                    result.add(ord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public List<OrderDetailDTO> getShoppingHistory(String userID) throws SQLException{
        List<OrderDetailDTO> result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select F.foodName,OD.quantity,OD.price,O.orderDate,OD.detailID,OD.orderID\n" +
                        "from tblUser U,tblOrder O,tblOrderDetail OD,tblFood F\n" +
                        "where U.userId=O.userID and O.orderID=OD.orderID and OD.foodID=F.foodID \n" +
                        "	and U.userId=? order by O.orderDate";
                stm=conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs=stm.executeQuery();
                while(rs.next()){
                    if(result==null){
                        result=new ArrayList<>();
                    }
                    String foodName=rs.getString("foodName");
                    String detailID=rs.getString("detailID");
                    String orderID=rs.getString("orderID");
                    String amount=rs.getString("quantity");
                    String price=rs.getString("price");
                    String orderDate=rs.getString("orderDate");
                    OrderDetailDTO ord=new OrderDetailDTO(detailID, orderID, foodName, Integer.parseInt(amount), Float.parseFloat(price));
                    ord.setOrderDate(orderDate);
                    result.add(ord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    public static List<OrderDetailDTO> getListDetail(String orderID) throws SQLException{
        List<OrderDetailDTO> result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select OD.detailID,F.foodName,OD.quantity,OD.price,OD.orderID\n" +
                    "from tblOrderDetail OD,tblFood  F \n" +
                    "where OD.orderID=? and OD.foodID=F.foodID ";
                stm=conn.prepareStatement(sql);
                stm.setString(1, orderID);
                rs=stm.executeQuery();
                while(rs.next()){
                    if(result==null){
                        result=new ArrayList<>();
                    }
                    String foodName=rs.getString("foodName");
                    String amount=rs.getString("quantity");
                    String price=rs.getString("price");
                    String orderIDofDetail=rs.getString("orderID");
                    String detailID=rs.getString("detailID");
                    OrderDetailDTO ord=new OrderDetailDTO(detailID, orderIDofDetail, foodName, Integer.parseInt(amount), Float.parseFloat(price));
                    result.add(ord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
}
