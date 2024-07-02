/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import DAO.AccountDAO;
import DAO.BillDAO;
import Model.Account;
import Model.Bill;
import Utils.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class UserGetPointController extends HttpServlet {

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
            out.println("<title>Servlet UserGetPointController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserGetPointController at " + request.getContextPath() + "</h1>");
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
        Validation validate = new Validation();
        BillDAO billDao = new BillDAO();
        int idBill = validate.getInt(request.getParameter("idBill"));
        Bill currentBill = billDao.getBillById(idBill);
        int idCustomer = currentBill.getCustomerID();
        AccountDAO accountDAO = new AccountDAO();
        Account accountCustomer = accountDAO.getAccountById(idCustomer);
        if (currentBill != null && currentBill.getStatus() == 5 && currentBill.getIsGetPoint() == 0) {
            float totalBill = currentBill.getTotal();
            int points = 0;
            if (totalBill < 500000) {
                points = 1;
            } else
            if (totalBill >= 500000 && totalBill < 1000000) {
                points = 5;
            } else if (totalBill >= 1000000 && totalBill < 2000000) {
                points = 10;
            } else if (totalBill >= 2000000 && totalBill < 3000000) {
                points = 20;
            } else if (totalBill >= 3000000 && totalBill < 5000000) {
                points = 30;
            } else if (totalBill >= 5000000) {
                points = 50;
            }
            float balance = points * 1000;
            float finalBalance = balance + accountCustomer.getBalance();
            int resultChangeStatusPoint = billDao.updateStatusPoint(idBill, 1);
            if (resultChangeStatusPoint >= 1) {
                int result = accountDAO.updateBalance(finalBalance, idCustomer);
                response.sendRedirect("/SWP391-MomAndBaby/account/history-order/detail/" + idBill + "?act=get-point&status=" + result);
            } else {
                response.sendRedirect("/SWP391-MomAndBaby/account/history-order/detail/" + idBill + "?act=get-point&status=" + 0);
            }
        } else {
            response.sendRedirect("/SWP391-MomAndBaby/account/history-order/detail/" + idBill + "?act=get-point&status=2");
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
