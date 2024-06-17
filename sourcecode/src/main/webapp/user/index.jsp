
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="./components/header.jsp" %>
<jsp:useBean id="sale" class="Utils.Sale"></jsp:useBean>
    <!-- section -->
    <div class="section">
        <div class="container">
            <div class="row" style="border-radius: 10px">
                <div
                    class="col-md-2"
                    style="border-right: 2px solid #ff469e ; border-top: 2px solid #ff469e; max-height: 290px;
                    overflow-y: auto;
                    "
                    >
                <c:forEach items="${categories}" var="cate" varStatus="i">
                    <p style="${i.index == 0 ? "margin-top: 20px" : ""}">
                        <a href="/SWP391-MomAndBaby/product/?type=category&id=${cate.ID}">${cate.name}</a>
                    </p>
                </c:forEach>
            </div>
            <div class="col-md-10" style="border-top: 2px solid #ff469e">
                <div
                    id="carousel-example-generic"
                    class="carousel slide"
                    data-ride="carousel"
                    style="margin-top: 23px; margin-left: 35px"
                    >
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <c:forEach items="${banners}" var="banner" varStatus="i">
                            <li
                                data-target="#carousel-example-generic"
                                data-slide-to="${i.index}"
                                class="${i.index == 0 ? "active" : ""}"
                                ></li>
                            </c:forEach>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" role="listbox">
                        <c:forEach items="${banners}" var="ba" varStatus="i">
                            <div class="item ${i.index == 0 ? "active" : ""}">
                                <img src="${ba.img}" alt="${ba.name}" />
                            </div>
                        </c:forEach>
                    </div>

                    <!-- Controls -->
                    <a
                        class="left carousel-control"
                        href="#carousel-example-generic"
                        role="button"
                        data-slide="prev"
                        style="top: 50%; transform: translateY(-50%)"
                        >
                        <i
                            class="fa fa-chevron-left"
                            aria-hidden="true"
                            style="margin-top: 50%"
                            ></i>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a
                        class="right carousel-control"
                        href="#carousel-example-generic"
                        role="button"
                        data-slide="next"
                        style="top: 50%; transform: translateY(-50%)"
                        >
                        <i
                            class="fa fa-chevron-right"
                            aria-hidden="true"
                            style="margin-top: 50%"
                            ></i>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- /section -->

<div class="section">
    <div class="container">
        <div class="abc">
            <div class="red-bar">|</div>
            <div class="category-text"><Strong>Today's</Strong></div>
        </div>
    </div>
</div>

<!-- SECTION -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <!-- section title -->
            <div class="col-md-12">
                <div class="section-title">
                    <h2 class="title">Deal</h2>
                    <div class="section-nav">
                        <div id="slick-nav-9" class="products-slick-nav"></div>
                    </div>
                </div>
            </div>
            <!-- /section title -->

            <!-- Products tab & slick -->
            <div class="col-md-12">
                <div class="row">
                    <div class="products-tabs">
                        <!-- tab -->
                        <div id="tab2" class="tab-pane fade in active">
                            <div class="products-slick" data-nav="#slick-nav-9">
                                <!-- product -->
                                <c:forEach items="${productsDeal}" var="productDeal">
                                    <div class="product">
                                        <div class="product-img">
                                            <img src="${productDeal.mainImg}" alt="${productDeal.name}" />
                                            <div class="product-label">
                                                <c:if test="${productDeal.newPrice > 0}">
                                                    <span class="sale">${sale.calculateSale(productDeal.newPrice, productDeal.oldPrice)}</span>
                                                </c:if>
                                                <c:if test="${productDeal.newPrice == 0}">
                                                    <span class="new">NEW</span>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="product-body">
                                            <h3 class="product-name">
                                                <a href="/SWP391-MomAndBaby/product/detail/${productDeal.ID}">${productDeal.name}</a>
                                            </h3>
                                            <h4 class="product-price">
                                                <c:if test="${productDeal.newPrice > 0}">
                                                    ${currency.currencyFormat(productDeal.newPrice, "VNĐ")} <del class="product-old-price">${currency.currencyFormat(productDeal.oldPrice, "VNĐ")}</del>
                                                </c:if>
                                                <c:if test="${productDeal.newPrice == 0}">
                                                    ${currency.currencyFormat(productDeal.oldPrice, "VNĐ")}
                                                </c:if>
                                            </h4>
                                        </div>
                                        <div class="add-to-cart">
                                            <c:if test="${productDeal.quantity > 0}">
                                                <a class="add-to-cart-btn" href="/SWP391-MomAndBaby/cart/add?productID=${productDeal.ID}&quantity=1&pathUrl=/SWP391-MomAndBaby">
                                                    <i class="fa fa-shopping-cart"></i> add to cart
                                                </a>
                                            </c:if>
                                            <c:if test="${productDeal.quantity == 0}">
                                                <a class="add-to-cart-btn" href="/SWP391-MomAndBaby/preOrder/add?productID=${productDeal.ID}&pathUrl=/SWP391-MomAndBaby">
                                                    <i class="fa fa-truck"></i> Pre order
                                                </a>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <!-- /tab -->
                    </div>
                </div>
            </div>
            <!-- /Products tab & slick -->

            <!-- button -->
            <div class="col-md-12 text-center" style="margin-top: 55px">
                <a href="/SWP391-MomAndBaby/product">
                    <button
                        class="btn btn-default"
                        style="
                        padding: 10px;
                        color: #fff;
                        background-color: #ff469e;
                        margin-bottom: 10px;
                        "
                        >
                        View All Products
                    </button>
                </a>
            </div>
            <!-- /button -->
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /SECTION -->

