<%@ include file="/jsp/static/header.jsp" %>
<link href="<c:url value="/css/user.css"/>" rel="stylesheet" type="text/css">
<!-- Dynamic page title -->
<title>EasyPay</title>

<%@ include file="/jsp/static/navbar-logo.jsp" %>
<%@ include file="/jsp/static/navbar-user.jsp" %>

<!-- Dynamic content -->
<c:set var="client" value="${client}" />
<c:set var="cards" value="${client.cards}" />

<div class="container viewport col">
    <c:choose>
        <c:when test="${client.account.isBlocked()}">
            <div class="row">
                <h4>You account was blocked. Please, contact the administration.</h4>
            </div>
        </c:when>
    
        <c:otherwise>
        
        <div class="jumbotron">
                            <div class="container col-8 d-flex justify-content-center welcome">
                                <div class="row">
                                    <c:choose>
                                        <c:when test="${client.cards.isEmpty()}">
                                            <h4>You have not added any cards.</h4>
                                        </c:when> 
                                        <c:otherwise>   
                                            <h4>You have the following opened cards:</h4>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="row">
                                <c:forEach var="card" items="${client.cards}">
                                    <div class="col-4 mt-1 mb-1"> 
                                    
   
                                            <div class="card text-center">
                                                <div class="card-body">
                                                    <h5 class="card-title"><i class="fas fa-credit-card"></i> ${card.cardNumber}</h5>
                                                    <p class="card-text">Valid till ${card.validTillMonth}/20${card.validTillYear}</p>
                                                    <ul class="list-group list-group-flush">
                                                        <li class="list-group-item">
                                                            <c:choose>
                                                                <c:when test="${card.isBlocked()}">                                                                   
                                                                        <button type="submit" formmethod="post" formaction="#"  class="btn btn-danger" disabled>Block</button>                                                              
                                                                </c:when> 
                                                                <c:otherwise>
                                                                    <form id="b-${card.cardNumber}">
                                                                        <input class="form-control" type="text" name="cnum" value="${card.cardNumber}" hidden="true" readonly>   
                                                                        <button type="submit" formmethod="post" formaction="account" class="btn btn-danger" form="b-${card.cardNumber}" name="action" value="block" id="${card.cardNumber}">Block</button>
                                                                    </form>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </li>
                                                        <li class="list-group-item">
                                                            <c:choose>
                                                                <c:when test="${card.isBlocked()}">
                                                                        <button type="submit" formmethod="post" formaction="#" class="btn btn-danger" disabled>Forget card</button>
                                                                </c:when> 
                                                                <c:otherwise>
                                                                    <form id="f-${card.cardNumber}">
                                                                        <input class="form-control" type="text" name="cnum" value="${card.cardNumber}" hidden="true" readonly>   
                                                                        <button type="submit" formmethod="post" formaction="account" class="btn btn-danger" form="f-${card.cardNumber}" name="action" value="forget-card" id="${card.cardNumber}">Forget card</button>
                                                                    </form>
                                                                </c:otherwise>
                                                            </c:choose>        
                                                        </li>
                                                    </ul>                                                                 
                                                </div> 
                                            </div>
                                        </div>    
                                    </c:forEach>
                                
                            </div>
                        </div>
        </c:otherwise>
    </c:choose>
</div>    


<%@ include file="/jsp/static/footer-user.jsp" %>