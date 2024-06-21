
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./components/header.jsp" %>
<!-- /HEADER -->

<!-- BREADCRUMB -->
<div id="breadcrumb" class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <div class="col-md-12">
                <ul class="breadcrumb-tree">
                    <li><a href="#">Home</a></li>
                    <li class="active">Cart</li>
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
    <div class="container" style="background-color: white;padding-top: 40px;">
        <!-- row -->

        <div class="order-details" style="margin-bottom: 15px; background-color: #f8f8ff">
            <div class="order-summary">
                <div class="col">
                    <div class="col-md-1">
                        <strong><p></p></strong>
                    </div>
                    <div class="col-md-3" >
                        <strong><p>Product</p></strong>
                    </div>
                    <div class="col-md-3">
                        <strong><p>Price</p></strong>
                    </div>
                    <div class="col-md-2">
                        <strong><p>Quantity</p></strong>
                    </div>
                    <div class="col-md-3" style="text-align: right">
                        <strong><p>Subtotal</p></strong>
                    </div>
                </div>
            </div>
        </div>
        <div style="width: 90%;margin: 0% 5%;border: 2px solid #100c08;margin-bottom: 10px"></div>
        <form action="/SWP391-MomAndBaby/cart" method="post" style="padding-top: 20px">
            <c:set var="totalCartPage" value="0" />
            <c:forEach items="${carts}" var="cart">
                <c:set var="productCartPage" value="${getDao.getProduct(cart.productID)}" />
                <div class="order-details" style="margin-bottom: 40px">
                    <input type="hidden" value="${productCartPage.ID}" name="productID"/>
                    <div class="order-summary">
                        <div class="order-products" style="padding-bottom: 10px">
                            <div class="col">
                                <div class="col-md-1">
                                    <a style="height: 100%; display: flex; align-items: center; cursor: pointer" onclick="confirmRemove('${cart.ID}')">
                                        X
                                    </a>
                                </div>
                                <div class="col-md-3" >
                                    <img
                                        src="${productCartPage.mainImg}"
                                        alt="${productCartPage.name}"
                                        style="height: 40px; width: 40px"
                                        />
                                    ${productCartPage.name}
                                </div>
                                <div class="col-md-3">
                                    <c:if test="${productCartPage.newPrice > 0}">
                                        ${currency.currencyFormatInput(productCartPage.newPrice)}
                                    </c:if>
                                    <c:if test="${productCartPage.newPrice == 0}">
                                        ${currency.currencyFormatInput(productCartPage.oldPrice)}
                                    </c:if>
                                </div>
                                <div class="col-md-2">
                                    <input
                                        type="number"
                                        name="qty_${productCartPage.ID}"
                                        class="qty-input"
                                        value="${cart.quantity}"
                                        style="width: 60px"
                                        min="1"
                                        max="${productCartPage.quantity}"
                                        />
                                    (in stock(<b>${productCartPage.quantity}<b>))
                                            </div>
                                            <div class="col-md-3" style="text-align: right">
                                                <c:if test="${productCartPage.newPrice > 0}">
                                                    <c:set var="totalCartPage" value="${totalCartPage + (productCartPage.newPrice * cart.quantity)}" />
                                                    ${currency.currencyFormatInput(productCartPage.newPrice * cart.quantity)}
                                                </c:if>
                                                <c:if test="${productCartPage.newPrice == 0}">
                                                    ${currency.currencyFormatInput(productCartPage.oldPrice * cart.quantity)}
                                                    <c:set var="totalCartPage" value="${totalCartPage + (productCartPage.oldPrice * cart.quantity)}" />
                                                </c:if>
                                            </div>
                                            </div>
                                            </div>
                                            </div>
                                            </div>
                                        </c:forEach>

                                        <div class="order-summary">
                                            <div class="order-products">
                                                <div class="order-col">
                                                    <div>
                                                        <a href="/SWP391-MomAndBaby/product" style="padding: 10px; background-color: #66FF00; border-radius: 5px; color: white; display: inline-block;">
                                                            <strong>Return to shop</strong>
                                                        </a>

                                                    </div>
                                                    <div>
                                                        <button name="btn-update-cart" 
                                                                style="margin-top: 20px; padding: 10px; background-color: #66FF00;
                                                                color: white; border: 1px solid #dcdcdc; border-radius: 5px">
                                                            <strong>Update cart</strong>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        </form>
                                        <div style="width: 90%;margin: 0% 5%;border: 2px solid #100c08;margin-top: 30px;margin-bottom: 20px"></div>
                                        <div class="row" style="margin-top: 20px">
                                            <form action="/SWP391-MomAndBaby/voucher" method="post" id="couponForm" >
                                                <div class="col-md-6" style="right: 15px; margin-top: 10px">
                                                    <div class="col-md-6 form-group">
                                                        <input
                                                            class="form-control"
                                                            type="text"
                                                            name="couponCode"
                                                            placeholder="Coupon Code"
                                                            style="padding: 20px"
                                                            />
                                                    </div>
                                                    <div class="form-group">
                                                        <button
                                                            type="submit"
                                                            class="btn btn-default"
                                                            name="use-voucher"
                                                            style="padding: 10px; color: #fff; background-color: #FF0800"
                                                            >
                                                            <strong>Apply Coupon</strong>
                                                        </button>
                                                    </div>
                                                    <c:choose>
                                                        <c:when test="${sessionScope.couponStatus == 'invalid'}">
                                                            <p style="color: red; margin-left: 10px">Invalid coupon code.</p>
                                                        </c:when>
                                                        <c:when test="${sessionScope.couponStatus == 'expired'}">
                                                            <p style="color: red; margin-left: 10px">Coupon has expired.</p>
                                                        </c:when>
                                                        <c:when test="${sessionScope.couponStatus == 'limitReached'}">
                                                            <p style="color: red; margin-left: 10px">Coupon usage limit reached.</p>
                                                        </c:when>
                                                        <c:when test="${sessionScope.couponStatus == 'login'}">
                                                            <p style="color: red; margin-left: 10px">Please login to used</p>
                                                        </c:when>
                                                        <c:when test="${sessionScope.couponStatus == 'used'}">
                                                            <p style="color: red; margin-left: 10px">Used this voucher. Try another voucher</p>
                                                        </c:when>
                                                        <c:when test="${sessionScope.couponStatus == 'applied'}">
                                                            <p style="color: green; margin-left: 10px">Coupon applied successfully!</p>
                                                            <button onclick="return confirm('Are you sure to delete voucher?')" style="margin-left: 10px" name="remove-voucher">X</button>
                                                        </c:when>
                                                    </c:choose>
                                                </div>
                                            </form>


                                            <div class="col-md-6">
                                                <div
                                                    class="order-summary"
                                                    style="
                                                    border: 1.5px solid black;
                                                    padding: 15px;
                                                    margin-bottom: 60px;
                                                    "
                                                    >
                                                    <div class="order-products">
                                                        <div class="order-col" style="margin-top: 20px; font-size: 35px">
                                                            <strong>Cart Total</strong>
                                                        </div>
                                                        <div
                                                            class="order-col"
                                                            style="margin-top: 20px; border-bottom: 1px solid black"
                                                            >
                                                            <div>Subtotal</div>
                                                            <div>${currency.currencyFormat(totalCartPage, "VNĐ")}</div>
                                                        </div>
                                                        <div
                                                            class="order-col"
                                                            style="margin-top: 20px; border-bottom: 1px solid black"
                                                            >
                                                            <div>Discount</div>
                                                            <div><c:choose>
                                                                    <c:when test="${sessionScope.discount != null}">
                                                                        ${currency.currencyFormat(discount, "VNĐ")}
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        ${currency.currencyFormat(0, "VNĐ")}
                                                                    </c:otherwise>
                                                                </c:choose></div>
                                                        </div>
                                                        <div
                                                            class="order-col"
                                                            style="margin-top: 20px; border-bottom: 1px solid black"
                                                            >
                                                            <div>Shipping</div>
                                                            <div>Free</div>
                                                        </div>
                                                        <div class="order-col" style="margin-top: 20px">
                                                            <div>Total</div>
                                                            <div>
                                                                <c:choose>
                                                                    <c:when test="${sessionScope.newTotal != null}">
                                                                        ${currency.currencyFormat(newTotal, "VNĐ")}
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        ${currency.currencyFormat(totalCartPage, "VNĐ")}
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                        </div>
                                                        <div class="text-center" style="margin-top: 20px">
                                                            <a href="/SWP391-MomAndBaby/checkout"
                                                               class="btn btn-default"
                                                               style="
                                                               padding: 10px;
                                                               color: #fff;
                                                               background-color: #FF0800;
                                                               margin-bottom: 20px;

                                                               "
                                                               >
                                                                <strong>Process to Checkout</strong>
                                                            </a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>

                                        <!-- /row -->
                                        </div>
                                        <!-- /container -->
                                        </div>
                                        <!-- /SECTION -->
                                        <script>
                                            document.querySelectorAll('.qty-input').forEach(input => {
                                                input.addEventListener('input', function () {
                                                    const min = parseInt(this.min);
                                                    const max = parseInt(this.max);
                                                    const value = parseInt(this.value);

                                                    if (value < min || value > max) {
                                                        Swal.fire({
                                                            title: 'Error!',
                                                            text: 'The quantity exceeds the existing product limit or is invalid',
                                                            icon: 'error',
                                                            confirmButtonText: 'OK'
                                                        }).then(() => {
                                                            // Optionally reset the input value to a valid state
                                                            this.value = (value < min) ? min : max;
                                                        });
                                                    }
                                                });
                                            });
                                        </script>
                                        <script>
                                            function confirmRemove(cartId) {
                                                Swal.fire({
                                                    title: "Are you sure?",
                                                    text: "You won't be able to revert this!",
                                                    icon: "warning",
                                                    showCancelButton: true,
                                                    confirmButtonColor: "#3085d6",
                                                    cancelButtonColor: "#d33",
                                                    confirmButtonText: "Yes, delete it!"
                                                }).then((result) => {
                                                    if (result.isConfirmed) {
                                                        window.location.href = "/SWP391-MomAndBaby/cart/remove?cartId=" + cartId;
                                                    }
                                                });
                                            }
                                        </script>
                                        <%@include file="./components/footer.jsp" %>
