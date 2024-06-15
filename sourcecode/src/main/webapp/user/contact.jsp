<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./components/header.jsp" %>
<!-- BREADCRUMB -->
<div id="breadcrumb" class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row" style="background: transparent">
            <div class="col-md-12">
                <ul class="breadcrumb-tree" style="list-style-type: none; padding:0">
                    <li><a href="/SWP391-MomAndBaby">Home</a></li>
                    <li><a href="/SWP391-MomAndBaby/contact">Contact</a></li>
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
            <div class="col-md-4">
                <div
                    class="order-summary"
                    style="
                    border: 1px solid #ed495c;
                    padding: 20px;
                    margin-bottom: 60px;
                    border-radius: 10px;
                    background-color: lightblue;
                    "
                    >
                    <div class="product-btns" style="margin-bottom: 20px">
                        <button class="btn-round">
                            <i class="fa fa-phone"></i>
                        </button>
                        <label style="margin-left: 15px">Call To Us</label>
                    </div>
                    <div>
                        <p>We are available from Monday to Saturday, from 9:00 AM to 10:00 PM</p>
                        <p>Phone: 0949204801</p>
                    </div>
                    <div
                        class="product-btns"
                        style="
                        border-top: 1px solid #ed495c;
                        margin-top: 20px;
                        margin-bottom: 20px;
                        "
                        >
                        <button class="btn-round" style="margin-top: 20px;">
                            <i class="fa fa-envelope"></i>
                        </button>
                        <label style="margin-left: 15px">Write To Us</label>
                    </div>
                    <div>
                        <p>
                            If you need any help please fill out the contact form and we we will get back to you as soon as we can.
                        </p>
                        <p>Email: momandbabies.company@gmail.com</p>
                    </div>
                </div>
            </div>
            <div class="col-md-8">
                <form action="/SWP391-MomAndBaby/contact" method="post">
                    <div
                        class="order-summary"
                        style="
                        border: 1px solid #ed495c;
                        padding: 20px;
                        margin-bottom: 60px;
                        border-radius: 10px;                      
                        background-color: lightblue;
                        "
                        >
                        <div class="row" style = "background-color: lightblue">
                            <div class="col-md-4 form-group">
                                <input
                                    id="fullname"
                                    class="input"
                                    type="text"
                                    name="fullname"
                                    placeholder="Your Name"
                                    
                                    />
                                <span class="message_error"></span>

                            </div>
                            <div class="col-md-4 form-group">
                                <input
                                    id="email"
                                    class="input"
                                    type="email"
                                    name="email"
                                    placeholder="Email"
 
                                    />
                                <span class="message_error"></span>
                            </div>
                            <div class="col-md-4 form-group">
                                <input
                                    id="phone"
                                    class="input"
                                    type="tel"
                                    name="phone"
                                    placeholder="Phone number"
                 
                                    />
                                <span class="message_error"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <textarea
                                id="message"
                                class="input"
                                name="message"
                                placeholder="Your Massage"
                                style="height: 150px; resize: none"
                                ></textarea>
                            <span class="message_error"></span>
                        </div>
                        <div
                            class="text-right"
                            style="margin-top: 40px; margin-bottom: 9px"
                            >
                            <button
                                id="sendMessage"
                                name="btn-send-message"
                                class="btn btn-default"
                                style="
                                padding: 10px;
                                color: #fff;
                                background-color: #db4444;
                                margin-bottom: 20px;
                                "
                                >
                                Send Massage
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /section -->
<script src="./static-admin/assets/js/validation.js"></script>
<script>
    let regexEmail = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    let fullname = document.querySelector('#fullname'),
            phone = document.querySelector('#phone'),
            email = document.querySelector('#email'),
            message = document.querySelector('#message'),
            btnSubmit = document.querySelector('#sendMessage');
    const messageEmpty = "Please enter information for this field",
            messageEmail = "Email invalid",
            messagePhone = "Phone invalid.";
    const inputsToValidateCheck = [
        {element: email, message: messageEmail, regex: regexEmail, type: "text", isEmpty: false},
        {element: phone, message: messagePhone, regex: /(84|0[3|5|7|8|9])+([0-9]{8})\b/g, type: "text", isEmpty: false},
        {element: fullname, message: messageEmpty, regex: /^.{1,}$/, type: "text", isEmpty: false},
        {element: message, message: messageEmpty, regex: /^.{1,}$/, type: "text", isEmpty: false}
    ];
    validation(inputsToValidateCheck, btnSubmit);
</script>
<%@include file="./components/footer.jsp" %>

