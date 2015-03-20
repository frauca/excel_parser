<!DOCTYPE html>
<html>
<head>
<title>Configuracions</title>
<asset:javascript src="angular/configuration/controllersCN.js"/>
</head>
<body>
	<div class="col-lg-12">
		<h4>Configuracions </h4>
	</div>
	<div class="col-lg-12" >
		<accordion close-others="oneAtATime">
	    	<g:render template="configuration/categoryEditorTemplate" />   
			<accordion-group is-open="status.isSecondOpen">
				<accordion-heading>
		            Accounts <i class="pull-right glyphicon" ng-class="{'glyphicon-chevron-down': status.isSecondOpen, 'glyphicon-chevron-right': !status.isSecondOpen}"></i>
		        </accordion-heading>
      			<div ng-controller="movAccountCtrl">
      				<div class="panel panel-default">
						<div class="panel-heading"> 
							
						</div>	
						<table ng-table="tableParams" show-filter="true" class="table">
							<tr ng-repeat="account in $data">
								<td data-title="'ccc'" filter="{ 'ccc': 'text' }" sorteable='ccc'>							
					                <span>{{account.ccc}}</span>
				                </td>
								<td data-title="'Name'" filter="{ 'name': 'text' }" sorteable='name'>
									<span ng-if="!account.$edit">{{account.name}}</span>
					                <div ng-if="account.$edit"><input class="form-control" type="text" ng-model="account.name" /></div>
				                </td>				                
				                 <td data-title="'comments'">
									<span ng-if="!account.$edit">{{account.comments}}</span>
					                <div ng-if="account.$edit"><input class="form-control" type="text" ng-model="account.comments" /></div>				           
				                </td>
								<td data-title="'Actions'" width="200">
									<a ng-if="!account.$edit"	href="" class="btn btn-default" ng-click="editAccount(account)">Edit</a>
									<a ng-if="account.$edit" href="" class="btn btn-primary"	ng-click="editAccount(account)">Save</a>									
								</td>
							</tr>
						</table>
					</div>
				</div>
		    </accordion-group>		
		    <accordion-group is-open="status.open">
		    	<accordion-heading>
		            Directoris <i class="pull-right glyphicon" ng-class="{'glyphicon-chevron-down': status.open, 'glyphicon-chevron-right': !status.open}"></i>
		        </accordion-heading>		   
			    	<div ng-controller="directoryCtrl">
			    		<div class="panel panel-default">
							<div class="panel-heading">			    	
								<form novalidate class="simple-form">
									Name: <input type="text" ng-model="newDir.path" /> 
									<input type="button" ng-click="addDirectory(newDir)" value="Add" />
								</form>
							</div>
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
					</div>
			</accordion-group>
		</accordion>
	</div>
</body>
</html>