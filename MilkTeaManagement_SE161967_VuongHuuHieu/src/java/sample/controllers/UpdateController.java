/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.users.UserDAO;
import sample.users.UserDTO;

/**
 *
 * @author ownhi
 */
public class UpdateController extends HttpServlet {
    private static final String ERROR="adminPage.jsp";
    private static final String SUCCESS="SearchController";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url= ERROR;
        try{
            String userID= request.getParameter("userID");
            String fullName= request.getParameter("fullName");
            String roleID= request.getParameter("roleID");
            String Email= request.getParameter("Email");
            String address= request.getParameter("address");
            UserDAO dao= new UserDAO();
            HttpSession session= request.getSession();
            UserDTO loginUser= (UserDTO) session.getAttribute("LOGIN_USER");
            
            boolean checkUpdate= dao.update(new UserDTO(userID, Email, roleID, "", fullName, address));
            if(checkUpdate){
                if(loginUser.getUserID().equals(userID)){
                    loginUser.setFullName(fullName);
                    loginUser.setRoleID(roleID);
                    loginUser.setEmail(Email);
                    loginUser.setAddress(address);
                    session.setAttribute("LOGIN_USER", loginUser);
                }
                url= SUCCESS;
            }
        }catch(Exception e){
            log("Error at UpdateController: "+ e.toString());
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
