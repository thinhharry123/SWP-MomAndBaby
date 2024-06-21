<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./components/header.jsp" %>
<!-- /HEADER -->
<!-- BREADCRUMB -->
<style>
    .input-toggle {
        position: relative;
        display: flex;
        justify-content: space-between;
        vertical-align: middle;
        padding: 10px 0;
    }

    .input-toggle input {
        display: none;
    }

    .input-toggle label {
        position: absolute;
        cursor: pointer;
        width: 40px;
        height: 20px;
        background-color: #ccc;
        border-radius: 15px;
        transition: background-color 0.3s;
    }

    .input-toggle label::before {
        content: '';
        position: absolute;
        top: 1px;
        left: 2px;
        width: 20px;
        height: 18px;
        background-color: #fff;
        border-radius: 50%;
        transition: transform 0.3s;
    }

    .input-toggle input:checked + label {
        background-color: #2196F3;
    }

    .input-toggle input:checked + label::before {
        transform: translateX(17px);
    }

    .additional-info {
        flex: 1;
        text-align: right;
    }

</style>
<div id="breadcrumb" class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <div class="col-md-12">
                <h3 class="breadcrumb-header">Checkout</h3>
                <ul class="breadcrumb-tree">
                    <li><a href="/MomAndBaby">Home</a></li>
                    <li class="active">Checkout</li>
                </ul>
            </div>
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /BREADCRUMB -->

<!-- SECTION -->
<div class="section">
    <!-- container -->
    <form action="/MomAndBaby/checkout" method="post">
        <div class="container">
            <!-- row -->
            <div class="row">
                <div class="col-md-7">
                    <!-- Billing Details -->
                    <div class="billing-details">
                        <div class="section-title">
                            <h3 class="title">Billing details</h3>
                        </div>
                        <div class="form-group">
                            <input
                                id="fullname"
                                class="input"
                                type="text"
                                name="fullname"
                                placeholder="Full name"
                                value="${accountLogin != null && accountLogin.fullname != null ? accountLogin.fullname : ""}"
                                />
                            <span class="message_error"></span>
                        </div>
                        <div class="form-group">
                            <input
                                id="phone"
                                class="input"
                                type="text"
                                name="phone"
                                placeholder="Phone number"
                                value="${accountLogin != null && accountLogin.phone != null ? accountLogin.phone : ""}"
                                />
                            <span class="message_error"></span>
                        </div>
                        <div class="form-group">
                            <input
                                id="email"
                                class="input"
                                type="email"
                                name="email"
                                placeholder="Email"
                                value="${accountLogin != null && accountLogin.email != null ? accountLogin.email : ""}"
                                />
                            <span class="message_error"></span>
                        </div>
                        <div class="form-group">
                            <select id="province" name="province" class="input" onchange="filterAddress('#district', this.value)">
                                <option value="">Choose  a province</option>
                                <c:forEach items="${provinces}" var="province">
                                    <option value="${province.provinceId}">${province.name}</option>
                                </c:forEach>
                            </select>
                            <span class="message_error"></span>
                        </div>
                        <div class="form-group">
                            <select name="district" class="input" id="district" onchange="filterAddress('#ward', this.value)">
                            </select>
                            <span class="message_error"></span>
                        </div>
                        <div class="form-group">
                            <select name="ward" class="input" id="ward">
                            </select>
                            <span class="message_error"></span>
                        </div>
                        <div class="form-group">
                            <input
                                id="detail-address"
                                class="input"
                                type="text"
                                name="details-address"
                                placeholder="Detail address"
                                />
                            <span class="message_error"></span>
                        </div>
                    </div>
                </div>
                <!-- Order Details -->
                <div class="col-md-5 order-details">
                    <div class="order-summary">
                        <div class="order-products">
                            <c:set var="totalCartCheckout" value="0" />
                            <c:forEach items="${carts}" var="cart">
                                <c:set var="productCheckout" value="${getDao.getProduct(cart.productID)}" />
                                <div class="order-col">
                                    <div>
                                        <img
                                            src="${productCheckout.mainImg}"
                                            alt=""
                                            style="height: 40px; width: 40px"
                                            />
                                        ${productCheckout.name} x ${cart.quantity}
                                    </div>
                                    <div>
                                        <c:if test="${productCheckout.newPrice > 0}">
                                            <c:set var="totalCartCheckout" value="${totalCartCheckout + (productCheckout.newPrice * cart.quantity)}" />
                                            ${currency.currencyFormatInput(productCheckout.newPrice * cart.quantity)}
                                        </c:if>
                                        <c:if test="${productCheckout.newPrice == 0}">
                                            ${currency.currencyFormatInput(productCheckout.oldPrice * cart.quantity)}
                                            <c:set var="totalCartCheckout" value="${totalCartCheckout + (productCheckout.oldPrice * cart.quantity)}" />
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="order-col">
                            <div><strong>SUBTOTAL</strong></div>
                            <div><strong class="order-total">
                                    ${currency.currencyFormat(totalCartCheckout, "VNĐ")}
                                </strong></div>
                        </div>
                        <div class="order-col">
                            <div><strong>Discount</strong></div>
                            <div>
                                <strong class="order-total">
                                    <c:choose>
                                        <c:when test="${sessionScope.ckdiscount != null}">
                                            ${currency.currencyFormat(sessionScope.ckdiscount, "VNĐ")}
                                        </c:when>
                                        <c:otherwise>
                                            ${currency.currencyFormat(0, "VNĐ")}
                                        </c:otherwise>
                                    </c:choose>
                                </strong>
                                <c:choose>
                                    <c:when test="${sessionScope.ckcouponStatus == 'invalid'}">
                                        <p style="color: red;">Invalid coupon code.</p>
                                    </c:when>
                                    <c:when test="${sessionScope.ckcouponStatus == 'expired'}">
                                        <p style="color: red;">Coupon has expired.</p>
                                    </c:when>
                                    <c:when test="${sessionScope.ckcouponStatus == 'limitReached'}">
                                        <p style="color: red;">Coupon usage limit reached.</p>
                                    </c:when>
                                    <c:when test="${sessionScope.ckcouponStatus == 'login'}">
                                        <p style="color: red;">Please login to used</p>
                                    </c:when>
                                    <c:when test="${sessionScope.ckcouponStatus == 'used'}">
                                        <p style="color: red;">Used this voucher. Try another voucher</p>
                                    </c:when>
                                    <c:when test="${sessionScope.ckcouponStatus == 'applied'}">
                                        <p style="color: green;">Coupon applied successfully!</p>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                        <div class="order-col">
                            <div>Shiping</div>
                            <div><strong>FREE</strong></div>
                        </div>
                        <div class="order-col">
                            <div><strong>TOTAL</strong></div>
                            <div><strong class="order-total">
                                    <c:choose>
                                        <c:when test="${sessionScope.cknewTotal != null}">
                                            ${currency.currencyFormat(cknewTotal, "VNĐ")}
                                        </c:when>
                                        <c:otherwise>
                                            ${currency.currencyFormat(totalCartCheckout, "VNĐ")}
                                        </c:otherwise>
                                    </c:choose>
                                </strong></div>
                        </div>
                    </div>
                    <div class="payment-method">
                        <div class="input-toggle">
                            <input type="checkbox" name="usePoint" id="usePoint" value="usepoint"/>
                            <label for="usePoint"></label>
                            <div class="additional-info">
                                Use point (Balance: <span id="balanceAmount">${currency.currencyFormat(accountBalance.balance, "points")}</span>)
                            </div>
                        </div>
                        <div><strong>Payment</strong></div>
                        <div class="input-radio">
                            <input type="radio" name="payment" id="payment-2" value="0" checked/>
                            <label for="payment-2">
                                <span></span>
                                Cash on delivery
                            </label>
                        </div>
                        <div class="input-radio">
                            <input type="radio" name="payment" id="payment-1" value="1"/>
                            <label for="payment-1">
                                <span></span>
                                Bank
                            </label>
                        </div>
                    </div>
                    <button name="btn-checkout"  id="checkout" class="primary-btn order-submit">Place order</button>
                </div>
                <!-- /Order Details -->
            </div>
            <!-- /row -->
        </div>
    </form>
    <!-- /container -->
