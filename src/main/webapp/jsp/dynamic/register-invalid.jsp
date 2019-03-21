
<%@ include file="/jsp/static/header.jsp" %>
<link href="<c:url value="/css/login.css"/>" rel="stylesheet" type="text/css">

<!-- Dynamic page title -->
<title>Registration</title>

<%@ include file="/jsp/static/navbar-logo.jsp" %>
<%@ include file="/jsp/static/navbar-login.jsp" %>

<!-- Dynamic content -->
<div class="container col-8 d-flex align-items-center justify-content-center login">
    <div class="row align-items-center">
        <form action="register" method="post"  id="login" class="login border border-danger rounded p-5 needs-validation" novalidate> 
            <div class="form-group text-center align-bottom">
                <h1>Registration</h1>
                <hr class="my-4">
            </div>
        
            <div class="form-row">
                <div class="form-group">
                    <label for="name">First Name:</label>
                    <input class="form-control is-valid" id="name" name="fname" type="text" value="${requestScope.userData['fname'][0]}" required>
                </div>
                <div class="form-group px-2"></div>
                <div class="form-group">
                    <label for="surname">Last Name:</label>
                    <input class="form-control is-valid" id="surname" name="lname" type="text" value="${requestScope.userData['lname'][0]}" required>
                </div>
            </div>
            
            <div class="form-row">
                <div class="form-group email">
                    <label for="email">Email:</label>
                    <input class="form-control is-valid" id="email" name="email" type="email" value="${requestScope.userData['email'][0]}" required>
                </div>
                <div class="form-group px-2"></div>
            <div class="form-group">
                    <label for="username">Username:</label>
                    <input class="form-control is-invalid" id="username" name="uname" type="text" placeholder="js123" required>
                        <div class="invalid-feedback">
                               Username already used.
                        </div>
                </div>
            </div>           

            <div class="form-row">
                <div class="form-group"> 
                    <label for="passw">Password:</label>
                    <input class="form-control is-valid" id="passw" name="passw" type="password" value="${requestScope.userData['passw'][0]}" pattern=.{5,10} required>
                </div>  
                <div class="form-group px-2"></div>
                <div class="form-group"> 
                    <label for="passw">Repeat password:</label>
                    <input class="form-control is-valid" id="repeat_passw" name="passw2" type="password" value="${requestScope.userData['passw'][0]}" required>
                </div>
            </div>
            <div class="form-group">
                <small id="passwHelp" class="form-text text-muted">Password contains between 5 and 10 letters and/or numbers.</small>
            </div>

            <div class="form-group col text-center">
                <div class="form-check">
                        <input class="form-check-input is-valid" id="consent" type="checkbox" name="agreed" value="true" required>
                        <label class=form-check-label" for="consent">I agree to the terms and conditions</label>
                </div>
            </div>  

            <div class="col text-center">
                 <button type="submit" class="btn btn-danger border-0" form="login" name="action" value="login">Submit</button> 
            </div>           
        </form>
    </div>
</div>

<%@ include file="/jsp/static/footer-js.jsp" %>