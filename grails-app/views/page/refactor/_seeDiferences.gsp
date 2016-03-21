<accordion-group is-open="status.isThirdOpen"> <accordion-heading>
See Diferences<i class="pull-right glyphicon"
	ng-class="{'glyphicon-chevron-down': status.isThirdOpen, 'glyphicon-chevron-right': !status.isThirdOpen}"></i>
</accordion-heading>
<div ng-controller="recalcTotalsCtrl">
	<div class="panel panel-default">
		<div class="panel-heading">
			<form novalidate class="simple-form">
				From: <input type="text" ng-model="fromDateSee" class="datepicker" />
				<select  ng-model="selectAccountSee" ng-options="account.id as (account.name + ' - ' + account.ccc) for account in accounts">
						<option/>
					</select>
				 <input
					type="button" ng-click="seeTotals()" value="See Difs" />
			</form>
		</div>
		<table ng-table="seeDifsTable" show-filter="true" class="table">
	        <tr ng-repeat="dif in $data">
	        	<td data-title="'Date'">{{dif.mov.operationDate}}</td>
	        	<td data-title="'Order'" >{{dif.movRaw.orderOfDoc}}</td>
	            <td data-title="'Concept'" >{{dif.mov.concept}}</td>
	            <td data-title="'Amount'" >{{dif.mov.amount}}</td>
	            <td data-title="'total'" >
	            	<span ng-class="{ 'plus': dif.isValid,'minus': !dif.isValid }">{{dif.total}} </span>/ {{dif.mov.totalAmount}}
	            </td>
	            <td data-title="'totalRaw'" >
	            	<span ng-class="{ 'plus': dif.isValidRaw,'minus': !dif.isValidRaw }">{{dif.totalRaw}}</span>/ {{dif.mov.totalAmountRaw}}
	            </td>
	            <td data-title="File"><button uib-popover="{{dif.sourceFile.name}}" popover-title="{{dif.sourceFile.name}}" type="button" class="btn btn-default">{{dif.sourceFile.id}}</button>
	            </td>
	        </tr>
	        </table>
	</div>
</div>
</accordion-group>