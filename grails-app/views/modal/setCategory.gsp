<div>
  <form novalidate class="simple-form">
    comment: <textarea rows="4" cols="4" ng-model="cat.comment" ></textarea><br />
    category: <select ng-model="cat.category">
    			<option ng-repeat="category in categories"  value="{{category.id}}">{{category.name}}</option>
    		</select>
    <input type="submit" ng-click="save(cat)" value="Save" />
  </form>
</div>