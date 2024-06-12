/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Admin;

import DAO.ProducerDAO;
import Model.Producer;
import Utils.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class ProducerController extends HttpServlet {

    private static ProducerDAO producerDao;

    public ProducerController() {
        this.producerDao = new ProducerDAO();
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
            out.println("<title>Servlet ProducerController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProducerController at " + request.getContextPath() + "</h1>");
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
        Validation validate = new Validation();
        if (path.endsWith("/SWP391-MomAndBaby/admin/producer")) {
            this.showAllProducer(request, response);
        } else {
            String paths[] = path.split("/");
            int id = validate.getInt(paths[paths.length - 1]);
            if (path.startsWith("/SWP391-MomAndBaby/admin/producer/update/")) {
                this.getCurrentProducerUpdate(request, response, id);
            } else if (path.startsWith("/SWP391-MomAndBaby/admin/producer/delete/")) {
                this.deleteProducer(request, response, id);
            }
        }
    }

    private void showAllProducer(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Producer> producers = producerDao.allProducer();
            request.setAttribute("producers", producers);
            request.getRequestDispatcher("/admin/view/producer/producer.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Show producer: " + e);
        }
    }

    private void getCurrentProducerUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            Producer c = producerDao.getProducerByID(id);
            if (c != null) {
                request.setAttribute("currentProducer", c);
                request.getRequestDispatcher("/admin/view/producer/updateProducer.jsp").forward(request, response);
            } else {
                response.sendRedirect("/SWP391-MomAndBaby/admin/404");
            }

        } catch (Exception e) {
            System.out.println("Get current category update: " + e);
        }
    }

    private void deleteProducer(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            int result = producerDao.delete(id);
            String url = "/SWP391-MomAndBaby/admin/producer?act=delete&status=" + result;
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Delete category: " + e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("btn-add-producer") != null) {
            this.addProducer(request, response);
        } else if (request.getParameter("btn-update-producer") != null) {
            this.updateProducer(request, response);
        } else if (request.getParameter("btn-delete-producers") != null) {
            this.deleteProducers(request, response);
        }
    }

    private void addProducer(HttpServletRequest request, HttpServletResponse response) {
        try {
            Validation validate = new Validation();
            String name = request.getParameter("name");
            Producer isExist = producerDao.getProducerByName(name);
            if (isExist != null) {
                String url = "/SWP391-MomAndBaby/admin/producer?act=add-new&status=" + 2;
                response.sendRedirect(url);
                return;
            }
            int status = validate.getInt(request.getParameter("status"));
            LocalDateTime dateTime = LocalDateTime.now();
            Timestamp date = Timestamp.valueOf(dateTime);
            Producer c = new Producer(-1, name, date, null, status);
            int result = producerDao.insert(c);
            String url = "/SWP391-MomAndBaby/admin/producer?act=add-new&status=" + result;
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Insert producer: " + e);
        }
    }

    private void updateProducer(HttpServletRequest request, HttpServletResponse response) {
        try {
            Validation validate = new Validation();
            int id = validate.getInt(request.getParameter("id"));
            Producer currentProducer = producerDao.getProducerByID(id);
            String name = request.getParameter("name");
            Producer isExist = producerDao.getProducerByName(name);
            if (isExist != null && !isExist.getName().toLowerCase().equals(currentProducer.getName().toLowerCase())) {
                String url = "/SWP391-MomAndBaby/admin/producer?act=update&status=" + 2;
                response.sendRedirect(url);
                return;
            }
            int status = validate.getInt(request.getParameter("status"));
            LocalDateTime dateTime = LocalDateTime.now();
            Timestamp dateUpdate = Timestamp.valueOf(dateTime);
            Producer c = new Producer(id, name, null, dateUpdate, status);
            int result = producerDao.update(c);
            String url = "/SWP391-MomAndBaby/admin/producer?act=update&status=" + result;
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Update producer: " + e);
        }
    }

    private void deleteProducers(HttpServletRequest request, HttpServletResponse response) {
        try {
            Validation validate = new Validation();
            String[] allIdChecked = request.getParameterValues("delete-producer-item");
            if (allIdChecked == null) {
                response.sendRedirect("/SWP391-MomAndBaby/admin/producer?act=delete&status=0");
                return;
            }
            int status = 0;
            for (String idS : allIdChecked) {
                int id = validate.getInt(idS);
                int result = producerDao.delete(id);
                if (result == 1) {
                    status = 1;
                }
            }
            String url = "/SWP391-MomAndBaby/admin/producer?act=delete&status=" + status;
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Delete categories:  " + e);
        }
    }

}
