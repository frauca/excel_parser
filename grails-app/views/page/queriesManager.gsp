<!DOCTYPE html>
<html>
<head>
<title>Pagina principal</title>
</head>
<body>
	<div class="col-lg-12">
		<h4>Queries </h4>
	</div>
	<div class="col-lg-12" ng-controller="queriesCtrl">
		<div class="panel-heading"> 				    		 
			<form novalidate class="simple-form">
				Name: <input type="text" ng-model="newQ.name" /> 
				SQL: <input type="text" ng-model="newQ.sql" /> 
				<input type="button" ng-click="addQuery(newQ)" value="Add" />
			</form>
		</div>
		<div class="panel panel-default">
			<table ng-table="tableParams" show-filter="true" class="table">
		        <tr ng-repeat="q in $data">
		        	<td data-title="'name'">{{q.name}}</td>
		        	<td data-title="'sql'">{{q.sql}}</td>
		        </tr>
	        </table>			
		</div>
	</div>
</body>
</html>
