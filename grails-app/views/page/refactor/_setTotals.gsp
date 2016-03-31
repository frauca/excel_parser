<accordion-group is-open="status.isSecondOpen"> <accordion-heading>
On each mov set total equals total Raw<i class="pull-right glyphicon"
	ng-class="{'glyphicon-chevron-down': status.isSecondOpen, 'glyphicon-chevron-right': !status.isSecondOpen}"></i>
</accordion-heading>
<div ng-controller="recalcTotalsCtrl">
	<div class="panel panel-default">
		<div class="panel-heading">
			<form novalidate class="simple-form">
				From: <input type="text" ng-model="fromDateSet" class="datepicker" />
				<select  ng-model="selectAccountSet" ng-options="account.id as (account.name + ' - ' + account.ccc) for account in accounts">
						<option/>
					</select>
				 <input
					type="button" ng-click="setTotals()" value="Set Raw" />
			</form>
		</div>
		<table show-filter="true" class="table">
			<thead>
				<tr>
		        	<td >Operation</td>
		            <td>Concept</td>
		            <td>Amount</td>
		            <td>Total</td>
		            <td>Total Raw</td>
		        </tr>
			</thead>
			<tbody>
				<tr ng-repeat="mov in setMovs">
		        	<td >{{mov.operationDate}}</td>
		            <td>{{mov.concept}}</td>
		            <td>{{mov.amount}}</td>
		            <td>{{mov.totalAmount}}</td>
		            <td>{{mov.totalAmountRaw}}</td>
		        </tr>
			</tbody>
	        
	     </table>
	</div>
</div>
</accordion-group>