<%-- 
    Document   : product
    Created on : Jun 19, 2024, 2:26:52 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../components/header.jsp" %>
<jsp:useBean id="currency" class="Utils.CurrencyConverter"></jsp:useBean>
    <section id="main-content">
        <section class="wrapper">
        <div class="table-agile-info">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Products
                </div>
                
                <div class="table-responsive" style="padding: 5px;">
                    <script>
                        $(document).ready(function () {
                            $("#product").DataTable();
                        });
                    </script>
                        <table id="product" class="table table-striped b-t b-light">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Product name</th>
                                    <th>Image</th>
                                    <th>Price</th>
                                    <th>Price sale</th>
                                    <th>Quantity</th>
                                    <th>Sold</th>
                                    <th>Date at</th>
                                    <th>Date update</th>
                                    <th>Status</th>
                                    <th style="width:30px;">Action</th>
                                </tr>
                            </thead>
                            <tbody class="render-data">
                            <c:forEach items="${products}" var="product" varStatus="index">
                                    <tr>
                                        <td>${index.index + 1}</td>
                                        <td>${product.name}</td>
                                        <td>
                                            <img src="${product.mainImg}" alt="alt"/>
                                        </td>
                                        <td><span class="text-ellipsis">${currency.currencyFormat(product.oldPrice, "")}</span></td>
                                        <td><span class="text-ellipsis">${currency.currencyFormat(product.newPrice, "")}</span></td>
                                        <td>
                                            <c:if test="${product.quantity >=1}">
                                                <span>${product.quantity}</span>
                                            </c:if>
                                            <c:if test="${product.quantity == 0}">
                                                <span class="label label-danger">Sold out</span>
                                            </c:if>
                                        </td>
                                        <td><span class="text-ellipsis">${product.sold}</span></td>
                                        <td><span class="text-ellipsis">${product.datePost}</span></td>
                                        <td><span class="text-ellipsis">${product.dateUpdate}</span></td>
                                        <td>
                                            <c:if test="${product.status == 1}">
                                                <span class="label label-success">Active</span>
                                            </c:if>
                                            <c:if test="${product.status == 0}">
                                                <span class="label label-default">Hidden</span>
                                            </c:if>
                                        </td>
                                        <td class="box-action">
                                            <a href="/SWP391-MomAndBaby/staff/product/view/${product.ID}" class="btn-action blue" title="Detail">
                                                <i class='bx bx-slider'></i>
                                            </a>
                                            <a href="/SWP391-MomAndBaby/staff/product/feedback/${product.ID}" class="btn-action red" title="Comment">
                                                <i class='bx bxs-comment-detail'></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                </div>
            </div>
        </div>
    </section>   
    <script src="./static-admin/ckeditor/ckeditor.js"></script> 
    <script>
                                                CKEDITOR.replace('ck-editor1');
                                                CKEDITOR.replace('ck-editor2');
    </script>
    <script src="./static-admin/assets/js/validation.js"></script>
    <script>
                                                // validation form
                                                const name = document.getElementById('name'),
                                                        model = document.getElementById('model'),
                                                        price = document.getElementById('price'),
                                                        priceSale = document.getElementById('price-sale'),
                                                        available = document.getElementById('available'),
                                                        mainImage = document.getElementById('mainImage'),
                                                        descImage = document.getElementById('descImage'),
                                                        colorImage = document.getElementById('colorImage');
                                                const messageName = "Name product can not empty",
                                                        messageModel = "Model product can not empty",
                                                        messagePrice = "Price product must be a postitive number",
                                                        messagePriceSale = "Price sale product must be a postitive number",
                                                        messageAvailable = "Available must be a integer number",
                                                        messageMainImage = "Please choose a image",
                                                        messageDescImage = "Please choose a image",
                                                        messageColorImage = "Please choose a image";

                                                // array to save all input to check, message show error of each and a string regex to check 
                                                const inputsToValidate = [
                                                    {element: name, message: messageName, regex: /^.{1,}$/},
                                                    {element: model, message: messageModel, regex: /^.{1,}$/},
                                                    {element: price, message: messagePrice, regex: /^[0-9]+(?:\.[0-9]+)?$/},
                                                    {element: priceSale, message: messagePriceSale, regex: /^[0-9]+(?:\.[0-9]+)?$/},
                                                    {element: available, message: messageAvailable, regex: /^\d+$/},
                                                    {element: mainImage, message: messageMainImage, regex: "image"},
                                                    {element: descImage, message: messageDescImage, regex: "image"},
                                                    {element: colorImage, message: messageColorImage, regex: "image"}
                                                ];
                                                validation(inputsToValidate, document.querySelector("#add-product"));
    </script>
    <%@include file="../components/footer.jsp" %>
