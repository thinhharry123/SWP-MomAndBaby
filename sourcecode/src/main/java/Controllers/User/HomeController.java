/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers.User;

import DAO.BannerDAO;
import DAO.BrandDAO;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import Model.Banner;
import Model.Brand;
import Model.Category;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author HP
 */
public class HomeController extends HttpServlet {
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        BannerDAO bannerDao = new BannerDAO();
        ProductDAO productDao = new ProductDAO();
        CategoryDAO categoryDao = new CategoryDAO();
        BrandDAO brandDao = new BrandDAO();
        List<Product> productsDeal = productDao.getProductByPriority(2);
        List<Product> productsFeature = productDao.getProductByPriority(3);
        List<Product> productsNormal = productDao.getProductByPriority(1);
        List<Banner> banners = bannerDao.getTop5Banner();
        List<Category> categories = categoryDao.getCategoryInHome();
        List<Brand> brands = brandDao.getTopBrand();
        request.setAttribute("brands", brands);
        request.setAttribute("banners", banners);
        request.setAttribute("categories", categories);
        request.setAttribute("productsDeal", productsDeal);
        request.setAttribute("productsFeature", productsFeature);
        request.setAttribute("productsNormal", productsNormal);
        request.getRequestDispatcher("./user/index.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
