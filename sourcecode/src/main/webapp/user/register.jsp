 
 


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Heeader here -->
<%@include file="./components/header.jsp" %>
<!-- /HEADER -->
<div class="section">
    <div class="container">
        <div class="row" style="margin-bottom: 80px; margin-top: 30px">
            <div class="col-md-6">
                <img src="./user/img/main_background_login.jpg" alt="" style="width: 100%" />
            </div>
            <form action="/SWP391-MomAndBaby/register" method="post">
                <div class="col-md-6" style="padding: 100px">
                    <h2>Create an account</h2>
                    <p>Enter your details below</p>
                    <div style="width: 400px; margin-bottom: 20px; margin-top: 20px" class="form-group">
                        <input class="input" type="text" name="fullname" placeholder="Fullname" id="fullname"/>
                        <span class="message_error"></span>
                    </div>
                    <div style="width: 400px; margin-bottom: 20px; margin-top: 20px" class="form-group">
                        <input class="input" type="text" name="username" placeholder="Username" id="username"/>
                        <span class="message_error"></span>
                    </div>
                    <div style="width: 400px; margin-bottom: 20px" class="form-group">
                        <input
                            class="input"
                            type="text"
                            name="email"
                            placeholder="Email"
                            id="email"
                            />
                        <span class="message_error"></span>
                    </div>
                    <div style="width: 400px; margin-bottom: 20px" class="form-group">
                        <input
                            class="input"
                            type="text"
                            name="phone"
                            placeholder="Phone"
                            id="phone"
                            />
                        <span class="message_error"></span>
                    </div>
                    <div style="width: 400px" class="form-group">
                        <input
                            class="input"
                            type="password"
                            name="password"
                            placeholder="Password"
                            id="password"
                            />
                        <span class="message_error"></span>
                    </div>
                    <div style="margin-top: 40px">
                        <button
                            class="btn btn-default"
                            name="register"
                            id="register-account"
                            style="
                            width: 400px;
                            padding: 10px 20px;
                            color: #fff;
                            background-color: #39FF14;
                            border-radius: 10px;
                            border: 2px solid #004953;
                            margin-bottom: 20px;
                            "
                            >
                            <b>Create Account</b>
                        </button>
                    </div>
                    <div>
                        <span style="display: block;color: red; text-align: center">
                            ${messageFailRegister}
                        </span>
                    </div>
                    <div class="text-center">
                        <p>Already have account <a href="/SWP391-MomAndBaby/login">Login</a></p>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- /SECTION -->

<!-- FOOTER -->
<script src="./static-admin/assets/js/validation.js"></script>
<script>
    let regexEmail = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    let fullname = document.querySelector('#fullname'),
            username = document.querySelector('#username'),
            email = document.querySelector('#email'),
            phone = document.querySelector('#phone'),
            password = document.querySelector('#password'),
            btnSubmit = document.querySelector('#register-account');
    const messageEmpty = "Please enter information for this field",
            messageEmail = "Email invalid",
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
<!-- Footer here -->
<%@include file="./components/footer.jsp" %>