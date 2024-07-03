<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./components/header.jsp" %>

<!-- BREADCRUMB -->
<style>
    .star-rating {
        direction: rtl;
        display: inline-block;
        padding: 20px;
        width: 100%;
        text-align: left;
    }

    .star-rating input {
        display: none;
    }

    .star-rating label {
        color: #bbb;
        font-size: 20px;
        padding: 0;
        cursor: pointer;
        transition: all 0.3s ease-in-out;
    }

    .star-rating input:checked ~ label,
    .star-rating input:hover ~ label,
    .star-rating label:hover,
    .star-rating label:hover ~ label {
        color: #f2b600;
    }
</style>
<div id="breadcrumb" class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <div class="col-md-10">
                <ul class="breadcrumb-tree">
                    <li><a href="/SWP391-MomAndBaby">Home</a></li>
                    <li><a>Account</a></li>
                </ul>
            </div>
            <div class="col-md-2">
                <ul>
                    <li>Welcome! <a>${accountLogin.username}</a></li>
                </ul>
            </div>
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /BREADCRUMB -->

<!-- Section -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">
            <div class="col-md-3">
                <div style="margin: 10px">
                    <div>
                        <label>Manage My Account</label>
                    </div>
                    <div style="margin-left: 30px; margin-top: 10px">
                        <a href="/SWP391-MomAndBaby/account">My profile</a>
                    </div>
                    <div style="margin-left: 30px; margin-top: 5px">
                        <a href="/SWP391-MomAndBaby/account/password">Change password</a>
                    </div>
                </div>

                <div style="margin: 10px">
                    <div>
                        <label>My Order</label>
                    </div>
                    <div style="margin-left: 30px; margin-top: 10px">
                        <a href="/SWP391-MomAndBaby/account/history-order">History order</a>
                    </div>
                    <div style="margin-left: 30px; margin-top: 10px">
                        <a href="/SWP391-MomAndBaby/account/pre-order">Pre order</a>
                    </div>
                </div>
            </div>
            <form action="/SWP391-MomAndBaby/account" method="post">
                <div class="col-md-9">
                    <div
                        class="order-summary"
                        style="
                        border: 1px solid black;
                        padding: 20px;
                        margin-bottom: 60px;
                        "
                        >
                        <div class="order-products">
                            <div class="order-col" style="margin-top: 20px">
                                <strong style="color: #db4444; font-size: 20px"
                                        >Feedback order #${currentBill.ID}</strong
                                >
                            </div>
                            <div
                                class="order-col"
                                style="
                                margin-top: 20px;
                                display: flex;
                                justify-content: space-between;
                                "
                                >
                                <div style="width: 100%">
                                    <p style="text-align: left">Rating: </p>
                                    <div class="star-rating">
                                        <input type="radio" id="star5" name="rating" value="5" ${feedbacked != null && feedbacked.star == 5 ? "checked" : ""}/>
                                        <label for="star5" title="5 stars">&#9733;</label>
                                        <input type="radio" id="star4" name="rating" value="4" ${feedbacked != null && feedbacked.star == 4 ? "checked" : ""}/>
                                        <label for="star4" title="4 stars">&#9733;</label>
                                        <input type="radio" id="star3" name="rating" value="3" ${feedbacked != null && feedbacked.star == 3 ? "checked" : ""}/>
                                        <label for="star3" title="3 stars">&#9733;</label>
                                        <input type="radio" id="star2" name="rating" value="2" ${feedbacked != null && feedbacked.star == 2 ? "checked" : ""}/>
                                        <label for="star2" title="2 stars">&#9733;</label>
                                        <input type="radio" id="star1" name="rating" value="1" ${feedbacked != null && feedbacked.star == 1 ? "checked" : ""}/>
                                        <label for="star1" title="1 star">&#9733;</label>
                                    </div>
                                    <input type="hidden" id="hiddenRating" name="rating" value="5">
                                    <input type="hidden" id="hiddenRating" name="idOrder" value="${currentBill.ID}">
                                </div>
                            </div>
                            <div
                                class="order-col"
                                style="display: flex; justify-content: space-between"
                                >
                                <div style="width: 100%">
                                    <p style="text-align: left">Feedback:</p>
                                    <textarea style="width: 100%" required name="feedback" placeholder="Give a feedback" rows="4">${feedbacked.feedback}</textarea>
                                </div>
                            </div>
                            <div class="text-right" style="margin-top: 40px">
                                <a href="/SWP391-MomAndBaby/account/history-order/detail/${currentBill.ID}"
                                   class="btn btn-ok" 
                                   style="
                                   padding: 10px;
                                   "
                                   >
                                    Back to order
                                </a>
                                <c:if test="${feedbacked != null && feedbacked.status <= 1}">
                                    <button
                                        class="btn btn-primary"
                                        name="btn-edit-feedback"
                                        style="
                                        padding: 10px;
                                        color: #fff;
                                        "
                                        >
                                        Save Changes
                                    </button>
                                </c:if>
                                <c:if test="${feedbacked != null && feedbacked.status == 2}">
                                    <a
                                        class="btn btn-danger"
                                        style="
                                        padding: 10px;
                                        color: #fff;
                                        cursor: not-allowed
                                        "
                                        >
                                        Your feedback has been violated. If you have any questions, please contact us
                                    </a>
                                </c:if>
                                <c:if test="${feedbacked == null}">

                                    <button
                                        class="btn btn-success"
                                        name="btn-new-feedback"
                                        style="
                                        padding: 10px;
                                        color: #fff;
                                        "
                                        >
                                        Feedback
                                    </button>
                                </c:if>

                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /section -->
<script>
    document.querySelectorAll('.star-rating input').forEach(star => {
        star.addEventListener('change', function () {
            document.getElementById('hiddenRating').value = this.value;
        });
    });
</script>
<%@include file="./components/footer.jsp" %>