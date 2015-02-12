<!DOCTYPE html>
<html>
<head>
<title>Pagina principal</title>
</head>
<body>
<g:link controller="page" action="configuration" >Configuration</g:link>
<g:link controller="page" action="configuration" target="_self" >Configuration</g:link>
	<div ng-controller="movListCtrl">
		<select ng-change="changedCCC()" ng-model="selectAccount" ng-options="account.id as account.name for account in accounts">
			<option/>
		</select>
		<select ng-change="changedFile()" ng-model="selectedFile" ng-options="file.id as file.name for file in files">
			<option/>
		</select>
		
		<table ng-table="tableParams" show-filter="true" class="table">
        <tr ng-repeat="mov in $data">
        	<td data-title="'Operation'">{{mov.operationDate}}</td>
        	<td data-title="'Value'">{{mov.valueDate}}</td>
            <td data-title="'Concept'" filter="{ 'concept': 'text' }">{{mov.concept}}</td>
            <td data-title="'Amount'" sortable="'amount'">{{mov.amount}}</td>
            <td data-title="'Total'">{{mov.total}}</td>
            <td data-title="'Category'" sortable="'category'">
            	<a ng-if="!mov.categoryName"	href="" class="btn btn-warning" ng-click="setCategory(mov)">No Catalogat</a>
            	<a ng-if="mov.categoryName"	ng-class="{'btn-success': mov.categoryType.name=='MANUAL','btn-primary': mov.categoryType.name=='AUTOMATIC'}" "href="" class="btn " ng-click="setCategory(mov)">{{mov.categoryName}}</a>
            </td>
        </tr>
        </table>
		
	</div>
</body>
</html>