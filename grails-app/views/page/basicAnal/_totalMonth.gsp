<div class="panel panel-default col-lg-3" ng-repeat="(monthKey,month) in result.month.months">
	<div class="panel-heading" ng-click="showMonthDetail(weekKey)">{{weekKey}}</div>
	<div class="panel-body">
		<p>Total:{{week.total}}</p>
		<p>Incomes:{{week.incomes }}</p>
		<p>Outcomes:{{week.outcomes }}</p>
	</div>
</div>