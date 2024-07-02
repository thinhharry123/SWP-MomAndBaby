<%-- 
    Document   : detailProduct
    Created on : Jun 19, 2024, 2:31:51 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../components/header.jsp" %>
<jsp:useBean id="currency" class="Utils.CurrencyConverter"></jsp:useBean>
<c:set value="${product}" var="product"></c:set>
<c:set value="${category}" var="category"></c:set>
<c:set value="${producer}" var="producer"></c:set>
<c:set value="${brand}" var="brand"></c:set>
    <section id="main-content">
        <section class="wrapper">
            <div class="gallery">
                 <a class="btn btn-info btn-back-to-page" href="/SWP391-MomAndBaby/staff/product"><< Back to product</a>
                <h2 class="w3ls_head">Details product</h2>
                <div class="box-content">
                    <form class="cmxform form-horizontal " id="signupForm">
                        <div class="row">
                            <div class="form-group col-lg-12">
                                <label class="control-label col-lg-12">Name product</label>
                                <div class="col-lg-12">
                                    <input value="${product.name}" disabled class=" form-control">
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Model</label>
                            <div class="col-lg-12">
                                <input value="${product.model}" disabled class=" form-control">
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Price</label>
                            <div class="col-lg-12">
                                <input value="${currency.currencyFormatInput(product.oldPrice)}" disabled class=" form-control">
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Price sale</label>
                            <div class="col-lg-12">
                                <input value="${currency.currencyFormatInput(product.newPrice)}" disabled class=" form-control">
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Date post</label>
                            <div class="col-lg-12">
                                <input value="${product.datePost}" disabled class=" form-control">
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Date update</label>
                            <div class="col-lg-12">
                                <input value="${product.dateUpdate}" disabled class=" form-control">
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Status</label>
                            <div class="col-lg-12">
                                <input  value="${product.status == 0 ? 'Hidden' : 'Active'}" disabled class=" form-control">
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Quantity</label>
                            <div class="col-lg-12">
                                <input value="${product.quantity}" disabled class=" form-control">
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Sold</label>
                            <div class="col-lg-12">
                                <input value="${product.sold}" disabled class=" form-control">
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Priority</label>
                            <div class="col-lg-12">
                                <c:choose>
                                    <c:when test="${product.priority == 1}">
                                        <input value="Normal" disabled class=" form-control">
                                    </c:when>
                                    <c:when test="${product.priority == 2}">
                                        <input value="Deal" disabled class=" form-control">
                                    </c:when>
                                    <c:when test="${product.priority == 3}">
                                        <input value="Feature" disabled class=" form-control">
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Category</label>
                            <div class="col-lg-12">
                                <input value="${category.name}" disabled class=" form-control">
                            </div>
                        </div>
                       <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Brand</label>
                            <div class="col-lg-12">
                                <input value="${brand.name}" disabled class=" form-control">
                            </div>
                        </div>     
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Producer</label>
                            <div class="col-lg-12">
                                <input  value="${producer.name}" disabled class=" form-control">
                            </div>
                        </div>
                        <div class="box-content-desc form-group col-lg-12">
                            <label class="control-label col-lg-12">Description</label>
                            <div class="col-lg-12">
                                ${product.description}
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="gallery-grids">
                <div class="gallery-top-grids">
                    <label class="control-label col-lg-12">Main image product</label>
                    <div class="col-sm-3 gallery-grids-left">
                        <div class="gallery-grid">
                            <img src="${product.mainImg}" alt="Main img" />
                        </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>               
            <div class="gallery-grids">
                <div class="gallery-top-grids">
                    <label class="control-label col-lg-12">Image description</label>
                    <c:if test="${imgDesc.size() > 0}">
                        <c:forEach items="${imgDesc}" var="desc">
                            <div class="col-sm-1 gallery-grids-left">
                                <div class="gallery-grid">
                                    <img src="${desc.imgUrl}" alt="${desc.productID}" />
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${imgDesc.size() <= 0}">
                        <p style="color: red;">No image</p>
                    </c:if>    
                    <div class="clearfix"></div>
                </div>
            </div>
            <a class="btn btn-info btn-back-to-page" href="/SWP391-MomAndBaby/staff/product"><< Back to product</a>
        </div>
    </section>
    <%@include file="../components/footer.jsp" %>
