/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.orders;

import sample.products.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.servlet.http.HttpSession;
import sample.utils.DBUtils;

/**
 *
 * @author ownhi
 */
public class OrderDAO {

    private static final String INSERT_ORDER = "INSERT INTO tblOrder(userID, [date], total) VALUES (?,?,?)";
    private static final String GET_ORDERID = "SELECT TOP 1 orderID FROM tblOrder ORDER BY orderID DESC";
    private static final String INSERT_ORDER_DETAIL = "INSERT INTO tblOrderDetail(orderID, productID, price, quantity) VALUES (?,?,?,?)";
    private static final String UPDATE= "UPDATE tblProduct SET quantity=? WHERE productID=?";

    public boolean addOrder(OrderDTO order) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_ORDER);
                ptm.setString(1, order.getUserID());
                ptm.setString(2, order.getDate());
                ptm.setString(3, String.valueOf(order.getTotal()));
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        }finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    public int getOrderID() throws SQLException, ClassNotFoundException{
        int orderID=0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm=conn.prepareStatement(GET_ORDERID);
                rs=ptm.executeQuery();
                while (rs.next()) {   
                    int odID= Integer.parseInt(rs.getString("orderID"));
                    orderID= odID;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return orderID;
    }
    public boolean updateQuantity(String productID,int quantity) throws ClassNotFoundException, SQLException{
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm= conn.prepareStatement(UPDATE);
                ptm.setString(1, String.valueOf(quantity));
                ptm.setString(2, productID);
                check= ptm.executeUpdate() >0?true:false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;        
    }
    
    public boolean addOrderDetail(int orderID,ProductDTO pro) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_ORDER_DETAIL);
                ptm.setString(1, String.valueOf(orderID));
                ptm.setString(2, pro.getProductID());
                ptm.setString(3, String.valueOf(pro.getPrice()));
                ptm.setString(4, String.valueOf(pro.getQuantity()));
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
