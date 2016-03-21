<div>
  <form novalidate class="simple-form">
   <div class="panel panel-primary">
	      <div class="panel-heading">
	          Mov 
	      </div>
	      <div class="panel-body">
		    valueDate: <input type="text" ng-model="mov.valueDate">
		    <br>concept: <input type="text" ng-model="mov.concept">
		    <select ng-options='concept as concept for concept in concepts' ng-model="mov.concept"></select>
		    <br><input type="submit" ng-click="save(mov)" value="Update" />
		    <br>operationDate:{{mov.operationDate}}
		    <br>conceptRaw:{{mov.conceptRaw}}
		    <br>totalAmountRaw:{{mov.totalAmountRaw}}
		    <br>totalAmount:{{mov.totalAmount}}
		    <br>ccc:{{mov.ccc.rawCCC}}-{{mov.ccc.name}}-{{mov.account}}
		    <br>id: {{mov.id}}
		    <br>filePath: {{addInfo.filePath}}
		   </div>
    </div>
    
  </form>
</div>