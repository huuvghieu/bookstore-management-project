/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.orders.OrderDTO;
import sample.products.Cart;
import sample.orders.OrderDAO;
import sample.orders.OrderDetailDTO;
import sample.orders.SendMail;
import sample.products.ProductDAO;
import sample.products.ProductDTO;
import sample.users.UserDAO;
import sample.users.UserDTO;

/**
 *
 * @author ownhi
 */
public class PurchaseController extends HttpServlet {

    private static final String ERROR = "userOrder.jsp";
    private static final String SUCCESS = "purchasePage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            //check login
            if (loginUser == null) {
                url = ERROR;
            } else if (session != null) {
                Cart cart = (Cart) session.getAttribute("CART");
                if (cart == null) {
                    cart = new Cart();
                    request.setAttribute("MESSAGE", "Your order is empty!!!");
                    url = ERROR;
                }

                OrderDAO dao = new OrderDAO();
                List<ProductDTO> listProducts = (List<ProductDTO>) session.getAttribute("LIST_SEEALLPRO");
                //addOrder
                String userID = loginUser.getUserID();
                LocalDate curDate = java.time.LocalDate.now();
                String date = curDate.toString();
                double total = Double.parseDouble(request.getParameter("total"));
                OrderDTO order = new OrderDTO(userID, date, total);
                boolean checkorder = dao.addOrder(order);
                //sendMail
                String subject = "Your order has been processing.";

                //addOrderDetail and updateQuantity
                boolean checkdetail = false;
                boolean checkQuantity = false;
                int orderID = dao.getOrderID();
                String msg = "";
                for (ProductDTO proCart : cart.getCart().values()) {
                    for (ProductDTO pro : listProducts) {
                        if (proCart.getProductID().equals(pro.getProductID())) {
                            int newQty = pro.getQuantity() - proCart.getQuantity();
                            checkQuantity = dao.updateQuantity(proCart.getProductID(), newQty);
                            checkdetail = dao.addOrderDetail(orderID, proCart);
                        }
                    }
                    msg = "<!DOCTYPE html>\n"
                            + "<html lang=\"en\">\n"
                            + "\n"
                            + "<head>\n"
                            + "</head>\n"
                            + "\n"
                            + "<body>\n"
                            + "    <h3 style=\"color: blue;\">Your order has been processing.</h3>\n"
                            + "   Full Name: " + loginUser.getFullName() + ", "
                            + "   User ID: " + loginUser.getUserID() + "\n"
                            + "    <div> Your order detail : </div>"
                            + "    Product Name : " + proCart.getName() + ", quantity: " + proCart.getQuantity() + ", price:" + proCart.getPrice() + ", "
                            + "    Total: " + total + "\n"
                            + "    <h3 style=\"color: blue;\">Thank you very much!</h3>\n"
                            + "\n"
                            + "</body>\n"
                            + "\n"
                            + "</html>";
                }

                if (checkorder && checkdetail && checkQuantity) {
                    url = SUCCESS;
                    request.setAttribute("MESSAGE_PURCHASE", "THANK YOU FOR YOUR PURCHASE!");
                    SendMail.send(loginUser.getEmail(), subject, msg, "hieuvhse161967@fpt.edu.vn", "0923817367Hieu04");
                } else {
                    url = ERROR;
                    request.setAttribute("MESSAGE_PURCHASE", "PURCHASE FAILED!");
                }

            }
        } catch (Exception e) {
            log("Error at PurchaseController: " + e.toString());
        } finally {
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
