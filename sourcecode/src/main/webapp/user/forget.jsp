
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./components/header.jsp" %>
<!-- /HEADER -->

<!-- SECTION -->
<div class="section">
    <div class="container">
        <div class="row" style="margin-bottom: 80px; margin-top: 30px">
            <div class="col-md-6">
                <img src="./user/img/main_background_login.jpg" alt="" style="width: 100%" />
            </div>
            <div class="col-md-6" style="padding: 100px; margin-top: 70px">
                <h2>Forget password</h2>
                <p>Enter your details below</p>
                <form action="/MomAndBaby/forget" method="post">
                    <div style="width: 400px; margin-bottom: 20px; margin-top: 20px">
                        <input
                            class="input"
                            type="text"
                            name="username"
                            placeholder="Username"
                            required
                            />
                    </div>
                    <div style="width: 400px">
                        <input
                            class="input"
                            type="text"
                            name="email"
                            placeholder="Email"
                            required
                            />
                    </div>
                    <div>
                        <div
                            style="
                            display: flex;
                            justify-content: space-between;
                            align-items: center;
                            margin-top: 40px;
                            "
                            >
                            <button
                                class="btn btn-default"
                                name="reset-password"
                                style="
                                padding: 10px;
                                color: #fff;
                                background-color: #db4444;
                                margin-bottom: 20px;
                                "
                                >
                                Reset password
                            </button>
                            <a href="/MomAndBaby/login">Login?</a>
                        </div>
                        <span style="display: block;color: red">${message}</span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- /SECTION -->

<!-- FOOTER -->
<%@include file="./components/footer.jsp" %>
