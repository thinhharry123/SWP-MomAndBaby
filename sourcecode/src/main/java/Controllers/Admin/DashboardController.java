/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers.Admin;

import DAO.AccountDAO;
import DAO.BillDAO;
import DAO.CategoryDAO;
import DAO.FeedbackDAO;
import DAO.ProducerDAO;
import DAO.ProductDAO;
import Model.Bill;
import Model.Category;
import Model.Feedback;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;


public class DashboardController extends HttpServlet {
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
        String path = request.getRequestURI();
        if (path.endsWith("/admin")) {
          AccountDAO accountDao = new AccountDAO();
            ProductDAO productDao = new ProductDAO();
            CategoryDAO categoryDao = new CategoryDAO();
            ProducerDAO producerDao = new ProducerDAO();
            BillDAO billDao = new BillDAO();
            FeedbackDAO feedbackDao = new FeedbackDAO();
            List<Category> categories = categoryDao.allCategory();
            List<Bill> newBills = billDao.getBillByStatus(1);
            List<Feedback> feedbacks = feedbackDao.allFeedbackByNew();
            int numberOfAccount = accountDao.allAccountByStaff().size();
            int numberOfProduct = productDao.getAll().size();
            int numberOfProducer = producerDao.allProducer().size();
            int numberOfCategory = categories.size();
            List<Product> topFiveProduct = productDao.getTopFiveProduct();
            request.setAttribute("feedbacks", feedbacks);
            request.setAttribute("newBills", newBills);
            request.setAttribute("categories", categories);
            request.setAttribute("numberOfProduct", numberOfProduct);
            request.setAttribute("numberOfAccount", numberOfAccount);
            request.setAttribute("numberOfCategory", numberOfCategory);
            request.setAttribute("numberOfProducer", numberOfProducer);
            request.setAttribute("topFiveProduct", topFiveProduct);
            request.getRequestDispatcher("/admin/view/home/home.jsp").forward(request, response);
        }
    } 

    
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
