/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers.User;

import DAO.AccountDAO;
import DAO.VoucherDAO;
import Model.Account;
import Model.Voucher;
import Utils.Email;
import Utils.TemplateEmail;
import Utils.Validation;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class SendMailController extends HttpServlet {
   
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
        if (path.endsWith("/staff/send-mail")) {
            AccountDAO accountDao = new AccountDAO();
            VoucherDAO voucherDao = new VoucherDAO();
            List<Voucher> voucherActive = voucherDao.allVoucherActive();
            List<Account> accounts = accountDao.allAccountByStaff();
            request.setAttribute("accounts", accounts);
            request.setAttribute("vouchers", voucherActive);
            request.getRequestDispatcher("/staff/view/mail/mail.jsp").forward(request, response);
        } else {

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
        request.setCharacterEncoding("UTF-8");
        Validation validate = new Validation();
        VoucherDAO voucherDao = new VoucherDAO();
        TemplateEmail temp = new TemplateEmail();
        if (request.getParameter("btn-send-mail") != null) {
            String replyMail = request.getParameter("emailReply").trim();
            String[] emails = request.getParameterValues("mail-user-item");
            emails = (emails == null ? new String[0] : emails);
            String title = request.getParameter("title").trim();
            String message = request.getParameter("message");
            int idVoucher = validate.getInt(request.getParameter("voucher"));
            Voucher v = voucherDao.currentVoucher(idVoucher);
            String messageToSend = "";
            if (v != null) {
                messageToSend = temp.Voucher(title, message, v.getCode());
            } else {
                replyMail = null;
                messageToSend = message;
            }
            Email mail = new Email();
            boolean isSend = false;
            for (String email : emails) {
                if (!isSend) {
                    isSend = mail.sendEmail(email, title, messageToSend, replyMail);
                } else {
                    mail.sendEmail(email, title, messageToSend, replyMail);
                }
            }
            String url = "/SWP391-MomAndBaby/staff/send-mail?act=send-mail&status=";
            if (isSend) {
                url += "1";
            } else {
                url += "0";
            }
            response.sendRedirect(url);
        }
    }

}
