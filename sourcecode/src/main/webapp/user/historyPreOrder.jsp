<%-- 
    Document   : historyOrder
    Created on : May 22, 2024, 8:20:57 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./components/header.jsp" %>
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css"/>
<!-- BREADCRUMB -->
<div id="breadcrumb" class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <div class="col-md-10">
                <ul class="breadcrumb-tree">
                    <li><a href="/SWP391-MomAndBaby">Home</a></li>
                    <li><a>History order</a></li>
                </ul>
            </div>
            <div class="col-md-2">
                <ul>
                    <li>Welcome! <a>${accountLogin.username}</a></li>
                </ul>
            </div>
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /BREADCRUMB -->

<!-- Section -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <div class="col-md-3">
                <div style="margin: 10px">
                    <div>
                        <label>Manage My Account</label>
                    </div>
                    <div style="margin-left: 30px; margin-top: 10px">
                        <a href="/SWP391-MomAndBaby/account">My profile</a>
                    </div>
                    <div style="margin-left: 30px; margin-top: 5px">
                        <a href="/SWP391-MomAndBaby/account/password">Change password</a>
                    </div>
                </div>

                <div style="margin: 10px">
                    <div>
                        <label>My Order</label>
                    </div>
                    <div style="margin-left: 30px; margin-top: 10px">
                        <a href="/SWP391-MomAndBaby/account/history-order">History order</a>
                    </div>
                    <div style="margin-left: 30px; margin-top: 10px">
                        <a href="/SWP391-MomAndBaby/account/pre-order">Pre order</a>
                    </div>
                </div>
            </div>
            <div class="col-md-9">
                <table class="table-responsive table table-hover" id="history">
                    <thead>
                    <th>No</th>
                    <th>Name</th>
                    <th>Product</th>
                    <th>Image</th>
                    <th>Status</th>
                    <th>Action</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${preOrders}" var="preOrder" varStatus="status">
                            <c:set var="productPreOrder" value="${getDao.getProduct(preOrder.productID)}" />
                            <c:set var="accountPreOrder" value="${getDao.getAccountById(preOrder.accountID)}" />
                            <c:if test="${productPreOrder != null && accountPreOrder != null}">
                                <tr>
                                    <td>${status.index + 1}</td>
                                    <td>${accountPreOrder.username}</td>
                                    <td>
                                        <a href="/SWP391-MomAndBaby/product/detail/${productPreOrder.ID}">${productPreOrder.name}</a>
                                    </td>
                                    <td>
                                        <img src="${productPreOrder.mainImg}" width="100px" height="70px" alt="${productPreOrder.name}"/>
                                    </td>
                                    <td>
                                        <c:if test="${preOrder.status == 1}">
                                            <span class="label label-success">Viewed</span>
                                        </c:if>
                                        <c:if test="${preOrder.status == 2}">
                                            <span class="label label-success">Sent mail</span>
                                        </c:if>
                                        <c:if test="${preOrder.status == 0}">
                                            <span class="label label-default">New</span>
                                        </c:if>
                                    </td>
                                    <td>
                                        <a onclick="return confirmDelete(${preOrder.ID})" href="/SWP391-MomAndBaby/account/pre-order/delete/${preOrder.ID}" title="Delete">
                                            <i class="fa fa-recycle"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script src="https://cdn.datatables.net/2.0.7/js/dataTables.js"></script>
<script>
                                            new DataTable('#history');
</script>
<script>
    function confirmDelete(id) {
        Swal.fire({
            title: "Delete pre order",
            text: "Are you sure to delete this pre order?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, do it!"
        }).then((result) => {
            if (result.isConfirmed) {
                const url = "/SWP391-MomAndBaby/account/pre-order/delete/" + id;
                window.location.href = url;
            } else {
                Swal.fire('Request canceled.');
            }
        });
        return false;
    }
</script>
<!-- /section -->
<%@include file="./components/footer.jsp" %>