<div class="section">
    <div class="container">
        <div class="abc">
            <div class="red-bar">|</div>
            <div class="category-text"><Strong>Brand</Strong></div>
        </div>
    </div>
</div>

<!-- SECTION -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <!-- section title -->
            <div class="col-md-12">
                <div class="section-title">
                    <h3 class="title">Brand</h3>
                    <div class="section-nav">
                        <div id="slick-nav-5" class="products-slick-nav"></div>
                    </div>
                </div>
            </div>
            <!-- /section title -->
            <!-- category -->
            <div class="col-md-12">
                <div class="row">
                    <c:forEach begin="0" end="5" items="${brands}" var="br">
                        <div class="col-md-2">
                            <div
                                class="order-summary text-center"
                                style="border: 1px solid #ff469e; padding: 10px; margin: 10px; border-radius: 10px"
                                >
                                <a href="/SWP391-MomAndBaby/product?type=brand&id=${br.ID}">
                                    <div class="product-btns" style="margin-bottom: 20px">
                                        <img src="${br.img}" alt="${br.name}" height="56px" width="93px" style="object-fit: cover"/>
                                    </div>
                                    <p>${br.name}</p>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <!-- /category -->
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /SECTION -->

<div class="section">
    <div class="container">
        <div class="abc">
            <div class="red-bar">|</div>
            <div class="category-text"><Strong>This Month</Strong></div>
        </div>
    </div>
</div>

<!-- SECTION -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <!-- section title -->
            <div class="col-md-12">
                <div class="section-title">
                    <h3 class="title">Feature Products</h3>
                    <div class="section-nav">
                        <a href="/SWP391-MomAndBaby/product">
                            <button
                                class="btn btn-default"
                                style="
                                padding: 10px;
                                color: #fff;
                                background-color: #ff469e;
                                margin-bottom: 10px;
                                "
                                >
                                View All
                            </button>
                        </a>
                    </div>
                </div>
            </div>
            <!-- /section title -->

            <!-- Products tab & slick -->
            <div class="col-md-12">
                <div class="row">
                    <div class="products-tabs">
                        <!-- tab -->
                        <div id="tab1" class="tab-pane active">
                            <div class="products-slick" data-nav="#slick-nav-1">
                                <!-- product -->
                                <c:forEach items="${productsFeature}" var="productFeature">
                                    <div class="product">
                                        <div class="product-img">
                                            <img src="${productFeature.mainImg}" alt="${productFeature.name}" />
                                            <div class="product-label">
                                                <c:if test="${productFeature.newPrice > 0}">
                                                    <span class="sale">${sale.calculateSale(productFeature.newPrice, productFeature.oldPrice)}</span>
                                                </c:if>
                                                <c:if test="${productFeature.newPrice == 0}">
                                                    <span class="new">NEW</span>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="product-body">
                                            <h3 class="product-name">
                                                <a href="/SWP391-MomAndBaby/product/detail/${productFeature.ID}">${productFeature.name}</a>
                                            </h3>
                                            <h4 class="product-price">
                                                <c:if test="${productFeature.newPrice > 0}">
                                                    ${currency.currencyFormat(productFeature.newPrice, "VNĐ")} <del class="product-old-price">${currency.currencyFormat(productFeature.oldPrice, "VNĐ")}</del>
                                                </c:if>
                                                <c:if test="${productFeature.newPrice == 0}">
                                                    ${currency.currencyFormat(productFeature.oldPrice, "VNĐ")}
                                                </c:if>
                                            </h4>
                                        </div>
                                        <div class="add-to-cart">
                                            <c:if test="${productFeature.quantity > 0}">
                                                <a class="add-to-cart-btn" href="/SWP391-MomAndBaby/cart/add?productID=${productFeature.ID}&quantity=1&pathUrl=/SWP391-MomAndBaby">
                                                    <i class="fa fa-shopping-cart"></i> add to cart
                                                </a>
                                            </c:if>
                                            <c:if test="${productFeature.quantity == 0}">
                                                <a class="add-to-cart-btn" href="/SWP391-MomAndBaby/preOrder/add?productID=${productFeature.ID}&pathUrl=/SWP391-MomAndBaby">
                                                    <i class="fa fa-truck"></i>Pre order
                                                </a>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div id="slick-nav-1" class="products-slick-nav"></div>
                        </div>
                        <!-- /tab -->
                    </div>
                </div>
            </div>
            <!-- Products tab & slick -->
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /SECTION -->

