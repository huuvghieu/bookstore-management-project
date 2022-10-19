/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.orders;

/**
 *
 * @author ownhi
 */
public class OrderDTO {
    private String userID;
    private String date;
    private double total;

    public OrderDTO() {
        this.userID = "";
        this.date = "";
        this.total = 0;
    }

    public OrderDTO(String userID, String date, double total) {
        this.userID = userID;
        this.date = date;
        this.total = total;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
