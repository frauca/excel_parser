
<div>
  <form novalidate class="simple-form">
   <div class="panel panel-primary">
	      <div class="panel-heading">
	          Category 
	      </div>
	      <div class="panel-body">
		    
		    category:<select ng-model="cat.category" ng-change="updateSub()" ng-options="category.id as category.name for category in categories"></select>
		    subcat:<select ng-model="cat.subcat" ng-options="subcat.id as subcat.name for subcat in subcats"></select>
		    <br>comment: <textarea rows="3" cols="60" ng-model="cat.comment" ></textarea><br />
		    <input type="submit" ng-click="save(cat)" value="Save" />
		   </div>
    </div>
    
  </form>
</div>