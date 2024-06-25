<%-- 
    Document   : orderSuccess
    Created on : Oct 28, 2023, 9:37:05 PM
    Author     : Le Tan Kim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="./components/header.jsp" %>
<<link rel="stylesheet" href="./user/css/order.css"/>
<main>
    <div class="container">
        <div class="wrapper-status">
            <div class="img-status">
                <c:if test="${success}">
                    <img src="https://www.primehairdepot.com/images/order_success_placed.gif" alt="Order sucess">
                </c:if>
                <c:if test="${fail}">
                    <img src="https://www.better-bounce.co.uk/userfiles/Order-failed.png" alt="Order fail">
                </c:if>
            </div>
            <div class="box-content-status">
                <p class="message-status">
                    <c:if test="${success}">
                        Thank you for choosing our product. We appreciate your trust and we’ll do our best to meet your expectations.
                    </c:if>
                    <c:if test="${fail}">
                        I'm sorry the system is busy right now, please try again later
                    </c:if>
                </p>
                <a href="/SWP391-MomAndBaby/product" class="back-to-page">
                    Continue to shopping
                </a>
            </div>
        </div>
    </div>
</main>
<%@include file="./components/footer.jsp" %>