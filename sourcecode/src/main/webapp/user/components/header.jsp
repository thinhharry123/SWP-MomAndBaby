

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="currency" class="Utils.CurrencyConverter"></jsp:useBean>
<jsp:useBean id="getDao" class="Utils.GetDAO"></jsp:useBean>
<jsp:useBean id="getCartLib" class="Utils.CartLib"></jsp:useBean>
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="utf-8" />
            <base href="http://localhost:8080/SWP391-MomAndBaby/" />     
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

            <title>Mom And Baby website</title>

            <!-- Google font -->
            <link
                href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700"
                rel="stylesheet"
                />

            <!-- Bootstrap -->
            <link type="text/css" rel="stylesheet" href="./user/css/bootstrap.min.css" />

            <!-- Slick -->
            <link type="text/css" rel="stylesheet" href="./user/css/slick.css" />
            <link type="text/css" rel="stylesheet" href="./user/css/slick-theme.css" />

            <!-- nouislider -->
            <link type="text/css" rel="stylesheet" href="./user/css/nouislider.min.css" />

            <!-- Font Awesome Icon -->
            <link rel="stylesheet" href="./user/css/font-awesome.min.css" />

            <!-- Custom stlylesheet -->
            <link type="text/css" rel="stylesheet" href="./user/css/style.css" />
            <link type="text/css" rel="stylesheet" href="./user/css/form.css" />
        </head>
        <body>
            <!-- HEADER -->
            <header>
                <!-- TOP HEADER -->
                <div id="top-header">
                    <div class="container">
                        <ul class="header-links text-center">
                            <p>
                                Summer Sale For Product - Free Express Delivery - DISCOUNT UP TO 50% OFF !
                                <a href="/SWP391-MomAndBaby/product">Shop now</a>
                            </p>
                        </ul>
                        <!-- Language Selector -->
                        <!-- /Language Selector -->
                    </div>
                </div>
                <!-- /TOP HEADER -->

                <!-- MAIN HEADER -->
                <div id="header">
                    <!-- container -->
                    <div class="container">
                        <!-- row -->
                        <div class="row">
                            <!-- LOGO -->
                            <div class="col-md-3">
                                <div class="header-logo">
                                    <a href="/SWP391-MomAndBaby" class="logo">
                                        <img src="./user/img/logo.jpg" alt="Logo">
                                    </a>
                                </div>
                            </div>
                            <!-- /LOGO -->

                            <!-- MENU BAR -->
                            <div class="col-md-4">
                                <nav id="navigation">
                                    <!-- container -->
                                    <div class="container">
                                        <!-- responsive-nav -->
                                        <div id="responsive-nav">
                                            <!-- NAV -->
                                            <ul class="main-nav nav navbar-nav">
                                                <li class="active"><a href="/SWP391-MomAndBaby/home">Home</a></li>
                                                <li><a href="/SWP391-MomAndBaby/product">Shop</a></li>
                                                <li><a href="/SWP391-MomAndBaby/contact">Contact</a></li>
                                                <li><a href="/SWP391-MomAndBaby/blog">Blog</a></li>
                                                <li><a href="/SWP391-MomAndBaby/about">About</a></li>
                                            </ul>
                                            <!-- /NAV -->
                                        </div>
                                        <!-- /responsive-nav -->
                                    </div>
                                    <!-- /container -->
                                </nav>
                            </div>
                            <!-- /MENU BAR -->

                            <!-- ACCOUNT -->
                            <div class="col-md-5 clearfix">
                                <div class="col-md-9">
                                    <form action="/SWP391-MomAndBaby/product/search" method="get">
                                        <div class="header-search">
                                            <input class="input" type="text" name="keyword" id="search" placeholder="What are you looking for?" value="${keyword}"/>
                                        <button class="search-icon" type="submit" 
                                                style="background: transparent; outline: none; border: none">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <div class="header-ctn">
                                <c:set var="userLogin" value="${getDao.getAccount(sessionScope.usernameUser)}" />
                                <c:set var="carts" value="${getCartLib.getAllCart(userLogin.ID)}" />
                                <!-- Cart -->
                                <div class="dropdown">
                                    <a
                                        class="dropdown-toggle"
                                        data-toggle="dropdown"
                                        aria-expanded="true"
                                        >
                                        <i class="fa fa-shopping-cart"></i>
                                        <div class="qty">${carts.size()}</div>
                                    </a>
                                    <div class="cart-dropdown">
                                        <div class="cart-list">
                                            <c:set var="totalCart" value="0" />
                                            <c:forEach items="${carts}" var="cart">
                                                <c:set var="productCart" value="${getDao.getProduct(cart.productID)}" />
                                                <div class="product-widget">
                                                    <div class="product-img">
                                                        <img src="${productCart.mainImg}" alt="${productCart.name}" />
                                                    </div>
                                                    <div class="product-body">
                                                        <h3 class="product-name">
                                                            <a href="/SWP391-MomAndBaby/product/detail/${productCart.ID}">${productCart.name}</a>
                                                        </h3>
                                                        <h4 class="product-price">
                                                            <span class="qty">${cart.quantity}x</span>
                                                            <c:if test="${productCart.newPrice > 0}">
                                                                <c:set var="totalCart" value="${totalCart + (productCart.newPrice * cart.quantity)}" />
                                                                ${currency.currencyFormat(productCart.newPrice * cart.quantity, "VNĐ")}
                                                            </c:if>
                                                            <c:if test="${productCart.newPrice == 0}">
                                                                ${currency.currencyFormat(productCart.oldPrice * cart.quantity, "VNĐ")}
                                                                <c:set var="totalCart" value="${totalCart + (productCart.oldPrice * cart.quantity)}" />
                                                            </c:if>
                                                        </h4>
                                                    </div>
                                                    <button class="delete">
                                                        <i class="fa fa-close"></i>
                                                    </button>
                                                </div>
                                            </c:forEach>
                                            <c:if test="${carts.size() == 0}">
                                                No have items here
                                            </c:if>
                                        </div>
                                        <c:if test="${carts.size() > 0}">
                                            <div class="cart-summary">
                                                <small>${carts.size()} Item(s) selected</small>
                                                <h5>SUBTOTAL: ${currency.currencyFormat(totalCart, "VNĐ")}</h5>
                                            </div>
                                            <div class="cart-btns">
                                                <a href="/SWP391-MomAndBaby/cart">View Cart</a>
                                                <a href="/SWP391-MomAndBaby/checkout"
                                                   >Checkout <i class="fa fa-arrow-circle-right"></i
                                                    ></a>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                                <!-- /Cart -->

                                <!-- Account -->

                                <div class="dropdown">
                                    <c:set var="userLoginRole" value="${sessionScope.usernameRole}" />
                                    <c:set var="userLoginUsername" value="${sessionScope.usernameUser}" />
                                    <c:if test="${!(userLoginUsername != null && userLoginRole != null)}">
                                        <a href="/SWP391-MomAndBaby/login"
                                           class="dropdown-toggle"
                                           >
                                            <i class="fa fa-user-o"></i>
                                        </a>
                                    </c:if>
                                    <c:if test="${userLoginUsername != null && userLoginRole != null}">
                                        <c:set var="accountBalance" value="${getDao.getAccount(userLoginUsername)}" />
                                        <a 
                                            class="dropdown-toggle"
                                            data-toggle="dropdown"
                                            aria-expanded="true"
                                            >
                                            <i class="fa fa-user-o"></i>
                                        </a>
                                        <div class="account-dropdown">
                                            <div>
                                                <h4>
                                                    Welcome ${sessionScope.usernameUser}
                                                </h4>
                                            </div>
                                            <div>
                                                <a >
                                                    <i class="fa fa-money" style="margin-right: 15px"></i>
                                                    Balance: ${currency.currencyFormat(accountBalance.balance, "points")}
                                                </a>
                                            </div>
                                            <div>
                                                <a href="/SWP391-MomAndBaby/account">
                                                    <i class="fa fa-user-o" style="margin-right: 15px"></i>
                                                    Manage My Account
                                                </a>
                                            </div>
                                            <div>
                                                <a href="/SWP391-MomAndBaby/account/history-order"
                                                   ><i
                                                        class="fa fa-shopping-cart"
                                                        style="margin-right: 15px"
                                                        ></i
                                                    >My Order</a
                                                >
                                            </div>
                                            <div>
                                                <a href="/SWP391-MomAndBaby/logout"
                                                   ><i
                                                        class="fa fa-sign-out"
                                                        style="margin-right: 15px"
                                                        ></i
                                                    >Logout</a
                                                >
                                            </div>
                                        </div>
                                    </c:if>
                                </div>
                                <!-- /Account -->

                                <!-- Menu Toogle -->
                                <div class="menu-toggle">
                                    <a href="#">
                                        <i class="fa fa-bars"></i>
                                        <span>Menu</span>
                                    </a>
                                </div>
                                <!-- /Menu Toogle -->
                            </div>
                        </div>
                        <!-- /ACCOUNT -->
                    </div>
                    <!-- row -->
                </div>
                <!-- container -->
            </div>
            <!-- /MAIN HEADER -->
        </header>
        <!-- /HEADER -->
