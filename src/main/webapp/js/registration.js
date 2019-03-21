<script>
//Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
'use strict';
window.addEventListener('load', function() {
 // Fetch all the forms we want to apply custom Bootstrap validation styles to
 var forms = document.getElementsByClassName('needs-validation');
 
 // Loop over them and prevent submission
 var validation = Array.prototype.filter.call(forms, function(form) {
   form.addEventListener('submit', function(event) {
     if (form.checkValidity() === false) {
       event.preventDefault();
       event.stopPropagation();
     }
     
     form.classList.add('was-validated');
   }, false);
 });
}, false);
})();
</script>

<script>
var check = function() {
	  if (document.getElementById('passw').value ==
	    document.getElementById('repeat_passw').value) {
	    document.getElementById('message').style.color = 'green';
	    document.getElementById('message').innerHTML = 'matching';
	  } else {
	    document.getElementById('message').style.color = 'red';
	    document.getElementById('message').innerHTML = 'not matching';
	  }
	}
</script>
