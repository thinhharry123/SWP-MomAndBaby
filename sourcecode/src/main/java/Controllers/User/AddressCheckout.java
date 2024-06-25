/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers.User;

import DAO.DistrictDao;
import DAO.WardDao;
import Model.District;
import Model.Ward;
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

<<<<<<< HEAD
/**
 *
 * @author HP
 */
=======

>>>>>>> e86cf60bcdccfe45f9b23cf7ef9e7f45846daf6e
public class AddressCheckout extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddressCheckout</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddressCheckout at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

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
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonData = mapper.readTree(request.getReader());
            String province = jsonData.get("province").asText();
            String ward = jsonData.get("district").asText();
            Validation validate = new Validation();
            if (!province.equals("")) {
                int idProvince = validate.getInt(province);
                DistrictDao districtDao = new DistrictDao();
                List<District> districts = districtDao.getDistrict(idProvince);
                PrintWriter out = response.getWriter();
                out.print(this.district(districts));
            } else if (!ward.equals("")) {
                int idWard = validate.getInt(ward);
                WardDao wardDao = new WardDao();
                List<Ward> wards = wardDao.getWard(idWard);
                PrintWriter out = response.getWriter();
                out.print(this.ward(wards));
            }
        } catch (IOException e) {
            System.out.println("Eror: " + e);
        }

    }

    private String district(List<District> districts) {
        String districtHtml = "<option value=\"\">Choose  a district</option>";
        for (District district : districts) {
            districtHtml += "<option value=\"" + district.getDistrict_id() + "\">" + district.getName() + "</option>";
        }
        return districtHtml;
    }

    private String ward(List<Ward> wards) {
        String wardHtml = "";
        for (Ward ward : wards) {
            wardHtml += "<option value=\"" + ward.getWardsID()+ "\">" + ward.getName() + "</option>";
        }
        return wardHtml;
    }

}
