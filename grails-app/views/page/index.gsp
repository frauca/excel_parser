<!DOCTYPE html>
<html>
<head>
<title>Pagina principal</title>
</head>
<body>
	<div ng-controller="movListCtrl">
		<table st-table="rowCollection" class="table table-striped">
			<thead>
				<tr>
					<th>operation D</th>
					<th>value D</th>
					<th>concept</th>
					<th>amount</th>
					<th>total</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="row in rowCollection">
					<td>{{row.operationDate}}</td>
					<td>{{row.valueDate}}</td>
					<td>{{row.concept}}</td>
					<td>{{row.amount}}</td>
					<td>{{row.totalAmount}}</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>