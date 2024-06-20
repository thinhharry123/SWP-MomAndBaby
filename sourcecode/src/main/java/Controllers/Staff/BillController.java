/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Staff;

import DAO.AccountDAO;
import DAO.BillDAO;
import DAO.BillDetailDAO;
import Model.Account;
import Model.Bill;
import Model.BillDetail;
import Utils.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class BillController extends HttpServlet {

    private static BillDAO billDao;

    public BillController() {
        this.billDao = new BillDAO();
    }

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
            out.println("<title>Servlet BillController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BillController at " + request.getContextPath() + "</h1>");
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
        String path = request.getRequestURI();
        Validation validate = new Validation();
        if (path.endsWith("/SWP391-MomAndBaby/staff/bill")) {
            this.showAllBill(request, response);
        } else {
            String paths[] = path.split("/");
            int idBill = validate.getInt(paths[paths.length - 1]);
            if (path.startsWith("/SWP391-MomAndBaby/staff/bill/view")) {
                this.viewBill(request, response, idBill);
            } else if (path.startsWith("/SWP391-MomAndBaby/staff/bill/delete")) {
//                this.deleteBill(request, response, idBill);
            }
        }
    }

    private void viewBill(HttpServletRequest request, HttpServletResponse response, int idBill) {
        try {
            BillDetailDAO billDetailsDao = new BillDetailDAO();
            Bill bill = billDao.getBillById(idBill);
            if (bill != null) {
                List<BillDetail> billDetails = billDetailsDao.getBillDetailById(idBill);
                request.setAttribute("bill", bill);
                request.setAttribute("billDetails", billDetails);
                request.getRequestDispatcher("/staff/view/bill/billDetails.jsp").forward(request, response);
            } else {
                response.sendRedirect("/SWP391-MomAndBaby/staff/404");
            }
        } catch (Exception e) {
            System.out.println("View bill: " + e);
        }
    }

    private void showAllBill(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Bill> bills = billDao.getAllBill();
            AccountDAO accountDao = new AccountDAO();
            List<Account> accounts = accountDao.allAccountByStaff();
            request.setAttribute("accounts", accounts);
            request.setAttribute("bills", bills);
            request.getRequestDispatcher("/staff/view/bill/bill.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Show bill: " + e);
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
        if (request.getParameter("btn-update-bill-status") != null) {
            updateBillStatus(request, response);
        } else if (request.getParameter("btn-delete-bills") != null) {
//            deleteBills(request, response);
        }
    }

    private void updateBillStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            Validation validate = new Validation();
            int id = validate.getInt(request.getParameter("idBill"));
            int status = validate.getInt(request.getParameter("status"));
            LocalDateTime dateTime = LocalDateTime.now();
            Timestamp dateUpdate = Timestamp.valueOf(dateTime);
            int result = billDao.updateStatus(id, status, dateUpdate);
            String url = "/SWP391-MomAndBaby/staff/bill/view/" + id + "?act=update-bill&status=" + result;
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Update status: " + e);
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
