<div class="navbar-nav order-2 w-100 d-flex flex-row-reverse">          
      		<a href="${pageContext.request.contextPath}/logout" class="nav-link mr-5">LOGOUT <i class="fas fa-user-alt-slash"></i></a>
   		    <a href="${pageContext.request.contextPath}/account" class="nav-link mr-5"> HOME</a>
   		 </div>

   		 <div class="navbar-nav order-0 w-100">
      		<span class="navbar-text ml-5">
     			 Welcome, ${client.name} ${client.surname}!
   		    </span>
   		 </div>
	</nav>
</div>


<c:set var="balance" value="${client.account.balance}" />
<c:set var="accNum" value="${client.account.accountNumber}" />
<fmt:formatNumber var = "balance" type = "number" minFractionDigits="2" maxFractionDigits="2" value = "${client.account.balance}" />


<div class = "main container-fluid content">
    <div class="row main-viewport">
        <div class="container col-2 sidebar border-right">
        
            <form class = "acc-data border-bottom">
                <div class="form-group">
                    <label class="row justify-content-center"><em>Account number:</em></label>
                    <label class="row justify-content-center"><em><c:out value="${accNum}" /></em></label>
                </div>
                <div class="form-group">
                    <label class="row justify-content-center"><em>Available balance:</em></label>
                    <label class="row justify-content-center" id="balance"><em>UAH</em><em> <c:out value="${balance}" /></em></label>
                </div>
            </form>      
            
            <ul class="nav flex-column sb-elements">
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${client.account.isBlocked()}">
                             <a class="nav-link disabled" href="#">Add a card</a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link" data-method="get" href="${pageContext.request.contextPath}/account/new-card">Add a card</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${client.account.isBlocked()}">
                             <a class="nav-link disabled" href="#">Add money to account</a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link" data-method="get" href="${pageContext.request.contextPath}/account/topup">Add money to account</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${client.account.isBlocked()}">
                              <a class="nav-link disabled" href="#">Pay using account</a>
                        </c:when>
                        <c:otherwise>
                             <a class="nav-link" data-method="get" href="${pageContext.request.contextPath}/account/payment">Pay using account</a>
                        </c:otherwise>
                    </c:choose>                                   
                </li>
                
                <li class="nav-item">
                    <c:choose>
                        <c:when test="${client.account.isBlocked()}">
                             <a class="nav-link disabled" href="#">Transfer money between own cards </a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-link" data-method="get" href="${pageContext.request.contextPath}/account/transfer">Transfer money between own cards </a>
                        </c:otherwise>
                    </c:choose>                   
                </li>
            </ul>
        </div>
        