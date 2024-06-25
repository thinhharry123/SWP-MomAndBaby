

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="currency" class="Utils.CurrencyConverter" />
<jsp:useBean id="getDao" class="Utils.GetDAO" />
<%@include file="./components/header.jsp" %>
<!-- END nav -->
<div class="hero-wrap hero-bread" 
     style="
     background-image: url('https://png.pngtree.com/thumb_back/fh260/back_our/20190620/ourmid/pngtree-taobao-pure-milk-promotion-carnival-poster-banner-background-image_153800.jpg');
     background-size: cover;
     background-position: center center;
     ">
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center" style="color: #333">
                <p class="breadcrumbs"><span class="mr-2"><a href="/SWP391-MomAndBaby">Home</a></span> <span>Blog</span></p>
                <h1 class="mb-0 bread">Blog</h1>
            </div>
        </div>
    </div>
</div>
<section class="ftco-section ftco-degree-bg">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 ftco-animate">
                <div class="row">
                    <c:forEach items="${blogs}" var="blog">
                        <div class="col-md-12 d-flex ftco-animate">
                            <div class="blog-entry align-self-stretch d-md-flex">
                                <a href="/SWP391-MomAndBaby/blog/view/${blog.ID}" class="block-20" style="background-image: url('${blog.image}');">
                                </a>
                                <div class="text d-block pl-md-4">
                                    <div class="meta mb-3">
                                        <div><a href="#">${blog.datePost}</a></div>
                                        <div><a href="#"> --  Admin</a></div>
                                        <div><a href="#" class="meta-chat"></a></div>
                                    </div>
                                    <h3 class="heading"><a href="#">${blog.title}</a></h3>
                                    <p>${blog.shortDesc}</p>
                                    <p><a href="/SWP391-MomAndBaby/blog/view/${blog.ID}" class="btn btn-primary py-2 px-3">Read more</a></p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div> <!-- .col-md-8 -->
            <div class="col-lg-4 sidebar ftco-animate">
                <div class="sidebar-box">
                    <form action="/SWP391-MomAndBaby/blog" method="post" class="search-form">
                        <div class="form-group">
                            <input type="text" name="key" class="form-control" placeholder="Search..." value="${key}">
                            <button name="btn-search" style="margin-top: 10px;" class="btn btn-warning">Search</button>
                        </div>
                    </form>
                </div>
                <div class="sidebar-box ftco-animate">
                    <h3 class="heading">Categories</h3>
                    <ul class="categories">
                        <c:forEach items="${categories}" var="cat">
                            <c:set var="numberOfPost" value="${getDao.getNumberOfPostByCategory(cat.ID)}" />
                            <c:if test="${numberOfPost > 0}">
                                <li><a href="/SWP391-MomAndBaby/blog/category/${cat.ID}">${cat.name} <span>(${numberOfPost})</span></a></li>
                                </c:if>
                            </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section> <!-- .section -->
<%@include file="./components/footer.jsp" %>