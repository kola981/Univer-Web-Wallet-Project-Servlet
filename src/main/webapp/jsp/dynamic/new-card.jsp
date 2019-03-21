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
                <form id="save-card" class="login border border-danger rounded p-5"> 
                    <div class="form-group text-center align-bottom">
                        <h1>New Card</h1>
                        <hr class="my-4">
                    </div>
        
                    <div class="form-row">
                        <div class="form-group">
                            <label for="card-number">Card Number:</label>
                            <input class="form-control" id="cnum" name="cnum" type="text" placeholder="420087652341" pattern="[0-9]{12}" required>
                        </div>          
                    </div>
            
                    <div class="form-row">
                        <div class="form-group">
                            <label for="tillM">valid till month:</label>
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
                                <select class="form-control" id="tillY" name="tillY" required>
                                    <option>19</option>
                                    <option>20</option>
                                    <option>21</option>
                                    <option>22</option>
                                    <option>23</option>
                                    <option>24</option>
                                    <option>25</option>
                                    <option>26</option>
                                    <option>27</option>
                                    <option>28</option>
                                    <option>29</option>
                                    <option>30</option>
                                </select>
                        </div>
                    </div>           
                    
                    <div class="form-row">
                        <div class="col text-center">
                            <button type="submit" form="save-card" formmethod="post" formaction="${pageContext.request.contextPath}/account" class="btn btn-danger border-0" name="action" value="save-new-card" id="nbtn">Save</button> 
                        </div>
                    </div>
                    <div class="form-row">
                        <p6 id="fb-v"></p6> 
                    </div>
                               
                </form>
               </div>
            </div>
        </div>    

<%@ include file="/jsp/static/footer-newcard.jsp" %>