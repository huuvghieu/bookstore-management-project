/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.users;

/**
 *
 * @author ownhi
 */
public class UserDTO {
    private String userID;
    private String Email;
    private String roleID;
    private String password;
    private String fullName;
    private String address;
    
    public UserDTO(){
        userID="";
        Email="";
        roleID="";
        password="";
        fullName="";
        address="";
    }
    public UserDTO(String userID, String Email, String roleID, String password, String fullName, String address) {
        this.userID = userID;
        this.Email = Email;
        this.roleID = roleID;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

            
}
