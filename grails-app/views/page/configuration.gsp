<!DOCTYPE html>
<html>
<head>
<title>Configuracions</title>
</head>
<body>
	<div ng-controller="movCategoriesCtrl">

		<form novalidate class="simple-form">
			Name: <input type="text" ng-model="newCat.name" /><br />
			<input type="button" ng-click="addCategory(newCat)" value="Add" />
		</form>

		<table ng-table="tableParams" show-filter="true" class="table">
			<tr ng-repeat="category in $data">
				<td data-title="'Name'" filter="{ 'name': 'text' }" sorteable='name'>
					<span ng-if="!category.$edit">{{user.name}}</span>
	                <div ng-if="category.$edit"><input class="form-control" type="text" ng-model="category.name" /></div>
                </td>
				<td data-title="'Actions'" width="200">
					<a ng-if="!category.$edit"	href="" class="btn btn-default btn-xs" ng-click="editCategory(category)">Edit</a>
					<a ng-if="category.$edit" href="" class="btn btn-primary btn-xs"	ng-click="editCategory(category)">Save</a>
				</td>
			</tr>
		</table>

	</div>
</body>
</html>