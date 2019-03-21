<%@ include file="/jsp/static/header.jsp"%>

<!-- Dynamic page title -->
<title>EasyPay</title>

<%@ include file="/jsp/static/navbar-logo.jsp"%>
<%@ include file="/jsp/static/navbar-user.jsp"%>

<!-- Dynamic content -->
<c:set var="client" value="${client}" />
<c:set var="cards" value="${client.cards}" />

<!-- Custom script -->
<%@ include file="/js/topup.js"%>

<div class="container viewport col">
	<div class="container col-8 align-items-center mt-5 login">
		<div class="row align-items-center">

			<form id="topup" class="login border border-danger rounded p-5">
				<div class="form-group text-center align-bottom">
					<h1>Add money to account</h1>
					<hr class="my-4">
				</div>

				<div class="form-row">
					<div class="form-group col-6">
						<label for="card-number">From Card:</label> <select
							class="form-control" id="exampleFormControlSelect1" name="cnum"
							onChange="showData(this,l);">
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
					<div class="form-group col-2"></div>
					<div class="form-group col-4">
						<div class="row">
							<label for="tillM">Valid till:</label>
						</div>
						<div class="row mt-2">
							<label for="tillM" id="l"></label>
						</div>
						<input class="form-control" type="text" name="tillM" value=""
							id="m" hidden="true"> <input class="form-control" type="text"
							name="tillY" value="" id="y" hidden="true">
					</div>
				</div>
				<hr class="my-4">
				<div class="form-row">
					<div class="col text-center">
						<input class="form-control" id="cnum" name="amount" type="text"
							placeholder="100.24" pattern="\d+(\.\d{1,2})?" required>
					</div>
					<div class="form-group px-2"></div>
					<div class="col text-center">
						<button type="submit" form="topup" formmethod="post"
							formaction="${pageContext.request.contextPath}/account"
							class="btn btn-danger border-0" name="action" value="topup">Confirm
							payment</button>
					</div>
				</div>
			</form>
















			<!-- 
                        <h1>Top up using card:</h1>                             
                </div>
                <hr class="my-4">
                <div class="row align-items-center">    
                    <form id="pay-to-card">
                        <div class="form-row">
                            <div class="form-group">
                                <label for="cnum">Card Number:</label>
                                <input class="form-control" type="text" name="cnum" value="${cardToUse.cardNumber}" readonly required>
                            </div>
                            <div class="form-group px-2"></div>          
                            <div class="form-group">
                                <label for="tillM">valid till month:</label>
                                <input class="form-control" type="text" name="tillM" value="${cardToUse.validTillMonth}" readonly required>
                            </div>

                            <div class="form-group px-2"></div>
                            <div class="form-group">
                                <label for="tillY">valid till year:</label>
                                <input class="form-control" name="tillY" type="text" value="20${cardToUse.validTillYear}" readonly required>
                            </div>
                        </div>     
                        <div class="form-row align-items-center">
                            <div class="form-group">
                                <label for="amount">Amount:</label>
                            </div>
                            <div class="form-group px-2"></div>
                            <div class="form-group">
                                <input class="form-control" id="cnum" name="amount" type="text" placeholder="100.24" pattern="\d+(\.\d{2})?" required>           
                            </div>
                            <div class="form-group px-2"></div>
                            <div class="form-group">
                                <button type="submit" form="pay-to-card" formmethod="post" formaction="${pageContext.request.contextPath}/account" class="btn btn-danger border-0"  name="action" value="topup">Confirm payment</button> 
                            </div>
                        </div>
                    </form>
                   -->

		</div>
	</div>
</div>

<%@ include file="/jsp/static/footer-user.jsp"%>