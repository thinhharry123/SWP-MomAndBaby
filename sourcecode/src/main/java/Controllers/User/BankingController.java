/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import Authentication.AuthUser;
import DAO.AccountDAO;
import DAO.BillDAO;
import DAO.BillDetailDAO;
import DAO.CartDAO;
import DAO.ProductDAO;
import DAO.UsedDAO;
import DAO.VoucherDAO;
import Model.Account;
import Model.Bill;
import Model.BillDetail;
import Model.Product;
import Model.Used;
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
import jakarta.servlet.http.HttpSession;
import java.util.List;


public class BankingController extends HttpServlet {

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
            out.println("<title>Servlet BankingController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BankingController at " + request.getContextPath() + "</h1>");
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
        AuthUser auth = new AuthUser();
        String usernameLogin = auth.isLoginUser(request, response);
        HttpSession session = request.getSession();
        AccountDAO accountDao = new AccountDAO();
        Account accountLogin  = accountDao.getAccountByUsername(usernameLogin);
        ProductDAO productDao = new ProductDAO();
        String transactionStatus = request.getParameter("vnp_TransactionStatus");
        List<BillDetail> billDetail = (List<BillDetail>) session.getAttribute("billDetail");
        Bill bill = (Bill) session.getAttribute("bill");
        System.out.println(bill + " " + billDetail);
        float isUsePoint = validate.getFloat(session.getAttribute("isUsePoint") + "");
        int type_order = 1;
        if (bill != null && billDetail != null) {
            if (transactionStatus != null) {
                if (transactionStatus.equals("00")) {
                    String transactionCode = request.getParameter("vnp_TxnRef");
                    bill.setTransactionCode(transactionCode);
                    BillDAO billDAO = new BillDAO();
                    BillDetailDAO billDetailDao = new BillDetailDAO();
                    int idBill = billDAO.addBill(bill);
                    if (idBill >= 1) {
                        for (BillDetail billItem : billDetail) {
                            billItem.setBillID(idBill);
                            int result = billDetailDao.addBillDetail(billItem);
                            if (result <= 0) {
                                type_order = 0;
                            } else {
                                Product productToUpdate = productDao.getProductByID(billItem.getProductID());
                                int quantity = productToUpdate.getQuantity() - billItem.getNumberOfProduct() <= 0 ? 0 : productToUpdate.getQuantity() - billItem.getNumberOfProduct();
                                int sold = productToUpdate.getSold() + billItem.getNumberOfProduct();
                                productToUpdate.setQuantity(quantity);
                                productToUpdate.setSold(sold);
                                productDao.updateQuantitySold(productToUpdate);
                            }
                        }
                        if (type_order == 1) {
                            String voucher = session.getAttribute("hasVoucher") + "";
                            if (!voucher.equals("null")) {
                                VoucherDAO voucherDAO = new VoucherDAO();
                                Voucher vou = voucherDAO.currentVoucher(bill.getVoucherID());
                                vou.setUsed(vou.getUsed() + 1);
                                UsedDAO usedDao = new UsedDAO();
                                Used used = new Used(bill.getVoucherID(), bill.getCustomerID(), bill.getDateOrder());
                                usedDao.addUsed(used);
                            }
                            Email email = new Email();
                            TemplateEmail template = new TemplateEmail();
                            String sendToConfirm = template.emailConfirmOrder(bill, billDetail, "Confirm order");
                            boolean isSendMail = email.sendEmail(bill.getEmail(), "Confirm order", sendToConfirm, null);
                            int count = 1;
                            while (!isSendMail && count < 5) {
                                isSendMail = email.sendEmail(bill.getEmail(), "Confirm order", sendToConfirm, null);
                                count++;
                            }
                            if (isUsePoint > 0) {
                                accountDao.updateBalance(accountLogin.getBalance() - bill.getIsUsedPoint(), accountLogin.getID());
                            }
                            CartDAO cartDao = new CartDAO();
                            cartDao.deleteCartItemByUser(bill.getCustomerID());
                            session.removeAttribute("billDetail");
                            session.removeAttribute("bill");
                            session.removeAttribute("hasVoucher");
                            response.sendRedirect("/SWP391-MomAndBaby/ordered/success");
                        } else {
                            response.sendRedirect("/SWP391-MomAndBaby/ordered/fail");
                        }
                    }
                } else {
                   response.sendRedirect("/SWP391-MomAndBaby/ordered/fail");
                }
            }
        } else {
            response.sendRedirect("/SWP391-MomAndBaby/404");
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
