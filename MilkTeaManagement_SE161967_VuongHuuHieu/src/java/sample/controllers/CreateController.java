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
import sample.users.UserDAO;
import sample.users.UserDTO;
import sample.users.UserError;

/**
 *
 * @author ownhi
 */
public class CreateController extends HttpServlet {
    private static final String ERROR="createUser.jsp";
    private static final String SUCCESS= "login.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url= ERROR;
        UserError userError= new UserError();
        try{
            UserDAO dao= new UserDAO();
            String userID= request.getParameter("userID");
            String fullName= request.getParameter("fullName");
            String roleID= request.getParameter("roleID");
            String Email= request.getParameter("Email");
            String address= request.getParameter("address");
            String password= request.getParameter("password");
            String confirm= request.getParameter("confirmpass");
            boolean checkValidation= true;
            if(userID.length()>10 || userID.length()<3){
                userError.setUserID("Length must be in[3,10]");
                checkValidation= false;
            }
            if(fullName.length()>50 || fullName.length()<2){
                userError.setFullName("Length must be in[2,50]");
                checkValidation= false;
            }
            if(!password.equals(confirm)){
                userError.setConfirmpass("Password and Confirm are different!");
                checkValidation= false;
            }
            if(!Email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")){
                userError.setEmail("It is not an email!");
                checkValidation= false;
            }
            if(address.length()>50 || address.length()<2){
                userError.setAddress("Length must be in[2,50]");
                checkValidation= false;
            }
            //ktra data da validation xong het chua
            if(checkValidation){
                UserDTO user= new UserDTO(userID, Email, roleID, password, fullName, address);
                boolean checkCreate= dao.createV2(user);
                if(checkCreate){
                    url=SUCCESS;
                }
            }else{
                request.setAttribute("USER_ERROR", userError);
            }
        }catch(Exception e){
            log("Error at CreateController: " + e.toString());
            if(e.toString().contains("duplicate")){
                userError.setUserID("Duplicated User ID");
                request.setAttribute("USER_ERROR", userError);
            }
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
