/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.orders;

import sample.products.*;

/**
 *
 * @author ownhi
 */
public class OrderDetailDTO {
    private int orderID;
    private double price;
    private String productID;
    private int quantity;

    public OrderDetailDTO(int orderID, double price, String productID, int quantity) {
        this.orderID = orderID;
        this.price = price;
        this.productID = productID;
        this.quantity = quantity;
    }

    public OrderDetailDTO() {
        this.orderID = 0;
        this.price = 0;
        this.productID = "";
        this.quantity = 0;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    
}
