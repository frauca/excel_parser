<!DOCTYPE html>
<html>
<head>
<title>Configuracions</title>
</head>
<body>
	<div class="col-lg-12">
		<h4>Configuracions </h4>
	</div>
	<div class="col-lg-12" >
		<accordion close-others="oneAtATime">
	    	<accordion-group   heading="Categories management" is-open="status.isFirstOpen" is-disabled="status.isFirstDisabled">
	    		<div ng-controller="movCategoriesCtrl"> 
					<form novalidate class="simple-form">
						Name: <input type="text" ng-model="newCat.name" /><br />
						<input type="button" ng-click="addCategory(newCat)" value="Add" />
					</form>
					<table ng-table="tableParams" show-filter="true" class="table">
						<tr ng-repeat="category in $data">
							<td data-title="'Name'" filter="{ 'name': 'text' }" sorteable='name'>
								<span ng-if="!category.$edit">{{category.name}}</span>
				                <div ng-if="category.$edit"><input class="form-control" type="text" ng-model="category.name" /></div>
			                </td>
							<td data-title="'Actions'" width="200">
								<a ng-if="!category.$edit"	href="" class="btn btn-default" ng-click="editCategory(category)">Edit</a>
								<a ng-if="category.$edit" href="" class="btn btn-primary"	ng-click="editCategory(category)">Save</a>
								<a href="" class="btn btn-danger "	ng-click="deleteCategory(category)">Delete</a>
							</td>
						</tr>
					</table>
				</div>
			</accordion-group>
			<accordion-group heading="Accounts management">
      			<p>The body of the accordion group grows to fit the contents</p>
		        <button class="btn btn-default btn-sm" ng-click="addItem()">Add Item</button>
		        <div ng-repeat="item in items">{{item}}</div>
		    </accordion-group>
		    <accordion-group is-open="status.open">
		        <accordion-heading>
		            I can have markup, too! <i class="pull-right glyphicon" ng-class="{'glyphicon-chevron-down': status.open, 'glyphicon-chevron-right': !status.open}"></i>
		        </accordion-heading>
		        This is just some content to illustrate fancy headings.
		    </accordion-group>
		    <accordion-group  heading="Directoris" > 
		    	<div ng-controller="directoryCtrl">
					<form novalidate class="simple-form">
						Name: <input type="text" ng-model="newDir.path" /><br />
						<input type="button" ng-click="addDirectory(newDir)" value="Add" />
					</form>
					<table ng-table="tableParams" show-filter="true" class="table">
						<tr ng-repeat="directory in $data">
							<td data-title="'Path'" filter="{ 'path': 'text' }" sorteable='path'>
								<span ng-if="!directory.$edit">{{directory.path}}</span>
				                <div ng-if="directory.$edit"><input class="form-control" type="text" ng-model="directory.path" /></div>
			                </td>
							<td data-title="'Actions'" width="200">
								<a ng-if="!directory.$edit"	href="" class="btn btn-default" ng-click="editDirectory(directory)">Edit</a>
								<a ng-if="directory.$edit" href="" class="btn btn-primary"	ng-click="editCategory(directory)">Save</a>
								<a href="" class="btn btn-danger "	ng-click="deleteDirectory(directory)">Delete</a>
							</td>
						</tr>
					</table>
				</div>
			</accordion-group>
		</accordion>
	</div>
</body>
</html>