<!-- HOT DEAL SECTION -->
<div id="hot-deal" class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row" style="background: transparent">
            <div class="col-md-12">
                <div class="hot-deal">
                    <h2 class="text-uppercase">hot deal this week</h2>
                    <p>New Collection Up to 50% OFF</p>
                    <a class="primary-btn cta-btn" href="/SWP391-MomAndBaby/product">Shop now</a>
                </div>
            </div>
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /HOT DEAL SECTION -->

<div class="section">
    <div class="container">
        <div class="abc">
            <div class="red-bar">|</div>
            <div class="category-text"><Strong>Our Products</Strong></div>
        </div>
    </div>
</div>

<!-- SECTION -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <!-- section title -->
            <div class="col-md-12">
                <div class="section-title">
                    <h3 class="title">Explore Our Products</h3>
                    <div class="section-nav">
                        <div id="slick-nav-2" class="products-slick-nav"></div>
                    </div>
                </div>
            </div>
            <!-- /section title -->

            <!-- Products tab & slick -->
            <div class="col-md-12">
                <div class="row">
                    <div class="products-tabs">
                        <!-- tab -->
                        <div id="tab2" class="tab-pane fade in active">
                            <div class="products-slick" data-nav="#slick-nav-2">
                                <c:forEach items="${productsNormal}" var="productNormal">
                                    <div class="product">
                                        <div class="product-img">
                                            <img src="${productNormal.mainImg}" alt="${productNormal.name}" />
                                            <div class="product-label">
                                                <c:if test="${productNormal.newPrice > 0}">
                                                    <span class="sale">${sale.calculateSale(productNormal.newPrice, productNormal.oldPrice)}</span>
                                                </c:if>
                                                <c:if test="${productNormal.newPrice == 0}">
                                                    <span class="new">NEW</span>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="product-body">
                                            <h3 class="product-name">
                                                <a href="/SWP391-MomAndBaby/product/detail/${productNormal.ID}">${productNormal.name}</a>
                                            </h3>
                                            <h4 class="product-price">
                                                <c:if test="${productNormal.newPrice > 0}">
                                                    ${currency.currencyFormat(productNormal.newPrice, "VNĐ")} <del class="product-old-price">${currency.currencyFormat(productNormal.oldPrice, "VNĐ")}</del>
                                                </c:if>
                                                <c:if test="${productNormal.newPrice == 0}">
                                                    ${currency.currencyFormat(productNormal.oldPrice, "VNĐ")}
                                                </c:if>
                                            </h4>
                                        </div>
                                        <div class="add-to-cart">
                                            <c:if test="${productNormal.quantity > 0}">
                                                <a class="add-to-cart-btn" href="/SWP391-MomAndBaby/cart/add?productID=${productNormal.ID}&quantity=1&pathUrl=/SWP391-MomAndBaby">
                                                    <i class="fa fa-shopping-cart"></i> add to cart
                                                </a>
                                            </c:if>
                                            <c:if test="${productNormal.quantity == 0}">
                                                <a class="add-to-cart-btn" href="/SWP391-MomAndBaby/preOrder/add?productID=${productNormal.ID}&pathUrl=/SWP391-MomAndBaby">
                                                    <i class="fa fa-truck"></i> Pre order
                                                </a>
                                            </c:if>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <!-- /tab -->
                    </div>
                </div>
            </div>
            <!-- /Products tab & slick -->

            <!-- button -->
            <div class="col-md-12 text-center" style="margin-top: 55px">
                <a href="/SWP391-MomAndBaby/product">
                    <button
                        class="btn btn-default"
                        style="
                        padding: 10px;
                        color: #fff;
                        background-color: #ff469e;
                        margin-bottom: 10px;
                        "
                        >
                        View All Products
                    </button>
                </a>
            </div>
            <!-- /button -->
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /SECTION -->

<!-- section -->
<div class="section" style="margin-bottom: 40px">
    <div class="container">
        <div class="row">
            <div class="col-md-4 text-center">
                <div class="product-btns" style="margin-bottom: 20px">
                    <button class="btn-fa">
                        <i class="fa fa-truck fa-2x"></i>
                    </button>
                </div>
                <h4>FREE AND FAST DELIVERY</h4>
                <p>Free delivery for all orders over $140</p>
            </div>
            <div class="col-md-4 text-center">
                <div class="product-btns" style="margin-bottom: 20px">
                    <button class="btn-fa">
                        <i class="fa fa-headphones fa-2x"></i>
                    </button>
                </div>
                <h4>24/7 CUSTOMER SERVICE</h4>
                <p>Friendly 24/7 customer support</p>
            </div>
            <div class="col-md-4 text-center">
                <div class="product-btns" style="margin-bottom: 20px">
                    <button class="btn-fa">
                        <i class="fa fa-shield fa-2x"></i>
                    </button>
                </div>
                <h4>MONEY BACK GUARANTEE</h4>
                <p>We reurn money within 30 days</p>
            </div>
        </div>
    </div>
</div>
<!-- /section -->
<!--FOTTER-->
<%@include file="./components/footer.jsp" %>