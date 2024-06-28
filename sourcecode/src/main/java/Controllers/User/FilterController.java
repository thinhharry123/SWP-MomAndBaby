/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import DAO.ProductDAO;
import Model.Product;
import Utils.CurrencyConverter;
import Utils.Sale;
import Utils.Validation;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class FilterController extends HttpServlet {

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
            out.println("<title>Servlet FilterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterController at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Validation validate = new Validation();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonData = mapper.readTree(request.getReader());
            String[] idCategories = mapper.readValue(jsonData.get("idCategory").toString(), String[].class);
            String[] idBrands = mapper.readValue(jsonData.get("idBrands").toString(), String[].class);
            float from = Float.parseFloat(jsonData.get("from").asText());
            float to = Float.parseFloat(jsonData.get("to").asText());
            int time = Integer.parseInt(jsonData.get("time").asText());
            int idCategoryInt[] = new int[idCategories.length];
            int idBrandInt[] = new int[idBrands.length];
            int index = 0;
            for (String id : idCategories) {
                idCategoryInt[index++] = validate.getInt(id);
            }
            index = 0;
            for (String id : idBrands) {
                idBrandInt[index++] = validate.getInt(id);
            }
            ProductDAO productDao = new ProductDAO();
            List<Product> products = productDao.filterProduct(idCategoryInt, idBrandInt, from, to, time);
            String htmlReturn = this.filterProduct(products);
            PrintWriter out = response.getWriter();
            out.print(htmlReturn);
        } catch (Exception e) {
            System.out.println("Filter product: " + e);
        }
    }

    private String filterProduct(List<Product> products) {
        CurrencyConverter currency = new CurrencyConverter();
        Sale sale = new Sale();
        String productReturn = "";
        if (products.size() == 0) {
            productReturn = "<div class=\"box-no-found\">\n"
                    + "                            <img src=\"./user/img/no-product-found.png\" alt=\"Not found\">\n"
                    + "                            <p  class=\"text-not-found\">Sorry, No Product Found</p>\n"
                    + "                          </div>";
            return productReturn;
        }
        int index = 0;
        for (Product pro : products) {
            productReturn += "<div class=\"cart-filter col-md-4 col-xs-6\">\n"
                    + "                            <div class=\"product\">\n"
                    + "                                <div class=\"product-img\">\n"
                    + "                                    <img src=\"" + pro.getMainImg() + "\" alt=\"" + pro.getName() + "\" />\n"
                    + "                                    <div class=\"product-label\">\n";
            if (pro.getNewPrice() > 0) {
                productReturn += "<span class=\"sale\">" + sale.calculateSale(pro.getNewPrice(), pro.getOldPrice()) + "</span>";
            }
            if (pro.getNewPrice() == 0) {
                productReturn += "<span class=\"new\">NEW</span>\n";
            }
            productReturn += "                                    </div>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"product-body\">\n"
                    + "                                    <h3 class=\"product-name\">\n"
                    + "                                        <a href=\"/SWP391-MomAndBaby/product/detail/" + pro.getID() + "\">" + pro.getName() + "</a>\n"
                    + "                                    </h3>\n"
                    + "                                    <h4 class=\"product-price\">\n";
            if (pro.getNewPrice() > 0) {
                productReturn += currency.currencyFormat(pro.getNewPrice(), "VNĐ") + " <del class=\"product-old-price\">" + currency.currencyFormat(pro.getOldPrice(), "VNĐ") + "</del>\n";
            } else {
                productReturn += currency.currencyFormat(pro.getOldPrice(), "VNĐ");
            }
            productReturn += "                                    </h4>\n"
                    + "                                </div>\n"
                    + "<div class=\"add-to-cart\">\n"
                    + "                                    <a class=\"add-to-cart-btn\" href=\"/SWP391-MomAndBaby/cart/add?productID=" + pro.getID() + "&quantity=1&pathUrl=/SWP391-MomAndBaby/product\">\n"
                    + "                                        <i class=\"fa fa-shopping-cart\"></i> add to cart\n"
                    + "                                    </a>\n"
                    + "                                </div>"
                    + "                            </div>\n"
                    + "                        </div>";
            if ((index + 1) % 3 == 0) {
                productReturn += "<div class=\"clearfix visible-md visible-lg\"></div>";
            }
            if ((index + 1) % 2 == 0) {
                productReturn += "<div class=\"clearfix visible-sm visible-xs\"></div>";
            }
            index++;
        }
        return productReturn;
    }

}
