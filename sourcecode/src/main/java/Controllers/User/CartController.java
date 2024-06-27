/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import Authentication.AuthUser;
import DAO.AccountDAO;
import DAO.CartDAO;
import DAO.ProductDAO;
import Model.Account;
import Model.Cart;
import Model.Product;
import Utils.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

public class CartController extends HttpServlet {

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
            out.println("<title>Servlet CartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartController at " + request.getContextPath() + "</h1>");
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
        //check if user is logged in
        AccountDAO accountDao = new AccountDAO();
        CartDAO cartDao = new CartDAO();
        String path = request.getRequestURI();
        if (path.endsWith("/cart")) {
            if (username != null) {//if user logged in
                Account account = accountDao.getAccountByUsername(username);
                //get user's account
                List<Cart> carts = cartDao.getAllCart(account.getID());
                //get the user's carts
                request.setAttribute("carts", carts);
                request.getRequestDispatcher("./user/cart.jsp").forward(request, response);
                //set the user's cart and send back to the jsp
            } else {
                response.sendRedirect("/SWP391-MomAndBaby/login");
                //user not logged in => go to login page
            }
        } else if (path.startsWith("/SWP391-MomAndBaby/cart/add")) {
            this.addToCart(request, response);
            //add product to cart
        } else if (path.startsWith("/SWP391-MomAndBaby/cart/remove")) {
            this.removeFromCart(request, response);
            //remove product from cart
        }
    }

    private void removeFromCart(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("couponStatus");
            session.removeAttribute("discount");
            session.removeAttribute("newTotal");
            session.removeAttribute("couponCode");
            session.removeAttribute("idVoucher");
            //remove all the discount related 
            AuthUser auth = new AuthUser();
            String username = auth.isLoginUser(request, response);
            if (username != null) {
                //if user is logged in
                CartDAO cartDao = new CartDAO();
                String path = "/SWP391-MomAndBaby/cart";
                int cartId = Integer.parseInt(request.getParameter("cartId"));
                int result = cartDao.deleteCartItem(cartId);
                //remove from cart based on cartId
                response.sendRedirect(path + "?act=remove-cart&status=" + result);
            } else {
                //if user not logged in yet
                response.sendRedirect("/SWP391-MomAndBaby/login");
            }
        } catch (Exception e) {
            System.out.println("removeFromCart: " + e);
        }
    }

    private void addToCart(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("couponStatus");
            session.removeAttribute("discount");
            session.removeAttribute("newTotal");
            session.removeAttribute("couponCode");
            session.removeAttribute("idVoucher");
            //remove all related to discount
            Validation validate = new Validation();
            AuthUser auth = new AuthUser();
            String username = auth.isLoginUser(request, response);
            if (username != null) {//ccheck if user is logged in
                CartDAO cartDao = new CartDAO();
                ProductDAO productDao = new ProductDAO();
                String path = request.getParameter("pathUrl");
                AccountDAO accountDao = new AccountDAO();
                Account account = accountDao.getAccountByUsername(username);
                //get account
                int accountId = account.getID();
                int quantity = validate.getInt(request.getParameter("quantity"));
                int productId = validate.getInt(request.getParameter("productID"));
                //get product id and it's quantity
                Product p = productDao.getProductByID(productId);
                Cart cartExist = cartDao.getByIdUser(productId, accountId);
                int result = 0;
                if (quantity <= 0) {//invalid quantity input
                    result = 2;
                } else if (cartExist != null) { //find items in cart
                    int newQuan = cartExist.getQuantity() + quantity;//get new quantity
                    if (newQuan > p.getQuantity()) {
                        result = 2; //more than what the store have
                    } else {
                        //store still has
                        float price = p.getNewPrice() > 0 ? p.getNewPrice() * quantity : p.getOldPrice() * quantity;
                        //new price is price after discounted => new price > 0 => there are discount => get value based on discounted price
                        //else no discount, get normal price
                        Cart c = new Cart(0, accountId, price, newQuan, productId);
                        c.setQuantity(newQuan);
                        //set quantity after updating and validating new quantity
                        c.setTotalPrice(price);
                        result = cartDao.updateToCart(c);
                        //update cart quantity and total
                    }
                } else {//the quantity input is valid and doesnt find any cart about that product
                    if (quantity > p.getQuantity()) {
                    //quantity more than quantity in stock
                        result = 2;
                    } else {//good quantity
                        float price = p.getNewPrice() > 0 ? p.getNewPrice() * quantity : p.getOldPrice() * quantity;
                        //if product has discount ?
                        Cart c = new Cart(0, accountId, price, quantity, productId);
                        result = cartDao.addToCart(c);
                        // Add to cart
                    }
                }
                response.sendRedirect(path + "?act=add-cart&status=" + result);
            } else {
                response.sendRedirect("/SWP391-MomAndBaby/login");
            }
        } catch (Exception e) {
            System.out.println("addToCart: " + e);
        }
    }

    private void updateCart(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("couponStatus");
            session.removeAttribute("discount");
            session.removeAttribute("newTotal");
            session.removeAttribute("couponCode");
            session.removeAttribute("idVoucher");
            Validation validate = new Validation();
            AuthUser auth = new AuthUser();
            String username = auth.isLoginUser(request, response);
            if (username != null) {//user is logged in
                CartDAO cartDao = new CartDAO();
                ProductDAO productDao = new ProductDAO();
                String path = "/SWP391-MomAndBaby/cart";
                AccountDAO accountDao = new AccountDAO();
                Account account = accountDao.getAccountByUsername(username);
                //get account
                int accountId = account.getID();
                // Retrieve all product IDs and quantities from the request
                Enumeration<String> parameterNames = request.getParameterNames();
                int result = 0;
                while (parameterNames.hasMoreElements()) {
                    String paramName = parameterNames.nextElement();
                    if (paramName.startsWith("qty_")) {//productID
                        int productId = Integer.parseInt(paramName.substring(4));
                        int quantity = validate.getInt(request.getParameter(paramName));
                        if (quantity <= 0) {
                            result = 2;
                        } else {
                            Product p = productDao.getProductByID(productId);
                            Cart cartExist = cartDao.getByIdUser(productId, accountId);
                            if (cartExist != null) {
                                if (quantity > p.getQuantity()) {
                                    result = 2;
                                } else {
                                    float price = p.getNewPrice() > 0 ? p.getNewPrice() * quantity : p.getOldPrice() * quantity;
                                    Cart c = new Cart(cartExist.getID(), accountId, price, quantity, productId);
                                    c.setQuantity(quantity);
                                    c.setTotalPrice(price);
                                    result = cartDao.updateToCart(c);
                                }
                            }
                        }
                    }
                }
                response.sendRedirect(path + "?act=update-cart&status=" + result);
            } else {
                response.sendRedirect("/SWP391-MomAndBaby/login");
            }
        } catch (Exception e) {
            System.out.println("updateCart: " + e);
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
        if (request.getParameter("add-to-cart") != null) {
            this.addToCart(request, response);
        } else if (request.getParameter("btn-update-cart") != null) {
            this.updateCart(request, response);
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
