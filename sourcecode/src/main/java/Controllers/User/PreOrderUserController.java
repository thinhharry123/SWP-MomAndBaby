/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import Authentication.AuthUser;
import DAO.AccountDAO;
import DAO.PreOrderDAO;
import Model.Account;
import Model.PreOrder;
import Utils.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author HP
 */
public class PreOrderUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PreOrderUserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PreOrderUserController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        AuthUser auth = new AuthUser();
        String username = auth.isLoginUser(request, response);
        String path = request.getRequestURI();
        if (username != null) {
            if (path.startsWith("/SWP391-MomAndBaby/preOrder/add")) {
                this.addToPreOrder(request, response, username);
            } else if (path.startsWith("/SWP391-MomAndBaby/cart/remove")) {
//            this.removeFromCart(request, response);
            }
        } else {
            response.sendRedirect("/SWP391-MomAndBaby/login");
        }
    }

    private void addToPreOrder(HttpServletRequest request, HttpServletResponse response, String username) {
        try {
            AccountDAO accountDao = new AccountDAO();
            Account accountLogin = accountDao.getAccountByUsername(username);
            if (accountLogin != null) {
                Validation validate = new Validation();
                int productId = validate.getInt(request.getParameter("productID"));
                String path = request.getParameter("pathUrl");
                int accountId = accountLogin.getID();
                PreOrderDAO preOrderDao = new PreOrderDAO();
                PreOrder p = new PreOrder(0, productId, accountId, 0);
                int result = preOrderDao.addPreOrder(p);
                response.sendRedirect(path + "?act=add-preorder&status=" + result);
            } else {
                response.sendRedirect("/SWP391-MomAndBaby/404");
            }
        } catch (Exception e) {
            System.out.println("Add to preOrder: " + e);
        }
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
