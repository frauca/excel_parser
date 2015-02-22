<!DOCTYPE html>
<html>
<head>
<title>Pagina principal</title>
</head>
<body>
	<div class="col-lg-12">
		<h4>Movements </h4>
	</div>
	<div class="col-lg-12" ng-controller="movListCtrl">
		<div class="panel panel-default">
			<div class="panel-heading">
	    		<i class="fa fa-list fa-fw"></i> Accounts   
	       		<select ng-change="changedCCC()" ng-model="selectAccount" ng-options="account.id as (account.name + ' - ' + account.ccc) for account in accounts">
					<option/>
				</select>
		 		<i class="fa fa-file-excel-o fa-fw"></i> File 
				<select ng-change="changedFile()" ng-model="selectedFile" ng-options="file.id as file.name for file in files">
					<option/>
				</select>
        	</div>
			<table ng-table="tableParams" show-filter="true" class="table">
	        <tr ng-repeat="mov in $data">
	        	<td data-title="'Operation'">{{mov.operationDate}}</td>
	        	<td data-title="'Value'">{{mov.valueDate}}</td>
	            <td data-title="'Concept'" filter="{ 'concept': 'text' }">{{mov.concept}}</td>
	            <td data-title="'Amount'" sortable="'amount'">
	            	<span ng-class="{ 'plus': mov.amount >= 0,'minus': mov.amount < 0 }">{{mov.amount}}</span>
	            </td>
	            <td data-title="'Total'">{{mov.total}}</td>
	            <td data-title="'Category'" sortable="'category'">
	            	<a ng-if="!mov.categoryName"	href="" class="btn btn-warning" ng-click="setCategory(mov)">No Catalogat</a>
	            	<a ng-if="mov.categoryName"	ng-class="{'btn-success': mov.categoryType.name=='MANUAL','btn-primary': mov.categoryType.name=='AUTOMATIC','btn-info': mov.categoryType.name=='MULTIPLE'}" "href="" class="btn " ng-click="setCategory(mov)">{{mov.categoryName}}</a>
	            </td>
	        </tr>
	        </table>			
		</div>
	</div>
	<div class="col-lg-12" ng-controller="uncatMovCtrl">  	
		<div class="panel panel-default">
			<div class="panel-heading">
	    		<i class="fa fa-list fa-fw"></i> Pendents de categoritzar  
        	</div>
			<table ng-table="tableParams2" show-filter="true" class="table">
	        <tr ng-repeat="mov in $data">
	        	<td data-title="'concept'">{{mov[0]}}</td>
	        	<td data-title="'Num'">{{mov[1]}}</td>
	            <td data-title="'Categorize'" >
	            	<select ng-change="categorizeConcept(mov[0],catForUncategorized)" ng-model="catForUncategorized" ng-options="category.id as category.name for category in categories">
	            		<option >Pendents Cat</option>
	            	</select>
	            </td>
	        </tr>
	        </table>			
		</div>
	</div>
</body>
</html>
