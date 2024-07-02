<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./components/header.jsp" %>
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css"/>
<jsp:useBean id="statusOrder" class="Utils.ConvertStatusOrder"></jsp:useBean>
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
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Status</th>
                    <th>Total</th>
                    <th>Action</th>
                </thead>
                    <tbody>
                        <c:forEach items="${bills}" var="bill" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${bill.customerName}</td>
                                <td>${bill.phone}</td>
                                <td>${bill.address}</td>
                                <td><span class="${statusOrder.statusTag(bill.status)}">
                                        ${statusOrder.statusText(bill.status)}
                                    </span></td>
                                <td>${bill.total}</td>
                                <td>
                                    <a href="/SWP391-MomAndBaby/account/history-order/detail/${bill.ID}" title="More">
                                        <i class="fa fa-eye"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-md-9">
                
            </div>
        </div>
    </div>
</div>
<%@include file="./components/footer.jsp" %>            