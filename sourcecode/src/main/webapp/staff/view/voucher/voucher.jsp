
<%@page import="java.time.LocalDate"%>
<%@page import="java.sql.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../components/header.jsp" %>
<jsp:useBean id="isActiveVoucher" class="Utils.VoucherActive" />
<%    Date now = Date.valueOf(LocalDate.now());
    request.setAttribute("now", now);
%>
<c:set var="now" value="${now}" />
<section id="main-content">
    <section class="wrapper">
        <div class="wrapper-box-add">
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
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                                <h4 class="modal-title">Add voucher</h4>
                                            </div>
                                            <div class="modal-body">
                                                <form role="form" method="post" action="/SWP391-MomAndBaby/staff/voucher">
                                                    <div class="form-group">
                                                        <label>Name voucher</label>
                                                        <input id="name" required="" type="text" name="name" class="form-control">
                                                        <span class="message_error"></span>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Code(From 3-15 character) </label>
                                                        <input id="code" pattern="[a-zA-Z0-9]{3,}" type="text" name="code" class="form-control">
                                                        <span class="message_error"></span>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Value </label>
                                                        <!--onchange="document.querySelector('.limit-voucher').max = this.value"-->
                                                        <input id="value" required type="text" name="value" class="form-control">
                                                        <span class="message_error"></span>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Limit used:</label>
                                                        <input id="limit" required type="text" name="limit" class="form-control limit-voucher">
                                                        <span class="message_error"></span>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Date start </label>
                                                        <input id="start" required type="datetime-local" name="start"  class="form-control">
                                                        <span class="message_error"></span>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Date end </label>
                                                        <input id="end" required type="datetime-local" name="end" class="form-control">
                                                        <span class="message_error"></span>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label">Status</label>
                                                        <select name="status" class="form-control">
                                                            <option value="1">Active</option>
                                                            <option value="0">Hidden</option>
                                                        </select>
                                                    </div>
                                                    <button id="btn-add-voucher" name="btn-add-voucher" type="submit" class="btn btn-group-justified btn-primary">Add</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
        <div class="table-agile-info">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Vouchers
                </div>

                <div class="table-responsive" style="padding: 5px;">
                    <script>
                        $(document).ready(function () {
                            $("#category").DataTable();
                        });
                    </script>
                    <form method="post" action="/SWP391-MomAndBaby/staff/voucher">
                        <div class="wrapper-box-btn">
                            <button name="btn-delete-vouchers" onclick="return confirm('Are you sure to delete?')" class="btn btn-sm btn-danger">
                                Delete all checked
                            </button>
                        </div>
                        <table id="category" class="table table-striped b-t b-light">
                            <thead>
                                <tr>
                                    <th style="width:20px;">
                                        <label class="i-checks all-check m-b-none">
                                            <input type="checkbox"><i></i>
                                        </label>
                                    </th>
                                    <th>Voucher name</th>
                                    <th>Voucher code</th>
                                    <th>Value</th>
                                    <th>Used</th>
                                    <th>Start</th>
                                    <th>End</th>
                                    <th>Time use</th>
                                    <th>Status</th>
                                    <th style="width:30px;">Action</th>
                                </tr>
                            </thead>
                            <tbody class="render-data">
                                <c:forEach items="${vouchers}" var="voucher">
                                    <tr>
                                        <td><input type="checkbox" name="delete-voucher-item" value="${voucher.id}"></td>
                                        <td>${voucher.name}</td>
                                        <td>
                                            <span class="label label-primary">${voucher.code}</span>
                                        </td>
                                        <td>${voucher.value}</td>
                                        <td>${voucher.used}</td>
                                        <td><span class="text-ellipsis">${voucher.start}</span></td>
                                        <td><span class="text-ellipsis">${voucher.end}</span></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${isActiveVoucher.isActive(voucher)}">
                                                    <span class="label label-warning">Activating</span>
                                                </c:when>
                                                <c:when test="${isActiveVoucher.isActive(voucher) == false}">
                                                    <span class="label label-danger">Expired</span>
                                                </c:when>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:if test="${voucher.status == 1}">
                                                <span class="label label-success">Active</span>
                                            </c:if>
                                            <c:if test="${voucher.status == 0}">
                                                <span class="label label-default">Hidden</span>
                                            </c:if>
                                        </td>
                                        <td class="box-action">
                                            <a href="/SWP391-MomAndBaby/staff/voucher/update/${voucher.id}" class="btn-action green">
                                                <i class="bx bx-edit"></i>
                                            </a>
                                            <a onclick="return confirm('Are you sure to delete?')" 
                                               href="/SWP391-MomAndBaby/staff/voucher/delete/${voucher.id}" 
                                               class="btn-action orange">
                                                <i class='bx bx-trash'></i>
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
    <script src="./static-admin/assets/js/validation.js"></script>
    <script>
        function updateEndMin() {
            const startInput = document.getElementById('start');
            const endInput = document.getElementById('end');
            
            endInput.min = startInput.value;
            
            // Nếu giá trị end hiện tại nhỏ hơn start, đặt lại giá trị end
            if (endInput.value < startInput.value) {
                endInput.value = startInput.value;
            }
        }
        document.addEventListener('DOMContentLoaded', function() {
            const startInput = document.getElementById('start');
            const endInput = document.getElementById('end');
            
            // Lấy thời gian hiện tại
            const now = new Date();
            const year = now.getFullYear();
            const month = String(now.getMonth() + 1).padStart(2, '0');
            const day = String(now.getDate()).padStart(2, '0');
            const hours = String(now.getHours()).padStart(2, '0');
            const minutes = String(now.getMinutes()).padStart(2, '0');
            
            // Định dạng datetime-local
            const datetimeLocal = year+ "-" + month +"-"+ day + "T" + hours+ ":" +minutes;
            console.log(datetimeLocal)
            
            // Thiết lập giá trị min cho cả start và end
            startInput.min = datetimeLocal;
            endInput.min = datetimeLocal;
            
            // Lắng nghe sự kiện thay đổi trên trường start
            startInput.addEventListener('change', updateEndMin);
        });
    </script>
    <script>
        // validation form
        const name = document.getElementById('name'),
                code = document.getElementById('code'),
                value = document.getElementById('value'),
                limit = document.getElementById('limit'),
                start = document.getElementById('start'),
                end = document.getElementById('end');
        const messageName = "Name product can not empty",
                messageCode = "Code must be from 3 to 15 character",
                messageValue = "Value must be a number",
                messageLimit = "Please enter a integer number",
                messageStart = "Date start can not empty",
                messageEnd = "Date end can not empty";
        // array to save all input to check, message show error of each and a string regex to check 
        const inputsToValidate = [
            {element: name, message: messageName, regex: /^.{1,}$/, type: "text", isEmpty: false},
            {element: code, message: messageCode, regex: /^[a-zA-Z0-9]{3,15}$/, type: "text", isEmpty: false},
            {element: value, message: messageValue, regex: /^[0-9]+(?:\.[0-9]+)?$/, type: "text", isEmpty: false},
            {element: limit, message: messageLimit, regex: /^[0-9]+$/, type: "text", isEmpty: false},
            {element: start, message: messageStart, regex: false},
            {element: end, message: messageEnd, regex: false}
        ];
        validation(inputsToValidate, document.querySelector("#btn-add-voucher"));
    </script>
    <%@include file="../components/footer.jsp" %>