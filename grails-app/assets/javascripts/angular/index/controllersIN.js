movsControllers.controller('movListCtrl', function($scope, $http, $filter,ngTableParams, $modal, Acc_movs, Categoritzation, File, Account,Category) {

	setSelects();
	$scope.showFilter=false;
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
			$http.get(getMovsURL()).success(
					function(data) {
						params.total(data.length);
						var orderedData = params.sorting() ? $filter('orderBy')
								(data, params.orderBy()) : data;
						orderedData = params.filter() ? $filter('filter')(
								orderedData, params.filter()) : orderedData;
						$defer.resolve(orderedData.slice((params.page() - 1)
								* params.count(), params.page()
								* params.count()));
					});
		}
	});

	$scope.setCategory = function(mov) {
		$scope.acc_mov = mov
		var modalInstance = $modal.open({
			templateUrl : 'modal/setCategory',
			controller : 'movCategorCtrl',
			scope : $scope
		});
		modalInstance.result.then(function(cat) {
			Acc_movs.update({
				id : mov.id,
				categoritzation : {
					id : cat.id
				}
			}, function() {
				$scope.tableParams.reload();
			});

		});
	}

	function getMovsURL() {
		movsurl = 'acount_movs.json?max=1000&sort=valueDate&order=desc';
		console.log("uncat"+$scope.uncategorized);
		if ($scope.selectedFile) {
			movsurl += "&file=" + $scope.selectedFile;
		}
		if ($scope.selectAccount) {
			movsurl += "&ccc=" + $scope.selectAccount;
		}
		if ($scope.uncategorized) {
			movsurl += "&uncat=true";
		}
		if ($scope.unSubCat) {
			movsurl += "&unSubCat=true";
		}
		if ($scope.category) {
			movsurl += "&category="+$scope.category;
		}
		if ($scope.year) {
			movsurl += "&year="+$scope.year;
		}
		if ($scope.month) {
			movsurl += "&month="+$scope.month;
		}
		return movsurl;
	}
	function setSelects() {
		$scope.files = File.query();
		$scope.accounts = Account.query();
		$scope.categories = Category.query();
		$scope.years = Acc_movs.years();
		$scope.months = [1,2,3,4,5,6,7,8,9,10,11,12]
	}
	$scope.changedCCC = function() {
		$scope.files = File.query({
			ccc : $scope.selectAccount
		});
		$scope.tableParams.reload();
	}
	$scope.updateSub = function(){
		$scope.subcats =Category.subcat({father:$scope.category});
		$scope.tableParams.reload();
	}
});

movsControllers.controller('movCategorCtrl', function($scope, Categoritzation,Category, $modalInstance, $q) {
	$scope.categories = Category.query();
	if ($scope.acc_mov.categoritzaion) {
		$scope.updating = true;
		$q.all([ getSyncCategoritzation($scope.acc_mov.categoritzaion) ]).then(
				function(data) {
					$scope.cat = data[0];
					$scope.cat.category = $scope.cat.category.id;
					if($scope.cat.subcat){
						$scope.cat.subcat=$scope.cat.subcat.id;
					}
					$scope.updateSub();
				});

	} else {
		$scope.updating = false;
		$scope.cat = new Categoritzation();
		$scope.cat.type = "MANUAL";
	}

	// $scope.cat.accountMov=$scope.acc_mov.id;
	$scope.save = function(cat) {
		if ($scope.updating) {
			Categoritzation.update(cat, function(data) {
				$modalInstance.close(data);
			});
		} else {
			Categoritzation.save(cat, function(data) {
				$modalInstance.close(data);
			});
		}

	}
	
	$scope.updateSub = function(){
		$scope.subcats =Category.subcat({father:$scope.cat.category});
	}
	function getSyncCategoritzation(catId) {
		var d = $q.defer();
		var result = Categoritzation.get({
			id : catId
		}, function(categorit) {
			d.resolve(categorit);
		});
		return d.promise;
	}
});





movsControllers.controller('uncatMovCtrl', function($scope, $http,$filter,ngTableParams,Category) {
	$scope.categories = Category.query();
	$scope.tableParams2 = new ngTableParams({
		page : 1, // show first page
		count : 10
	// count per page
	}, {
		counts : [], // hides page sizes
		getData : function($defer, params) {
			$http.get(getUrl()).success(
					function(data) {
						params.total(data.length);//update paginator
						$defer.resolve(data.slice((params.page() - 1)
								* params.count(), params.page()
								* params.count()));
					});
		}
	});
	
	function getUrl(){
		var url='accountMov/showUncatPending?max=-1';
		if($scope.concept){
			url=url+"&concept="+$scope.concept
		}
		
		return url;
	}
	
	$scope.categorizeConcept= function(concept,cat){
		console.log(" categorize " +concept+ " cat "+cat)
		$http.get('accountMov/categorizedAll?concept='+concept+"&cat="+cat).success(
				function(data) {
					$scope.tableParams2.reload();
				});
	}
});

