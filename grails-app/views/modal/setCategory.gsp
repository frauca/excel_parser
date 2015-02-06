<div>
  <form novalidate class="simple-form">
    comment: <textarea rows="4" cols="4" ng-model="cat.comment" ></textarea><br />
    category{{cat.category}}: 
    		<select ng-model="cat.category" ng-options="category.id as category.name for category in categories"></select>
    <input type="submit" ng-click="save(cat)" value="Save" />
  </form>
</div>