/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import Authentication.AuthUser;
import DAO.AccountDAO;
import DAO.CartDAO;
import DAO.ProductDAO;
import DAO.UsedDAO;
import DAO.VoucherDAO;
import Model.Account;
import Model.Cart;
import Model.Product;
import Model.Used;
import Model.Voucher;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class VoucherUserController extends HttpServlet {

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
            out.println("<title>Servlet VoucherUserController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VoucherUserController at " + request.getContextPath() + "</h1>");
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
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response)
            throws jakarta.servlet.ServletException, IOException {
        if (request.getParameter("use-voucher") != null) {
            this.applyCoupon(request, response);
        } else if (request.getParameter("remove-voucher") != null) {
            HttpSession session = request.getSession();
            session.removeAttribute("idVoucher");
            session.removeAttribute("couponStatus");
            session.removeAttribute("discount");
            session.removeAttribute("newTotal");
            session.removeAttribute("ckIdVoucher");
            session.removeAttribute("ckcouponStatus");
            session.removeAttribute("ckdiscount");
            session.removeAttribute("cknewTotal");
            response.sendRedirect("/SWP391-MomAndBaby/cart?act=remove-voucher&status=1");
        }
    }

    private void applyCoupon(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) {
        try {
            AuthUser auth = new AuthUser();
            String usernameLogin = auth.isLoginUser(request, response);
            AccountDAO accountDao = new AccountDAO();
            Account a = accountDao.getAccountByUsername(usernameLogin);
            String couponCode = request.getParameter("couponCode");
            VoucherDAO couponDao = new VoucherDAO();
            Voucher coupon = couponDao.getVoucherByCode(couponCode);
            if (a == null) {
                request.getSession().setAttribute("couponStatus", "login");
            } else if (coupon == null) {
                request.getSession().setAttribute("couponStatus", "invalid");
            } else {
                UsedDAO usedDao = new UsedDAO();
                Used used = usedDao.getUsed(a.getID(), coupon.getId());
                if (used != null) {
                    request.getSession().setAttribute("couponStatus", "used");
                } else {
                    Date currentDate = new Date();
                    if (coupon.getStatus() == 0 || coupon.getStart().after(currentDate) || coupon.getEnd().before(currentDate)) {
                        request.getSession().setAttribute("couponStatus", "expired");
                    } else if (coupon.getUsed() >= coupon.getLimit()) {
                        request.getSession().setAttribute("couponStatus", "limitReached");
                    } else {
                        float discount = coupon.getValue();
                        float totalCartValue = getTotalCartValue(request);
                        float newTotal = totalCartValue - discount;
                        if (newTotal < 0) {
                            newTotal = 0;
                        }
                        request.getSession().setAttribute("idVoucher", coupon.getId());
                        request.getSession().setAttribute("couponStatus", "applied");
                        request.getSession().setAttribute("discount", discount);
                        request.getSession().setAttribute("newTotal", newTotal);
                    }
                }
            }
            response.sendRedirect("/SWP391-MomAndBaby/cart");
        } catch (Exception e) {
            System.out.println("applyCoupon: " + e);
        }
    }

    private float getTotalCartValue(jakarta.servlet.http.HttpServletRequest request) {
        try {
            CartDAO cartDAO = new CartDAO();
            AccountDAO accountDao = new AccountDAO();
            ProductDAO productDao = new ProductDAO();
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("usernameUser");
            Account account = accountDao.getAccountByUsername(username);
            if (account == null) {
                session.removeAttribute("usernameUser");
            } else {
                List<Cart> carts = cartDAO.getAllCart(account.getID());
                float total = 0;
                for (Cart cart : carts) {
                    Product p = productDao.getProductByID(cart.getProductID());
                    if (p.getNewPrice() > 0) {
                        total += p.getNewPrice() * cart.getQuantity();
                    } else {
                        total += p.getOldPrice() * cart.getQuantity();
                    }
                }
                return total;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
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
