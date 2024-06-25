<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- FOOTER -->
<footer id="footer" style="background: #fff; color: #333">
    <!-- top footer -->
    <div class="section">
        <!-- container -->
        <div class="container">
            <!-- row -->
            <div class="row" style="background: transparent">
                <div class="col-md-4 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Support</h3>
                        <ul class="footer-links">
                            <li>
                                <a href="https://maps.app.goo.gl/x3gBBDFjiJQRj7g86"
                                   ><i class="fa fa-map-marker"></i> FPT University Hồ Chí Minh</a
                                >
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-phone"></i> 0949204801
                                </a>
                            </li>
                            <li>
                                <a href="#"
                                   ><i class="fa fa-envelope-o"></i> momandbabies.company@gmail.com</a
                                >
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="clearfix visible-xs"></div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Account</h3>
                        <ul class="footer-links">
                            <li><a href="/SWP391-MomAndBaby/account">My Account</a></li>
                            <li><a href="/SWP391-MomAndBaby/login">Login / Register</a></li>
                            <li><a href="/SWP391-MomAndBaby/cart">Cart</a></li>
                            <li><a href="/SWP391-MomAndBaby/product">Shop</a></li>
                        </ul>
                    </div>
                </div>

                <div class="col-md-3 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Quick Link</h3>
                        <ul class="footer-links">
                            <li><a href="/SWP391-MomAndBaby/about">About</a></li>
                            <li><a href="/SWP391-MomAndBaby/contact">Contact</a></li>
                            <li><a href="/SWP391-MomAndBaby/blog">Blog</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-2 col-xs-6">
                    <div class="footer">
                        <h3 class="footer-title">Social contact</h3>
                        <ul class="footer-links" style="margin-top: 15px">
                            <li style="display: flex;justify-content: space-around">
                                <a href="https://www.facebook.com/" style="display: inline"><i class="fa fa-facebook"></i></a>
                                <a href="https://x.com/?lang=vi" style="display: inline"><i class="fa fa-twitter"></i></a>
                                <a href="https://www.instagram.com/" style="display: inline"><i class="fa fa-instagram"></i></a>
                                <a href="https://www.linkedin.com/" style="display: inline"><i class="fa fa-linkedin"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- /row -->
        </div>
        <!-- /container -->
    </div>
    <!-- /top footer -->

    <!-- bottom footer -->
    <div id="bottom-footer" class="section">
        <div class="container">
            <!-- row -->
            <div class="row" style="background: transparent">
                <div class="col-md-12 text-center">
                    <ul class="footer-payments">
                        <li style="display: inline-block; font-size: 40px;">
                            <a href="https://www.visa.com.vn/vi_VN"><i class="fa fa-cc-visa"></i></a>
                        </li>
                        <li style="display: inline-block; font-size: 40px;">
                            <a href="https://www.paypal.com/vn/home"><i class="fa fa-cc-paypal"></i></a>
                        </li>
                        <li style="display: inline-block; font-size: 40px;">
                            <a href="https://www.mastercard.com.vn/vi-vn.html"><i class="fa fa-cc-mastercard"></i></a>
                        </li>
                        <li style="display: inline-block; font-size: 40px;">
                            <a href="https://www.discover.com/"><i class="fa fa-cc-discover"></i></a>
                        </li>
                        <li style="display: inline-block; font-size: 40px;">
                            <a href="https://www.americanexpress.com/"><i class="fa fa-cc-amex"></i></a>
                        </li>
                    </ul>
                    <span class="copyright">
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        Copyright &copy;
                        <script>
                            document.write(new Date().getFullYear());
                        </script>
                        All rights reserved | Design by 
                        <i class="fa fa-heart-o" aria-hidden="true"></i>
                        <a href="https://colorlib.com" target="_blank">Group 8</a>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    </span>
                </div>
            </div>
            <!-- /row -->
        </div>
        <!-- /container -->
    </div>
    <!-- /bottom footer -->
</footer>
<!-- /FOOTER -->

<!-- jQuery Plugins -->
<script>
    var baseURL = window.location.origin + window.location.pathname;
    window.history.replaceState({}, document.title, baseURL);</script>

</script>
<script src="./user/js/jquery.min.js"></script>
<script src="./user/js/bootstrap.min.js"></script>
<script src="./user/js/slick.min.js"></script>
<script src="./user/js/nouislider.min.js"></script>
<script src="./user/js/jquery.zoom.min.js"></script>
<script src="./user/js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<jsp:useBean id="convertActionText" scope="page" class="Utils.ConvertActionText"></jsp:useBean>
    <script type="text/javascript">
    (function(d, m){
    var kommunicateSettings =
    {
    "appId":"f665ebfecdda235d3debc330a371c71f",
            "popupWidget":true,
            "automaticChatOpenOnNavigation":true,
            "quickReplies":["Speak with an Agent", "Book a Demo", "Sample Bots"],
            "preLeadCollection": [{
            "field": "Name",
                    "required": true,
                    "placeholder": "Enter your name"
            },
            {
            "field": "Email",
                    "type": "email",
                    "required": true,
                    "placeholder": "Enter your email"
            },
            {
            "field": "Phone",
                    "type": "number",
                    "required": true,
                    "element": "input", // Optional field (Possible values: textarea or input) 
                    "placeholder": "Enter your phone number"
            }
            ],
            "onInit": function () {
            var chatContext = {
            "userId": '<USERID>',
                    "email": '<EMAILID>'
            }
            Kommunicate.updateChatContext(chatContext);
            }
    };
    var s = document.createElement("script"); s.type = "text/javascript"; s.async = true;
    s.src = "https://widget.kommunicate.io/v2/kommunicate.app";
    var h = document.getElementsByTagName("head")[0]; h.appendChild(s);
    window.kommunicate = m; m._globals = kommunicateSettings;
    })(document, window.kommunicate || {});
    </script>
    <c:set var="type_message" value="${param.status}"/>
<c:set var="action" value="${param.act}"/>
<script>
    <c:if test="${type_message != null && type_message == 1}"                                                  >
        Swal.fire({
            title: 'Success!',
            text: '${convertActionText.convertActionText(action, type_message)}',
            icon: 'success',
            confirmButtonText: 'OK'
                            });
                            </c:if>
            <c:if test="${type_message != null && (type_message == 0 || type_message == 2 || type_message == 3)}">
                            Swal.fire({
            title: 'Error!',
            text: '${convertActionText.convertActionText(action, type_message)}',
            icon: 'error',
            confirmButtonText: 'OK'
                    });
                    </c:if>
            </script>
            </body>
            </html>
