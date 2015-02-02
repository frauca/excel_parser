var movsControllers = angular.module('movsControllers', []);

movsControllers.controller('movListCtrl', function ($scope,$http,$filter,ngTableParams,$modal,Acc_movs,Categoritzation) {
	
	
	$scope.tableParams = new ngTableParams({
        page: 1,            // show first page
        count: 10           // count per page
    }, {
    	counts: [], // hides page sizes
        getData: function($defer, params) {
        	$http.get('acount_movs.json?max=-1').success(function(data) {
	        	var orderedData = params.sorting() ?
                        $filter('orderBy')(data, params.orderBy()) : data;
                orderedData = params.filter() ?
                                $filter('filter')(orderedData, params.filter()) : orderedData;
	            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
        	});  
        }
    });
	
	$scope.setCategory = function(mov){
		$scope.acc_mov=mov
		var modalInstance = $modal.open({
		      templateUrl: 'modal/setCategory',
		      controller:'movCategorCtrl',
		      scope:$scope
		    });
		modalInstance.result.then(function (cat) {
				mov.categoritzation=cat.id;
				Acc_movs.update(mov,function(){
					$scope.tableParams.reload();
				});
				
		    });
	}
	
	$scope.getCategoryFromId = function(catId){
		categorit = Categoritzation.get({id:1},function(categorit){
			console.log("cate ."+categorit.category);
			return "other";
		});
	}
});

movsControllers.controller('movCategorCtrl', function ($scope,Categoritzation,Category,$modalInstance) {
	$scope.categories=Category.query();
	$scope.cat = new Categoritzation();
	$scope.cat.type="MANUAL";
	//$scope.cat.accountMov=$scope.acc_mov.id;
	$scope.save=function(cat){	
		Categoritzation.save(cat,function(data){
			$modalInstance.close(data);
		});
		
	}
});

movsControllers.controller('movCategoriesCtrl', function ($scope,$http,$filter,$resource,$timeout,ngTableParams,Category) {
	
		$scope.tableParams = new ngTableParams({
	        page: 1,            // show first page
	        count: 10           // count per page
	    }, {
	    	counts: [], // hides page sizes
	        getData: function($defer, params) {
	        	$http.get('category.json?max=-1').success(function(data) {
		        	var orderedData = params.sorting() ?
	                        $filter('orderBy')(data, params.orderBy()) : data;
	                orderedData = params.filter() ?
	                                $filter('filter')(orderedData, params.filter()) : orderedData;
		            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
	        	});  
	        }
	    });
	  
	//console.log('create table'+cats);

	$scope.editCategory = function(category){
		if(category.$edit){
			console.log("try to update");
			cat =new Category(category);
			Category.update(cat,function(data){
				$scope.tableParams.reload();
			});
			category.$edit=false;
		}else{
			category.$edit=true;
		}
	}
	
	$scope.addCategory = function(category){
		cat =new Category(category);
		Category.create(cat,function(data){
			$scope.tableParams.reload();
		});
		
	}
	
	$scope.deleteCategory = function(category){
		cat =new Category(category);
		Category.remove(cat,function(data){
			$scope.tableParams.reload();
		});
	}
});