<%-- 
    Document   : detailProduct
    Created on : Jun 19, 2024, 2:31:51 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../components/header.jsp" %>
<jsp:useBean id="calculateFeedback" class="Utils.CalculateFeedback" />
<section id="main-content">
    <section class="wrapper">
        <div class="mail-w3agile">
            <!-- page start-->
            <form action="/SWP391-MomAndBaby/staff/product" method="post">
                <div class="row">
                    <div class="col-sm-12 mail-w3agile">
                        <section class="panel">
                            <header class="panel-heading wht-bg">
                                <h4 class="gen-case comment-header-custom">All comment for <b>"${product.name}"</b></h4>
                                <h4 class="gen-case">Feedback (${feedbacks.size()}) <==> <b>Average star(${calculateFeedback.totalStar(feedbacks)}) </b></h4>
                            </header>
                            <div class="panel-body minimal">
                                <div class="mail-option">
                                    <div class="chk-all">
                                        <div class="pull-left mail-checkbox all-check">
                                            <input type="checkbox" class="btn-check-all">
                                        </div>
                                    </div>
                                    <div class="btn-group hidden-phone">
                                        <a data-toggle="dropdown" href="#" class="btn mini blue">
                                            Option
                                            <i class="fa fa-angle-down "></i>
                                        </a>
                                        <ul class="dropdown-menu dropdown-menu-customer">
                                            <li>
                                                <button class="btn btn-success" name="btn-active-feedback">Active all choose</button>
                                            </li>
                                            <li>
                                                <button class="btn btn-default" name="btn-view-feedback">Make as viewed</button>
                                            </li>
                                            <li>
                                                <button class="btn btn-danger" name="btn-ban-feedback">Ban all choose</button>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="table-inbox-wrap ">
                                    <table class="table table-inbox table-hover">
                                        <tbody>
                                            <c:forEach items="${feedbacks}" var="feedback">
                                                <c:set value="${getDao.getAccountById(feedback.userID)}" var="accountFeedback" />
                                                <tr class="unread">
                                                    <td class="inbox-small-cells">
                                                        <input type="hidden" name="idProduct" value="${product.ID}">
                                                        <input type="checkbox" class="mail-checkbox" name="item-feedback" value="${feedback.ID}">
                                                    </td>
                                                    <td class="inbox-small-cells"><i class="fa fa-star"></i></td>
                                                    <td class="view-message  dont-show">${accountFeedback.username}</td>
                                                    <td class="view-message ">${feedback.feedback}</td>
                                                    <td class="view-message ">
                                                        <span class="label label-warning">
                                                            ${feedback.star}
                                                            star
                                                        </span>
                                                    </td>
                                                    <td class="view-message  inbox-small-cells"><i class="fa fa-paperclip"></i></td>
                                                    <td class="view-message  text-right">${feedback.datePost}</td>
                                                    <td class="view-message text-right">
                                                        <c:if test="${feedback.status == 1}">
                                                            <span class="label label-success">Active</span>
                                                        </c:if>
                                                        <c:if test="${feedback.status == 0}">
                                                            <span class="label label-primary">New</span>
                                                        </c:if>
                                                        <c:if test="${feedback.status == 2}">
                                                            <span class="label label-danger">Ban</span>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${feedbacks.size() == 0}">
                                                <tr>
                                                    <td colspan="10">No feedback for this product</td>
                                                </tr>
                                            </c:if>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </section>
                    </div>
                </div>
            </form>
            <!-- page end-->
        </div>
    </section>
</section>
