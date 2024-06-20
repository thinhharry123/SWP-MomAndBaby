/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Staff;

import DAO.BrandDAO;
import DAO.CategoryDAO;
import DAO.ImgDescriptionDAO;
import DAO.ProducerDAO;
import DAO.ProductDAO;
import Model.Brand;
import Model.Category;
import Model.ImgDescription;
import Model.Producer;
import Model.Product;
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
import java.util.Collection;
import java.util.List;

/**
 *
 * @author HP
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50)
public class ProductStaffController extends HttpServlet {

    private static CategoryDAO categoryDao;
    private static ProducerDAO producerDao;
    private static ProductDAO productDao;
    private static BrandDAO brandDao;

    public ProductStaffController() {
        this.categoryDao = new CategoryDAO();
        this.producerDao = new ProducerDAO();
        this.productDao = new ProductDAO();
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
            out.println("<title>Servlet ProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductController at " + request.getContextPath() + "</h1>");
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
        if (path.endsWith("/SWP391-MomAndBaby/staff/product")) {
            this.showAllProduct(request, response);
        } else {
            String paths[] = path.split("/");
            int id = validate.getInt(paths[paths.length - 1]);
            if (path.startsWith("/SWP391-MomAndBaby/staff/product/update/")) {
                boolean isUpdate = this.getCurrentProductUpdate(request, response, id);
                if (isUpdate) {
                    request.getRequestDispatcher("/staff/view/product/updateProduct.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/SWP391-MomAndBaby/staff/404");
                }
            } else if (path.startsWith("/SWP391-MomAndBaby/staff/product/view/")) {
                boolean isView = this.getCurrentProductUpdate(request, response, id);
                if (isView) {
                    request.getRequestDispatcher("/staff/view/product/detailProduct.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/SWP391-MomAndBaby/staff/404");
                }
            } else if (path.startsWith("/SWP391-MomAndBaby/staff/product/comment/")) {
//                boolean isView = this.getCurrentProductComment(request, response, slug);
//                if (isView) {
//                    request.getRequestDispatcher("/admin/view/product/commentProduct.jsp").forward(request, response);
//                } else {
//                    response.sendRedirect("/SWP391-MomAndBaby/admin/404");
//                }
            } else if (path.startsWith("/SWP391-MomAndBaby/staff/product/delete/")) {
                this.deleteProduct(request, response, id);
            }
        }
    }

    private void showAllProduct(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Category> categories = categoryDao.allCategory();
            List<Producer> producers = producerDao.allProducer();
            List<Product> products = productDao.getAll();
            List<Brand> brands = brandDao.allBrand();
            request.setAttribute("products", products);
            request.setAttribute("categories", categories);
            request.setAttribute("producers", producers);
            request.setAttribute("brands", brands);
            request.getRequestDispatcher("/staff/view/product/product.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Show product: " + e);
        }
    }

    private boolean getCurrentProductUpdate(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            ImgDescriptionDAO imgDao = new ImgDescriptionDAO();
            Product currentProduct = productDao.getProductByID(id);
            if (currentProduct == null) {
                return false;
            } else {
                List<ImgDescription> imgDesc = imgDao.getAllImgDescriptionByProduct(id);
                Category category = categoryDao.getCategoryByID(currentProduct.getCategoryID());
                Producer producer = producerDao.getProducerByID(currentProduct.getProducerID());
                Brand brand = brandDao.getBrandByID(currentProduct.getBrandID());
                request.setAttribute("product", currentProduct);
                request.setAttribute("imgDesc", imgDesc);
                request.setAttribute("producer", producer);
                request.setAttribute("category", category);
                request.setAttribute("brand", brand);
                List<Category> categories = categoryDao.allCategory();
                List<Producer> producers = producerDao.allProducer();
                List<Brand> brands = brandDao.allBrand();
                request.setAttribute("categories", categories);
                request.setAttribute("producers", producers);
                request.setAttribute("brands", brands);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Get current product update: " + e);
        }
        return false;
    }
    
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response, int id) {
        try {
            String pathProductImg = "./uploads/product/";
            String pathUploadImg = getServletContext().getRealPath(pathProductImg);
            Validation validate = new Validation();
            Upload upload = new Upload();
            ImgDescriptionDAO imgDao = new ImgDescriptionDAO();
            Product product = productDao.getProductByID(id);
            List<ImgDescription> imgDesc = imgDao.getAllImgDescriptionByProduct(product.getID());
            int resultDelete = productDao.delete(id);
            if (resultDelete >= 1) {
                String fileName[] = product.getMainImg().split("/");
                String pathUploadDesc = getServletContext().getRealPath("./uploads/descriptions/");
                imgDao.delete(product.getID());
            }
            String url = "/SWP391-MomAndBaby/staff/product?act=delete&status=";
            if (resultDelete >= 1) {
                url += 1;
            } else {
                url += 0;
            }
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Delete product: " + e);
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
        if (request.getParameter("btn-add-product") != null) {
            this.addProduct(request, response);
        } else if (request.getParameter("btn-update-product") != null) {
            this.updateProduct(request, response);
        } else if (request.getParameter("btn-delete-products") != null) {
            this.deleteProducts(request, response);
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            String pathProduct = "./uploads/product/";
            String uploadPath = getServletContext().getRealPath(pathProduct);
            Upload upload = new Upload();
            Validation validate = new Validation();
            String name = request.getParameter("name");
            String model = request.getParameter("model");
            float oldPrice = Float.parseFloat(request.getParameter("oldPrice"));
            float newPrice = Float.parseFloat(request.getParameter("newPrice"));
            if(newPrice >= oldPrice) {
                response.sendRedirect("/SWP391-MomAndBaby/staff/product?act=add-new-product&status=2");
                return;
            }
            int quantity = validate.getInt(request.getParameter("quantity"));
            String desc = request.getParameter("desc");
            int priority = validate.getInt(request.getParameter("priority"));
            int categoryID = validate.getInt(request.getParameter("categoryID"));
            int producerID = validate.getInt(request.getParameter("producerID"));
            int brandID = validate.getInt(request.getParameter("brandID"));
            Part mainImgParth = request.getPart("imgMain");
            String fileNameImg = pathProduct + upload.uploadImg(mainImgParth, uploadPath);
            LocalDateTime dateTime = LocalDateTime.now();
            Timestamp date = Timestamp.valueOf(dateTime);
            int status = validate.getInt(request.getParameter("status"));
            int sold = 0;
            Product p = new Product(0, name, oldPrice, newPrice, desc, date, null, fileNameImg, status, quantity,
                    sold, model, priority, categoryID, producerID, brandID);
            int curentIdProduct = productDao.insert(p);
//      insert into color and desc product
            String pathDesc = "./uploads/descriptions/";
            String uploadPathDesc = getServletContext().getRealPath(pathDesc);
            ImgDescriptionDAO imgDescDao = new ImgDescriptionDAO();
            for (Part part : request.getParts()) {
                if (part.getName().equals("imgDesc")) {
                    String nameImgDescPath = pathDesc + upload.uploadImg(part, uploadPathDesc);
                    ImgDescription imgDesc = new ImgDescription(0, nameImgDescPath, curentIdProduct);
                    imgDescDao.insert(imgDesc);
                }
            }
            int statusResponse = 0;
            if (curentIdProduct > 0) {
                statusResponse = 1;
            }
            String url = "/SWP391-MomAndBaby/staff/product?act=add-new-product&status=" + statusResponse;
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Insert product: " + e);
        }
    }
    
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            String pathProduct = "./uploads/product/";
            String uploadPath = getServletContext().getRealPath(pathProduct);
            Upload upload = new Upload();
            Validation validate = new Validation();
            int idProduct = validate.getInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String model = request.getParameter("model");
            float oldPrice = Float.parseFloat(request.getParameter("oldPrice"));
            float newPrice = Float.parseFloat(request.getParameter("newPrice"));
            if(newPrice >= oldPrice) {
                response.sendRedirect("/SWP391-MomAndBaby/staff/product?act=update-product&status=2");
                return;
            }
            int quantity = validate.getInt(request.getParameter("quantity"));
            int sold = validate.getInt(request.getParameter("sold"));
            String desc = request.getParameter("desc");
            int priority = validate.getInt(request.getParameter("priority"));
            int categoryID = validate.getInt(request.getParameter("categoryID"));
            int producerID = validate.getInt(request.getParameter("producerID"));
            int brandID = validate.getInt(request.getParameter("brandID"));
            String oldMainImg = request.getParameter("oldMainImg");
            Part mainImgParth = request.getPart("imgMain");
            String fileNameImg = upload.uploadImg(mainImgParth, uploadPath);
            String imgMainUpload = pathProduct;
            if (fileNameImg != null) {
                imgMainUpload += fileNameImg;
            } else {
                imgMainUpload = oldMainImg;
            }
            LocalDateTime dateTime = LocalDateTime.now();
            Timestamp dateUpdate = Timestamp.valueOf(dateTime);
            int status = validate.getInt(request.getParameter("status"));
            Product p = new Product(idProduct, name, oldPrice, newPrice, desc, null, dateUpdate, imgMainUpload, status, quantity,
                    sold, model, priority, categoryID, producerID, brandID);
            int statusUpdate = productDao.update(p);
//      insert into color and desc product
            updateDetailsProduct(request.getParts(), idProduct, request);
            int statusResponse = 0;
            if (statusUpdate > 0) {
                statusResponse = 1;
            }
            String url = "/SWP391-MomAndBaby/staff/product?act=update-product&status=" + statusResponse;
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Update product: " + e);
        }
    }

    private boolean updateDetailsProduct(Collection<Part> parts, int idProduct, HttpServletRequest request) {
        Upload upload = new Upload();
        String pathDesc = "./uploads/descriptions/";
        String uploadPathDesc = getServletContext().getRealPath(pathDesc);
        ImgDescriptionDAO imgDescDao = new ImgDescriptionDAO();
        boolean isDescUpdate = false;
        boolean isColorUpdate = false;
        int indexColor = 0;
        for (Part part : parts) {
            if (part.getName().equals("imgDesc")) {
                String fileNameUploadImgDesc = upload.uploadImg(part, uploadPathDesc);
                if (fileNameUploadImgDesc != null && !isDescUpdate) {
                    List<ImgDescription> imgDesc = imgDescDao.getAllImgDescriptionByProduct(idProduct);
                    imgDescDao.delete(idProduct);
                    isDescUpdate = true;
                }
                if (isDescUpdate) {
                    String nameImgDescPath = pathDesc + fileNameUploadImgDesc;
                    ImgDescription imgDesc = new ImgDescription(0, nameImgDescPath, idProduct);
                    imgDescDao.insert(imgDesc);
                }
            }
        }
        return true;
    }
    
    private void deleteProducts(HttpServletRequest request, HttpServletResponse response) {
        try {
            Validation validate = new Validation();
            String pathProductImg = "./uploads/product/";
            String pathUploadImg = getServletContext().getRealPath(pathProductImg);
            ImgDescriptionDAO imgDao = new ImgDescriptionDAO();
            Upload upload = new Upload();
            String[] listDeleteProduct = request.getParameterValues("delete-product-item");
            if(listDeleteProduct == null) {
                response.sendRedirect("/SWP391-MomAndBaby/staff/product?act=delete&status=0");
                return;
            }
            boolean isDelete = false;
            for (String item : listDeleteProduct) {
                int id = validate.getInt(item);
                Product product = productDao.getProductByID(id);
                List<ImgDescription> imgDesc = imgDao.getAllImgDescriptionByProduct(id);
                int result = productDao.delete(id);
                if (result >= 1) {
                    isDelete = true;
                    String fileName[] = product.getMainImg().split("/");
                    String pathUploadDesc = getServletContext().getRealPath("./uploads/descriptions/");
                    String pathUploadColor = getServletContext().getRealPath("./uploads/colors/");
                    imgDao.delete(product.getID());
                }
            }
            String url = "/SWP391-MomAndBaby/staff/product?act=delete&status=";
            if (isDelete) {
                url += 1;
            } else {
                url += 0;
            }
            response.sendRedirect(url);
        } catch (Exception e) {
            System.out.println("Delete categories:  " + e);
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
