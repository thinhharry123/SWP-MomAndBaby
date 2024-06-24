<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../components/header.jsp" %>
<section id="main-content">
    <section class="wrapper">
        <div class="wrapper-box-add">
            <div class="row">
                <div class="col-lg-12">
                    <div id="toast"></div>
                    <section class="panel">
                        <div class="panel-body">
                            <div class="position-center">
                                <div class="text-center">
                                    <a href="#myModal" data-toggle="modal" class="btn btn-success">
                                        Add new
                                    </a>
                                </div>
                                <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                                                <h4 class="modal-title">Add banner</h4>
                                            </div>
                                            <div class="modal-body">
                                                <form role="form" method="post" action="/MomAndBaby/staff/banner" enctype="multipart/form-data">
                                                    <div class="form-group">
                                                        <label>Banner image</label>
                                                        <input onchange="previewImage(event, '.box-img-preview')" required type="file" name="image-banner" id="image-banner" class="form-control">
                                                        <div class="box-img-preview">
                                                            
                                                        </div>
                                                        <span class="message_error"></span>
                                                    </div>
                                                    <div class="form-group">
                                                        <label>Banner name</label>
                                                        <input required type="text" name="name-banner" id="name-banner" class="form-control">
                                                        <span class="message_error"></span>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="control-label">Status</label>
                                                        <select name="status" class="form-control">
                                                            <option value="1">Active</option>
                                                            <option value="0">Hidden</option>
                                                        </select>
                                                    </div>
                                                    <button name="btn-add-banner" id="add-new" type="submit" class="btn btn-group-justified btn-primary">Add</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
        <div class="table-agile-info">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Banners
                </div>
            </div>
            <div class="table-responsive" style="padding: 5px;">
                <script>
                    $(document).ready(function () {
                        $("#category").DataTable();
                    });
                </script>
                <form method="post" action="/MomAndBaby/staff/banner">
                    <div class="wrapper-box-btn">
                        <button name="btn-delete-banners" onclick="return confirm('Are you sure to delete?')" class="btn btn-sm btn-danger">
                            Delete all checked
                        </button>
                    </div>
                    <table id="category" class="table table-striped b-t b-light">
                        <thead>
                            <tr>
                                <th style="width:20px;">
                                    <label class="i-checks all-check m-b-none">
                                        <input type="checkbox"><i></i>
                                    </label>
                                </th>
                                <th>Banner image</th>
                                <th>Banner name</th>
                                <th>Date at</th>
                                <th>Date update</th>
                                <th>Status</th>
                                <th style="width:30px;">Action</th>
                            </tr>
                        </thead>
                        <tbody class="render-data">
                            <c:forEach items="${banners}" var="banner">
                                <tr>
                                    <td><input type="checkbox" name="deleteBanner" value="${banner.ID}"></td>
                                    <td>
                                        <img src="${banner.img}" alt="alt"/>
                                    </td>
                                    <td><span class="text-ellipsis">${banner.name}</span></td>
                                    <td><span class="text-ellipsis">${banner.datePost}</span></td>
                                    <td>
                                        <span class="text-ellipsis">
                                            ${banner.dateUpdate}
                                        </span>
                                    </td>
                                    <td>
                                        <c:if test="${banner.status == 1}">
                                            <span class="label label-success">Active</span>
                                        </c:if>
                                        <c:if test="${banner.status == 0}">
                                            <span class="label label-default">Hidden</span>
                                        </c:if>
                                    </td>
                                    <td class="box-action">
                                        <a href="/MomAndBaby/staff/banner/update/${banner.ID}" class="active btn-action green">
                                            <i class="bx bx-edit"></i>
                                        </a>
                                        <a onclick="return confirm('Are you sure to delete?')" 
                                           href="/MomAndBaby/staff/banner/delete/${banner.ID}" 
                                           class="active btn-action orange">
                                            <i class='bx bx-trash'></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        </div>
    </section>
    <script src="./static-admin/assets/js/validation.js"></script>
    <script>
                    let banner = document.querySelector('#image-banner'),
                    name = document.querySelector('#name-banner'),
                            btnSubmit = document.querySelector('#add-new');
                    const messageImage = "Please choose a image",
                            messageEmpty = "This field can not empty";;
                    const inputsToValidateCheck = [
                        {element: banner, message: messageImage, regex: null, type: "image", isEmpty: false},
                        {element: name, message: messageEmpty, regex: /^.{1,}$/, type: "text", isEmpty: false}
                    ];
                    validation(inputsToValidateCheck, btnSubmit);
                </script>
    <%@include file="../components/footer.jsp" %>
