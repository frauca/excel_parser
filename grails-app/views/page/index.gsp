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
					<th>first name</th>
					<th>last name</th>
					<th>birth date</th>
					<th>balance</th>
					<th>email</th>
				</tr>
			</thead>
			<tbody>
				<tr ng-repeat="row in rowCollection">
					<td>{{row.firstName}}</td>
					<td>{{row.lastName}}</td>
					<td>{{row.birthDate}}</td>
					<td>{{row.balance}}</td>
					<td>{{row.email}}</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>