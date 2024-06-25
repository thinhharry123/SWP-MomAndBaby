/*
 * Click nbfs:/nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs:/nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import Authentication.AuthUser;
import DAO.AccountDAO;
import DAO.BillDAO;
import DAO.BillDetailDAO;
import DAO.CartDAO;
import DAO.DistrictDao;
import DAO.ProductDAO;
import DAO.ProvinceDao;
import DAO.UsedDAO;
import DAO.VoucherDAO;
import DAO.WardDao;
import Model.Account;
import Model.Bill;
import Model.BillDetail;
import Model.Cart;
import Model.District;
import Model.Product;
import Model.Province;
import Model.Used;
import Model.Voucher;
import Model.Ward;
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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckoutController extends HttpServlet {

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
            out.println("<title>Servlet CheckoutController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutController at " + request.getContextPath() + "</h1>");
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
        CartDAO cartDao = new CartDAO();
        Validation validate = new Validation();
        HttpSession session = request.getSession();
        if (username != null) {
            ProvinceDao provinceDao = new ProvinceDao();
            List<Province> provinces = provinceDao.getProvinces();
            request.setAttribute("provinces", provinces);
            Account account = accountDao.getAccountByUsername(username);
            if (account == null) {
                session.removeAttribute("usernameUser");
                response.sendRedirect("/SWP391-MomAndBaby/login");
                return;
            }
            request.setAttribute("accountLogin", account);
            List<Cart> carts = cartDao.getAllCart(account.getID());
            if (carts.size() == 0) {
                response.sendRedirect("/SWP391-MomAndBaby");
                return;
            }
            request.setAttribute("carts", carts);
            String voucherSet = session.getAttribute("idVoucher") + "";
            if (!voucherSet.equals("null")) {
                int idVoucher = validate.getInt(voucherSet);
                this.checkVoucher(request, response, idVoucher);
            }
            request.getRequestDispatcher("./user/checkout.jsp").forward(request, response);
        } else {
            response.sendRedirect("/SWP391-MomAndBaby/login");
        }
    }

    public void checkVoucher(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            AuthUser auth = new AuthUser();
            String usernameLogin = auth.isLoginUser(request, response);
            AccountDAO accountDao = new AccountDAO();
            Account a = accountDao.getAccountByUsername(usernameLogin);
            VoucherDAO couponDao = new VoucherDAO();
            Voucher coupon = couponDao.currentVoucher(id);
            if (a == null) {
                request.getSession().setAttribute("couponStatus", "login");
            } else if (coupon == null) {
                request.getSession().setAttribute("ckcouponStatus", "invalid");
            } else {
                UsedDAO usedDao = new UsedDAO();
                Used used = usedDao.getUsed(a.getID(), coupon.getId());
                if (used != null) {
                    request.getSession().setAttribute("ckcouponStatus", "used");
                } else {
                    Date currentDate = new Date();
                    if (coupon.getStatus() == 0 || coupon.getStart().after(currentDate) || coupon.getEnd().before(currentDate)) {
                        request.getSession().setAttribute("ckcouponStatus", "expired");
                    } else if (coupon.getUsed() >= coupon.getLimit()) {
                        request.getSession().setAttribute("ckcouponStatus", "limitReached");
                    } else {
                        float discount = coupon.getValue();
                        float totalCartValue = getTotalCartValue(request);
                        float newTotal = totalCartValue - discount;
                        if (newTotal < 0) {
                            newTotal = 0;
                        }
                        request.getSession().setAttribute("ckIdVoucher", coupon.getId());
                        request.getSession().setAttribute("ckcouponStatus", "applied");
                        request.getSession().setAttribute("ckdiscount", discount);
                        request.getSession().setAttribute("cknewTotal", newTotal);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("applyCoupon: " + e);
        }
    }

    private float getTotalCartValue(HttpServletRequest request) {
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
        String usernameLogin = auth.isLoginUser(request, response);
        if (usernameLogin != null) {
            HttpSession session = request.getSession();
            Validation validate = new Validation();
            ProvinceDao provinceDao = new ProvinceDao();
            DistrictDao districtDao = new DistrictDao();
            ProductDAO productDao = new ProductDAO();
            AccountDAO accountDao = new AccountDAO();
            Account accountLogin = accountDao.getAccountByUsername(usernameLogin);
            WardDao wardDao = new WardDao();
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            int province = validate.getInt(request.getParameter("province"));
            int district = validate.getInt(request.getParameter("district"));
            int ward = validate.getInt(request.getParameter("ward"));
            String isUsePoint = request.getParameter("usePoint");
            Ward wa = wardDao.getWardByID(ward);
            District dis = districtDao.getDistrictByID(district);
            Province pro = provinceDao.getProvinceByID(province);
            String waName = wa != null ? wa.getName() : "N/A";
            String districtName = dis != null ? dis.getName() : "N/A";
            String proName = pro != null ? pro.getName() : "N/A";
            String address = waName + ", " + districtName + ", " + proName;
            String detailAddress = request.getParameter("details-address");
            String voucherSet = session.getAttribute("ckIdVoucher") + "";
            float newToal = validate.getFloat(session.getAttribute("cknewTotal") + "");
            float totalToPay = newToal == -1 ? 0 : newToal;
            LocalDateTime dateTime = LocalDateTime.now();
            Timestamp dateOrder = Timestamp.valueOf(dateTime);
            int payMethod = validate.getInt(request.getParameter("payment"));
            int idVoucher = -1;
            int status = 1;
            CartDAO cartDao = new CartDAO();
            List<Cart> carts = cartDao.getAllCart(accountLogin.getID());
            if (carts.size() == 0) {
                response.sendRedirect("/SWP391-MomAndBaby");
                return;
            }
            List<BillDetail> billDetail = new ArrayList<>();
            boolean isOkCheckOut = true;
            for (Cart cart : carts) {
                Product productCheck = productDao.getProductByID(cart.getProductID());
                if (cart.getQuantity() > productCheck.getQuantity()) {
                    isOkCheckOut = false;
                    break;
                }
                float price = productCheck.getNewPrice() > 0 ? productCheck.getNewPrice() : productCheck.getOldPrice();
                if (newToal == -1) {
                    totalToPay += price * cart.getQuantity();
                }
                String imgProduct = productCheck.getMainImg();
                String nameProduct = productCheck.getName();
                BillDetail billItem = new BillDetail(0, 0, imgProduct, cart.getQuantity(), price, nameProduct, cart.getProductID());
                billDetail.add(billItem);
            }
            if (isOkCheckOut) {
                Bill bill = new Bill(0, accountLogin.getID(), email, fullname, phone, address,
                        detailAddress, totalToPay, status, payMethod, dateOrder, null, null);
                if (isUsePoint != null && isUsePoint.equals("usepoint")) {
                    float usedPoint = accountLogin.getBalance() > totalToPay ? totalToPay : accountLogin.getBalance();
                    bill.setIsUsedPoint(usedPoint);
                } else {
                    bill.setIsUsedPoint(0);
                }
                if (!voucherSet.equals("null")) {
                    idVoucher = validate.getInt(voucherSet);
                    bill.setVoucherID(idVoucher);
                    session.setAttribute("hasVoucher", idVoucher);
                }
                if (payMethod == 1) {
                    session.setAttribute("billDetail", billDetail);
                    session.setAttribute("bill", bill);
                    if (isUsePoint != null && isUsePoint.equals("usepoint")) {
                        totalToPay = accountLogin.getBalance() > totalToPay ? 0 : totalToPay - accountLogin.getBalance();
                        session.setAttribute("isUsePoint", accountLogin.getBalance());
                    }
                    long totalSendVNPAY = (long) totalToPay;
                    response.sendRedirect("vnpay?amount=" + totalSendVNPAY);
                } else {
                    BillDAO billDAO = new BillDAO();
                    BillDetailDAO billDetailDao = new BillDetailDAO();
                    int idBill = billDAO.addBill(bill);
                    int type_order = 1;
                    if (idBill >= 1) {
                        for (BillDetail billItem : billDetail) {
                            billItem.setBillID(idBill);
                            int result = billDetailDao.addBillDetail(billItem);
                            if (result <= 0) {
                                type_order = 0;
                            } else {
                                if (!voucherSet.equals("null")) {
                                    UsedDAO usedDao = new UsedDAO();
                                    Used used = new Used(bill.getVoucherID(), bill.getCustomerID(), bill.getDateOrder());
                                    usedDao.addUsed(used);
                                }
                                Product productToUpdate = productDao.getProductByID(billItem.getProductID());
                                int quantity = productToUpdate.getQuantity() - billItem.getNumberOfProduct() <= 0 ? 0 : productToUpdate.getQuantity() - billItem.getNumberOfProduct();
                                int sold = productToUpdate.getSold() + billItem.getNumberOfProduct();
                                productToUpdate.setQuantity(quantity);
                                productToUpdate.setSold(sold);
                                productDao.updateQuantitySold(productToUpdate);
                            }
                        }
                        if (type_order == 1) {
                            if (!voucherSet.equals("null")) {
                                VoucherDAO voucherDAO = new VoucherDAO();
                                Voucher vou = voucherDAO.currentVoucher(idVoucher);
                                vou.setUsed(vou.getUsed() + 1);
                            }
                            Email emailSend = new Email();
                            TemplateEmail template = new TemplateEmail();
                            String sendToConfirm = template.emailConfirmOrder(bill, billDetail, "Confirm order");
                            boolean isSendMail = emailSend.sendEmail(bill.getEmail(), "Confirm order", sendToConfirm, null);
                            int count = 1;
                            while (!isSendMail && count < 5) {
                                isSendMail = emailSend.sendEmail(bill.getEmail(), "Confirm order", sendToConfirm, null);
                                count++;
                            }
                            if (isUsePoint!= null && isUsePoint.equals("usepoint")) {
                                accountDao.updateBalance(accountLogin.getBalance() - bill.getIsUsedPoint(), accountLogin.getID());
                            }
                            cartDao.deleteCartItemByUser(bill.getCustomerID());
                            response.sendRedirect("/SWP391-MomAndBaby/ordered/success");
                        } else {
                            response.sendRedirect("/SWP391-MomAndBaby/ordered/fail");
                        }
                    }
                }
            } else {
                response.sendRedirect("/SWP391-MomAndBaby/ordered/fail?act=limit-cart&status=0");
            }
        } else {
            response.sendRedirect("/SWP391-MomAndBaby/login");
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
