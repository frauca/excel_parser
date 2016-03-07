<!DOCTYPE html>
<html>
<head>
<title>basicAnalyzer</title>
<asset:javascript src="angular/queriesManager/basicAnalyzer.js"/>
</head>
<body>

<script>
  $(function() {
    $( "input.datepicker" ).datepicker({ dateFormat: 'yy-mm-dd' });
  });
  </script>
</head>
<body>
 	<div class="col-lg-12" ng-controller="basicAnalyzer">
 		<div class="panel panel-default">
			<div class="panel-heading">
				<i ng-click="showFilter=!showFilter" class="glyphicon-chevron-up pull-right glyphicon"></i>
				Filter
			</div>
			<div class="panel-body">
				<div class="col-lg-6 input-group">
					<div class="col-lg-6">
						<label for="fromDate">From</label>
		                <input type="text" ng-change="qryHasChange(qry)" class="datepicker"  ng-model="qry.fromDate" placeholder="From Date"/>
					</div>
					<div class="col-lg-6">
						<label for="fromDate">To</label>
						<input  ng-change="qryHasChange(qry)" ng-model='qry.toDate' class="datepicker" placeholder="To Date">
					</div>
				</div>
				<div class="col-lg-6">
				</div>
			</div>
		</div>
		<g:render template="/page/basicAnal/totals" />
		
 		<div class="panel panel-default">
			<div class="panel-heading">
				Procesed
			</div>
			<div class="panel-body">
				<div class="col-lg-12">
					<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
						<li class="active"><a href="#weekly" data-toggle="tab" >Weekly</a></li>
						<li><a href="#monthly" data-toggle="tab" >Mensual</a></li>
				        <li><a href="#anual" data-toggle="tab" >Anual</a></li>
				        
					        	        
					    </ul>
			
						<div class="tab-content">
							<div id="weekly" class="tab-pane fade in active">
								<g:render template="/page/basicAnal/totalWeek" />
							</div>
							<div id="monthly" class="tab-pane fade">
								<g:render template="/page/basicAnal/totalMonth" />
							</div>
							<div id="anual" class="tab-pane fade ">
								<g:render template="/page/basicAnal/totalYear" />
							</div>
						</div>
				</div>	
			</div>		
	</div>
</body>
</html>
