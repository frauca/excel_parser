<accordion-group is-open="status.isFourthOpen"> 
	<accordion-heading>
	Files That Validates <i class="pull-right glyphicon"
		ng-class="{'glyphicon-chevron-down': status.isFourthOpen, 'glyphicon-chevron-right': !status.isFourthOpen}"></i>
	</accordion-heading>
	<div ng-controller="filesValidatorsCtrl">
		<div class="panel panel-default">
			<div class="panel-heading">
				<form novalidate class="simple-form">
					From: <input type="text" ng-model="validatePath" /> 
					<input		type="button" ng-click="readFolder()" value="Read Folder" />
					<select  ng-model="selectedFile" ng-options="file.name for file in files">
						<option/>
					</select>
					<input		type="button" ng-click="processFile()" value="Process this file" /> 
				</form>
			</div>
			<table ng-table="bindings" show-filter="true" class="table">
	        <tr ng-repeat="bind in $data">
	        	<td data-title="'Bindings'"  sortable="'countMatches'" ><a href="" class="btn btn-default" ng-click="validateModal(bind)">{{bind.countMatches}}</a></td>
	        	<td data-title="'Date'">{{bind.correctMov.operationDate}}</td>
	        	<td data-title="'Order'" >{{bind.correctMov.orderOfDoc}}</td>
	        	<td data-title="'Amount'" >{{bind.correctMov.amount}}</td>
	        	<td data-title="'Concept'" >{{bind.correctMov.concept}}</td>
	        </tr>
	        </table>
		</div>
	</div>
</accordion-group>