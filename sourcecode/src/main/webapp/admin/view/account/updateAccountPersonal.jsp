<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../components/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <section class="panel">
                    <header class="panel-heading">
                        Update account personal
                    </header>
                    <c:set var="account" value="${account}" />
                        <div class="panel-body">
                            <div class=" form">
                                <form class="cmxform form-horizontal" method="post" action="/SWP391-MomAndBaby/admin/account" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <div class="control-label col-lg-3">
                                            <label>Avatar</label>
                                        </div>
                                        <div class="col-lg-6">
                                            <input onchange="previewImage(event, '.box-img-preview')" type="file" name="avatar" class="form-control">
                                            <div class="box-img-preview preview-avatar">
                                                <img src="${account.avatar}" alt="default avatar">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="control-label col-lg-3">
                                            <label>Full name</label>
                                        </div>
                                        <div class="col-lg-6">
                                            <input value="${account.ID}" type="hidden" name="id">
                                        <input value="${account.fullname}" id="fullname" required type="text" name="fullname" class="form-control">
                                        <span class="message_error"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label col-lg-3">
                                        <label>Email</label>
                                    </div>
                                    <div class="col-lg-6">
                                        <input value="${account.email}" id="email" required type="text" name="email" class="form-control">
                                        <span class="message_error"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label col-lg-3">
                                        <label>Phone</label>
                                    </div>
                                    <div class="col-lg-6">
                                        <input value="${account.phone}" id="phone" required type="text" name="phone" class="form-control">
                                        <span class="message_error"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label col-lg-3">
                                        <label>Username</label>
                                    </div>
                                    <div class="col-lg-6">
                                        <input value="${account.username}" id="username" readonly="true" type="text" name="username" class="form-control">
                                        <span class="message_error"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label col-lg-3">
                                        <label>Password</label>
                                    </div>
                                    <div class="col-lg-6">
                                        <input value="${account.password}" type="hidden" name="oldPassword">
                                        <input id="password" type="password" name="password" class="form-control">
                                        <span class="message_error"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-lg-6">
                                        <input value="${account.role}" type="hidden" name="role">
                                        <input  value="${account.status}" type="hidden" name="status">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="col-lg-offset-3 col-lg-6">
                                        <button id="update-personal" name="btn-update-account-personal" type="submit" class="btn btn-group-justified btn-primary">Save</button>
                                    </div>
                                </div>
                            </form>
                            <a class="btn btn-info btn-back-to-page" href="/admin"><< Back to home</a>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </section>
    <script src="./static-admin/assets/js/validation.js"></script>
    <script>
                                                    let regexEmail = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                                                    const fullname = document.querySelector('#fullname'),
                                                            username = document.querySelector('#username'),
                                                            email = document.querySelector('#email'),
                                                            phone = document.querySelector('#phone'),
                                                            password = document.querySelector('#password'),
                                                            btnSubmit = document.querySelector('#update-personal');
                                                    const messageEmpty = "Hãy nhập thông tin cho trường này",
                                                            messageEmail = "Hãy nhập đúng định dạng email",
                                                            messagePassword = "Mật khẩu phải từ 8 kí tự.",
                                                            messagePhone = "Số điện thoại không hợp lệ.",
                                                        messageUsername = "Username phải từ 8 kí tự.";
                                                    const inputsToValidateCheck = [
                                                        {element: password, message: messagePassword, regex: /^([a-zA-Z0-9.,!#$%&'*+/=?^_]{8,})?$/, type: "text", isEmpty: true},
                                                        {element: email, message: messageEmail, regex: regexEmail, type: "text", isEmpty: false},
                                                        {element: phone, message: messagePhone, regex: /(84|0[3|5|7|8|9])+([0-9]{8})\b/g, type: "text", isEmpty: false},
                                                        {element: username, message: messageUsername, regex: /^([a-zA-Z0-9.,!#$%&'*+/=?^_]{8,})?$/, type: "text", isEmpty: false},
                                                        {element: fullname, message: messageEmpty, regex: /^.{1,}$/, type: "text", isEmpty: false}
                                                    ];
                                                    validation(inputsToValidateCheck, btnSubmit);
    </script>                        
    <%@include file="../components/footer.jsp" %>
