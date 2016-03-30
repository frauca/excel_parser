<div class="row">
	<div class="col-md-6">
	  <div class="panel panel-default">
		  <div class="panel-body">
		     <div class="row">
		     	<div class="col-md-12"><h3>{{bind.correctMov.concept}} - {{bind.correctMov.amount}}</h3></div>
		     	<div class="col-md-4">id:{{bind.correctMov.id}}</div>
		     	<div class="col-md-8">operation Date:{{bind.correctMov.operationDate}}</div>
		     </div>
		  </div>
	  </div>
	</div>
	<div class="col-md-6">
	  <div class="panel panel-default">
		  <div class="panel-body">
		    <h3>binded movs</h3>
		    
		    <table ng-table="bindeds" show-filter="true" class="table">
	        <tr ng-repeat="mov in bind.possibles">
	        	<td data-title="'Date'">{{mov.operationDate}}</td>
	        	<td data-title="'Amount'" >{{mov.amount}}</td>
	        	<td data-title="'Concept'" >{{mov.concept}}</td>
	        	<td data-title="'Id'" >{{mov.id}}</td>
	        </tr>
	        </table>
		  </div>
	  </div>
	</div>
</div>