
movsControllers.controller('queriesCtrl', function($scope,$http,ngTableParams,Query) {
	$scope.tableParams = new ngTableParams({
		page : 1, // show first page
		count : 10,
		sorting: {
			valueDate: 'desc'     // initial sorting
        }
	// count per page
	}, {
		counts : [], // hides page sizes
		getData : function($defer, params) {
			$http.get("query").success(
					function(data) {
						params.total(data.length);
						$defer.resolve(data.slice((params.page() - 1)
								* params.count(), params.page()
								* params.count()));
					});
		}
	});
	
	$scope.addQuery = function(query) {
		q = new Query(query);
		if(!$scope.editing){
			Query.create(q, function(data) {
				$scope.tableParams.reload();
			});
		}else{
			Query.update(q, function(data) {
				$scope.tableParams.reload();
			});
		}
		$scope.editing=false;
		$scope.newQ = new Query();
	}
	
	$scope.editQ = function(query) {
		$scope.editing=true;
		$scope.newQ = new Query(query);
	}
	
	$scope.showQuery = function(query) {
		$scope.showResult=true
		$scope.q2e=query
		$scope.extractVars(query)
		query.params=JSON.parse(query.arguments)
	}
	
	$scope.extractVars =function(query){
		$scope.vars = query.sql.match(/#[^#]+#/g);
	}
	
	$scope.execute = function(query) {
		console.log("parameters "+query.params)
		$scope.showResult=true
		query.arguments=JSON.stringify(query.params)
		Query.update(query, function(data) {
			executeById(query.id)
		});
	}
	
	function executeById(id){
		$http.get("SQLQueries/execute?id="+id).then(
				function(result) {
					$scope.result=result.data
				});
	}
	
	
	$scope.showQList = function(){
		$scope.showResult=false
	}
});