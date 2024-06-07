/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.Admin;

import DAO.CategoryDAO;
import Model.Category;
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


public class CategoryController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private  static CategoryDAO categoryDao;
    public CategoryController(){
        this.categoryDao = new CategoryDAO();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CategoryController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryController at " + request.getContextPath() + "</h1>");
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
        if(path.endsWith("/SWP391-MomAndBaby/admin/category")){
          this.showAllCategory(request, response);
        }else{
            String paths[] = path.split("/");
            int id = validate.getInt(paths[paths.length-1]);
            if(path.startsWith("/SWP391-MomAndBaby/admin/category/update/")){
                this.getCurrentCategoryUpdate(request, response, id);
            }else if(path.startsWith("/SWP391-MomAndBaby/admin/category/delete")){
                this.deleteCategory(request, response, id);
            }
        }
    }
    
    private void showAllCategory(HttpServletRequest request, HttpServletResponse response){
        try{
            List<Category> categories = categoryDao.allCategory();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/admin/view/category/category.jsp").forward(request, response);
        }catch(Exception e){
            System.out.println("Show category: "+e);
        }
    }//get all category using dao 
    private void getCurrentCategoryUpdate(HttpServletRequest request, HttpServletResponse response, int id){
    try{
        Category c = categoryDao.getCategoryByID(id);
        if(c!=null){
            request.setAttribute("currentCategory", c);
            request.getRequestDispatcher("/admin/view/category/updateCategory.jsp");
        }else{
            response.sendRedirect("/SWP391-MomAndBaby/admin/404");
        }
    }catch(Exception e){
        System.out.println("Get current category to update: "+e);
    }
}//get the current category based on id?
private void deleteCategory(HttpServletRequest request, HttpServletResponse response,int id){
    try{
        int result = categoryDao.delete(id);
        response.sendRedirect("/SWP391-MomAndBaby/admin/category?act=delete&status="+result);
    }catch(Exception e){
        System.out.println("Delete category: "+e);
    }
}//delete a category based on id
private void addCategory(HttpServletRequest request, HttpServletResponse response){
    try{
        Validation validate = new Validation();
        String name = request.getParameter("name");
        Category isExist = categoryDao.getCategoryByName(name);
        //check if category's name has exist
        if(isExist !=null){
            String url ="/SWP391-MomAndBaby/admin/category?act=add-new&status=" + 2;
            response.sendRedirect(url);
            return;
        }//it has exist then redirect and return
        int status = validate.getInt(request.getParameter("status"));
        //get the current status
        LocalDateTime dateTime = LocalDateTime.now();
        Timestamp date = Timestamp.valueOf(dateTime);
        //get timestamp for when adding category
        Category c = new Category(0, name, date, null, status);      
        //-> just created,havent been updated yet ->add a category with null as dateUpdate
        int result = categoryDao.insert(c);
        //simply insert in the db
        String url ="/SWP391-MomAndBaby/admin/category?act=add-new&status="+result;
        response.sendRedirect(url);
        //redirect to the url
    }catch(Exception e){
        System.out.println("add category: "+e);
    }
}private void updateCategory(HttpServletRequest request,HttpServletResponse response){
    try{
        Validation validate = new Validation();
        int id = validate.getInt(request.getParameter("id"));
        //get int from request scope
        Category currentCategory = categoryDao.getCategoryByID(id);
        //get currentCategory by id
        String name = request.getParameter("name");
        //get name parameter
        Category isExist = categoryDao.getCategoryByName(name);
        //check if that category exist ?
        if(isExist!=null && !isExist.getName().toLowerCase().equals(currentCategory.getName().toLowerCase())){//doesnt found that category's name
                String url ="/SWP391-MomAndBaby/admin/category?act=update&status="+2;
                response.sendRedirect(url);
                return;
            
        }
        //if found ?
        int status = validate.getInt(request.getParameter("status"));//get status from request scope
        LocalDateTime dateTime = LocalDateTime.now();
        Timestamp dateUpdate = Timestamp.valueOf(dateTime);//get update time
        Category c = new Category(id, name, null, dateUpdate, status);
        int result = categoryDao.update(c);//update category
        String url ="/SWP391-MomAndBaby/admin/category?act=update&status="+result;
        response.sendRedirect(url);//redirect
    }catch(Exception e){
        System.out.println("Update category: "+e);
    }
}
private void deleteCategories(HttpServletRequest request, HttpServletResponse response) {
    try{
        Validation validate = new Validation();
        String[] allIdChecked = request.getParameterValues("delete-category-item");
        if(allIdChecked == null){//if no id was check for delete
            response.sendRedirect("/SWP391-MomAndBaby/admin/category?act=delete?status=0");
            return;
        }
        //else 
        int status = 0;
        for(String idS: allIdChecked){
            int id = validate.getInt(idS);
            int result = categoryDao.delete(id);
            if(result==1){
                status = 1;
            }
        }
        String url = "/SWP391-MomAndBaby/admin/"
                + "category?act=delete&status="+status;
        response.sendRedirect(url);
    }catch(Exception e){
        System.out.println("Delete categories: "+e);
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
        if(request.getParameter("btn-add-category")!=null){
            this.addCategory(request, response);
        }else if(request.getParameter("btn-update-category")!=null){
            this.updateCategory(request, response);
        }else if(request.getParameter("btn-delete-categories")!=null){
            this.deleteCategories(request, response);
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
