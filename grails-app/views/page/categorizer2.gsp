<!DOCTYPE html>
<html>
<head>
<title>Pagina principal</title>
<asset:javascript src="angular/categorizer2/controllersCAT.js"/>
</head>
<body>
	<div class="col-lg-12">
		<h4>Queries </h4>
	</div>
	<div class="col-lg-12" ng-controller="categorizerCtrl">
		<div >
			<div class="panel-heading"> 				    		 
				<form novalidate class="simple-form">
					Concept: <input type="text" ng-change="tableParams.reload()" ng-model="concept" /> 
				</form>
			</div>
			<div class="panel panel-default">
				<table ng-table="tableParams" show-filter="true" class="table">
			        <tr ng-repeat="q in $data">
			        	<td data-title="'concept'">{{q[0]}}</td>
			        	<td data-title="'total'">{{q[1]}}</td>
			        	<td data-title="'total'" class="green">{{q[2]}}</td>
			        	<td data-title="'total'" class="red">{{q[2]}}</td>
			        </tr>
		        </table>			
			</div>
		</div>
	</div>
</body>
</html>
