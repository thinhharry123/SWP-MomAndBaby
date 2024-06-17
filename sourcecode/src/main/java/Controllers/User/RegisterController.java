/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.User;

import Authentication.AuthUser;
import DAO.AccountDAO;
import Model.Account;
import Utils.Email;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class RegisterController extends HttpServlet {

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
            out.println("<title>Servlet RegisterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
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
        AuthUser au = new AuthUser();
        if (au.isLoginUser(request, response) != null) {
            response.sendRedirect("/SWP391-MomAndBaby");
        } else {//is logged in with user role, has a username
            request.getRequestDispatcher("/user/register.jsp").forward(request, response);
        }
    }

    
    public static void setSessionWithExpiration(HttpSession session, String attributeValue) {
        final HttpSession finalSession = session;
        session.setAttribute("otpConfirm", attributeValue);
        //add the otp to the session
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                finalSession.removeAttribute("otpConfirm");
                //after expire it'll remove the otp so cant confirm
                this.cancel();
            }
        }, 300 * 1000);
    }

    public String generateOTP() {//create a random otp
        int length = 6;
        String digits = "0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder(length);
        //create string with length =6
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(digits.length());
            //select a number in the string based on the index
            otp.append(digits.charAt(index));
            //append the digit at that index in otp
        }
        return otp.toString();
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
        AccountDAO accountDao = new AccountDAO();
        Email e = new Email();
        HttpSession session = request.getSession();
        String msg = "";
        int type_message = 0;
        if (request.getParameter("register") != null) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            Account accountCheck = accountDao.isExistAccount(username, email);
            if (accountCheck != null) {//if username/email is taken
                request.setAttribute("messageFailRegister", "Username or email has been taken");
                request.getRequestDispatcher("/user/register.jsp").forward(request, response);
                return;
            }//else continues
            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            LocalDateTime dt = LocalDateTime.now();
            Timestamp dateCreated = Timestamp.valueOf(dt);
            int roleUser = 2;
            Account a = new Account(0, username, password, email, phone, 1, fullname, dateCreated, roleUser, null);
            if (username.equals("") || password.equals("") || email.equals("") || phone.equals("") || fullname.equals("")) {
                session.setAttribute("messageFailRegister", "Please field all input.");
                session.setMaxInactiveInterval(3);
                response.sendRedirect("/SWP391-MomAndBaby/register");
            } else {
                session.setAttribute("accountRegister", a);
                String otpConfirm = this.generateOTP();
                setSessionWithExpiration(session, otpConfirm);
                String content = "Your OTP code is: " + otpConfirm + ". "
                        + "This OTP code will expire in 5 minutes(300 seconds). "
                        + "Warning: Please do not provide the OTP code to anyone for any reason.";
                boolean isSend = false;
                isSend = e.sendEmail(email, "Confirm account OTP", content, null);
                if (isSend) {
                    type_message = 1;
                    msg = "OTP has been sent to your email.";
                    //otp is send and set in session for verification
                } else {
                    type_message = 0;
                    session.removeAttribute("otpConfirm");
                    msg = "OTP failed to reach your email";
                    //fail to send =>remove otp in session

                }
                request.getRequestDispatcher("/user/otp.jsp").forward(request, response);
            }
        } else if (request.getParameter("confirm-otp") != null) {
            String otpUser = request.getParameter("otp");
            String otpSession = (String) session.getAttribute("otpConfirm");
            String countdownValue = request.getParameter("countdownValue");
            request.setAttribute("timemount", countdownValue);
            if (otpSession != null && otpUser.equals(otpSession)) {
                Account a1 = (Account) session.getAttribute("accountRegister");
                int result = accountDao.insert(a1);//insert new user to DB
                if (result <= 0) {
                    msg = "Account registration failed.";
                    type_message = 0;
                } else {
                    msg = "Successfully registered account.";
                    type_message = 1;
                }
                session.removeAttribute("otpConfirm");
                session.removeAttribute("accountRegister");
                request.setAttribute("message", msg);
                request.setAttribute("type_message", type_message);
                request.getRequestDispatcher("/user/login.jsp").forward(request, response);

            } else if (otpSession == null) {
                //expired => removed otpSession  
                type_message = 0;
                msg = "OTP has expired.";
                request.setAttribute("message", msg);
                request.setAttribute("type_message", type_message);
                request.getRequestDispatcher("/user/otp.jsp").forward(request, response);
            } else {
                type_message = 0;
                msg = "OTP code is incorrect. Try again.";
                request.setAttribute("message", msg);
                request.setAttribute("type_message", type_message);
                request.getRequestDispatcher("/user/otp.jsp").forward(request, response);
            }
        } else if (request.getParameter("resend-otp") != null) {
            //resend otp 
            Account a2 = (Account) session.getAttribute("accountRegister");
            if (a2 != null) {
                String otpConfirm = this.generateOTP();
                session.setAttribute("accountRegister", a2);
                setSessionWithExpiration(session, otpConfirm);
                String content = "Your OTP code is: " + otpConfirm + ". "
                        + "This OTP code is valid for 1 minute."
                        + "Warning: Tuyet Doi does not provide OTP to anyone for any reason.";
                boolean isSend = false;
                isSend = e.sendEmail(a2.getEmail(), "Confirm acccount", content, null);
                if (isSend) {
                    type_message = 1;
                    msg = "New OTP has been sent to your email.";
                } else {
                    session.removeAttribute("otpConfirm");
                    type_message = 0;
                    msg = "Sending OTP failed.";
                }
                request.setAttribute("message", msg);
                request.setAttribute("type_message", type_message);
                request.getRequestDispatcher("/user/otp.jsp").forward(request, response);
            } else {
                request.setAttribute("message", msg);
                request.setAttribute("type_message", type_message);
                request.getRequestDispatcher("/user/register.jsp").forward(request, response);
            }

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
