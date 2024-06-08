/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Staff;

import DAO.AccountDAO;
import DAO.RoleDAO;
import Model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
public class LoginStaffController extends HttpServlet {

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
            out.println("<title>Servlet LoginStaffController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginStaffController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/staff/view/auth/login.jsp").forward(request, response);
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
        if (request.getParameter("login") != null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            AccountDAO accountDao = new AccountDAO();
            Account a = accountDao.login(username);
            String message = "";
            if(a == null){
                message="Account does not exist";
                this.goToLoginPage(request, response, message);
                return ;
            
            }
            if(!a.getRoleName().equals("staff")&&!a.getRoleName().equals("admin")){
                message="Account cannot log in here";
                this.goToLoginPage(request, response, message);
                return;
                
            }if(!a.getPassword().equals(password)){
                message="Password is not valid";
                this.goToLoginPage(request, response, message);
                return;
            }if(a.getStatus()!=1){
                message="Your account is lock";
                this.goToLoginPage(request, response, message);
                return;
            }
                        RoleDAO roleDao = new RoleDAO();
            HttpSession session = request.getSession();
            String roleName = roleDao.getRoleById(a.getRole()).getName();
            String fullNameOrUsername = a.getFullname() != null ? a.getFullname() : username;
            session.setAttribute("usernameStaff", username);
            session.setAttribute("userRoleStaff", "staff");
            response.sendRedirect("/SWP391-MomAndBaby/staff");
        }
    }

    
    private void goToLoginPage(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, String message) {
        try {
            request.setAttribute("message", message);
            request.getRequestDispatcher("/staff/view/auth/login.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Go to login page: " + e);
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
