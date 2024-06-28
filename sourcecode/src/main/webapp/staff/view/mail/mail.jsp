<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../components/header.jsp" %>
<section id="main-content">
    <section class="wrapper">
        <!-- page start-->
        <div class="mail-w3agile">
            <div class="row">
                <div class="col-sm-12 mail-w3agile">
                    <section class="panel">
                        <header class="panel-heading wht-bg">
                            <h4 class="gen-case">Send mail</h4>
                        </header>
                        <div class="panel-body">
                            <div class="compose-mail">
                                <form role="form-horizontal" method="post" action="/SWP391-MomAndBaby/staff/send-mail">
                                    <div class="form-group flex-column">
                                        <label for="to" class="">Email to reply:</label>
                                        <input type="text" id="email-reply" name="emailReply" class="form-control">
                                        <span class="message_error"></span>
                                    </div>
                                    <div class="wrapper-box-add">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <section class="panel">
                                                    <div class="panel-body">
                                                        <div>
                                                            <div class="text-left">
                                                                <a href="#myModal" data-toggle="modal" class="btn btn-success">
                                                                    Select user to send
                                                                </a>
                                                            </div>
                                                            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
                                                                <div class="modal-dialog">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <button aria-hidden="true" data-dismiss="modal" class="close btn-ok" type="button">Ok</button>
                                                                            <h4 class="modal-title">Select user</h4>
                                                                        </div>
                                                                        <div class="modal-body">
                                                                            <div class="form-group">
                                                                                <span class="btn btn-primary" onclick="checkAllUser(0)">Check all user</span>
                                                                                <div class="row">
                                                                                    <c:forEach items="${accounts}" var="account">
                                                                                        <div class="form-group col-lg-12" style="display: flex; align-items: center">
                                                                                            <div class="col-lg-2" style="width: 20px">
                                                                                                <input id="${account.ID}check" class="send-mail-role-0" style="width: 20px; margin-top: 0" name="mail-user-item" value="${account.email}" type="checkbox"/>
                                                                                            </div>
                                                                                            <label for="${account.ID}check" class="control-label col-lg-10">
                                                                                                ${account.email}
                                                                                                <span class="label label-warning">${account.roleName}</span>
                                                                                            </label>
                                                                                        </div>
                                                                                    </c:forEach>
                                                                                </div>
                                                                            </div>
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
                                    <div class="form-group flex-column">
                                        <label for="subject" class="">Subject:</label>
                                        <input type="text" name="title" id="title-send-mail" class="form-control">
                                        <span class="message_error"></span>
                                    </div>
                                    <div class="compose-editor">
                                        <textarea name="message" id="ck-editor4" class="wysihtml5 form-control" rows="9"></textarea>
                                        <span class="message_error"></span>
                                    </div>
                                    <div class="form-group flex-column">
                                        <label for="subject" class="">Select voucher</label>
                                        <select name="voucher" required>
                                            <option value="">Select voucher</option>
                                            <c:forEach items="${vouchers}" var="voucher">  
                                                <option value="${voucher.id}">${voucher.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="compose-btn">
                                        <button name="btn-send-mail" id="send-mail-btn" class="btn btn-primary btn-lg"><i class='bx bxs-send'></i> Send</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
            <!-- page end-->
        </div>
    </section>
    <script src="./static-admin/assets/js/validation.js"></script>
    <script src="./static-admin/ckeditor/ckeditor.js"></script> 
    <script>
                                                                                    function checkAllUser(type) {
                                                                                        const allCheck = document.querySelectorAll(".send-mail-role-" + type);
                                                                                        allCheck.forEach((item) => {
                                                                                            if (item.checked) {
                                                                                                item.checked = false;
                                                                                            } else {
                                                                                                item.checked = true;
                                                                                            }
                                                                                        })
                                                                                    }
                                                                                    CKEDITOR.replace('ck-editor4', {
                                                                                        language: 'en'
                                                                                    });
                                                                                    // validation form
                                                                                    const email = document.getElementById('email-reply'),
                                                                                            title = document.getElementById('title-send-mail'),
                                                                                            ck = document.getElementById('ck-editor4');
                                                                                    const messageEmail = "Email is not valid. Ex: domain.@gmail.com",
                                                                                            messageEmpty = "This field can not empty";
                                                                                    // array to save all input to check, message show error of each and a string regex to check 
                                                                                    const inputsToValidate = [
                                                                                        {element: title, message: messageEmpty, regex: /^.{1,}$/, type: "text", isEmpty: false},
                                                                                        {element: ck, message: messageEmpty, regex: "ck-editor4", type: "ckeditor", isEmpty: false},
                                                                                        {element: email, message: messageEmail, regex: /^(?:([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/, type: "text", isEmpty: false},
                                                                                    ];
                                                                                    validation(inputsToValidate, document.querySelector("#send-mail-btn"));
    </script>
    <%@include file="../components/footer.jsp" %>