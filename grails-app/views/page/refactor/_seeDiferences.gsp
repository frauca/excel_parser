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
	        	<td data-title="'Date'">{{dif.operationDate}}</td>
	        	<td data-title="'Order'" >{{dif.orderOfDoc}}</td>
	            <td data-title="'Concept'" >{{dif.concept}}</td>
	            <td data-title="'Amount'" >{{dif.amount}}</td>
	            <td data-title="'total/movTot'" >
	            	<span ng-class="{ 'plus': dif.isValid,'minus': !dif.isValid }">{{dif.total}} </span>/ {{dif.totalAmount}}
	            	<span ng-show="dif.total!=dif.totalRaw" class="glyphicon glyphicon-thumbs-down red"/>
	            </td>
	            <td data-title="'totalRaw/movRaw'" >
	            	<span ng-class="{ 'plus': dif.isValidRaw,'minus': !dif.isValidRaw }">{{dif.totalRaw}}</span>/ {{dif.totalAmountRaw}}
	            	<span ng-show="dif.totalAmount!=dif.totalAmountRaw" class="glyphicon glyphicon-thumbs-down red"/>
	            </td>
	            <td data-title="File">
	            <button ns-popover   ns-popover-template="popover"   ns-popover-trigger="click"   ns-popover-placement="bottom">    {{dif.fileId}}</button>
	            <script type="text/ng-template" id="popover">
 					 <b>{{dif.fileName}}</b>
  					<p>{{dif.filePath}}</p>
				</script>
	            </td>
	        </tr>
	        </table>
	</div>
</div>
</accordion-group>