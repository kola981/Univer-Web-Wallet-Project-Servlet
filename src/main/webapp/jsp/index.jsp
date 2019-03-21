<%@ include file="/jsp/static/header.jsp" %>

<!-- Dynamic page title -->
<title>Welcome</title>

<%@ include file="/jsp/static/navbar-logo.jsp" %>
<%@ include file="/jsp/static/navbar-welcome.jsp" %>

<!-- Dynamic content -->
<div class="container col-8 d-flex justify-content-center welcome">
	<div class="row align-items-center">
		<div class="card mh-100 text-center border-danger">
 			<div class="card-body">
    			<h5 class="card-title">Welcome</h5>
    			<p class="card-text">Please login or register to continue.</p>
 			</div>
  			<img src="<c:url value="/img/payment.jpg"/>" class="card-img-top" alt="...">
		</div>
	</div>	
</div>

<%@ include file="/jsp/static/footer.jsp" %>