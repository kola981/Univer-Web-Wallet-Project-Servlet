<script type="text/javascript">
    var cardDetails = [];
    var cardNumbers = [];
    var months = [];
    var years = [];
    
    function ready(){
    	cardNumbers.push("--");
    	cardDetails.push("");
    	months.push("");
    	years.push("");
    	
    	<c:forEach var="card" items="${cards}">
    	 	<c:choose>
               <c:when test="${card.isBlocked()}"></c:when>         
               <c:otherwise>
               		cardNumbers.push("${card.cardNumber}");
               		cardDetails.push("${card.validTillMonth}/20${card.validTillYear}");
               		months.push("${card.validTillMonth}");
               		years.push("20${card.validTillYear}");
               </c:otherwise>
            </c:choose>
        </c:forEach> 	
    }
    
    document.addEventListener("DOMContentLoaded", ready);
    
    function showData(obj, label){
        var selectBox = obj;
    	var selected = selectBox.options[selectBox.selectedIndex].innerHTML;
    	
    	
    	for (var i = 0; i < cardDetails.length; i++){
    		if(cardNumbers[i].toString()===selected){
    			var temp = "";
    			temp = cardDetails[i];
                label.innerHTML = temp;
                temp = months[i];
                document.getElementById("m").value = temp;
                temp = years[i];
                document.getElementById("y").value = temp;
            }
    	}  	   
    }
</script>