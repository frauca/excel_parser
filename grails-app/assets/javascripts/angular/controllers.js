var movsControllers = angular.module('movsControllers', []);

movsControllers.controller('movListCtrl', function ($scope,$http,$filter,ngTableParams) {
	$http.get('acount_movs.json?max=-1').success(function(data) {
		$scope.rowCollection = data;
		$scope.tableParams = new ngTableParams({
	        page: 1,            // show first page
	        count: 10           // count per page
	    }, {
	    	counts: [], // hides page sizes
	        total: data.length, // length of data
	        getData: function($defer, params) {
	        	var orderedData = params.sorting() ?
                        $filter('orderBy')(data, params.orderBy()) : data;
                orderedData = params.filter() ?
                                $filter('filter')(orderedData, params.filter()) : orderedData;
	            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
	        }
	    });
	  });
});