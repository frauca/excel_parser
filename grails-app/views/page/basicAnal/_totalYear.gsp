<div class="panel panel-default " ng-repeat="(yearKey,year) in result.year.years">
	<div class="panel-heading" >{{yearKey}}</div>
	<div class="panel-body">
		<p>Total:{{year.total}}</p>
		<p>Incomes:{{year.incomes }}</p>
		<p>Outcomes:{{year.outcomes }}</p>
		
		<div class="panel panel-default col-lg-3" ng-repeat="(accountKey,account) in year.accounts">
			<div class="panel-heading" ng-click="showYearDetail(accountKey)">{{accountKey}}</div>
			<div class="panel-body">
				<p>Total:{{account.total}}</p>
				<p>Incomes:{{account.incomes }}</p>
				<p>Outcomes:{{account.outcomes }}</p>
			</div>
		</div>
		<div class=" col-lg-12">
			<table ng-table="tableParams" show-filter="true" class="table">
				<tr ng-repeat="mov in yearBottomDetails">
					<td data-title="'Operation'">{{mov.operationDate}}</td>
					<td data-title="'Value'" sortable="'valueDate'">{{mov.valueDate}}</td>
					<td data-title="'Concept'" filter="{ 'concept': 'text' }">{{mov.concept}}</td>
					<td data-title="'Amount'" sortable="'amount'"><span
						ng-class="{ 'plus': mov.amount >= 0,'minus': mov.amount < 0 }">{{mov.amount}}</span>
					</td>
					<td data-title="'Total'">{{mov.total}}</td>
					<td data-title="'Category'">{{mov.categoryType.name}}</td>
		
				</tr>
			</table>
			....
		</div>
		<div class=" col-lg-12">
			<table ng-table="tableParams" show-filter="true" class="table">
				<tr ng-repeat="mov in yearTopDetails">
					<td data-title="'Operation'">{{mov.operationDate}}</td>
					<td data-title="'Value'" sortable="'valueDate'">{{mov.valueDate}}</td>
					<td data-title="'Concept'" filter="{ 'concept': 'text' }">{{mov.concept}}</td>
					<td data-title="'Amount'" sortable="'amount'"><span
						ng-class="{ 'plus': mov.amount >= 0,'minus': mov.amount < 0 }">{{mov.amount}}</span>
					</td>
					<td data-title="'Total'">{{mov.total}}</td>
					<td data-title="'Category'">{{mov.categoryType.name}}</td>
		
				</tr>
			</table>
		</div>
	</div>
</div>
