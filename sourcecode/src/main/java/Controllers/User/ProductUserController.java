/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import DAO.BrandDAO;
import DAO.CategoryDAO;
import DAO.FeedbackDAO;
import DAO.ImgDescriptionDAO;
import DAO.ProductDAO;
import Model.Brand;
import Model.Category;
import Model.Feedback;
import Model.ImgDescription;
import Model.Product;
import Utils.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


public class ProductUserController extends HttpServlet {

    private static CategoryDAO categoryDao;
    private static BrandDAO brandDao;
    private static ProductDAO productDao;
    private static Validation validate;
    private static FeedbackDAO feedbackDao;
    private static final int numberProductInPage = 3;

    public ProductUserController() {
        this.categoryDao = new CategoryDAO();
        this.productDao = new ProductDAO();
        this.validate = new Validation();
        this.feedbackDao = new FeedbackDAO();
        this.brandDao = new BrandDAO();
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
            out.println("<title>Servlet ProductUserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductUserController at " + request.getContextPath() + "</h1>");
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
        if (path.endsWith("/SWP391-MomAndBaby/product/") || path.endsWith("/SWP391-MomAndBaby/product")) {
            this.productPage(request, response, 1, numberProductInPage);
        } else if (path.startsWith("/SWP391-MomAndBaby/product/page-")) {
            String paths[] = path.split("/");
            String pages[] = paths[paths.length - 1].split("page-");
            int page = validate.getInt(pages[pages.length - 1]);
            if (page == -1) {
                response.sendRedirect("/SWP391-MomAndBaby/404");
            }
            this.productPage(request, response, page, numberProductInPage);
        }  else if (path.startsWith("/SWP391-MomAndBaby/product/detail/")) {
            String paths[] = path.split("/");
            int id = validate.getInt(paths[paths.length - 1]);
            Product p = productDao.statusIsActive(id);
            if (p == null) {
                response.sendRedirect("/SWP391-MomAndBaby/404");
            } else {
                ImgDescriptionDAO imgDescDao = new ImgDescriptionDAO();
                List<ImgDescription> imgDesc = imgDescDao.getAllImgDescriptionByProduct(id);
                List<Product> productsRelative = productDao.getAllProductActiveRelative(p);
                List<Feedback> feedbacks = feedbackDao.allFeedbackByProduct(id);
                request.setAttribute("feedbacks", feedbacks);
                request.setAttribute("product", p);
                request.setAttribute("imgDesc", imgDesc);
                request.setAttribute("productsRelative", productsRelative);
                request.getRequestDispatcher("/user/detailProduct.jsp").forward(request, response);
            }
        }
    }

    private void productPage(HttpServletRequest request, HttpServletResponse response, int page, int pageSize) {
        try {
            String type = request.getParameter("type");
            int id = validate.getInt(request.getParameter("id"));
            List<Category> categories = categoryDao.getCategoryActive();
            List<Brand> brands = brandDao.getBrandActive();
            List<Product> products = new ArrayList<>();
            int allProduct = 0;
            String urlPage = "product";
            String key = "";
            if (type != null && type.equals("category")) {
                products = productDao.getProductsByPage(page, pageSize, "category", id);
                allProduct = productDao.getAllProductActive("category", id).size();
                key = "?type=category&id=" + id;
                request.setAttribute("idCategory", id);
            } else if(type != null && type.equals("brand")) {
                products = productDao.getProductsByPage(page, pageSize, "brand", id);
                allProduct = productDao.getAllProductActive("brand", id).size();
                request.setAttribute("idBrand", id);
                key = "?type=brand&id=" + id;
            } else {
                products = productDao.getProductsByPage(page, pageSize, "");
                allProduct = productDao.getAllProductActive("").size();
                type = "Shop";
            }
            if (products.size() == 0 && page != 1) {
                response.sendRedirect("/SWP391-MomAndBaby/404");
            } else {
                request.setAttribute("categories", categories);
                request.setAttribute("brands", brands);
                request.setAttribute("products", products);
                request.setAttribute("page", page);
                request.setAttribute("sizeProduct", allProduct);
                request.setAttribute("type", type);
                request.setAttribute("urlPage", urlPage);
                request.setAttribute("key", key);
                request.getRequestDispatcher("/user/product.jsp").forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Product page: " + e);
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
