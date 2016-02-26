<accordion-group ng-repeat="(accountKey,account) in accountsDetails"> 
	<accordion-heading>{{accountKey}} Total:{{account.total}} -- Incomes:{{account.incomes }} --Outcomes:{{account.outcomes }} 
	</accordion-heading>
	<div>
		<table ng-table="tableParams" show-filter="true" class="table">
			<tr ng-repeat="mov in account.details">
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
</accordion-group>