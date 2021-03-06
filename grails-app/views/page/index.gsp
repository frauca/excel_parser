<!DOCTYPE html>
<html>
<head>
<title>Pagina principal</title>
<asset:javascript src="angular/index/controllersIN.js"/>
<asset:javascript src="plugins/ui-select/select.js"/>
<asset:stylesheet src="plugins/ui-select/select.css"/>
</head>
<body>
	<div class="col-lg-12">
		<h4>Movements </h4>
	</div>
	<div class="col-lg-12" ng-controller="movListCtrl">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i ng-click="showFilter=!showFilter" ng-class="{'glyphicon-chevron-down': !showFilter, 'glyphicon-chevron-up': showFilter}" class="pull-right glyphicon"></i>
				Filters:
				<div ng-show="showFilter" ng-hide="!showFilter">
					<i class="fa fa-list fa-fw"></i> Accounts   
		       		<select ng-change="changedCCC()" ng-model="selectAccount" ng-options="account.id as (account.name + ' - ' + account.ccc) for account in accounts">
						<option/>
					</select>
			 		<i class="fa fa-file-excel-o fa-fw"></i> File 
					<select ng-change="tableParams.reload()" ng-model="selectedFile" ng-options="file.id as file.name for file in files">
						<option/>
					</select>
					Nomes sense cat <input ng-change="tableParams.reload()" type="checkbox" ng-model="uncategorized">
					Nomes sense subcat <input ng-change="tableParams.reload()" type="checkbox" ng-model="unSubCat">
					<br>category:<select ng-model="category" ng-change="updateSub()" ng-options="category.id as category.name for category in categories"></select>
		    		subcat:<select ng-model="subcat" ng-change="updateSub()" ng-options="subcat.id as subcat.name for subcat in subcats"></select>
		    		year <select ng-options="year as year for year in years" ng-model="year" ng-change='tableParams.reload()'></select>
		    		month <select ng-options='month as month for month in months' ng-model="month" ng-change='tableParams.reload()'></select>
				</div>
	    		
        	</div>
			<table ng-table="tableParams" show-filter="true" class="table">
	        <tr ng-repeat="mov in $data">
	        	<td data-title="'Operation'" sortable="'operationDate'">{{mov.operationDate}}</td>
	        	<td data-title="'Order'" sortable="'orderOfDoc'">{{mov.orderOfDoc}}</td>
	            <td data-title="'Concept'" filter="{ 'concept': 'text' }">{{mov.concept}}</td>
	            <td data-title="'Amount'" sortable="'amount'">
	            	<span ng-class="{ 'plus': mov.amount >= 0,'minus': mov.amount < 0 }">{{mov.amount}}</span>
	            </td>
	            <td data-title="'Total'">{{mov.total}}</td>
	            <td data-title="'Category'" >
	            	<a ng-if="!mov.categoryName"	href="" class="btn btn-warning" ng-click="setCategory(mov)">No Catalogat</a>
	            	<a ng-if="mov.categoryName"	ng-class="{'btn-success': mov.categoryType.name=='MANUAL','btn-primary': mov.categoryType.name=='AUTOMATIC','btn-info': mov.categoryType.name=='MULTIPLE'}" "href="" class="btn " ng-click="setCategory(mov)">{{mov.categoryName}}</a>
	            </td>
	            <td data-title="'Edit'">
	            	<a href="" class="btn btn-default" ng-click="editMov(mov)">Edit</a>
	            	<a href="" class="btn btn-danger" ng-click="deleteMov(mov)">Del</a>
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
        	<div class="panel panel-default">
        		<form novalidate class="simple-form">
					Concept: <input type="text" ng-model="concept" /> 
					<br><input type="button" ng-click="tableParams2.reload()" value="Filter" />
				</form>
        		
        	</div>
			<table ng-table="tableParams2" show-filter="true" class="table">
	        <tr ng-repeat="mov in $data">
	        	<td data-title="'concept'">{{mov[0]}}</td>
	        	<td data-title="'Num'">{{mov[1]}}</td>
	        	<td data-title="'NotNulls'" ng-class="{ 'minus': mov[0] == min[1],'plus': mov[0] != min[1] }">{{mov[2]}}</td>
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
