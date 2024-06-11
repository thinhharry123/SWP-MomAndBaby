/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Admin;

import DAO.BrandDAO;
import DAO.ProducerDAO;
import Model.Banner;
import Model.Brand;
import Model.Producer;
import Utils.DateCustom;
import Utils.Upload;
import Utils.Validation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author HP
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50)   // 50 MB
public class BrandController extends HttpServlet {

    private static BrandDAO brandDao;

    public BrandController() {
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
            out.println("<title>Servlet BrandController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BrandController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        if (path.endsWith("/SWP391-MomAndBaby/admin/brand")) {
            this.showAllBrand(request, response);
        } else {
            String paths[] = path.split("/");
            int id = validate.getInt(paths[paths.length - 1]);
            if (path.startsWith("/SWP391-MomAndBaby/admin/brand/update/")) {
                this.getCurrentBrandUpdate(request, response, id);
            } else if (path.startsWith("/SWP391-MomAndBaby/admin/brand/delete/")) {
                this.deleteBrand(request, response, id);
            }
        }
    }

    private void showAllBrand(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Brand> producers = brandDao.allBrand();
            request.setAttribute("brands", producers);
            request.getRequestDispatcher("/admin/view/brand/brand.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Show brand: " + e);
        }
    }

    private void getCurrentBrandUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            Brand c = brandDao.getBrandByID(id);
            if (c != null) {
                request.setAttribute("currentBrand", c);
                request.getRequestDispatcher("/admin/view/brand/updateBrand.jsp").forward(request, response);
            } else {
                response.sendRedirect("/SWP391-MomAndBaby/admin/404");
            }

        } catch (Exception e) {
            System.out.println("Get current brand update: " + e);
        }
    }

    private void deleteBrand(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            int result = brandDao.delete(id);
            String url = "/SWP391-MomAndBaby/admin/brand?act=delete&status=" + result;
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Delete brand: " + e);
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
        if (request.getParameter("btn-add-brand") != null) {
            this.addBrand(request, response);
        } else if (request.getParameter("btn-update-brand") != null) {
            this.updateBrand(request, response);
        } else if (request.getParameter("btn-delete-brands") != null) {
            this.deleteBrands(request, response);
        }
    }

    private void addBrand(HttpServletRequest request, HttpServletResponse response) {
        try {
            Validation validate = new Validation();
            Upload upload = new Upload();
            DateCustom date = new DateCustom();
            String name = request.getParameter("name");
            Brand isExist = brandDao.getBrandByName(name);
            if(isExist != null) {
                response.sendRedirect("/SWP391-MomAndBaby/admin/brand?act=add-new&status=" + 2);
                return;
            }
            Part mainImgParth = request.getPart("image-brand");
            String pathProduct = "./uploads/brand/";
            String uploadPath = getServletContext().getRealPath(pathProduct);
            String nameImgBanner = upload.uploadImg(mainImgParth, uploadPath);
            int type_message = 0;
            if (nameImgBanner != null) {
                String fileNameSaveDb = pathProduct + nameImgBanner;
                int status = validate.getInt(request.getParameter("status"));
                Timestamp createAt = date.getCurrentTime();
                Brand b = new Brand(-1, name, fileNameSaveDb, createAt, null, status);
                int result = brandDao.insert(b);
                if (result >= 1) {
                    type_message = 1;
                } else {
                    type_message = 0;
                }
            } else {
                type_message = 0;
            }
            response.sendRedirect("/SWP391-MomAndBaby/admin/brand?act=add-new&status=" + type_message);
        } catch (Exception e) {
            System.out.println("Insert brand: " + e);
        }
    }

    private void updateBrand(HttpServletRequest request, HttpServletResponse response) {
        try {
            Validation validate = new Validation();
            Upload upload = new Upload();
            DateCustom date = new DateCustom();
            int id = validate.getInt(request.getParameter("id"));
            Brand currentBrand = brandDao.getBrandByID(id);
            String name = request.getParameter("name");
            Brand isExist = brandDao.getBrandByName(name);
            if(isExist != null && !currentBrand.getName().toLowerCase().equals(name.toLowerCase())) {
                response.sendRedirect("/SWP391-MomAndBaby/admin/brand?act=update&status=" + 2);
                return;
            }
            int status = validate.getInt(request.getParameter("status"));
            Timestamp modifiedAt = date.getCurrentTime();
            Part mainImgParth = request.getPart("image-brand");
            String pathProduct = "./uploads/brand/";
            String uploadPath = getServletContext().getRealPath(pathProduct);
            String nameImgBanner = upload.uploadImg(mainImgParth, uploadPath);
            String fileNameSaveDb = request.getParameter("old-image");
            if (nameImgBanner != null) {
                fileNameSaveDb = pathProduct + nameImgBanner;
            }
            Brand b = new Brand(id, name, fileNameSaveDb, null, modifiedAt, status);
            int result = brandDao.update(b);
            String url = "/SWP391-MomAndBaby/admin/brand?act=update&status=" + result;
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Update producer: " + e);
        }
    }

    private void deleteBrands(HttpServletRequest request, HttpServletResponse response) {
        try {
            Validation validate = new Validation();
            String[] allIdChecked = request.getParameterValues("delete-brand-item");
            if (allIdChecked == null) {
                response.sendRedirect("/SWP391-MomAndBaby/admin/brand?act=delete&status=0");
                return;
            }
            int status = 0;
            for (String idS : allIdChecked) {
                int id = validate.getInt(idS);
                int result = brandDao.delete(id);
                if (result == 1) {
                    status = 1;
                }
            }
            String url = "/SWP391-MomAndBaby/admin/brand?act=delete&status=" + status;
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Delete brands:  " + e);
        }
    }

}
