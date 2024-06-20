/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Staff;

import DAO.AccountDAO;
import DAO.PreOrderDAO;
import DAO.ProductDAO;
import DAO.VoucherDAO;
import Model.Account;
import Model.PreOrder;
import Model.Product;
import Model.Voucher;
import Utils.Email;
import Utils.TemplateEmail;
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
 * @author Admin
 */
public class PreOrderStaffController extends HttpServlet {

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
            out.println("<title>Servlet PreOrderStaffController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PreOrderStaffController at " + request.getContextPath() + "</h1>");
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
        Validation validate = new Validation();
        String path = request.getRequestURI();
        if (path.endsWith("/SWP391-MomAndBaby/staff/preorder")) {
            this.getAllPreOrder(request, response);
        } else {
            String paths[] = path.split("/");
            int id = validate.getInt(paths[paths.length - 1]);
            if (path.startsWith("/SWP391-MomAndBaby/staff/banner/delete")) {
//                this.delete(request, response, id);
            } else if (path.startsWith("/SWP391-MomAndBaby/staff/banner/update")) {
//                this.currentUpdate(request, response, id);
            }
        }
    }

    private void getAllPreOrder(HttpServletRequest request, HttpServletResponse response) {
        try {
            PreOrderDAO preOrderDao = new PreOrderDAO();
            List<PreOrder> preOrders = preOrderDao.getAll();
            request.setAttribute("preOrders", preOrders);
            request.getRequestDispatcher("/staff/view/preOrder/preOrder.jsp").forward(request, response);
        } catch (Exception e) {

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
        Validation validate = new Validation();
        TemplateEmail temp = new TemplateEmail();
        PreOrderDAO preOrderDao = new PreOrderDAO();
        try {
            String[] allItemChecked = request.getParameterValues("preOrder-item");
            if (allItemChecked == null) {
                response.sendRedirect("/SWP391-MomAndBaby/staff/preorder?act=action-preorder&status=2");
                return;
            }
            int status = 0;
            AccountDAO accountDao = new AccountDAO();
            ProductDAO productDao = new ProductDAO();
            for (String id : allItemChecked) {
                if (request.getParameter("btn-mail-preorder") != null) {
                    PreOrder preOrder = preOrderDao.getByID(validate.getInt(id));
                    Product product = productDao.getProductByID(preOrder.getProductID());
                    if (product.getQuantity() > 0) {
                        Account account = accountDao.getAccountById(preOrder.getAccountID());
                        Email mail = new Email();
                        String templateEmailSend = temp.emailSendPreOrder(product, account, "Send contact pre order");
                        int result = preOrderDao.updateStatus(2, validate.getInt(id));
                        if (result > 0) {
                            boolean isSend = mail.sendEmail(account.getEmail(), "Send contact pre order", templateEmailSend, null);
                            int index = 0;
                            while (!isSend && index < 5) {
                                isSend = mail.sendEmail(account.getEmail(), "Send contact pre order", templateEmailSend, null);
                            }
                            if (isSend) {
                                status = 1;
                            }
                        }
                    } else {
                        status = 3;
                    }
                } else if (request.getParameter("btn-view-preorder") != null) {
                    int result = preOrderDao.updateStatus(1, validate.getInt(id));
                    if (result == 1) {
                        status = 1;
                    }
                } else {
                    int result = preOrderDao.delete(validate.getInt(id));
                    if (result == 1) {
                        status = 1;
                    }
                }

            }
            String url = "/SWP391-MomAndBaby/staff/preorder?act=action-preorder&status=" + status;
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Action preOrder:  " + e);
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
