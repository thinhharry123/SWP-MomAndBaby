<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- footer -->
<div class="footer" style="background-color: #c1e3e1">
    <div class="wthree-copyright">
        <p>© 
            <script>
                let date = new Date();
                document.write(date.getFullYear());
            </script>
            | Design by <a href="/SWP391-MomAndBaby/admin"> Group 8</a></p>
    </div>
</div>
<!-- / footer -->
</section>
<!--main content end-->
</section>
<script src="./static-admin/assets/js/bootstrap.js"></script>
<script src="./static-admin/assets/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="./static-admin/assets/js/scripts.js"></script>
<script src="./static-admin/assets/js/jquery.slimscroll.js"></script>
<script src="./static-admin/assets/js/jquery.nicescroll.js"></script>
<script src="./static-admin/assets/js/jquery.scrollTo.js"></script>
<script src="./static-admin/assets/js/toast.js"></script>
<script src="./static-admin/assets/js/app.js"></script> 
<jsp:useBean id="convertActionText" scope="page" class="Utils.ConvertActionText"></jsp:useBean>
<c:set var="type_message" value="${param.status}"/>
<c:set var="action" value="${param.act}"/>
<script>
    <c:if test="${type_message != null && type_message == 1}">
        showSuccess("${convertActionText.convertActionText(action, type_message)}");
    </c:if>
    <c:if test="${type_message != null && (type_message == 0 || type_message == 2 || type_message == 3)}">
        showError("${convertActionText.convertActionText(action, type_message)}");
    </c:if>
</script>
<script>
    let checkAll = document.querySelector(".all-check input[type='checkbox']");
    let inputItemCheck = document.querySelectorAll("table tr td input[type='checkbox']");
    if (checkAll) {
        checkAll.addEventListener('click', () => {
            inputItemCheck.forEach((item) => {
                if (checkAll.checked == true) {
                    item.checked = true;
                } else {
                    item.checked = false;
                }
            })
        })
    }
</script>
</body>
</html>
