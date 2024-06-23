/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import DAO.BrandDAO;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import Model.Brand;
import Model.Category;
import Model.Product;
import Utils.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;


public class SearchProductController extends HttpServlet {

    private static final int numberProductInPage = 9;

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
        HttpSession session = request.getSession();
        Validation validate = new Validation();
        if (path.endsWith("/SWP391-MomAndBaby/product/search")) {
            String keyword = request.getParameter("keyword");
            session.setAttribute("key", keyword);
            this.searchPage(request, response, keyword, 1, numberProductInPage);
        } else if (path.startsWith("/SWP391-MomAndBaby/product/search/page-")) {
            String key = (String) session.getAttribute("key");
            String paths[] = path.split("/");
            String pages[] = paths[paths.length - 1].split("page-");
            int page = validate.getInt(pages[pages.length - 1]);
            if (page == -1) {
                response.sendRedirect("/SWP391-MomAndBaby/404");
            }
            this.searchPage(request, response, key, page, numberProductInPage);
        }
    }

    private void searchPage(HttpServletRequest request, HttpServletResponse response, String keyword, int page, int pageSize) {
        try {
            BrandDAO brandDao = new BrandDAO();
            CategoryDAO categoryDao = new CategoryDAO();
            ProductDAO productDao = new ProductDAO();
            List<Product> products = productDao.seachProduct(keyword, page, pageSize);
            List<Category> categories = categoryDao.getCategoryActive();
            List<Brand> brands = brandDao.getBrandActive();
            request.setAttribute("categories", categories);
            request.setAttribute("brands", brands);
            request.setAttribute("keyword", keyword);
            request.setAttribute("products", products);
            request.setAttribute("page", page);
            request.setAttribute("sizeProduct", productDao.seachProduct(keyword).size());
            request.getRequestDispatcher("/user/search.jsp").forward(request, response);
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
//        processRequest(request, response);
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
