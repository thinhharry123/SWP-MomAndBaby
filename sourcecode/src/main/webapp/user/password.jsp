<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./components/header.jsp" %>

<!-- BREADCRUMB -->
<div id="breadcrumb" class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <div class="col-md-10">
                <ul class="breadcrumb-tree">
                    <li><a href="/SWP391-MomAndBaby">Home</a></li>
                    <li><a>Password</a></li>
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
            <form action="/SWP391-MomAndBaby/account" method="post">
                <div class="col-md-9">
                    <div
                        class="order-summary"
                        style="
                        border: 1px solid black;
                        padding: 20px;
                        margin-bottom: 60px;
                        "
                        >
                        <div class="order-products">
                            <div class="order-col" style="margin-top: 20px">
                                <strong style="color: #db4444; font-size: 20px"
                                        ><i class="fa fa-pencil"></i> Edit Your password</strong
                                >
                            </div>
                            <div class="form-group">
                                <input
                                    id="old-password"
                                    class="input"
                                    type="text"
                                    name="old-password"
                                    placeholder="Current Password"
                                    />
                                <span class="message_error"></span>
                            </div>
                            <div class="form-group">
                                <input
                                    id="new-password"
                                    class="input"
                                    type="password"
                                    name="new-password"
                                    placeholder="New Password"
                                    />
                                <span class="message_error"></span>
                            </div>

                            <div class="text-right" style="margin-top: 40px">
                                <button
                                    id="change-password"
                                    class="btn btn-default"
                                    name="btn-change-password"
                                    style="
                                    padding: 10px;
                                    color: #fff;
                                    background-color: #db4444;
                                    margin-bottom: 20px;
                                    "
                                    >
                                    Save Changes
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<script src="./static-admin/assets/js/validation.js"></script>
<script>
    let regexEmail = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    let oldPassword = document.querySelector('#old-password'),
            newPassword = document.querySelector('#new-password'),
            btnSubmit = document.querySelector('#change-password');
    const messagePassword = "Password must be from 8 character.";
    const inputsToValidateCheck = [
        {element: newPassword, message: messagePassword, regex: /^([a-zA-Z0-9.,!#$%&'*+/=?^_]{8,})?$/, type: "text", isEmpty: false},
        {element: oldPassword, message: messagePassword, regex: /^([a-zA-Z0-9.,!#$%&'*+/=?^_]{8,})?$/, type: "text", isEmpty: false}
    ];
    validation(inputsToValidateCheck, btnSubmit);
</script>
<%@include file="./components/footer.jsp" %>