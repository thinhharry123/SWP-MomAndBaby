

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- /HEADER --> <!-- will be add later -->

<!-- SECTION -->
<div class="section">
    <div class="container">
        <div class="row" style="margin-bottom: 80px; margin-top: 30px">
            <div class="col-md-6">
                <img src="./user/img/main_background_login.jpg" alt="" style="width: 100%" />
            </div>
            <div class="col-md-6" style="padding: 100px; margin-top: 70px">
                <h2>Login to Exclusive</h2>
                <p>Enter your details below</p>
                <form action="/SWP391-MomAndBaby/login" method="post">
                    <div style="width: 400px; margin-bottom: 20px; margin-top: 20px">
                        <input
                            class="input"
                            type="text"
                            name="username"
                            placeholder="Userame"
                            required
                            />
                    </div>
                    <div style="width: 400px">
                        <input
                            class="input"
                            type="password"
                            name="password"
                            placeholder="Password"
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
                                name="submitLogin"
                                style="
                                padding: 10px;
                                color: #fff;
                                background-color: #db4444;
                                margin-bottom: 20px;
                                "
                                >
                                Login
                            </button>
                            <a href="/SWP391-MomAndBaby/forget">Forget Password?</a>
                        </div>
                        <span style="display: block;color: red">${messageUserAuth != null ? messageUserAuth : message}</span>
                        <div class="text-center">
                            <p>Create new account <a href="/SWP391-MomAndBaby/register">Register</a></p>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- /SECTION -->

<!-- FOOTER --> <!-- will be add later -->

