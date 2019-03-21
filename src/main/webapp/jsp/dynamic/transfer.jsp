<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/jsp/static/header.jsp" %>

<link href="<c:url value="/css/service.css"/>" rel="stylesheet" type="text/css">

<!-- Dynamic page title -->
<title>EasyPay</title>

<%@ include file="/jsp/static/navbar-logo.jsp" %>
<%@ include file="/jsp/static/navbar-user.jsp" %>

<!-- Dynamic content -->
<c:set var="client" value="${client}" />
<c:set var="cards" value="${client.cards}" />

<div class="container viewport col">
           <div class="container col-8 d-flex align-items-center justify-content-center login">
             <div class="row align-items-center">
                <form id="transfer-between-cards" class="login border border-danger rounded p-5" > 
                    <div class="form-group text-center align-bottom">
                        <h1>Transfer between own cards</h1>
                        <hr class="my-4">
                    </div>
                    
                    <div class="form-row  position-relative">
                        <div class="col-5">
                            <div class="form-row">
                                <div class="form-group">
                                    <label for="card-number">From Card:</label>
                                    <select class="form-control" id="cardSelect1" name="fromCard" onChange="showData(this,c1);">
                                        <option selected="selected">--</option>
                                        <c:forEach var="card" items="${cards}">
                                            <c:choose>
                                                <c:when test="${card.isBlocked()}"></c:when>         
                                                <c:otherwise>
                                                    <option>${card.cardNumber}</option>
                                                </c:otherwise>
                                            </c:choose>   
                                        </c:forEach>
                                    </select>
                                </div>          
                            </div>
                            <div class="form-row">
                                <div class="form-group">
                                    <label for="tillM">valid till:</label>
                                    <label for="tillM" id="c1"></label>                                           
                                </div>
                                 
                            </div>        
                        </div>
                        <div class="col-2 d-flex align-items-center flex-direction:column">
                                <div class="row">
                                <i class="fas fa-angle-double-right"></i>
                                <i class="fas fa-angle-double-right"></i>
                                <i class="fas fa-angle-double-right"></i>
                                </div>
                        </div>
                        <div class="col-5">
                            <div class="form-row">
                                <div class="form-group">
                                    <label for="card-number">To Card:</label>
                                    <select class="form-control" id="cardSelect2" name="toCard" onChange="showData(this,c2);">
                                        <option selected="selected">--</option>
                                        <c:forEach var="card" items="${cards}">
                                            <c:choose>
                                                <c:when test="${card.isBlocked()}"></c:when>         
                                                <c:otherwise>
                                                    <option>${card.cardNumber}</option>
                                                </c:otherwise>
                                            </c:choose>     
                                        </c:forEach>
                                    </select>
                                    
                                </div>          
                            </div>
                            <div class="form-row">
                                <div class="form-group">                                  
                                    <label for="tillM">valid till:</label>
                                    <label for="tillM" id="c2"></label> 
                            
                                </div>
                                
                            </div>        
                        </div>
                    </div>
        
                    <hr class="my-2">
                    <div class="form-row ">
                        <div class="col text-center position-relative">
                             <input class="form-control" id="am" name="amount" type="text" placeholder="100.24" pattern="\d+(\.\d{1,2})?" required>
                             <p5 id="fb-am"></p5>                          
                        </div>
                        <div class="form-group px-2"></div>
                        <div class="col text-center">
                            <button id="tbtn" type="submit" form="transfer-between-cards" formmethod="post" formaction="${pageContext.request.contextPath}/account" class="btn btn-danger border-0" name="action" value="transfer">Confirm transfer</button> 
                        </div>
                   </div>         
                </form>
               </div>
           </div>           
    </div>
<%@ include file="/jsp/static/footer-transfer.jsp" %>