<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="./components/header.jsp" %>

    <!-- Section -->
    <div class="section">
      <div class="container text-center">
        <h1 style="font-size: 100px; margin-top: 100px; margin-bottom: 50px">
            <img src="https://cdn-icons-png.flaticon.com/128/4126/4126518.png" style="width: 150px">
          404 Not Found <i class="fa fa-exclamation-circle"></i>
        </h1>
        <p style="font-size: 20px; margin-top: 40px; margin-bottom: 60px">
            Your visited page is not found. You may go back to home page. 
        </p>
        <a href="/SWP391-MomAndBaby">
          <button
            class="btn btn-default"
            style="
              padding: 10px;
              color: #fff;
              background-color: #db4444;
              margin-bottom: 20px;
            "
          >
            Back to home page
          </button>
        </a>
      </div>
    </div>
    <!-- /Section -->

    <%@include file="./components/footer.jsp" %>