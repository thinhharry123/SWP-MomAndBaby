
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
                <h2 class="w3ls_head">Details product</h2>
                <a class="btn btn-info btn-back-to-page" href="/SWP391-MomAndBaby/admin/product"><< Back to product</a>
                <div class="box-content">
                    <form class="cmxform form-horizontal form-validate" accept-charset="UTF-8" method="post" action="/SWP391-MomAndBaby/admin/product" enctype="multipart/form-data">
                        <div class="row">
                            <div class="form-group col-lg-12">
                                <label class="control-label col-lg-12">Name product</label>
                                <div class="col-lg-12">
                                    <input name="id" value="${product.ID}" type="hidden"/>
                                <input id="name" value="${product.name}" name="name" class=" form-control">
                                <span class="message_error"></span>
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Model</label>
                            <div class="col-lg-12">
                                <input id="model" value="${product.model}" name="model" class=" form-control">
                                <span class="message_error"></span>
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Price</label>
                            <div class="col-lg-12">
                                <input id="price" onchange="document.querySelector('.price-sale').max = this.value" pattern="[0-9]+([.,][0-9]+)?" value="${currency.currencyFormatInput(product.oldPrice)}" min="0" name="oldPrice" type="text" class="form-control" required>
                                <span class="message_error"></span>
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Price sale</label>
                            <div class="col-lg-12">
                                <input id="price-sale" pattern="[0-9]+([.,][0-9]+)?" value="${currency.currencyFormatInput(product.newPrice)}" min="0" max="${product.oldPrice}" name="newPrice" type="text" class="form-control price-sale" required>
                                <span class="message_error"></span>
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Quantity</label>
                            <div class="col-lg-12">
                                <input id="quantity" value="${product.quantity}" name = "quantity" class=" form-control" required>
                                <input value="${product.sold}" name = "sold" type="hidden">
                                <span class="message_error"></span>
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Main image</label>
                            <div class="col-lg-12">
                                <input id="mainImage" onchange="previewImage(event, '.box-img-preview.main')" type="file" name="imgMain" class="form-control main-img">
                                <input name="oldMainImg" value="${product.mainImg}" type="hidden"/>
                                <div class="box-img-preview main">
                                    <img class="preview-main" src="${product.mainImg}" alt="" />
                                </div>
                                <span class="message_error"></span>
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Description image</label>
                            <div class="col-lg-12">
                                <input id="descImage" onchange="previewImage(event, '.box-img-preview.desc')" type="file" multiple="true" name="imgDesc" class="form-control">
                                <div class="box-img-preview desc">
                                    <c:if test="${imgDesc.size() > 0}">
                                        <c:forEach items="${imgDesc}" var="desc">
                                            <img src="${desc.imgUrl}" alt="${desc.productID}" />
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${imgDesc.size() <= 0}">
                                        <p>No image</p>
                                    </c:if>    
                                </div>
                                <span class="message_error"></span>
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Priority</label>
                            <div class="col-lg-12">
                                <select name="priority" class="form-control">
                                    <option ${product.priority == 1 ? "selected": ""} value="1">Normal</option>
                                    <option ${product.priority == 2 ? "selected": ""} value="2">Deal</option>
                                    <option ${product.priority == 3 ? "selected": ""} value="3">Feature</option> 
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Category</label>
                            <div class="col-lg-12">
                                <select name="categoryID" class="form-control">
                                    <c:forEach var="cat" items="${categories}">
                                        <option ${product.categoryID == cat.ID ? "selected": ""} value="${cat.ID}">${cat.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Brand</label>
                            <div class="col-lg-12">
                                <select name="brandID" class="form-control">
                                    <c:forEach var="bra" items="${brands}">
                                        <option ${product.brandID == bra.ID ? "selected": ""} value="${bra.ID}">${bra.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Producer</label>
                            <div class="col-lg-12">
                                <select name="producerID" class="form-control">
                                    <c:forEach var="pro" items="${producers}">
                                        <option ${product.producerID == pro.ID ? "selected": ""} value="${pro.ID}">${pro.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="box-content-desc form-group col-lg-12">
                            <label class="control-label col-lg-12">Description</label>
                            <div class="col-lg-12">
                                <textarea required class="form-control" name="desc" id="ck-editor1" cols="30" rows="10">
                                    ${product.description}
                                </textarea>
                                <span class="message_error"></span>
                            </div>
                        </div>
                        <div class="form-group col-lg-6">
                            <label class="control-label col-lg-12">Status</label>
                            <div class="col-lg-12">
                                <select name="status" class="form-control">
                                    <option ${product.status == 1 ? "selected": ""} value="1">Active</option>
                                    <option ${product.status == 0 ? "selected": ""} value="0">Hidden</option>
                                </select>
                            </div>
                        </div>
                        <button id="update-product" name="btn-update-product" type="submit" class="btn btn-group-justified btn-primary">Update</button>
                        <a class="btn btn-info btn-back-to-page" href="/SWP391-MomAndBaby/admin/product"><< Back to product</a>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <script src="./static-admin/ckeditor/ckeditor.js"></script> 
    <script>
                                    CKEDITOR.replace('ck-editor1', {
                                        language: 'en'
                                    });
    </script>
    <script src="./static-admin/assets/js/validation.js"></script>
    <script>
                                    // validation form
                                    const name = document.getElementById('name'),
                                            model = document.getElementById('model'),
                                            price = document.getElementById('price'),
                                            priceSale = document.getElementById('price-sale'),
                                            available = document.getElementById('quantity'),
                                            mainImage = document.getElementById('mainImage'),
                                            descImage = document.getElementById('descImage'),
                                            desc = document.getElementById('ck-editor1');
                                    const messageEmpty = "This field can not empty",
                                            messageModel = "Model product can not empty",
                                            messagePrice = "Price product must be a postitive number",
                                            messagePriceSale = "Price sale product must be a postitive number",
                                            messageAvailable = "Available must be a integer number",
                                            messageMainImage = "Please choose a image",
                                            messageDescImage = "Please choose a image";

                                    // array to save all input to check, message show error of each and a string regex to check 
                                    const inputsToValidate = [
                                        {element: name, message: messageEmpty, regex: /^.{1,}$/, type: "text", isEmpty: false},
                                        {element: model, message: messageEmpty, regex: /^.{1,}$/, type: "text", isEmpty: false},
                                        {element: price, message: messagePrice, regex: /^[0-9]+(?:\.[0-9]+)?$/, type: "text", isEmpty: false},
                                        {element: priceSale, message: messagePriceSale, regex: /^[0-9]+(?:\.[0-9]+)?$/, type: "text", isEmpty: false},
                                        {element: available, message: messageAvailable, regex: /^\d+$/, type: "text", isEmpty: false},
                                        {element: mainImage, message: messageMainImage, regex: "image", type: "image", isEmpty: true},
                                        {element: descImage, message: messageDescImage, regex: "image", type: "image", isEmpty: true},
                                        {element: desc, message: messageEmpty, regex: "ck-editor1", type: "ckeditor", isEmpty: false}
                                    ];
                                    validation(inputsToValidate, document.querySelector("#update-product"));
    </script>
    <%@include file="../components/footer.jsp" %>