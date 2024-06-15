

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./components/header.jsp" %>
<!-- Header here -->
<div class="section">
    <div class="container">
        <div class="row" style="margin-bottom: 80px; margin-top: 30px">
            <div class="col-md-6">
                <img src="./user/img/main_background_login.jpg" alt="" style="width: 100%" />
            </div>
            <div class="col-md-6" style="padding: 100px; margin-top: 70px">
                <h2>Confirm OTP</h2>
                <p>Enter your OTP below</p>
                <form action="/SWP391-MomAndBaby/register" method="post">
                    <div style="width: 400px; margin-bottom: 20px; margin-top: 20px">
                        <input
                            class="input"
                            type="text"
                            name="otp"
                            placeholder="OTP"
                            required
                            />
                        <span class="message_error"></span>
                        <input type="hidden" id="countdownValue" name="countdownValue" value="${timemount == null ? 300 : timemount}">
                        <span id="countdown" style="display:none; color: red; margin-top: 10px; text-align: center"></span>
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
                                name="confirm-otp"
                                style="
                                padding: 10px;
                                color: #fff;
                                background-color: #db4444;
                                margin-bottom: 20px;
                                "
                                >
                                Confirm OTP
                            </button>
                        </div>
                        <span style="display: block;color: red; text-align: center">${message}</span>
                    </div>
                </form>
                <form method="post" action="/SWP391-MomAndBaby/register">
                    <button name="resend-otp" id="resend-otp">Resend OTP</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src='./assets/js/validation.js'></script>
<script>
    var resendOtpButton = document.getElementById("resend-otp");
    var countdownValueInput = document.getElementById("countdownValue");
    var otpSession = '<%= session.getAttribute("otpConfirm")%>';
    if (otpSession !== 'null' && otpSession !== '') {
        var countDownTime = countdownValueInput.value;
        let hasReloaded = true;
        resendOtpButton.disabled = true;
        resendOtpButton.style.cursor = "not-allowed";
        var timer = setInterval(function () {
            countDownTime--;
            if (countDownTime <= 0) {
                document.getElementById("countdown").innerText = 'OTP has expired';
                resendOtpButton.disabled = false;
                resendOtpButton.style.cursor = "pointer";
                clearInterval(timer);
            } else {
                countdownValueInput.value = countDownTime;
                document.getElementById("countdown").innerText = 'Remaining term of OTP: ' + countDownTime + ' seconds';
                document.getElementById("countdown").style.display = "block";
            }
        }, 1000);
    }
</script>
<script>
    let otp = document.querySelector('#otp'),
            btnSubmit = document.querySelector('#btn-otp');
    const messageOTP = "OPT must be 6 integer number.";
    const inputsToValidateCheck = [
        {element: otp, message: messageOTP, regex: /^([0-9]{6})?$/, type: "text", isEmpty: false}
    ];
    validation(inputsToValidateCheck, btnSubmit);
</script>
<!-- Footer here -->
<%@include file="./components/footer.jsp" %>
