<!DOCTYPE html>
<html>
<head>
<title>Pagina principal</title>
<asset:javascript src="angular/queriesManager/controllersQM.js"/>
</head>
<body>
	<div class="col-lg-12">
		<h4>Queries </h4>
	</div>
	<div class="col-lg-12" ng-controller="queriesCtrl">
		<div ng-if="!showResult">
			<div class="panel-heading"> 				    		 
				<form novalidate class="simple-form">
					Name: <input type="text" ng-model="newQ.name" /> 
					<br>Desc: <textarea rows="1" cols="80"  ng-model="newQ.description"></textarea>
					<br>SQL: <textarea rows="4" cols="80"  ng-model="newQ.sql"></textarea>
					<br><input  ng-if="!editing" type="button" ng-click="addQuery(newQ)" value="Add" />
					<br><input  ng-if="editing" type="button" ng-click="addQuery(newQ)" value="Edit" />
				</form>
			</div>
			<div class="panel panel-default">
				<table ng-table="tableParams" show-filter="true" class="table">
			        <tr ng-repeat="q in $data">
			        	<td data-title="'name'"><a href="" ng-click="showQuery(q)">{{q.name}}</a></td>
			        	<td data-title="'description'">{{q.description.substring(1,80)}}...</td>
			        	<td data-title="'Actions'">
							<a href="" class="btn btn-default" ng-click="editQ(q)">Edit</a>
						</td>
			        </tr>
		        </table>			
			</div>
		</div>
		<div ng-if="showResult" class="panel panel-default">
			<div class="panel-heading"> 				    
					Genera el sql per posar alguna variable posala entre #var#		 
					<br>SQL: <textarea rows="4" cols="80" ng-change="extractVars(q2e)"  ng-model="q2e.sql"></textarea>
					<div ng-repeat="var in vars" >{{var}}: <input  ng-model="q2e.params[var]"></div>
					<br><input  type="button" ng-click="execute(q2e)" value="Execute" />
					
			</div>
			<div class="panel panel-default">
				<a href="" ng-click="showQList()">list</a>
			</div>
			<div class="panel panel-default">
				<table  class="table">
			        <tr ng-repeat="line in result track by $index">
			        	<td  ng-repeat="col in line track by $index">{{col}}</td>
			        </tr>
		        </table>			
			</div>
		</div>
	</div>
</body>
</html>
