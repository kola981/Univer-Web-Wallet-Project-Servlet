
<script>
		document.addEventListener("DOMContentLoaded", nval);
		function nval() {
			document
					.getElementById('nbtn')
					.addEventListener(
							'click',
							function(event) {

								var ely = document.getElementById('tillY');
								var elm = document.getElementById('tillM');
								
								var year = Number("20"+ely.value);
								
								var currentYear = new Date().getFullYear();
								var currentMonth = new Date().getMonth();
								
								var month = convertMonth(elm.value);								
								ely.classList.remove('is-invalid');
								document.getElementById('fb-v').innerHTML = "";
								if ((year < currentYear) || ((year === currentYear) && (month < currentMonth))) {
									event.preventDefault();
                                    event.stopPropagation();                                    
                                    var str = "Card is expired. Please enter valid card.";
                                    ely.classList.add('fb-v');
                                    document.getElementById('fb-v').innerHTML = str;
								}
								
							}, false);
		};
		function convertMonth (str){
			var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
			return months.indexOf(str);
		};
	</script>