</div>
<!-- /SECTION -->

<!-- FOOTER -->
<script src="./user/js/checkout.js"></script>
<script src="./static-admin/assets/js/validation.js"></script>
<script>
                                document.getElementById('usePoint').addEventListener('click', function (e) {
                                    this.checked = e.target.checked;
                                });

</script>
<script>
    let regexEmail = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    let fullname = document.querySelector('#fullname'),
            phone = document.querySelector('#phone'),
            email = document.querySelector('#email'),
            detailAddress = document.querySelector('#detail-address'),
            province = document.querySelector('#province'),
            district = document.querySelector('#district'),
            ward = document.querySelector('#ward'),
            btnSubmit = document.querySelector('#checkout');
    const messageEmpty = "Please enter information for this field",
            messageEmail = "Email invalid",
            messagePhone = "Phone invalid.";
    const inputsToValidateCheck = [
        {element: email, message: messageEmail, regex: regexEmail, type: "text", isEmpty: false},
        {element: phone, message: messagePhone, regex: /(84|0[3|5|7|8|9])+([0-9]{8})\b/g, type: "text", isEmpty: false},
        {element: fullname, message: messageEmpty, regex: /^.{1,}$/, type: "text", isEmpty: false},
        {element: detailAddress, message: messageEmpty, regex: /^.{1,}$/, type: "text", isEmpty: false},
        {element: province, message: messageEmpty, regex: /^.{1,}$/, type: "select", isEmpty: false},
        {element: district, message: messageEmpty, regex: /^.{1,}$/, type: "select", isEmpty: false},
        {element: ward, message: messageEmpty, regex: /^.{1,}$/, type: "select", isEmpty: false}
    ];
    validation(inputsToValidateCheck, btnSubmit);
</script>
<%@include file="./components/footer.jsp" %>