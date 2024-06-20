<%-- 
    Document   : preOrder
    Created on : Jun 20, 2024, 3:31:07 PM
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
                        Pre order
                    </div>

                    <div class="table-responsive" style="padding: 5px;">
                        <script>
                            $(document).ready(function () {
                                $("#product").DataTable();
                            });
                        </script>
                        <form method="post" action="/SWP391-MomAndBaby/staff/preorder">
                            <div class="wrapper-box-btn">
                                <button name="btn-mail-preorder" onclick="return confirm('Are you sure to send mail?')" class="btn btn-sm btn-warning">
                                    Send mail all check
                                </button>
                                <button name="btn-view-preorder" onclick="return confirm('Are you sure to make viewed?')" class="btn btn-sm btn-primary">
                                    Mark as view
                                </button>
                                <button name="btn-delete-preorder" onclick="return confirm('Are you sure to delete?')" class="btn btn-sm btn-danger">
                                    Delete all check
                                </button>
                            </div>
                            <table id="product" class="table table-striped b-t b-light">
                                <thead>
                                    <tr>
                                        <th style="width:20px;">
                                            <label class="i-checks all-check m-b-none">
                                                <input type="checkbox"><i></i>
                                            </label>
                                        </th>
                                        <th>Customer</th>
                                        <th>Email</th>
                                        <th>Product</th>
                                        <th>Image</th>                                  
                                        <th>Status</th>
                                        <th style="width:30px;">Action</th>
                                    </tr>
                                </thead>
                                <tbody class="render-data">
                                <c:forEach items="${preOrders}" var="preorder">
                                    <c:set var="productPreOrder" value="${getDao.getProduct(preorder.productID)}" />
                                    <c:set var="accountPreOrder" value="${getDao.getAccountById(preorder.accountID)}" />
                                    <tr>
                                        <td><input type="checkbox" name="preOrder-item" value="${preorder.ID}"></td>
                                        <td><a href="/SWP391-MomAndBaby/staff/account/update/${accountPreOrder.ID}">${accountPreOrder.username}</a></td>
                                        <td><span class="text-ellipsis">${accountPreOrder.email}</span></td>
                                        <td>
                                            <a href="/SWP391-MomAndBaby/staff/product/view/${productPreOrder.ID}">${productPreOrder.name}</a>
                                        </td>
                                        <td>
                                            <img src="${productPreOrder.mainImg}" alt="${productPreOrder.name}"/>
                                        </td>
                                        <td>
                                            <c:if test="${preorder.status == 1}">
                                                <span class="label label-success">Viewed</span>
                                            </c:if>
                                            <c:if test="${preorder.status == 2}">
                                                <span class="label label-success">Sent mail</span>
                                            </c:if>
                                            <c:if test="${preorder.status == 0}">
                                                <span class="label label-default">New</span>
                                            </c:if>
                                        </td>
                                        <td class="box-action">

                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </section>   
    <%@include file="../components/footer.jsp" %>
