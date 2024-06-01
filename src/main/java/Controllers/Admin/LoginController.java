
package Controllers.Admin;

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


public class LoginController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("/admin/view/auth/login.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
            Account account = accountDao.login(username);
            String message = "";
            if (account == null) {
                message = "Account does not exist";
                this.goToLoginPage(request, response, message);
                return;
            }

            if (!account.getRoleName().equals("admin")) {
                message = "Account cannot log in here";
                this.goToLoginPage(request, response, message);
                return;
            }
            if (!account.getPassword().equals(password)) {
                message = "Password is not valid";
                this.goToLoginPage(request, response, message);
                return;
            }
            if (account.getStatus() != 1) {
                message = "Your account is locked";
                this.goToLoginPage(request, response, message);
                return;
            }
            RoleDAO roleDao = new RoleDAO();
            HttpSession session = request.getSession();
            String roleName = roleDao.getRoleById(account.getRole()).getName();
            String fullNameOrUsername = account.getFullname() != null ? account.getFullname() : username;
            session.setAttribute("usernameAdmin", username);
            session.setAttribute("userRole", roleName);
            response.sendRedirect("/MomAndBaby/admin");
        }
    }
    
    private void goToLoginPage(HttpServletRequest request, HttpServletResponse response, String message) {
        try {
            request.setAttribute("message", message);
            request.getRequestDispatcher("/admin/view/auth/login.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Go to login page: " + e);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
