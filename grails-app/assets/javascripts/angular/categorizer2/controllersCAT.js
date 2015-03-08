
movsControllers.controller('categorizerCtrl', function($scope,$http,ngTableParams) {
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
			$http.get("query/catByConcept?concept="+$scope.concept).success(
					function(data) {
						params.total(data.length);
						$defer.resolve(data.slice((params.page() - 1)
								* params.count(), params.page()
								* params.count()));
					});
		}
	});
	
});