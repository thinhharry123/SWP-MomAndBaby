/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Staff;

import DAO.VoucherDAO;
import Model.Voucher;
import Utils.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VoucherController extends HttpServlet {

    private static VoucherDAO voucherDao;

    public VoucherController() {
        this.voucherDao = new VoucherDAO();
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
            out.println("<title>Servlet VoucherController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VoucherController at " + request.getContextPath() + "</h1>");
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
        if (path.endsWith("/SWP391-MomAndBaby/staff/voucher")) {
            this.showAllVoucher(request, response);
        } else {
            String paths[] = path.split("/");
            int id = validate.getInt(paths[paths.length - 1]);
            if (path.startsWith("/SWP391-MomAndBaby/staff/voucher/update/")) {
                this.getCurrentVoucherUpdate(request, response, id);
            } else if (path.startsWith("/SWP391-MomAndBaby/staff/voucher/delete/")) {
                this.deleteVoucher(request, response, id);
            }
        }
    }

    private void showAllVoucher(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Voucher> vouchers = voucherDao.allVoucher();
            request.setAttribute("vouchers", vouchers);
            request.getRequestDispatcher("/staff/view/voucher/voucher.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Show voucher: " + e);
        }
    }

    private void getCurrentVoucherUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            Voucher c = voucherDao.currentVoucher(id);
            if (c != null) {
                request.setAttribute("currentVoucher", c);
                request.getRequestDispatcher("/staff/view/voucher/updateVoucher.jsp").forward(request, response);
            } else {
                response.sendRedirect("/admin/404");
            }
        } catch (Exception e) {
            System.out.println("Get current voucher update: " + e);
        }
    }

    private void deleteVoucher(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            int result = voucherDao.delete(id);
            String url = "/SWP391-MomAndBaby/staff/voucher?act=delete&status=" + result;
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Delete voucher: " + e);
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
        if (request.getParameter("btn-add-voucher") != null) {
            this.addVoucher(request, response);
        } else if (request.getParameter("btn-update-voucher") != null) {
            this.updateVoucher(request, response);
        } else if (request.getParameter("btn-delete-vouchers") != null) {
            this.deleteVouchers(request, response);
        }
    }

    private void addVoucher(HttpServletRequest request, HttpServletResponse response) {
        try {
            Validation validate = new Validation();
            String name = request.getParameter("name").trim();
            String code = request.getParameter("code").trim();
            if (this.isExist(code)) {
                response.sendRedirect("/SWP391-MomAndBaby/staff/voucher?act=add-voucher&status=2");
                return;
            }
            String datetimeStart = request.getParameter("start").trim();
            String dateTimeEnd = request.getParameter("end").trim();

            // Chuyển đổi giá trị đó sang đối tượng LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime localDateTimeStart = LocalDateTime.parse(datetimeStart, formatter);
            LocalDateTime localDateTimeEnd = LocalDateTime.parse(dateTimeEnd, formatter);
            Timestamp start = Timestamp.valueOf(localDateTimeStart);
            Timestamp end = Timestamp.valueOf(localDateTimeEnd);
            float value = validate.getFloat(request.getParameter("value").trim());
            int status = validate.getInt(request.getParameter("status"));
            float limit = validate.getFloat(request.getParameter("limit").trim());
            Voucher v = new Voucher(-1, name, code, value, start, end, status, limit);
            int result = voucherDao.insert(v);
            String url = "/SWP391-MomAndBaby/staff/voucher?act=add-voucher&status=" + result;
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Insert voucher: " + e);
        }
    }

    private boolean isExist(String code) {
        Voucher v = voucherDao.getVoucherByCode(code);
        if (v == null) {
            return false;
        }
        return true;
    }

    private void updateVoucher(HttpServletRequest request, HttpServletResponse response) {
        try {
            Validation validate = new Validation();
            int id = validate.getInt(request.getParameter("id"));
            String name = request.getParameter("name").trim();
            String code = request.getParameter("code").trim();
            Voucher vCheck = voucherDao.currentVoucher(id);
            if (this.isExist(code) && !vCheck.getCode().equals(code)) {
                response.sendRedirect("/SWP391-MomAndBaby/staff/voucher/update/" + id + "?act=update-voucher&status=2");
                return;
            }
            float value = validate.getFloat(request.getParameter("value").trim());
            String datetimeStart = request.getParameter("start").trim();
            String dateTimeEnd = request.getParameter("end").trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime localDateTimeStart = LocalDateTime.parse(datetimeStart, formatter);
            LocalDateTime localDateTimeEnd = LocalDateTime.parse(dateTimeEnd, formatter);
            Timestamp start = Timestamp.valueOf(localDateTimeStart);
            Timestamp end = Timestamp.valueOf(localDateTimeEnd);
            int status = validate.getInt(request.getParameter("status").trim());
            float limit = validate.getFloat(request.getParameter("limit").trim());
            Voucher v = new Voucher(id, name, code, value, start, end, status, limit);
            int result = voucherDao.update(v);
            String url = "/SWP391-MomAndBaby/staff/voucher?act=update-voucher&status=" + result;
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Update voucher: " + e);
        }
    }

    private void deleteVouchers(HttpServletRequest request, HttpServletResponse response) {
        Validation validate = new Validation();
        try {
            String[] allSlugChecked = request.getParameterValues("delete-voucher-item");
            if (allSlugChecked == null) {
                response.sendRedirect("/SWP391-MomAndBaby/staff/voucher?act=delete&status=2");
                return;
            }
            int status = 0;
            for (String id : allSlugChecked) {
                int result = voucherDao.delete(validate.getInt(id));
                if (result == 1) {
                    status = 1;
                }
            }
            String url = "/SWP391-MomAndBaby/staff/voucher?act=delete&status=" + status;
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Delete vouchers:  " + e);
        }
    }

}
