<div>
  <form novalidate class="simple-form">
   <div class="panel panel-primary">
	      <div class="panel-heading">
	          Mov 
	      </div>
	      <div class="panel-body">
		    valueDate: <input type="text" ng-model="mov.valueDate">
		    concept: dateValue: <input type="text" ng-model="mov.concept">
		    <input type="submit" ng-click="save(mov)" value="Update" />
		    <br>operationDate:{{mov.operationDate}}
		    <br>conceptRaw:{{mov.conceptRaw}}
		    <br>file:{{acc_mov.filePath}}
		    <br>totalAmountRaw:{{mov.totalAmountRaw}}
		    <br>totalAmount:{{mov.totalAmount}}
		    <br>ccc:{{mov.account.rawCCC}}-{{mov.account.name}}
		   </div>
    </div>
    
  </form>
</div>