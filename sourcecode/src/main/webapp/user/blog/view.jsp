
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="currency" class="Utils.CurrencyConverter" />
<jsp:useBean id="getDao" class="Utils.GetDAO" />
<%@include file="./components/header.jsp" %>
<!-- END nav -->
<style>
    .container-blog img,
    .container-blog table{
        max-width: 100%;
    }
</style>
<div class="hero-wrap hero-bread" style="background-image: url('${currentBlog.image}');">
    <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
            <div class="col-md-9 ftco-animate text-center">
                <p class="breadcrumbs"><span class="mr-2"><a href="/SWP391-MomAndBaby">Home</a></span> <span>Blog</span></p>
                <h1 class="mb-0 bread">Blog</h1>
            </div>
        </div>
    </div>
</div>

<section class="ftco-section ftco-degree-bg">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 ftco-animate" style="padding:10px;border: 1px solid #fc8383">
                <h2 style="font-weight: bold">${currentBlog.title}</h2>
                <div class="meta" style="display: flex; gap: 10px; font-size: 12px; font-style: italic">
                    <div>Post by admin at ${currentBlog.datePost}</div>
                </div>
                <div class="container-blog" style="padding-top:10px;border-top: 1px solid #fafafa">
                    ${currentBlog.description}
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
                <div class="sidebar-box ftco-animate">
                    <h3 class="heading">Suggest product</h3>
                    <c:forEach items="${productHelp}" var="product">
                        <div class="block-21 mb-4 d-flex" style="padding:10px;border: 1px solid #fc8383">
                            <a class="blog-img mr-4" style="background-image: url(${product.mainImg});"></a>
                            <div class="text">
                                <h3 class="heading-1"><a href="/SWP391-MomAndBaby/product/detail/${product.ID}">${product.name}</a></h3>
                                <div class="meta">
                                    <div>
                                        <a href="/SWP391-MomAndBaby/product/detail/${product.ID}">
                                            <c:if test="${product.newPrice > 0}">
                                                ${currency.currencyFormat(product.newPrice, "VNĐ")}
                                            </c:if>
                                            <c:if test="${product.newPrice == 0}">
                                                ${currency.currencyFormat(product.oldPrice, "VNĐ")}
                                            </c:if>
                                        </a>
                                    </div>
                                    <div>
                                        <a href="/SWP391-MomAndBaby/product/detail/${product.ID}">
                                            Sold: ${product.sold}
                                        </a>
                                    </div>
                                    <div>
                                        <a href="/SWP391-MomAndBaby/product/detail/${product.ID}">
                                            Quantity: ${product.quantity}
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

        </div>
    </div>
</section> <!-- .section -->
<%@include file="./components/footer.jsp" %>