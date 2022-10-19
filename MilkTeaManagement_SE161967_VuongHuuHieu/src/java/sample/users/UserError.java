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
public class UserError {
    private String userID;
    private String password;
    private String roleID;
    private String fullName;
    private String confirmpass;
    private String Email;
    private String address;
    private int status;

    public UserError() {
        this.userID = "";
        this.password = "";
        this.roleID = "";
        this.fullName = "";
        this.confirmpass = "";
        this.Email = "";
        this.address = "";
        this.status=0;
    }

    public UserError(String userID, String password, String roleID, String fullName, String confirmpass, String Email, String address, int status) {
        this.userID = userID;
        this.password = password;
        this.roleID = roleID;
        this.fullName = fullName;
        this.confirmpass = confirmpass;
        this.Email = Email;
        this.address = address;
        this.status  = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getConfirmpass() {
        return confirmpass;
    }

    public void setConfirmpass(String confirmpass) {
        this.confirmpass = confirmpass;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
