<!DOCTYPE html>
<html>
<head>
<title>Pagina principal</title>
</head>
<body>
	<div ng-controller="movListCtrl">
	
		
		<table ng-table="tableParams" show-filter="true" class="table">
        <tr ng-repeat="mov in $data">
        	<td data-title="'Operation'">{{mov.operationDate}}</td>
        	<td data-title="'Value'">{{mov.valueDate}}</td>
            <td data-title="'Concept'" filter="{ 'concept': 'text' }">{{mov.concept}}</td>
            <td data-title="'Amount'" sortable="'amount'">{{mov.amount}}</td>
            <td data-title="'Total'">{{mov.total}}</td>
            <td >
            	<a ng-if="!mov.categoritzation"	href="" class="btn btn-default" ng-click="setCategory(mov)">No Catalogat</a>
            	<a ng-if="mov.categoritzation"	href="" class="btn btn-default">-{{getCategoryFromId(mov.categoritzation.id)}}-</a>
            </td>
        </tr>
        </table>
		
	</div>
</body>
</html>