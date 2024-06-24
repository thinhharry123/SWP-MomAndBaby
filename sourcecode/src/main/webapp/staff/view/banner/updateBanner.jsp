<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../components/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <section class="panel">
                    <header class="panel-heading">
                        Update banner
                    </header>
                    <c:set var="banner" value="${banner}" />
                    <div class="panel-body">
                        <div class=" form">
                            <form class="cmxform form-horizontal" method="post" action="/MomAndBaby/staff/banner" enctype="multipart/form-data">
                                <div class="form-group ">
                                    <label for="cname" class="control-label col-lg-3">Banner image</label>
                                    <div class="col-lg-6">
                                        <input onchange="previewImage(event, '.box-img-preview')" id="image-banner" type="file" name="image-banner" class="form-control input-img">
                                        <div class="box-img-preview">
                                            <img class="show-img-preview" style="width: 100px;" src="${banner.img}" alt="${banner.datePost}"/>
                                        </div>
                                        <span class="message_error"></span>
                                        <input value="${banner.img}" type="hidden" name="old-image">
                                        <input value="${banner.ID}" type="hidden" name="id">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-lg-3">Banner name</label>
                                    <div class="col-lg-6">
                                        <input required type="text" name="name-banner" id="name-banner" class="form-control" value="${banner.name}">
                                        <span class="message_error"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-lg-3">Status</label>
                                    <div class="col-lg-6">
                                        <select name="status" class="form-control">
                                            <option value="1"
                                                    <c:if test="${banner.status == 1}">selected</c:if>
                                                        >Active
                                                    </option>
                                                    <option value="0"
                                                    <c:if test="${banner.status == 0}">selected</c:if>>Hidden</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-lg-offset-3 col-lg-6">
                                            <button class="btn btn-primary" id="btn-update-banner" name="btn-update-banner" type="submit">Save</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <a class="btn btn-info btn-back-to-page" href="/admin/banner"><< Back to banner</a>
                        </div>
                    </section>
                </div>
            </div>
        </section>     
        <script src="./static-admin/assets/js/validation.js"></script>
        <script>
                                            let banner = document.querySelector('#image-banner'),
                                                    name = document.querySelector('#name-banner'),
                                                    btnSubmit = document.querySelector('#btn-update-banner');
                                            const messageImage = "Please choose a image",
                                                    messageEmpty = "This field can not empty";
                                            
                                            const inputsToValidateCheck = [
                                                {element: banner, message: messageImage, regex: null, type: "image", isEmpty: true},
                                                {element: name, message: messageEmpty, regex: /^.{1,}$/, type: "text", isEmpty: false}
                                            ];
                                            validation(inputsToValidateCheck, btnSubmit);
        </script>
    <%@include file="../components/footer.jsp" %>
