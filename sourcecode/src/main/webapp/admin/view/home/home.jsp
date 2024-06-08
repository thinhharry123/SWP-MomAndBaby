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
        <!-- tasks -->
        <div class="agile-last-grids">
            <div class="col-md-6 agile-last-left agile-last-middle">
                <div class="agile-last-grid">
                    <div class="area-grids-heading">
                        <h3>Order</h3>
                    </div>
                    <div class="box-filter-by-date">
                        <div class="group-input">
                            <label for="fromOrder">From: </label>
                            <input type="date" id="fromOrder"/>
                        </div>
                        <div class="group-input">
                            <label for="toOrder">To </label>
                            <input type="date" id="toOrder"/>
                        </div>
                        <div class="group-input group-btn">
                            <button class="btn btn-default" onclick="filterStatisticRevenue('#fromOrder', '#toOrder')">Go</button>
                        </div>
                    </div>
                    <div id="revenue"></div>
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
            <div class="col-md-4 stats-info widget">
                <div class="stats-info-agileits">
                    <div class="stats-title">
                        <h4 class="title">Product of category</h4>
                    </div>
                    <div class="stats-body">
                        <ul class="list-unstyled">
                            <c:forEach items="${categories}" var="cat">
                                <li>
                                    ${cat.name}
                                    <span class="pull-right">${getDao.getNumberOfProductByCategory(cat.ID)}</span>  
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-8 stats-info stats-last widget-shadow">
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
    <script>statisOrder()</script>
    <!--main content end-->
<%@include file="../components/footer.jsp" %>