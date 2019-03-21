<%@ include file="/jsp/static/header.jsp" %>
<link href="<c:url value="/css/login.css"/>" rel="stylesheet" type="text/css">

<!-- Dynamic page title -->
<title>Login</title>

<%@ include file="/jsp/static/navbar-logo.jsp" %>
<%@ include file="/jsp/static/navbar-login.jsp" %>

<!-- Dynamic content -->

<div class="container col-8 d-flex align-items-center justify-content-center login">
    <div class="row align-items-center">
        <form id="login" class="login border border-danger rounded p-5">
            <div class="form-group text-center align-bottom">
                <h1>Login</h1>
                <hr class="my-4">
            </div>
        
            <div class="form-group">
                <label for="username">Username:</label>
                <input class="form-control" id="username" name="username" type="text" placeholder="your username" >
            </div>

            <div class="form-group">
                <label for="passw">Password:</label>
                <input class="form-control" id="passw" name="password" type="password" placeholder="your password"  > 
            </div>

            <div class="col text-center">
                 <button type="submit" class="btn btn-danger border-0" form="login" formmethod="post" formaction="${pageContext.request.contextPath}/login">Submit</button> 
            </div>           
        </form>
    </div>
</div>

<%@ include file="/jsp/static/footer.jsp" %>
