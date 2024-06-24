
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="sale" class="Utils.Sale"></jsp:useBean>
<jsp:useBean id="calculateStar" class="Utils.CalculateFeedback"></jsp:useBean>
<%@include file="./components/header.jsp" %>
<!-- BREADCRUMB -->
<div id="breadcrumb" class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <div class="col-md-12">
                <ul class="breadcrumb-tree">
                    <li><a href="/MomAndBaby">Home</a></li>
                    <li><a href="/MomAndBaby/product">Shop</a></li>
                    <li><a href="/MomAndBaby/product/detail/${product.ID}">${product.name}</a></li>
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
    <div class="container">
        <!-- row -->
        <div class="row">
            <!-- Product main img -->
            <div class="col-md-5 col-md-push-2">
                <div id="product-main-img">
                    <div class="product-preview">
                        <img src="${product.mainImg}" alt="Main img" />
                    </div>
                    <c:forEach items="${imgDesc}" var="img">
                        <div class="product-preview">
                            <img src="${img.imgUrl}" alt="Img desc" />
                        </div>
                    </c:forEach>
                </div>
            </div>
            <!-- /Product main img -->

            <!-- Product thumb imgs -->
            <div class="col-md-2 col-md-pull-5">
                <div id="product-imgs">
                    <div class="product-preview">
                        <img src="${product.mainImg}" alt="Main img" />
                    </div>
                    <c:forEach items="${imgDesc}" var="img">
                        <div class="product-preview">
                            <img src="${img.imgUrl}" alt="Img desc" />
                        </div>
                    </c:forEach>
                </div>
            </div>
            <!-- /Product thumb imgs -->

            <!-- Product details -->
            <div class="col-md-5">
                <div class="product-details">
                    <h2 class="product-name">${product.name}</h2>
                    <div>
                        <c:set var="totalStar" value="${calculateStar.totalStar(feedbacks)}" />
                        <div class="product-rating">
                            <c:choose>
                                <c:when test="${totalStar > 0}">
                                    <c:forEach var="i" begin="1" end="${calculateStar.floor(totalStar)}">
                                        <i class="fa fa-star"></i>
                                    </c:forEach>

                                    <c:if test="${totalStar - calculateStar.floor(totalStar) >= 0.5}">
                                        <i class="fa fa-star-half-o" style="color: #ffad33"></i>
                                    </c:if>
                                    <c:forEach var="i" begin="${calculateStar.floor(totalStar) + (totalStar - calculateStar.floor(totalStar) >= 0.5 ? 1 : 0)}" end="4">
                                        <i class="fa fa-star-o"></i>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="i" begin="0" end="4">
                                        <i class="fa fa-star-o"></i>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <a class="review-link" href="#"
                           >(${feedbacks.size()} Reviews) | <i>In Stock (${product.quantity})</i></a
                        >
                    </div>
                    <div>
                        <c:if test="${product.newPrice > 0}">
                            <h3 class="product-price"
                                style="display: flex; align-items:  center; gap: 10px;">
                                ${currency.currencyFormatInput(product.newPrice)}
                                <del style="color: #ddd; font-size: 16px">${currency.currencyFormatInput(product.oldPrice)}</del> VNĐ
                                <span class="sale"
                                      style="
                                      font-size: 17px;
                                      background: pink;
                                      padding: 5px 10px;
                                      border-radius: 5px;
                                      color: #fff"
                                      >${sale.calculateSale(product.newPrice, product.oldPrice)}
                                </span>
                            </h3>
                        </c:if>
                        <c:if test="${product.newPrice == 0}">
                            <h3 class="product-price">${currency.currencyFormatInput(product.oldPrice)} VNĐ</h3>
                        </c:if>    
                    </div>
                    <!--  -->
                    <div class="product-options" style="border-top: 1px solid black">
                        <div class="row" style="display: flex; align-items: center; gap: 10px; padding: 0px">
                            <div class="col-md-2">
                                <label style="margin-top: 10px">Quantity: </label>
                            </div>
                            <div class="col-md-6 color-options" style="margin-top: 10px">
                                <a>
                                    ${product.quantity} product
                                </a>
                            </div>
                        </div>
                        <div class="row" style="display: flex; align-items: center; gap: 10px; padding: 0px">
                            <c:set var="category" value="${getDao.getCategoryById(product.categoryID)}" />
                            <div class="col-md-2">
                                <label style="margin-top: 10px">Category: </label>
                            </div>
                            <div class="col-md-6 color-options" style="margin-top: 10px">
                                <a href="/MomAndBaby/product?type=category&id=${category != null ? category.ID : 0}">
                                    ${category != null ? category.name : "N/A"}
                                </a>
                            </div>
                        </div>
                        <div class="row" style="display: flex; align-items: center; gap: 10px; padding: 0px">
                            <c:set var="brand" value="${getDao.getBrandById(product.brandID)}" />
                            <div class="col-md-2">
                                <label style="margin-top: 10px">Brand: </label>
                            </div>
                            <div class="col-md-6 color-options" style="margin-top: 10px">
                                <a href="/MomAndBaby/product?type=brand&id=${brand != null ? brand.ID : 0}">
                                    ${brand != null ? brand.name : "N/A"}
                                </a>
                            </div>
                        </div>
                        <div class="row" style="display: flex; align-items: center; gap: 10px;padding: 0px">
                            <c:set var="producer" value="${getDao.getProducerByID(product.producerID)}" />
                            <div class="col-md-2">
                                <label style="margin-top: 10px">Producer </label>
                            </div>
                            <div class="col-md-6 color-options" style="margin-top: 10px">
                                <a>
                                    ${producer != null ? producer.name : "N/A"}
                                </a>
                            </div>
                        </div>
                    </div>
                    <c:if test="${product.quantity  > 0}">
                        <form action="/MomAndBaby/cart/add" method="post">
                            <input name="productID" value="${product.ID}" type="hidden"/>
                            <input name="pathUrl" value="/MomAndBaby/product/detail/${product.ID}" type="hidden"/>
                            <div
                                class="add-to-cart"
                                style="display: flex; align-items: center"
                                >
                                <div
                                    class="value-button decrease_"
                                    id="decrease"
                                    value="Decrease Value"
                                    style="
                                    display: inline-flex;
                                    justify-content: center;
                                    align-items: center;
                                    width: 30px;
                                    height: 30px;
                                    border: 1px solid #ddd;
                                    background-color: #db4444;
                                    "
                                    >
                                    -
                                </div>
                                <input
                                    class="qty-input"
                                    type="number"
                                    name="quantity"
                                    id="number"
                                    value="1"
                                    min="1"
                                    max="${product.quantity}"
                                    style="
                                    width: 50px;
                                    height: 29px;
                                    text-align: center;
                                    border: none;
                                    border-top: 1px solid #ddd;
                                    border-bottom: 1px solid #ddd;
                                    "
                                    />
                                <div
                                    class="value-button increase_"
                                    id="increase"
                                    value="Increase Value"
                                    style="
                                    display: inline-flex;
                                    justify-content: center;
                                    align-items: center;
                                    width: 30px;
                                    height: 30px;
                                    border: 1px solid #ddd;
                                    background-color: #db4444;
                                    "
                                    >
                                    +
                                </div>
                                <button
                                    class="add-to-cart-btn"
                                    style="margin-left: 10px; border-radius: 5px; height: 35px"
                                    name="add-to-cart"
                                    >
                                    <i class="fa fa-shopping-cart"></i> Add to cart
                                </button>
                            </div>
                            <!--  -->
                        </form>
                    </c:if>
                    <c:if test="${product.quantity == 0}">
                        <div
                            class="add-to-cart"
                            style="display: flex;
                            align-items: center;
                            "
                            >
                            <a href="/MomAndBaby/preOrder/add?productID=${product.ID}&pathUrl=/MomAndBaby/product/detail/${product.ID}"
                               class="add-to-cart-btn"
                               style="
                               margin-left: 10px;
                               border-radius: 5px;
                               display: flex;
                               align-items: center;
                               "
                               >
                                <i class="fa fa-truck"></i> Pre order
                            </a>
                        </div>
                    </c:if>
                    <ul class="product-links">
                        <li>Share:</li>
                        <li>
                            <a href="#"><i class="fa fa-facebook"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-google-plus"></i></a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-envelope"></i></a>
                        </li>
                    </ul>
                </div>
            </div>
            <!-- /Product details -->
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /SECTION -->
<div class="section">
    <!-- container -->
    <div class="container" style="margin-bottom: 20px; margin-top: 20px">
        <!-- row -->
        <div class="row">
            <div class="col-md-12">
                <h4>Feedback</h4>
                <div class="feedback-container">
                    <div class="statatis-feedback">
                        <div class="feadback-total">
                            <h4>${totalStar}<span>/5</span></h4>
                        </div>
                        <div class="feedback-star">
                            <ul class="list-star">
                                <c:choose>
                                    <c:when test="${totalStar > 0}">
                                        <c:forEach var="i" begin="1" end="${calculateStar.floor(totalStar)}">
                                            <li class="star-item active">
                                                <i class="fa fa-star"></i>
                                            </li>
                                        </c:forEach>
                                        <c:if test="${totalStar - calculateStar.floor(totalStar) >= 0.5}">
                                            <li class="star-item active">
                                                <i class="fa fa-star-half-o" style="color: #ffad33"></i>
                                            </li>
                                        </c:if>
                                        <c:forEach var="i" begin="${calculateStar.floor(totalStar) + (totalStar - calculateStar.floor(totalStar) >= 0.5 ? 1 : 0)}" end="4">
                                            <li class="star-item">
                                                <i class="fa fa-star-o"></i>
                                            </li>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="i" begin="0" end="4">
                                            <li class="star-item">
                                                <i class="fa fa-star-o"></i>
                                            </li>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>
                        <div class="number-feedback">
                            <c:choose>
                                <c:when test="${feedbacks.size() > 0}">
                                    Have <c:out value="${feedbacks.size()}" /> feedbacks
                                </c:when>
                                <c:otherwise>
                                    No feedback for this product
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <c:if test="${feedbacks.size() > 0}">
                        <div class="wrapper-feedback">
                            <ul class="list-feedback">
                                <c:forEach items="${feedbacks}" var="feed">
                                    <c:set var="user" value="${getDao.getUser(feed.userID)}" />
                                    <li class="feedback-item">
                                        <div class="feedback-avatar">
                                            <img src="${user.avatar}"
                                                 alt=""
                                                 />
                                        </div>
                                        <div class="info-feedback">
                                            <h4>${user.fullname != null ? user.fullname : user.username}</h4>
                                            <div class="start-item-feedback">
                                                <c:set var="userStar"  value="${feed.star}" />
                                                <ul class="list-star">
                                                    <c:if test="${userStar > 0}">
                                                        <c:forEach begin="0" end="${userStar - 1}">
                                                            <li class="star-item active">
                                                                <i class="fa fa-star"></i>
                                                            </li>
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:if test="${userStar < 5}">
                                                        <c:forEach begin="0" end="${4 - userStar}">
                                                            <li class="star-item">
                                                                <i class="fa fa-star"></i>
                                                            </li>
                                                        </c:forEach>
                                                    </c:if>
                                                </ul>
                                                <span>
                                                    <c:choose>
                                                        <c:when test="${feed.dateUpdate != null}">
                                                            Update at ${feed.dateUpdate}
                                                        </c:when>
                                                        <c:otherwise>
                                                            Post at ${feed.datePost}
                                                        </c:otherwise>
                                                    </c:choose>
                                                </span>
                                            </div>
                                            <div class="feedback-content">
                                                <p>${feed.feedback}</p>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </c:if>
                </div>
            </div>
            <!-- product -->
            <div class="col-md-12">
                <h4>Description</h4>
                ${product.description}
            </div>
            <!-- /product -->
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- Section -->
<div class="section">
    <!-- container -->
    <div class="container" style="margin-bottom: 100px; margin-top: 40px">
        <!-- row -->
        <div class="row">
            <div class="col-md-12">
                <div class="section-title">
                    <h3 class="title">Related Item</h3>
                </div>
            </div>

            <!-- product -->
            <c:forEach items="${productsRelative}" var="pro" varStatus="status">
                <!-- product -->
                <div class="col-md-3 col-xs-6">
                    <div class="product">
                        <div class="product-img">
                            <img src="${pro.mainImg}" alt="${pro.name}" />
                            <div class="product-label">
                                <c:if test="${pro.newPrice > 0}">
                                    <span class="sale">${sale.calculateSale(pro.newPrice, pro.oldPrice)}</span>
                                </c:if>
                                <c:if test="${pro.newPrice == 0}">
                                    <span class="new">NEW</span>
                                </c:if>
                            </div>
                        </div>
                        <div class="product-body">
                            <h3 class="product-name">
                                <a href="/MomAndBaby/product/detail/${pro.ID}">${pro.name}</a>
                            </h3>
                            <h4 class="product-price">
                                <c:if test="${pro.newPrice > 0}">
                                    ${currency.currencyFormat(pro.newPrice, "VNĐ")} <del class="product-old-price">${currency.currencyFormat(pro.oldPrice, "VNĐ")}</del>
                                </c:if>
                                <c:if test="${pro.newPrice == 0}">
                                    ${currency.currencyFormat(pro.oldPrice, "VNĐ")}
                                </c:if>
                            </h4>
                        </div>
                        <div class="add-to-cart">
                            <a class="add-to-cart-btn" href="/MomAndBaby/cart/add?productID=${pro.ID}&quantity=1&pathUrl=/MomAndBaby/product/detail/${product.ID}">
                                <i class="fa fa-shopping-cart"></i> add to cart
                            </a>
                        </div>
                    </div>
                </div>
                <!-- clearfix for responsive layout -->
                <c:if test="${status.index == 1}">
                    <div class="clearfix visible-sm visible-xs"></div>
                </c:if>
            </c:forEach>
            <!-- /product -->
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /Section -->
<script src="./user/js/jquery.min.js"></script>

<script>
    $(document).ready(function () {
        var valueElement = $("#number");

        function incrementValue(e) {
            valueElement.val(
                    Math.max(parseInt(valueElement.val()) + e.data.increment, 1)
                    );
            return false;
        }
        $("#increase").bind("click", {increment: 1}, incrementValue);
        $("#decrease").bind("click", {increment: -1}, incrementValue);
    });
</script>
<script>
    document.querySelector('.qty-input').addEventListener('input', function () {
        const min = parseInt(this.min);
        const max = parseInt(this.max);
        const value = parseInt(this.value);
        if (value < min || value > max) {
            showMessgae();
        }
    });
    function showMessgae() {
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
</script>
<%@include file="./components/footer.jsp" %>
