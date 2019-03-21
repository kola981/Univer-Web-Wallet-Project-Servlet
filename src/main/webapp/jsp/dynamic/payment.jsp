<%@ include file="/jsp/static/header.jsp" %>

<!-- Dynamic page title -->
<title>EasyPay</title>

<%@ include file="/jsp/static/navbar-logo.jsp" %>
<%@ include file="/jsp/static/navbar-user.jsp" %>

<!-- Dynamic content -->
<c:set var="client" value="${client}" />
<c:set var="cards" value="${client.cards}" />

<div class="container viewport col">
           <div class="container col-8 align-items-center login">
                <div class="row align-items-center text-center">
                        <h1>Pay using account</h1>                             
                </div>
                <hr class="my-3">
                <div class="row align-items-center">    
                    <form id="pay-to-card">
                        <div class="form-row">
                            <div class="form-group">
                                <label for="cnum">Card Number:</label>
                                <input class="form-control" id="cnum" name="cnum" type="text" placeholder="420087652341" pattern="[0-9]{12}" required>
                            </div>
                            <div class="form-group px-2"></div>          
                            <div class="form-group">
                                <label for = "tillM">valid till month:</label>
                                <select class="form-control" id="tillM" name="tillM" required>
                                        <option>Jan</option>
                                        <option>Feb</option>
                                        <option>Mar</option>
                                        <option>Apr</option>
                                        <option>May</option>
                                        <option>Jun</option>
                                        <option>Jul</option>
                                        <option>Aug</option>
                                        <option>Sep</option>
                                        <option>Oct</option>
                                        <option>Nov</option>
                                        <option>Dec</option>
                                </select>
                            </div>

                            <div class="form-group px-2"></div>
                            <div class="form-group">
                                <label for="tillY">valid till year:</label>
                                <input class="form-control" id="tillY" name="tillY" type="text" placeholder="2021" min="2019" pattern="[0-9]{4}" required>
                                <div class="invalid-feedback" id="feedback-card-date"></div>
                            </div>
                        </div>     
                        <div class="form-row align-items-center">
                            <div class="form-group">
                                    <label for="ref">Reference:</label>
                                    <input class="form-control" id="ref" name="ref" type="text" placeholder="Invoice #1234" required>
                            </div>
                            <div class="form-group px-2"></div>
                            <div class="form-group">
                                <label for="amount">Amount:</label>
                                <input class="form-control" id="amount-card" name="amount" type="text" placeholder="100.24" pattern="\d+(\.\d{1,2})?" required>           
                                <div class="invalid-feedback" id="feedback-card"></div>                            
                            </div>
                            <div class="form-group px-2"></div>
                            <div class="form-group">
                                <label for="ref"></label>
                                <button type="submit" id="cbtn" form="pay-to-card" formmethod="post" formaction="${pageContext.request.contextPath}/account" class="btn btn-danger border-0"  name="action" value="payment" >Confirm payment</button> 
                            </div>
                        </div>
                    </form>
                </div>


                <hr class="my-3">
                

                <div class="row align-items-center">
                        <form id="pay-to-account" class="needs-validation">
                            <div class="form-row">
                                <div class="form-group">
                                    <label for="acnum">Account Number:</label>
                                    <input class="form-control" id="acnum" name="acnum" type="text" placeholder="1200876523" pattern="[0-9]+" required>
                                </div>
                                <div class="form-group px-2"></div>          
                                <div class="form-group">
                                    <label for="bnum">Bank number:</label>
                                    <input class="form-control" id="bnum" name="bnum" type="text" placeholder="45001" pattern="[0-9]{5}" required>
                                </div> 
                            </div>
                            <div class="form-row">       
                                <div class="form-group">
                                    <label for="ref">Reference:</label>
                                    <input class="form-control" id="ref" name="ref" type="text" placeholder="Invoice #1234" required>
                                </div>
                                <div class="form-group px-2"></div>
                                <div class="form-group">
                                    <label for="cp">Counterparty:</label>
                                    <input class="form-control" id="cp" name="cp" type="text" placeholder="Oblenergo" required>
                                </div>
                            </div>
                            <div class="form-row align-items-center">
                                <div class="form-group">
                                    <label for="amount">Amount:</label>
                                </div>          
                                <div class="form-group px-2"></div>
                                <div class="form-group">
                                    <input class="form-control" id="amount-bank" name="amount" type="text" placeholder="100.24" pattern="\d+(\.\d{1,2})?" required>
                                    <div class="invalid-feedback" id="feedback-bank"></div>
                                </div>          
                                <div class="form-group px-2"></div>
                                <div class="form-group">
                                    <button type="submit" id="bbtn" form="pay-to-account" formmethod="post" formaction="${pageContext.request.contextPath}/account" class="btn btn-danger border-0"  name="action" value="payment">Confirm payment</button> 
                                </div>
                            </div>
                        </form>
                </div>
            </div>
        </div>    

<%@ include file="/jsp/static/footer-payment.jsp" %>