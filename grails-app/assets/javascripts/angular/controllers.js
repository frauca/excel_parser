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

movsControllers.controller('movCategoriesCtrl', function ($scope,$http,$filter,$timeout,ngTableParams,Category) {
	console.log('create table');
	cats=Category.query();
	//console.log('create table'+cats);
	$scope.tableParams = new ngTableParams({
        page: 1,            // show first page
        count: 10           // count per page
    }, {
    	counts: [], // hides page sizes
    	getData: function($defer, params) {
            // ajax request to api
            Category.get(params.url(), function(data) {
                $timeout(function() {
                	console.log('here it is'+data.total);
                    // update table params
                    params.total(data.total);
                    // set new data
                    $defer.resolve(data.result);
                }, 500);
            });
        }
    });
	console.log('created table');
	$scope.editCategory = function(category){
		if(category.$edit){
			category.$edit=false;
		}else{
			category.$edit=true;
		}
	}
	
	$scope.addCategory = function(category){
		cat =new Category(category);
		Category.save(cat,function(){
			 console.log('done');
		});
	}
});