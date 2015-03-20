<accordion-group is-open="status.isFirstOpen"> <accordion-heading>
Categories <i class="pull-right glyphicon"
	ng-class="{'glyphicon-chevron-down': status.isFirstOpen, 'glyphicon-chevron-right': !status.isFirstOpen}"></i>
</accordion-heading>
<div ng-controller="movCategoriesCtrl">
	<ul class="breadcrumb">
		<li ng-repeat="hier in catsHier"><a ng-click="setHier(hier.id)" href="#">{{hier.text}}</a> <span class="divider"></span></li>
	</ul>
	<div class="panel panel-default">
		<div class="panel-heading">
			<form novalidate class="simple-form">
				Name: <input type="text" ng-model="newCat.name" /> <input
					type="button" ng-click="addCategory(newCat)" value="Add" />
			</form>
		</div>
		<table ng-table="tableParams" show-filter="true" class="table">
			<tr ng-repeat="category in $data">
				<td data-title="'Name'" filter="{ 'name': 'text' }" sorteable='name'>
					<span ng-if="!category.$edit"><a ng-click="addHier(category)">{{category.name}}</a></span>
					<div ng-if="category.$edit">
						<input class="form-control" type="text" ng-model="category.name" />
					</div>
				</td>
				<td data-title="'Actions'" width="200"><a
					ng-if="!category.$edit" href="" class="btn btn-default"
					ng-click="editCategory(category)">Edit</a> <a
					ng-if="category.$edit" href="" class="btn btn-primary"
					ng-click="editCategory(category)">Save</a> <a href=""
					class="btn btn-danger " ng-click="deleteCategory(category)">Delete</a>
				</td>
			</tr>
		</table>
	</div>
</div>
</accordion-group>