<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../components/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <section class="panel">
                    <header class="panel-heading">
                        Update brand
                    </header>
                    <c:set var="brand" value="${currentBrand}"></c:set>
                        <div class="panel-body">
                            <div class=" form">
                                <form class="cmxform form-horizontal" method="post" action="/SWP391-MomAndBaby/admin/brand" enctype="multipart/form-data">
                                    <div class="form-group ">
                                        <label for="cname" class="control-label col-lg-3">Brand image</label>
                                        <div class="col-lg-6">
                                            <input onchange="previewImage(event, '.box-img-preview')" id="image-brand" type="file" name="image-brand" class="form-control input-img">
                                            <div class="box-img-preview">
                                                <img class="show-img-preview" style="width: 100px;" src="${brand.img}" alt="${brand.datePost}"/>
                                        </div>
                                        <span class="message_error"></span>
                                        <input value="${brand.img}" type="hidden" name="old-image">
                                        <input value="${brand.ID}" type="hidden" name="id">
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label for="cname" class="control-label col-lg-3">Name (required)</label>
                                    <div class="col-lg-6">
                                        <input value="${brand.name}" class=" form-control" id="name-brand" name="name">
                                        <span class="message_error"></span>
                                        <input value="${brand.ID}" type="hidden" name="id">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-lg-3">Status</label>
                                    <div class="col-lg-6">
                                        <select name="status" class="form-control">
                                            <option value="1"
                                                    <c:if test="${category.status == 1}">selected</c:if>
                                                        >Active
                                                    </option>
                                                    <option value="0"
                                                    <c:if test="${category.status == 0}">selected</c:if>>Hidden</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-offset-3 col-lg-6">
                                            <button class="btn btn-primary" id="update-brand" name="btn-update-brand" type="submit">Save</button>
                                        </div>
                                    </div>
                                </form>
                                <a class="btn btn-info btn-back-to-page" href="/SWP391-MomAndBaby/admin/brand"><< Back to brand</a>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </section>
        <script src="./static-admin/assets/js/validation.js"></script>
        <script >
                                                const name = document.getElementById('name-brand'),
                                                        brand = document.querySelector('#image-brand');
                                                const messageEmpty = "This field can not empty",
                                                        messageImage = "Please choose a image";
                                                // array to save all input to check, message show error of each and a string regex to check 
                                                const inputsToValidate = [
                                                    {element: brand, message: messageImage, regex: null, type: "image", isEmpty: true},
                                                    {element: name, message: messageEmpty, regex: /^.{1,}$/, type: "text", isEmpty: false}
                                                ];
                                                validation(inputsToValidate, document.querySelector("#update-brand"));
        </script>
    <%@include file="../components/footer.jsp" %>
