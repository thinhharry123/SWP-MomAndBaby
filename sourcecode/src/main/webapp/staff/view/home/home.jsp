<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../components/header.jsp" %>
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
<section id="main-content">
    <section class="wrapper">
        <!-- //market-->
        <div class="market-updates">
            <div class="col-md-3 market-update-gd">
                <div class="market-update-block clr-block-2">
                    <div class="col-md-4 market-update-right">
                        <i class='bx bx-category-alt' ></i>

                    </div>
                    <div class="col-md-8 market-update-left">
                        <h4>Category</h4>
                        <h3>${numberOfCategory}</h3>
                    </div>
                    <div class="clearfix"> </div>
                </div>
            </div>
            <div class="col-md-3 market-update-gd">
                <div class="market-update-block clr-block-4">
                    <div class="col-md-4 market-update-right">
                        <i class='bx bx-buildings'></i>
                    </div>
                    <div class="col-md-8 market-update-left">
                        <h4>Producer</h4>
                        <h3>${numberOfProducer}</h3>
                    </div>
                    <div class="clearfix"> </div>
                </div>
            </div>
            <div class="col-md-3 market-update-gd">
                <div class="market-update-block clr-block-1">
                    <div class="col-md-4 market-update-right">
                        <i class='bx bx-package'></i>
                    </div>
                    <div class="col-md-8 market-update-left">
                        <h4>Product</h4>
                        <h3>${numberOfProduct}</h3>
                    </div>
                    <div class="clearfix"> </div>
                </div>
            </div>
            <div class="col-md-3 market-update-gd">
                <div class="market-update-block clr-block-3">
                    <div class="col-md-4 market-update-right">
                        <i class='bx bx-user'></i>
                    </div>
                    <div class="col-md-8 market-update-left">
                        <h4>Account</h4>
                        <h3>${numberOfAccount}</h3>
                    </div>
                    <div class="clearfix"> </div>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>	
        <!-- //market-->
        <div class="agil-info-calendar">
            <div class="col-md-6 w3agile-notifications">
                <div class="notifications">
                    <!--notification start-->
                    <header class="panel-heading">
                        Notification new bill
                    </header>
                    <div class="notify-w3ls">
                        <c:if test="${newBills != null && newBills.size() > 0}">
                            <c:forEach items="${newBills}" var="bill">
                                <div class="alert alert-success clearfix">
                                    <span class="alert-icon"><i class='bx bxs-component'></i></span>
                                    <div class="notification-info">
                                        <ul class="clearfix notification-meta">
                                            <li class="pull-left notification-sender">
                                                <span>
                                                    <a href="/SWP391-MomAndBaby/staff/bill/view/${bill.ID}">
                                                        ${bill.customerName}
                                                    </a>
                                                </span> just order
                                            </li>
                                            <li class="pull-right notification-time">${bill.dateOrder}</li>
                                        </ul>
                                        <p>
                                            <a href="/SWP391-MomAndBaby/staff/bill/view/${bill.ID}">
                                                Click to change status of order now
                                            </a>
                                        </p>
                                    </div>
                                </div>      
                            </c:forEach>
                        </c:if>
                        <c:if test="${newBills != null && newBills.size() == 0}">
                            <div class="alert alert-info clearfix">
                                <span>
                                    No new bill
                                </span>
                            </div>
                        </c:if>
                    </div>
                    <!--notification end-->
                </div>
            </div>
            <!--Comment -->
            <div class="col-md-6 w3agile-notifications">
                <div class="notifications">
                    <!--notification start-->
                    <header class="panel-heading">
                        Notification feedback
                    </header>
                    <div class="notify-w3ls">
                        <c:if test="${feedbacks != null && feedbacks.size() > 0}">
                            <c:forEach items="${feedbacks}" var="feedback">
                                <c:set value="${getDao.getAccountById(feedback.userID)}" var="accountFeedback" />
                                <div class="alert alert-warning clearfix">
                                    <span class="alert-icon"><i class='bx bxs-message-rounded-dots' ></i></span>
                                    <div class="notification-info">
                                        <ul class="clearfix notification-meta">
                                            <li class="pull-left notification-sender">
                                                <span>
                                                    <a href="/SWP391-MomAndBaby/staff/product/feedback/${getDao.getProductByByComment(feedback.ID)}">
                                                        ${accountFeedback.username}
                                                    </a>
                                                </span>
                                                <c:if test="${feedback.dateUpdate != null}">
                                                    just update comment
                                                </c:if>
                                                <c:if test="${feedback.dateUpdate == null}">
                                                    just comment
                                                </c:if>
                                            </li>
                                            <li class="pull-right notification-time">
                                                <c:if test="${feedback.dateUpdate != null}">
                                                    ${feedback.dateUpdate}
                                                </c:if>
                                                <c:if test="${feedback.dateUpdate == null}">
                                                    ${feedback.datePost}
                                                </c:if>
                                            </li>
                                        </ul> 
                                        <p>
                                            <a href="/SWP391-MomAndBaby/staff/product/feedback/${getDao.getProductByByComment(feedback.ID)}">
                                                Click to change status of comment now
                                            </a>
                                        </p>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${feedbacks != null && feedbacks.size() == 0}">
                            <div class="alert alert-info clearfix">
                                <span>
                                    No new comment
                                </span>
                            </div>
                        </c:if>
                    </div>
                    <!--notification end-->
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        <!-- tasks -->
        <div class="agile-last-grids">
            <div class="col-md-6 stats-info widget">
                <div class="stats-info-agileits">
                    <div class="stats-title">
                        <h4 class="title">Product of category</h4>
                    </div>
                    <div class="stats-body">
                        <ul class="list-unstyled">
                            <c:forEach items="${categories}" var="cat">
                                <li>
                                    ${cat.name}
                                    <span class="pull-right">${getNumberProduct.getNumberProductByCategory(cat.ID)}</span>  
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-6 agile-last-left agile-last-right">
                <div class="agile-last-grid">
                    <div class="area-grids-heading">
                        <h3>Order status</h3>
                    </div>
                    <div class="box-filter-by-date">
                        <div class="group-input">
                            <label for="fromStatus">From: </label>
                            <input type="date" id="fromStatus"/>
                        </div>
                        <div class="group-input">
                            <label for="toStatus">To </label>
                            <input type="date" id="toStatus"/>
                        </div>
                        <div class="group-input group-btn">
                            <button class="btn btn-default" 
                                    onclick="filterStatisticBill('#fromStatus', '#toStatus')"
                                    >
                                Go</button>
                        </div>
                    </div>
                    <div id="status-bill"></div>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        <!-- //tasks -->
        <div class="agileits-w3layouts-stats">
            <div class="col-md-12 stats-info stats-last widget-shadow">
                <div class="stats-last-agile">
                    <table class="table stats-table ">
                        <thead>
                            <tr>
                                <th>S.NO</th>
                                <th>PRODUCT</th>
                                <th>STATUS</th>
                                <th>SOLD</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.topFiveProduct}" var="topProduct" varStatus="loopStatus">
                                <tr>
                                    <th scope="row">${loopStatus.index + 1}</th>
                                    <td>${topProduct.name}</td>
                                    <td>
                                        <c:if test="${topProduct.status == 1}">
                                            <span class="label label-success">Active</span>
                                        </c:if>
                                        <c:if test="${topProduct.status == 0}">
                                            <span class="label label-default">Hidden</span>
                                        </c:if>
                                    </td>
                                    <td><h5>${topProduct.sold}</h5></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="clearfix"> </div>
        </div>
    </section>
    <script>statisOrderStaff()</script>
    <!--main content end-->
    <%@include file="../components/footer.jsp" %>