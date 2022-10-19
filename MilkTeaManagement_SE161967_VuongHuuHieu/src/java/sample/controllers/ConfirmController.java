/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.products.Cart;
import sample.products.ProductDTO;
import sample.users.UserDTO;

/**
 *
 * @author ownhi
 */
public class ConfirmController extends HttpServlet {
    private static final String ERROR="userOrder.jsp";
    private static final String SUCCESS="purchasePage.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url= ERROR;
        try{
            HttpSession session= request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            
            //check login
            if(loginUser== null){
                url="login.jsp";
            }else{            
            if(session!=null){
                Cart cart= (Cart) session.getAttribute("CART");
                if(cart==null){
                    cart= new Cart();
                    request.setAttribute("MESSAGE_ORDER", "Your order is empty!!!");                    
                    url=ERROR;
                }
                //check quantity in stock

            ArrayList<String> message = new ArrayList<>();
            List<ProductDTO> listProducts = (List<ProductDTO>) session.getAttribute("LIST_SEEALLPRO"); 
            boolean checkQuantity=false;
                for (ProductDTO proCart : cart.getCart().values()) {
                    for (ProductDTO pro : listProducts) {
                        
                       if(proCart.getProductID().equals(pro.getProductID())){
                           if(pro.getQuantity() < proCart.getQuantity()){
                               String mess= "Quantity of "+proCart.getName()+" is not enough!("+ pro.getQuantity() +" left)";
                               message.add(mess);
                               checkQuantity=true;                                                            
                           }
                       }
                    }
                }
                if(checkQuantity){
                    request.setAttribute("MESSAGE_ORDER", message);
                    url = ERROR;
                }else{
                    url = SUCCESS;
                }
            }
            }
        }catch(Exception e){
            log("Error at ConfirmController: " + e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
