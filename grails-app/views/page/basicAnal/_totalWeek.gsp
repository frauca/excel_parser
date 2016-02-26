
<div class="panel panel-default col-lg-3" ng-repeat="(weekKey,week) in result.week.weeks">
	<div class="panel-heading" ng-click="showWeekDetail(weekKey)">{{weekKey}}</div>
	<div class="panel-body">
		<p>Total:{{week.total}}</p>
		<p>Incomes:{{week.incomes }}</p>
		<p>Outcomes:{{week.outcomes }}</p>
	</div>
</div>
<div class="panel panel-default col-lg-12" >
	<div class="panel-heading" ">Details</div>
	<div class="panel-body">
		<div class="col-lg-12" >
			<accordion close-others="oneAtATime">
				<accordion-group >
					<accordion-heading>
			            Total:{{weekDetails.total}} -- Incomes:{{weekDetails.incomes }} --Outcomes:{{weekDetails.outcomes }}
			        </accordion-heading>
			        <div >
			        	<table ng-table="tableParams" show-filter="true" class="table">
				        <tr ng-repeat="mov in weekDetails.details">
				        	<td data-title="'Operation'">{{mov.operationDate}}</td>
				        	<td data-title="'Value'" sortable="'valueDate'">{{mov.valueDate}}</td>
				            <td data-title="'Concept'" filter="{ 'concept': 'text' }">{{mov.concept}}</td>
				            <td data-title="'Amount'" sortable="'amount'">
				            	<span ng-class="{ 'plus': mov.amount >= 0,'minus': mov.amount < 0 }">{{mov.amount}}</span>
				            </td>
				            <td data-title="'Total'">{{mov.total}}</td>
				            <td data-title="'Category'" >
				            	{{mov.categoryType.name}}
				            </td>
				  
				        </tr>
				        </table>
			        </div>
			      </accordion-group>
			      <g:render template="/page/basicAnal/accountsDetails" />
			</accordion>
		</div>
	</div>
</div>