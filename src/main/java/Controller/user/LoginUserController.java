/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.user;

import DAO.AccountDAO;
import Model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class LoginUserController extends HttpServlet {

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
            out.println("<title>Servlet LoginUserController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginUserController at " + request.getContextPath() + "</h1>");
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
        //authentication
        //if(user is logged in)
        //else {
        request.getRequestDispatcher("/user/login.jsp").forward(request, response);
        //}
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
        AccountDAO accountDao = new AccountDAO();
        HttpSession session = request.getSession();
        if(request.getParameter("submitLogin")!=null){
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Account a = accountDao.login(username);
            String message = "";
            boolean isError = false;
            if(a == null && !isError){
                message = "Your account does not exist";
                isError = true;
            }
            if(!isError && !a.getRoleName().equals("user")){
                message = "Your account can not log in here";
                isError = true;
            }if(!isError && a.getStatus()==0){
                message = "Your account is blocked";
                isError = true;
            }
            if(!isError && !a.getPassword().equals(password)){
                message = "Password is not valid";
                isError = true;
            }
            if(message!= ""){
                request.setAttribute("messageUserAuth", message);
                request.getRequestDispatcher("/user/login.jsp");
                return;
            }
            session.setAttribute("usernameUser", a.getUsername());
            session.setAttribute("usernameRole", a.getRoleName());
            response.sendRedirect("/SWP391-MomAndBaby/");
        }
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
