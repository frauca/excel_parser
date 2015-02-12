
<div>
  <form novalidate class="simple-form">
   <div class="panel panel-primary">
	      <div class="panel-heading">
	          Category 
	      </div>
	      <div class="panel-body">
		    comment: <textarea rows="10" cols="15" ng-model="cat.comment" ></textarea><br />
		    category{{cat.category}}: 
		    		<select ng-model="cat.category" ng-options="category.id as category.name for category in categories"></select>
		    <input type="submit" ng-click="save(cat)" value="Save" />
		   </div>
    </div>
    
  </form>
</div>