<%@ include file="/jsp/static/header.jsp"%>

<!-- Dynamic page title -->
<title>Admin</title>

<%@ include file="/jsp/static/navbar-logo.jsp"%>
<%@ include file="/jsp/static/navbar-admin.jsp"%>

<!-- Dynamic content -->
<c:set var="admin" value="${admin}" />
<c:set var="accounts" value="${admin.accounts}" />
<c:set var="cards" value="${admin.blockedCards}" />
<fmt:formatNumber var = "balance" type = "number" minFractionDigits="2" maxFractionDigits="2" value = "${admin.pool}" />

<div class="container col-11 justify-content-center welcome">
	<div class="row justify-content-center">
		<h4 class="text-center mt-4">Pool account: UAH <c:out value="${balance}"></c:out></h4>
	</div>
	<div class="row">
		<hr class="my-3">
	</div>
	<div class="row mt-3">

		<div class="container col-5 justify-content-center">
			<div>
				<h4>Client accounts:</h4>
			</div>
			<div>
				<table class="table table-hover cardList">
					<thead>
					</thead>
					<tbody>
						<c:forEach var="account" items="${accounts}">
						<fmt:formatNumber var = "acc_balance" type = "number" minFractionDigits="2" maxFractionDigits="2" value = "${account.balance}" />
							<tr>
								<td>${account.accountNumber}</td>
								<td>UAH ${acc_balance}</td>
								<td><c:choose>
										<c:when test="${account.isBlocked()}">
											<form id="u-${account.accountNumber}">
												<input class="form-control" type="text" name="acnum"
													value="${account.accountNumber}" hidden="true" readonly>
												<button type="submit" formmethod="post" formaction="admin"
													class="btn btn-danger" form="u-${account.accountNumber}"
													name="action" value="account-unblock"
													id="${account.accountNumber}">Unblock</button>
											</form>
										</c:when>
										<c:otherwise>
											<form id="b-${account.accountNumber}">
												<input class="form-control" type="text" name="acnum"
													value="${account.accountNumber}" hidden="true" readonly>
												<button type="submit" formmethod="post" formaction="admin"
													class="btn btn-danger" form="b-${account.accountNumber}"
													name="action" value="account-block"
													id="${account.accountNumber}">Block</button>
											</form>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

		<div class="col-1 justify-content-center"></div>
		<div class="container col-5 justify-content-center">

			<h4>Blocked cards:</h4>
			<table class="table table-hover cardList">
				<thead>
				</thead>
				<tbody>
					<c:forEach var="card" items="${cards}">
						<tr>
							<td>${card.cardNumber}</td>
							<td>
								<form id="u-${card.cardNumber}">
									<input class="form-control" type="text" name="cnum"
										value="${card.cardNumber}" hidden="true" readonly>
									<button type="submit" formmethod="post" formaction="admin"
										class="btn btn-danger" form="u-${card.cardNumber}"
										name="action" value="card-unblock" id="${card.cardNumber}">Unblock</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
</div>


<%@ include file="/jsp/static/footer.jsp"%>