/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Staff;

import DAO.BannerDAO;
import Model.Banner;
import Utils.DateCustom;
import Utils.NumberCustom;
import Utils.Upload;
import Utils.Validation;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.sql.Timestamp;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50)   // 50 MB
public class BannerController extends HttpServlet {

    private static BannerDAO bannerDao;

    public BannerController() {
        this.bannerDao = new BannerDAO();
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
        Validation validate = new Validation();
        String path = request.getRequestURI();
        if (path.endsWith("/SWP391-MomAndBaby/staff/banner")) {
            this.getAllBanner(request, response);
        } else {
            String paths[] = path.split("/");
            int id = validate.getInt(paths[paths.length - 1]);
            if (path.startsWith("/SWP391-MomAndBaby/staff/banner/delete")) {
                this.delete(request, response, id);
            } else if (path.startsWith("/SWP391-MomAndBaby/staff/banner/update")) {
                this.currentUpdate(request, response, id);
            }
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            BannerDAO bannerDao = new BannerDAO();
            Banner banner = bannerDao.getBannerById(id);
            if (banner != null) {
                int result = bannerDao.delete(id);
                int type_message = 0;
                if (result >= 1) {
                    type_message = 1;
                } else {
                    type_message = 0;
                }
                response.sendRedirect("/SWP391-MomAndBaby/staff/banner?act=delete&status=" + type_message);
            } else {
                response.sendRedirect("/SWP391-MomAndBaby/staff/404");
            }
        } catch (Exception e) {
            System.out.println("All banner: " + e);
        }
    }

    private void getAllBanner(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Banner> banners = bannerDao.allBanner();
            request.setAttribute("banners", banners);
            request.getRequestDispatcher("/staff/view/banner/banner.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Get all banner: " + e);
        }
    }

    private void currentUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            BannerDAO bannerDao = new BannerDAO();
            Banner banner = bannerDao.getBannerById(id);
            if (banner != null) {
                request.setAttribute("banner", banner);
                request.getRequestDispatcher("/staff/view/banner/updateBanner.jsp").forward(request, response);
            } else {
                response.sendRedirect("/SWP391-MomAndBaby/staff/404");
            }
        } catch (Exception e) {
            System.out.println("Current banner: " + e);
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
        if (request.getParameter("btn-add-banner") != null) {
            this.addNew(request, response);
        } else if (request.getParameter("btn-update-banner") != null) {
            this.update(request, response);
        } else  if(request.getParameter("btn-delete-banners") != null) {
            this.deleteBanners(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        try {
            NumberCustom number = new NumberCustom();
            BannerDAO bannerDao = new BannerDAO();
            Upload upload = new Upload();
            DateCustom date = new DateCustom();
            int idBanner = number.getInt(request.getParameter("id"));
            Part mainImgParth = request.getPart("image-banner");
            String pathProduct = "./uploads/banner/";
            String nameBanner = request.getParameter("name-banner").trim();
            String uploadPath = getServletContext().getRealPath(pathProduct);
            String nameImgBanner = upload.uploadImg(mainImgParth, uploadPath);
            String fileNameSaveDb = request.getParameter("old-image");
            if (nameImgBanner != null) {
                fileNameSaveDb = pathProduct + nameImgBanner;
            }
            int status = number.getInt(request.getParameter("status"));
            Timestamp modifiedAt = date.getCurrentTime();
            Banner b = new Banner(idBanner, fileNameSaveDb, nameBanner, null, modifiedAt, status);
            int result = bannerDao.update(b);
            int type_message = 0;
            if (result >= 1) {
                type_message = 1;
            } else {
                type_message = 0;
            }
            response.sendRedirect("/SWP391-MomAndBaby/staff/banner?act=update&status=" + type_message);
        } catch (Exception e) {
            System.out.println("Update banner: " + e);
        }
    }

    private void addNew(HttpServletRequest request, HttpServletResponse response) {
        try {
            NumberCustom number = new NumberCustom();
            BannerDAO bannerDao = new BannerDAO();
            Upload upload = new Upload();
            DateCustom date = new DateCustom();
            HttpSession session = request.getSession();
            Part mainImgParth = request.getPart("image-banner");
            String nameBanner = request.getParameter("name-banner").trim();
            String pathProduct = "./uploads/banner/";
            String uploadPath = getServletContext().getRealPath(pathProduct);
            String nameImgBanner = upload.uploadImg(mainImgParth, uploadPath);
            int type_message = 0;
            if (nameImgBanner != null) {
                String fileNameSaveDb = pathProduct + nameImgBanner;
                int status = number.getInt(request.getParameter("status"));
                Timestamp createAt = date.getCurrentTime();
                Banner b = new Banner(0, fileNameSaveDb, nameBanner, createAt, null, status);
                int result = bannerDao.insert(b);
                if (result >= 1) {
                    type_message = 1;
                } else {
                    type_message = 0;
                }
            } else {
                type_message = 0;
            }
            response.sendRedirect("/SWP391-MomAndBaby/staff/banner?act=add-new&status=" + type_message);
        } catch (Exception e) {
            System.out.println("Add new banner: " + e);
        }
    }

    private void deleteBanners(HttpServletRequest request, HttpServletResponse response) {
        try {
            String uploadPath = getServletContext().getRealPath("./uploads/banners/");
            Validation validate = new Validation();
            Upload upload = new Upload();
            String[] idChecked = request.getParameterValues("deleteBanner");
            if (idChecked == null) {
                response.sendRedirect("/SWP391-MomAndBaby/staff/banner?act=delete&status=2");
                return;
            }
            int isDelete = 0;
            for (String idDelete : idChecked) {
                int id = validate.getInt(idDelete);
                Banner b = bannerDao.currentBanner(id);
                int result = bannerDao.delete(id);
                if (result == 1) {
                    isDelete = 1;
                }
            }
            String url = "/SWP391-MomAndBaby/staff/banner?act=delete&status=" + isDelete;
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Delete banners: " + e);
        }
    }

}
