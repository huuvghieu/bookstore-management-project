/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.products;

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
public class ProductDAO {

    private static final String SEE_ALL = "SELECT productID, name, price, quantity, img, categoryID FROM tblProduct";
    private static final String CATEGORY = "SELECT categoryID, name FROM tblCategory";
    private static final String CATEGORYID = "SELECT productID, name, price, quantity, img, categoryID FROM tblProduct WHERE categoryID=?";
    private static final String SEARCH_PRO = "SELECT productID, name, price, quantity, img, categoryID FROM tblProduct WHERE name LIKE ?";
    private static final String GET_proID = "SELECT productID, name, price, quantity, img, categoryID FROM tblProduct WHERE productID=?";

    public List<ProductDTO> getListProduct(String searchpro) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_PRO);
                ptm.setString(1, "%" + searchpro + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String produtctID = rs.getString("productID");
                    String name = rs.getString("name");
                    double price = Double.parseDouble(rs.getString("price"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    String img = rs.getString("img");
                    CategoryDTO categoryDTO = new CategoryDTO();
                    String categoryID = rs.getString("categoryID");
                    categoryDTO.setCategoryID(categoryID);
                    list.add(new ProductDTO(produtctID, name, price, quantity, categoryDTO, img));
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
        return list;
    }

    public List<ProductDTO> getAllProducts() throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEE_ALL);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String produtctID = rs.getString("productID");
                    String name = rs.getString("name");
                    double price = Double.parseDouble(rs.getString("price"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    String img = rs.getString("img");
                    String categoryID = rs.getString("categoryID");
                    CategoryDTO categoryDTO = new CategoryDTO();
                    categoryDTO.setCategoryID(categoryID);
                    list.add(new ProductDTO(produtctID, name, price, quantity, categoryDTO, img));
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
        return list;
    }

    public List<CategoryDTO> getAllCategory() throws SQLException {
        List<CategoryDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CATEGORY);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String categoryID = rs.getString("categoryID");
                    String name = rs.getString("name");
                    list.add(new CategoryDTO(categoryID, name));
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
        return list;
    }

    public List<ProductDTO> getProductsbyCID(String categoryID) throws SQLException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CATEGORYID);
                ptm.setString(1, categoryID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String produtctID = rs.getString("productID");
                    String name = rs.getString("name");
                    double price = Double.parseDouble(rs.getString("price"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    String img = rs.getString("img");
                    CategoryDTO categoryDTO = new CategoryDTO();
                    categoryDTO.setCategoryID(categoryID);                    
                    list.add(new ProductDTO(produtctID, name, price, quantity, categoryDTO, img));
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
        return list;
    }

}
