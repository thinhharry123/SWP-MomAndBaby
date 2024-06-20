<%-- 
    Document   : account
    Created on : Jun 18, 2024, 12:28:31 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../components/header.jsp" %>
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
                                                <h4 class="modal-title">Add account</h4>
                                            </div>
                                            <div class="modal-body">
                                                <form role="form" method="post" action="/SWP391-MomAndBaby/staff/account">
                                                    <div class="form-group">
                                                        <label>Full name</label>
                                                        <input id="fullname" required type="text" name="fullname" class="form-control">
                                                        <span class="message_error"></span>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Email</label>
                                                        <input id="email" required type="text" name="email" class="form-control">
                                                        <span class="message_error"></span>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Phone</label>
                                                        <input id="phone" required type="text" name="phone" class="form-control">
                                                        <span class="message_error"></span>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Username</label>
                                                        <input id="username" required type="text" name="username" class="form-control">
                                                        <span class="message_error"></span>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Password</label>
                                                        <input id="password" required type="password" name="password" class="form-control">
                                                        <span class="message_error"></span>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label">Role</label>
                                                        <select name="role" class="form-control">
                                                                <option value="2">User</option>
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label">Status</label>
                                                        <select name="status" class="form-control">
                                                            <option value="1">Active</option>
                                                            <option value="0">Hidden</option>
                                                        </select>
                                                    </div>
                                                    <button id="add-account" name="btn-add-account" type="submit" class="btn btn-group-justified btn-primary">Add</button>
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
                    Account
                </div>
               
                <div class="table-responsive" style="padding: 5px;">
                    <script>
                        $(document).ready(function () {
                            $("#category").DataTable();
                        });
                    </script>
                    <form method="post" action="/SWP391-MomAndBaby/staff/account">
                        <div class="wrapper-box-btn">
                            <button name="btn-delete-accounts" onclick="return confirm('Are you sure to delete?')" class="btn btn-sm btn-danger">
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
                                    <th>Full name</th>
                                    <th>Email</th>
                                    <th>Phone</th>
                                    <th>Username</th>
                                    <th>Date at</th>
                                    <th>Role</th>
                                    <th>Status</th>
                                    <th style="width:30px;">Action</th>
                                </tr>
                            </thead>
                            <tbody class="render-data">
                                <c:forEach items="${accounts}" var="account">
                                    <c:if test="${account.username != sessionScope.usernameAdmin}">
                                        <tr>
                                            <td><input type="checkbox" name="delete-account-item" value="${account.ID}"></td>
                                            <td>${account.fullname}</td>
                                            <td>${account.email}</td>
                                            <td>${account.phone}</td>
                                            <td>${account.username}</td>
                                            <td><span class="text-ellipsis">${account.date}</span></td>
                                            <td>
                                                <span class="label label-warning">User</span>
                                            </td>
                                            <td>
                                                <c:if test="${account.status == 1}">
                                                    <span class="label label-success">Active</span>
                                                </c:if>
                                                <c:if test="${account.status == 0}">
                                                    <span class="label label-default">Hidden</span>
                                                </c:if>
                                            </td>
                                            <td class="box-action">
                                                <a href="/SWP391-MomAndBaby/staff/account/update/${account.ID}" class="active btn-action green">
                                                    <i class="bx bx-edit"></i>
                                                </a>
                                                <a onclick="return confirm('Are you sure to delete?')" 
                                                   href="/SWP391-MomAndBaby/staff/account/delete/${account.ID}" 
                                                   class="active btn-action orange">
                                                    <i class='bx bx-trash'></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:if>
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
                                                    let regexEmail = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                                                    let fullname = document.querySelector('#fullname'),
                                                            username = document.querySelector('#username'),
                                                            email = document.querySelector('#email'),
                                                            phone = document.querySelector('#phone'),
                                                            password = document.querySelector('#password'),
                                                            btnSubmit = document.querySelector('#add-account');
                                                    const messageEmpty = "Please enter information for this field.",
                                                            messageEmail = "Email invalid.",
                                                            messagePassword = "Password must be from 8 character.",
                                                            messagePhone = "Phone number invalid.",
                                                    messageUsername = "Username must be from 8 character.";
                                                    const inputsToValidateCheck = [
                                                        {element: password, message: messagePassword, regex: /^([a-zA-Z0-9.,!#$%&'*+/=?^_]{8,})?$/, type: "text", isEmpty: false},
                                                        {element: email, message: messageEmail, regex: regexEmail, type: "text", isEmpty: false},
                                                        {element: phone, message: messagePhone, regex: /(84|0[3|5|7|8|9])+([0-9]{8})\b/g, type: "text", isEmpty: false},
                                                        {element: username, message: messageUsername, regex: /^([a-zA-Z0-9.,!#$%&'*+/=?^_]{8,})?$/, type: "text", isEmpty: false},
                                                        {element: fullname, message: messageEmpty, regex: /^.{1,}$/, type: "text", isEmpty: false}
                                                    ];
                                                    validation(inputsToValidateCheck, btnSubmit);
    </script>
    <%@include file="../components/footer.jsp" %>
