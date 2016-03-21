<accordion-group is-open="status.isFirstOpen"> <accordion-heading>
Recalc totals <i class="pull-right glyphicon"
	ng-class="{'glyphicon-chevron-down': status.isFirstOpen, 'glyphicon-chevron-right': !status.isFirstOpen}"></i>
</accordion-heading>
<div ng-controller="recalcTotalsCtrl">
	<div class="panel panel-default">
		<div class="panel-heading">
			<form novalidate class="simple-form">
				From: <input type="text" ng-model="fromDate" class="datepicker" />
				<select  ng-model="selectAccount" ng-options="account.id as (account.name + ' - ' + account.ccc) for account in accounts">
						<option/>
					</select>
				 <input
					type="button" ng-click="recalc()" value="Recalc" />
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
				<tr ng-repeat="mov in recalcMovs">
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