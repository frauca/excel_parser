<div class="panel panel-default " ng-repeat="(monthKey,month) in result.month.months">
	<div class="panel-heading" >{{monthKey}}</div>
	<div class="panel-body">
		<p>Total:{{month.total}}</p>
		<p>Incomes:{{month.incomes }}</p>
		<p>Outcomes:{{month.outcomes }}</p>
		
		<div class="panel panel-default col-lg-3" ng-repeat="(accountKey,account) in month.accounts">
			<div class="panel-heading" >{{accountKey}}</div>
			<div class="panel-body">
				<p>Total:{{account.total}}</p>
				<p>Incomes:{{account.incomes }}</p>
				<p>Outcomes:{{account.outcomes }}</p>
			</div>
		</div>
	</div>
</div>
