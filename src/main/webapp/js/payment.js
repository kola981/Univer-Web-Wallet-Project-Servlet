
<script>
		document.addEventListener("DOMContentLoaded", cval);
		function cval() {
			document
					.getElementById('cbtn')
					.addEventListener(
							'click',
							function(event) {
								${client.account.balance}
								var el = document.getElementById('amount-card');
								var ely = document.getElementById('tillY');
								var elm = document.getElementById('tillM');
									
								var transactionAmount = parseFloat(el.value);
								var currentBalance = parseFloat(${client.account.balance});
								
								var year = Number(ely.value);
								
								var currentYear = new Date().getFullYear();
								var currentMonth = new Date().getMonth();
								
								var month = convertMonth(elm.value);								
							
								el.classList.remove('is-invalid');
								ely.classList.remove('is-invalid');
								elm.classList.remove('is-invalid');
								document.getElementById('feedback-card').innerHTML = "";
								document.getElementById('feedback-card-date').innerHTML = "";
								
								if (transactionAmount > currentBalance) {
									event.preventDefault();
									event.stopPropagation();
									var str = "Transaction amount UAH "
											+ transactionAmount
											+ " > than available balance UAH "
											+ currentBalance;
									document.getElementById('feedback-card').innerHTML = str;
									el.classList.add('is-invalid');
								} else if (year < currentYear){
									event.preventDefault();
                                    event.stopPropagation();                                    
                                    var str = "Incorrect month or year";
                                    ely.classList.add('is-invalid');
                                    document.getElementById('feedback-card-date').innerHTML = str;
								} else if ((year === currentYear) && (month < currentMonth)) {
									event.preventDefault();
                                    event.stopPropagation();                                    
                                    var str = "Incorrect month or year";
                                    document.getElementById('feedback-card-date').innerHTML = str;
                                    elm.classList.add('is-invalid');
								}
								
							}, false);
		};
		function convertMonth (str){
			var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
			return months.indexOf(str);
		};
	</script>
	<script>
	document.addEventListener("DOMContentLoaded", bval);
	function bval() {
		document
				.getElementById('bbtn')
				.addEventListener(
						'click',
						function(event) {

							var el = document.getElementById('amount-bank');
							var transactionAmount = parseFloat(el.value);
							var currentBalance = parseFloat(${client.account.balance});

							el.classList.remove('is-invalid');
							document.getElementById('feedback-bank').innerHTML = "";
							
							if (transactionAmount > currentBalance) {
								event.preventDefault();
								event.stopPropagation();
								var str = "Transaction amount UAH "
										+ transactionAmount
										+ " > than available balance UAH "
										+ currentBalance;
								document.getElementById('feedback-bank').innerHTML = str;
								el.classList.add('is-invalid');
							}
						}, false);
	};
</script>
