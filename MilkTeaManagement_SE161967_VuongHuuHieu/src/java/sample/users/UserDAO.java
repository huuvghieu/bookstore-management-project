/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author ownhi
 */
public class UserDAO {

    private static final String LOGIN = "SELECT fullName, roleID, Email, address FROM tblUser WHERE userID=? AND password=?";
    private static final String SEARCH = "SELECT userID, fullName, roleID, Email, address FROM tblUser WHERE fullName LIKE ?";
    private static final String UPDATE= "UPDATE tblUser SET fullName=? , roleID=? , Email=? , address=? WHERE userID=?";
    private static final String DELETE="DELETE tblUser WHERE userID=?";
    private static final String DUPLICATE="SELECT userID FROM tblUser WHERE userID=?";
    private static final String CREATE="INSERT INTO tblUser(userID, Email, roleID, password, fullName, address) VALUES(?,?,?,?,?,?)";
    public UserDTO checkLogin(String userID, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userID);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String fullName= rs.getString("fullName");
                    String roleID= rs.getString("roleID");
                    String Email= rs.getString("Email");
                    String address= rs.getString("address");
                    user = new UserDTO(userID, Email, roleID, password, fullName, address);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return user;
    }

    public List<UserDTO> getListUser(String search) throws SQLException {
        List<UserDTO> list= new ArrayList<>();
        Connection conn= null;
        PreparedStatement ptm= null;
        ResultSet rs= null;
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                ptm= conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs= ptm.executeQuery();
                while(rs.next()){
                    String userID= rs.getString("userID");
                    String fullname= rs.getString("fullname");
                    String roleID= rs.getString("roleID");
                    String Email= rs.getString("Email");
                    String address= rs.getString("address");
                    String password= "***";
                    list.add(new UserDTO(userID, Email, roleID, password, fullname, address));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!= null) rs.close();
            if(ptm!= null) ptm.close();
            if(conn!=null) conn.close();            
        }
        return list;
    }
    public boolean update(UserDTO user) throws SQLException{
        boolean check= false;
        Connection conn=null;
        PreparedStatement ptm= null;
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                ptm= conn.prepareStatement(UPDATE);
                ptm.setString(1, user.getFullName());
                ptm.setString(2, user.getRoleID());
                ptm.setString(3, user.getEmail());
                ptm.setString(4, user.getAddress());
                ptm.setString(5, user.getUserID());
                check= ptm.executeUpdate() >0?true:false;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
        }
        return check;
    }
    public boolean delete(String userID) throws SQLException{
        boolean result= false;
        Connection conn= null;
        PreparedStatement ptm= null;
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                ptm= conn.prepareStatement(DELETE);
                ptm.setString(1, userID);
                result= ptm.executeUpdate()>0?true:false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(ptm!= null) ptm.close();
            if(conn!= null) conn.close();
        }
        return result;
    }
    public boolean checkDuplicate(String userID) throws SQLException{
        boolean check= true;
        Connection conn= null;
        PreparedStatement ptm= null;
        ResultSet rs= null;
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                ptm= conn.prepareStatement(DUPLICATE);
                ptm.setString(1, userID);
                rs= ptm.executeQuery();
                if(rs.next()){
                    check=false;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!=null) rs.close();
            if(ptm!=null) ptm.close();
            if(conn!=null) conn.close();
            
        }
        return check;
    }

    public boolean createV2(UserDTO user) throws SQLException, ClassNotFoundException{
        boolean check= false;
        Connection conn= null;
        PreparedStatement ptm= null;
        try{
            conn= DBUtils.getConnection();
            if(conn!=null){
                 ptm= conn.prepareStatement(CREATE);
                ptm.setString(1, user.getUserID());
                ptm.setString(2, user.getEmail());
                ptm.setString(3, user.getRoleID());
                ptm.setString(4, user.getPassword());
                ptm.setString(5, user.getFullName());
                ptm.setString(6, user.getAddress());
                check= ptm.executeUpdate()>0?true:false;    
            }
        }finally{
            if(ptm!=null) ptm.close();
            if(conn!=null) ptm.close();
        }
        return check;
    }
}
