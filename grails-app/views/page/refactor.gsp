<!DOCTYPE html>
<html>
<head>
<title>Refactor</title>
<asset:javascript src="angular/configuration/refactor.js"/>
<script>
  $(function() {
    $( "input.datepicker" ).datepicker({ dateFormat: 'yy-mm-dd' });
  });
  </script>
</head>
<body>
	<div class="col-lg-12">
		<h4>Refactor </h4>
	</div>
	<div class="col-lg-12" >
		<accordion close-others="oneAtATime">
	    	<g:render template="refactor/recalcTotals" />   
	    	<g:render template="refactor/setTotals" />  
	    	<g:render template="refactor/seeDiferences" />  
		</accordion>
	</div>
</body>
</html>