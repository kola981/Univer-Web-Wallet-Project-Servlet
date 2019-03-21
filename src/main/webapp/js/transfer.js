<script type="text/javascript">
    var cardDetails = [];
    var cardNumbers = [];
    function ready(){
    	cardNumbers.push("--");
    	cardDetails.push("");   	
        	
    	<c:forEach var="card" items="${cards}">
    	 	<c:choose>
               <c:when test="${card.isBlocked()}"></c:when>         
               <c:otherwise>               	  	
               			cardNumbers.push("${card.cardNumber}");    	   
               			cardDetails.push("${card.validTillMonth}/20${card.validTillYear}");              		
               </c:otherwise>
            </c:choose>
        </c:forEach> 	
    };
    
    document.addEventListener("DOMContentLoaded", ready);
    
    function showData(obj, label){
        var selectBox = obj;
    	var selected = selectBox.options[selectBox.selectedIndex].innerHTML;
    	
    	
    	for (var i = 0; i < cardDetails.length; i++){
    		if(cardNumbers[i].toString()===selected){
    			var temp = "";
    			temp = cardDetails[i];
                label.innerHTML = temp;
            }
    	}  	   
    }
</script>
<script>
document.addEventListener("DOMContentLoaded", tr);
function tr() {
    document
            .getElementById('tbtn')
            .addEventListener(
                    'click',
                    function(event) {

                        var cs1 = document.getElementById('cardSelect1');
                        var cs2 = document.getElementById('cardSelect2');
                        
                        cs1.classList.remove('is-invalid');
                        cs2.classList.remove('is-invalid');
                        document.getElementById('fb-am').innerHTML = "";
                        
                        if (cs1.value==="--") {
                            event.preventDefault();
                            event.stopPropagation();
                            var str = " '--' is not a card number";
                            document.getElementById('fb-am').innerHTML = str;
                            cs1.classList.add('is-invalid');
                        } else if (cs2.value==="--") {
                            event.preventDefault();
                            event.stopPropagation();
                            var str = " '--' is not a card number";
                            document.getElementById('fb-am').innerHTML = str;
                            cs2.classList.add('is-invalid');
                        }else if (cs1.value===cs2.value) {
                            event.preventDefault();
                            event.stopPropagation();
                            var str = "Transaction with the same card not allowed";
                            document.getElementById('fb-am').innerHTML = str;
                            cs1.classList.add('is-invalid');
                            cs2.classList.add('is-invalid');
                        }
                    }, false);
};
</script>