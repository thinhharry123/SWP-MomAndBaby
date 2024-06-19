<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../components/header.jsp" %>
<jsp:useBean id="currency" class="Utils.CurrencyConverter"></jsp:useBean>
    <section id="main-content">
        <section class="wrapper">
            <!--            <div class="wrapper-box-add">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div id="toast"></div>
                                    <section class="panel">
                                        <div class="panel-body">
                                            <div class="position-center">
                                                <div class="text-center">
                                                    <a href="#myModal" data-toggle="modal" class="btn btn-success">
                                                        Add new
                                                    </a>
                                                </div>
                                                <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
                                                    <div class="modal-dialog" style="width: 90%">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                                                <h4 class="modal-title">Add blog</h4>
                                                            </div>
                                                            <div class="modal-body">
                                                                <form accept-charset="UTF-8" method="post" action="/MomAndBaby/staff/blog" enctype="multipart/form-data">
                                                                    <div class="form-group">
                                                                        <label class="control-label">Customer</label>
                                                                        <select name="customer" id="customerSelect" class="form-control">
                                                                            <option value="">Select customer</option>
        <c:forEach var="account" items="${accounts}">
            <option value="${account.ID}" data-fullname="${account.fullname}" data-email="${account.email}" data-phone="${account.phone}">${account.username} -- MKH: ${account.ID}</option>
        </c:forEach>
    </select>
</div>
<div class="form-group">
    <label>Fullname</label>
    <input id="fullname" required type="text" name="fullname" class="form-control">
    <span class="message_error"></span>
</div>
<div class="form-group">
    <label>Phone</label>
    <input id="phone" required type="text" name="phone" class="form-control">
    <span class="message_error"></span>
</div>
<div class="form-group">
    <label>Email</label>
    <input id="email" required type="text" name="email" class="form-control">
    <span class="message_error"></span>
</div>
<div class="form-group">
    <label>Address</label>
    <input id="address" required type="text" name="adress" class="form-control">
    <span class="message_error"></span>
</div>
<div class="form-group">
    <label>Address detail</label>
    <input id="addressDetail" required type="text" name="addressDetail" class="form-control">
    <span class="message_error"></span>
</div>
<div class="form-group">
    <label class="control-label">Status</label>
    <select name="status" class="form-control">
        <option value="1">Active</option>
        <option value="0">Hidden</option>
    </select>
</div>
<div class="form-group">
    <h4>Search Products</h4>
    <input type="text" id="searchProductInput" placeholder="Enter product name">
    <div id="searchResults"></div>

    <h4>Selected Products</h4>
    <div id="selectedProducts"></div>
</div>
</div>


        <c:set value="${categories}" var="category"></c:set>
        <c:if test="${category.size() == 0}">
            <span  class="btn btn-group-justified btn-danger">
                Please add category, producer and brand before add product
            </span>
        </c:if>
        <c:if test="${category.size() > 0}">
            <button id="add-blog" name="btn-add-blog" type="submit" class="btn btn-group-justified btn-primary">Add</button>
        </c:if>
        </form>
    </div>
</div>
</div>
</div>
</div>
</div>
</section>
</div>-->
        </div>
        </div>
        <div class="table-agile-info">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Bills
                </div>

                <div class="table-responsive" style="padding: 5px;">
                    <script>
                        $(document).ready(function () {
                            $("#product").DataTable();
                        });
                    </script>
                    <form method="post" action="/MomAndBaby/staff/bill">
                        <table id="product" class="table table-striped b-t b-light">
                            <thead>
                                <tr>
                                    <th style="width:20px;">
                                        <label class="i-checks all-check m-b-none">
                                            <input name="delete-item-bill" type="checkbox"><i></i>
                                        </label>
                                    </th>
                                    <th>Bill code</th>
                                    <th>Customer name</th>
                                    <th>Phone</th>
                                    <th>Address</th>
                                    <th>Total</th>
                                    <th>Pay method</th>
                                    <th>Status</th>
                                    <th>Order at</th>
                                    <th>Update at</th>
                                    <th style="width:30px;">Action</th>
                                </tr>
                            </thead>
                            <tbody class="render-data">
                                <c:forEach items="${bills}" var="bill">
                                    <tr>
                                        <td><input type="checkbox" name="delete-item-bill" value="${bill.ID}"></td>
                                        <td>#ORDER${bill.ID}</td>
                                        <td>${bill.customerName}</td>
                                        <td>${bill.phone}</td>
                                        <td>${bill.address}</td>
                                        <td>${currency.currencyFormat(bill.total, "")}</td>
                                        <td>
                                            <c:if test="${bill.payment == 0}">
                                                <span class="label label-info">Cash</span>
                                            </c:if>
                                            <c:if test="${bill.payment == 1}">
                                                <span class="label label-danger">Banking</span>
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${bill.status == 1}">
                                                <span class="label label-primary">
                                                    <i class='bx bxs-message-rounded-add'></i>
                                                    New
                                                </span>
                                            </c:if>
                                            <c:if test="${bill.status == 2}">
                                                <span class="label label-warning">
                                                    <i class='bx bx-show' ></i>
                                                    Accept
                                                </span>
                                            </c:if>
                                            <c:if test="${bill.status == 3}">
                                                <span class="label label-info">
                                                    <i class='bx bx-package' ></i>
                                                    Preparing
                                                </span>
                                            </c:if>
                                            <c:if test="${bill.status == 4}">
                                                <span class="label label-danger">
                                                    <i class='bx bxs-truck' ></i>
                                                    Delivery
                                                </span>
                                            </c:if>
                                            <c:if test="${bill.status == 5}">
                                                <span class="label label-success">
                                                    <i class='bx bxs-check-circle' ></i>
                                                    Finish
                                                </span>
                                            </c:if>
                                            <c:if test="${bill.status == 0}">
                                                <span class="label label-default">
                                                    <i class='bx bx-block' ></i>
                                                    Cancel
                                                </span>
                                            </c:if>
                                        </td>
                                        <td><span class="text-ellipsis">${bill.dateOrder}</span></td>
                                        <td><span class="text-ellipsis">${bill.dateUpdate}</span></td>
                                        <td class="box-action">
                                            <a title="Detail" href="/MomAndBaby/staff/bill/view/${bill.ID}" class="btn-action blue">
                                                <i class='bx bx-slider' ></i>
                                            </a>
                                            <a title="Print bill" href="/MomAndBaby/staff/bill/view/${bill.ID}?act=print-bill" class="btn-action green">
                                                <i class='bx bxs-printer'></i>
                                            </a>
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