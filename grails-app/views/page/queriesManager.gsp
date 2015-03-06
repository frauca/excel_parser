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
		<div ng-if="!showResult">
			<div class="panel-heading"> 				    		 
				<form novalidate class="simple-form">
					Name: <input type="text" ng-model="newQ.name" /> 
					<br>SQL: <textarea rows="4" cols="80"  ng-model="newQ.sql"></textarea>
					<br><input  ng-if="!editing" type="button" ng-click="addQuery(newQ)" value="Add" />
					<br><input  ng-if="editing" type="button" ng-click="addQuery(newQ)" value="Edit" />
				</form>
			</div>
			<div class="panel panel-default">
				<table ng-table="tableParams" show-filter="true" class="table">
			        <tr ng-repeat="q in $data">
			        	<td data-title="'name'"><a href="" ng-click="execute(q)">{{q.name}}</a></td>
			        	<td data-title="'sql'">{{q.sql}}</td>
			        	<td data-title="'Actions'">
							<a href="" class="btn btn-default" ng-click="editQ(q)">Edit</a>
						</td>
			        </tr>
		        </table>			
			</div>
		</div>
		<div ng-if="showResult">
			<div class="panel-heading"> 				    		 
					<br>SQL: {{q2e.sql}}
					 var str = "The #rain# 10 in SPAIN stays 30 #on# mainly 300 in the plain"; 
        var res = str.match(/#[^#]+#/g);
        for(cc in res){
          document.write("<br>The value of lastIndex is " +res[cc]);
        }
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
