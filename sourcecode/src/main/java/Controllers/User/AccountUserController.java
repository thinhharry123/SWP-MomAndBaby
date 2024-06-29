/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import Authentication.AuthUser;
import DAO.AccountDAO;
import DAO.BillDAO;
import DAO.BillDetailDAO;
import DAO.FeedbackDAO;
import DAO.PreOrderDAO;
import Model.Account;
import Model.Bill;
import Model.BillDetail;
import Model.Feedback;
import Model.PreOrder;
import Utils.DateCustom;
import Utils.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

public class AccountUserController extends HttpServlet {

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
            out.println("<title>Servlet AccountUserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountUserController at " + request.getContextPath() + "</h1>");
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
        AccountDAO accountDao = new AccountDAO();
        Account accountLogin = accountDao.getAccountByUsername(username);
        HttpSession session = request.getSession();
        Validation validate = new Validation();
        BillDAO billDao = new BillDAO();

        if (accountLogin != null && accountLogin.getStatus() == 1) {
            String path = request.getRequestURI();
            request.setAttribute("accountLogin", accountLogin);
            if (path.endsWith("/SWP391-MomAndBaby/account")) {
                request.getRequestDispatcher("/user/account.jsp").forward(request, response);
            } else if (path.endsWith("/SWP391-MomAndBaby/account/password")) {
                request.getRequestDispatcher("/user/password.jsp").forward(request, response);
            } else if (path.endsWith("/SWP391-MomAndBaby/account/history-order")){
                List<Bill> bills = billDao.getBillByCustomer(accountLogin.getID());
                request.setAttribute("bills", bills);
                request.getRequestDispatcher("/user/historyOrder.jsp").forward(request, response);
            }else if (path.endsWith("/SWP391-MomAndBaby/account/pre-order")) {
                PreOrderDAO preOrderDao = new PreOrderDAO();
                List<PreOrder> preOrders = preOrderDao.getAllByAccountID(accountLogin.getID());
                request.setAttribute("preOrders", preOrders);
                request.getRequestDispatcher("/user/historyPreOrder.jsp").forward(request, response);
            }
            else {
                response.sendRedirect("/SWP391-MomAndBaby/404");
            }
        } else {
            session.removeAttribute("usernameUser");
            response.sendRedirect("/SWP391-MomAndBaby/login");
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
        AuthUser auth = new AuthUser();
        String username = auth.isLoginUser(request, response);
        AccountDAO accountDao = new AccountDAO();
        Account accountLogin = accountDao.getAccountByUsername(username);
        HttpSession session = request.getSession();
        Validation validate = new Validation();
        DateCustom date = new DateCustom();

        if (accountLogin != null && accountLogin.getStatus() == 1) {
            if (request.getParameter("btn-change-info") != null) {
                String fullname = request.getParameter("fullname");
                if (!fullname.equals("")) {
                    accountLogin.setFullname(fullname);
                }
                String email = request.getParameter("email");
                boolean isValidEmail = false;
                if (!email.equals("")) {
                    Account isExistEmail = accountDao.isExistAccount("", email);
                    if (isExistEmail != null && isExistEmail.getUsername().equalsIgnoreCase(accountLogin.getUsername())) {
                        isValidEmail = true;
                    }
                }
                if (isValidEmail) {
                    int type_message = 1;
                    String phone = request.getParameter("phone");
                    if (!phone.equals("")) {
                        accountLogin.setPhone(phone);
                        int result = accountDao.updatePersonalUser(accountLogin);
                        if (result <= 0) {
                            type_message = 0;
                        }
                    }
                    response.sendRedirect("/SWP391-MomAndBaby/account?act=update-account&status=" + type_message);
                } else {
                    response.sendRedirect("/SWP391-MomAndBaby/account?act=update-account&status=2");
                }
            } else if (request.getParameter("btn-change-password") != null) {
                String newPassword = request.getParameter("new-password");
                String oldPassword = request.getParameter("old-password");
                if (accountLogin.getPassword().equals(oldPassword)) {
                    int result = accountDao.updatePassword(newPassword, accountLogin.getID());
                    response.sendRedirect("/SWP391-MomAndBaby/account/password?act=update-account&status=" + result);
                } else {
                    response.sendRedirect("/SWP391-MomAndBaby/account/password?act=update-password&status=2");
                }
            }
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
