/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class UserDAO {
    public UserDTO checkLogin(String userID,String password) throws SQLException{
        UserDTO result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select fullName,roleID,address\n" +
                            "from tblUser\n" +
                            "where userId=? and password =? and status=1";
                stm=conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs=stm.executeQuery();
                if(rs.next()){
                    String fullname=rs.getString("fullName");
                    String roleID=rs.getString("roleID");
                    String address=rs.getString("address");
                    result=new UserDTO(userID, fullname, "", roleID,address);
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
    public void createNew(UserDTO user) throws SQLException, ClassNotFoundException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="INSERT INTO tblUser(fullName, userId, roleID, password,status,address) "
                        + " VALUES(?,?,?,?,1,?)";
                stm=conn.prepareStatement(sql);
                stm.setString(1, user.getFullName());
                stm.setString(2, user.getUserID());
                stm.setString(3, user.getRoleID());
                stm.setString(4, user.getPassword());
                stm.setString(5, user.getAddress());
                stm.executeUpdate();
            }  
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
}
