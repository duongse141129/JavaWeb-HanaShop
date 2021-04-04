/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author Admin
 */
public class UserDTO {
    private String userID;
    private String fullName;
    private String password;
    private String roleID;
    private String address;
    public UserDTO() {
    }

    public UserDTO(String userID, String fullName, String password, String roleID, String address) {
        this.userID = userID;
        this.fullName = fullName;
        this.password = password;
        this.roleID = roleID;
        this.address = address;
    }



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return fullName;
    }
    
    
}
