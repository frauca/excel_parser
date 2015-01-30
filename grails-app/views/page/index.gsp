<!DOCTYPE html>
<html>
<head>
<title>Pagina principal</title>
</head>
<body>
	<div ng-controller="movListCtrl">
	
		
		<table ng-table="tableParams" show-filter="true" class="table">
        <tr ng-repeat="user in $data">
        	<td data-title="'Operation'">{{user.operationDate}}</td>
        	<td data-title="'Value'">{{user.valueDate}}</td>
            <td data-title="'Concept'" filter="{ 'concept': 'text' }">{{user.concept}}</td>
            <td data-title="'Amount'" sortable="'amount'">{{user.amount}}</td>
            <td data-title="'Total'">{{user.total}}</td>
            <td >
            	<a ng-if="!user.categoritzation"	href="" class="btn btn-default">No Catalogat</a>
            	<a ng-if="user.categoritzation"	href="" class="btn btn-default">{{user.categoritzation.category.name}}</a>
            </td>
        </tr>
        </table>
		
	</div>
</body>
</html>