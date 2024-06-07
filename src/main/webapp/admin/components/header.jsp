
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="isLogout" value="${param.logout != null}" />
<c:set var="req" value="${pageContext.getRequest()}" />
<jsp:useBean id="getDao" class="Utils.GetDAO"></jsp:useBean>
<c:set var="session" value="${req.getSession()}" />
<c:set var="adminLogin" value="${getDao.getAccount(sessionScope.usernameAdmin)}" />
<!DOCTYPE html>
<head>
    <title>Admin | Mom And babies</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!-- bootstrap-css -->
    <base href="http://localhost:8080/SWP391-MomAndBaby/" />
    <!--<base href="https://c586-2402-800-6343-a711-8958-7dd9-7dc8-a065.ngrok-free.app/SWP391-MomAndBaby/" />-->
    <link rel="icon" type="image/x-icon" href="./uploads/base/favicon.png">
    <link rel="stylesheet" href="./static-admin/assets/css/bootstrap.min.css" >
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
    <!-- //bootstrap-css -->
    <!-- Custom CSS -->
    <link rel="stylesheet" href="./static-admin/assets/css/form.css">
    <link href="./static-admin/assets/css/style.css" rel='stylesheet' type='text/css' />
    <link href="./static-admin/assets/css/style-responsive.css" rel="stylesheet"/>
    <!-- font CSS -->
    <link href='//fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
    <!-- font-awesome icons -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" href="./static-admin/assets/css/toast.css">
    <link rel="stylesheet" href="./static-admin/assets/css/myCustom.css">
    <!-- //font-awesome icons -->
    <script src="./static-admin/assets/js/jquery2.0.3.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <script src="./static-admin/assets/js/filter.js"></script>
</head>
<body>
    <section id="container">
        <div id="toast"></div>
        <!--header start-->
        <header class="header fixed-top clearfix" style="background-color: #c1e3e1">
            <!--logo start-->
            <div class="brand">
                <a href="/SWP391-MomAndBaby/admin" class="logo">
                    <img src="./static-admin/assets/images/logo.png" alt="Logo" />
                </a>
                <div class="sidebar-toggle-box">
                    <div class="fa fa-bars">
                        <i class='bx bx-menu-alt-left'></i>
                    </div>
                </div>
            </div>
            <!--logo end-->
            <div class="top-nav clearfix">
                <!--search & user info start-->
                <ul class="nav pull-right top-menu">
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="/admin">
                            <img src="${adminLogin.avatar}" alt="default avatar">
                            <span class="username">${adminLogin.fullname != null ? adminLogin.fullname : adminLogin.username}</span>
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu extended logout">
                            <li>
                                <a href="/SWP391-MomAndBaby/admin/account/personal/${adminLogin.username}">
                                    <i class='bx bxs-user'></i>
                                    Personal
                                </a>
                            </li>
                            <li onclick="return confirm('Are you sure to logout?')">
                                <a href="/SWP391-MomAndBaby/admin/logout">
                                    <i class='bx bx-log-out'></i>
                                    Logout
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </header>
        <!--header end-->
        <!--sidebar start-->
        <aside>
            <div id="sidebar" class="nav-collapse" style="background-color: rgb(118 155 186 / 78%);">
                <!-- sidebar menu start-->
                <div class="leftside-navigation">
                    <ul class="sidebar-menu" id="nav-accordion">
                        <li>
                            <a href="/SWP391-MomAndBaby/admin">
                                <span>Home</span>
                            </a>
                        </li>
                        <li>
                            <a target="_blank" href="https://dashboard.kommunicate.io/conversations">
                                <i class='bx bxs-comment-detail'></i>
                                <span>Go to chat</span>
                            </a>
                        </li>
                        <li>
                            <a href="/SWP391-MomAndBaby/admin/category">
                                <span>Category</span>
                            </a>
                        </li>
                        <li>
                            <a href="/SWP391-MomAndBaby/admin/brand">
                                <span>Brand</span>
                            </a>
                        </li>
                        <li>
                            <a href="/SWP391-MomAndBaby/admin/producer">
                                <span>Producer</span>
                            </a>
                        </li>
                        <li>
                            <a href="/SWP391-MomAndBaby/admin/product">
                                <span>Product</span>
                            </a>
                        </li>               
                        <li>
                            <a href="/SWP391-MomAndBaby/admin/account">
                                <span>Account</span>
                            </a>
                        </li>
                    </ul>            
                </div>
                <!-- sidebar menu end-->
            </div>
        </aside>
        <!--sidebar end-->
        <!--main content start-->