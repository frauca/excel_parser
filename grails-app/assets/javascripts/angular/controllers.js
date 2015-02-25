var movsControllers = angular.module('movsControllers', []);

movsControllers.controller('movListCtrl', function($scope, $http, $filter,ngTableParams, $modal, Acc_movs, Categoritzation, File, Account) {

	setSelects();

	$scope.tableParams = new ngTableParams({
		page : 1, // show first page
		count : 10
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
		movsurl = 'acount_movs.json?max=-1';
		if ($scope.selectedFile) {
			movsurl += "&file=" + $scope.selectedFile;
		}
		if ($scope.selectAccount) {
			movsurl += "&ccc=" + $scope.selectAccount;
		}
		return movsurl;
	}
	function setSelects() {
		$scope.files = File.query();
		$scope.accounts = Account.query();
	}
	$scope.changedCCC = function() {
		$scope.files = File.query({
			ccc : $scope.selectAccount
		});
		$scope.tableParams.reload();
	}
	$scope.changedFile = function() {
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

movsControllers.controller('movCategoriesCtrl', function($scope, $http,$filter, $resource, $timeout, ngTableParams, Category) {

	$scope.tableParams = new ngTableParams({
		page : 1, // show first page
		count : 10
	// count per page
	}, {
		counts : [], // hides page sizes
		getData : function($defer, params) {
			$http.get('category.json?max=-1').success(
					function(data) {
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

	// console.log('create table'+cats);

	$scope.editCategory = function(category) {
		if (category.$edit) {
			console.log("try to update");
			cat = new Category(category);
			Category.update(cat, function(data) {
				$scope.tableParams.reload();
			});
			category.$edit = false;
		} else {
			category.$edit = true;
		}
	}

	$scope.addCategory = function(category) {
		cat = new Category(category);
		Category.create(cat, function(data) {
			$scope.tableParams.reload();
		});

	}

	$scope.deleteCategory = function(category) {
		cat = new Category(category);
		Category.remove(cat, function(data) {
			$scope.tableParams.reload();
		});
	}
});

movsControllers.controller('directoryCtrl', function($scope,$http,$filter, Directory,ngTableParams) {
	console.log('Directory controller');
	$scope.tableParams = new ngTableParams({
		page : 1, // show first page
		count : 10
	// count per page
	}, {
		counts : [], // hides page sizes
		getData : function($defer, params) {
			$http.get('directory.json?max=-1').success(
					function(data) {
						params.total(data.length);//update paginator
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

	// console.log('create table'+cats);

	$scope.editDirectory = function(directory) {
		if (directory.$edit) {
			di = new Category(directory);
			Directory.update(dir, function(data) {
				$scope.tableParams.reload();
			});
			directory.$edit = false;
		} else {
			directory.$edit = true;
		}
	}

	$scope.addDirectory = function(directory) {
		dir = new Directory(directory);
		Directory.create(dir, function(data) {
			$scope.tableParams.reload();
		});

	}

	$scope.deleteDirectory = function(directory) {
		dir = new Directory(directory);
		Directory.remove(dir, function(data) {
			$scope.tableParams.reload();
		});
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
			$http.get('accountMov/showUncatPending?max=-1').success(
					function(data) {
						params.total(data.length);//update paginator
						$defer.resolve(data.slice((params.page() - 1)
								* params.count(), params.page()
								* params.count()));
					});
		}
	});
	
	$scope.categorizeConcept= function(concept,cat){
		console.log(" categorize " +concept+ " cat "+cat)
		$http.get('accountMov/categorizedAll?concept='+concept+"&cat="+cat).success(
				function(data) {
					$scope.tableParams2.reload();
				});
	}
});


movsControllers.controller('movAccountCtrl', function($scope, $http, $filter, $resource, $timeout, ngTableParams, Account) {

	$scope.tableParams = new ngTableParams({
		page : 1, // show first page
		count : 10
	// count per page
	}, {
		counts : [], // hides page sizes
		getData : function($defer, params) {
			$http.get('account.json?max=-1').success(
					function(data) {
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
	

	$scope.editAccount = function(account) {
		if (account.$edit) {
			//TODO: Comentar amb roger com vol omplir els camps ccc & iban
			account.ccc = account.rawCCC;
			console.log("try to update editAccount");
			acc = new Account(account);
			
			console.log("After new Account");
			
			//acc.iban = account.ccc;
			Account.update(acc, function(data) {
				console.log("try to update ");
				$scope.tableParams.reload();
			});
			account.$edit = false;
		} else {
			account.$edit = true;
		}
	}

	$scope.addAccount = function(account) {
		acc = new Account(account);
		acc.rawCCC = account.ccc;
		Account.create(acc, function(data) {
			$scope.tableParams.reload();
		});

	}

	$scope.deleteAccount = function(account) {
		acc = new Account(account);
		Account.remove(acc, function(data) {
			$scope.tableParams.reload();
		});
	}
});


movsControllers.controller('queryOverViewCtrl', function($scope, $http, $filter, $resource, $timeout) {
	
	$http.get('query/overView').success(				
		function(data) {
			
			/* Set Data for donut*/
			var arrayToReturn=[];
			var dataToadd;
			for(var i=0; i < data.categoria.length; i++){						
				dataToadd = {'label': data.categoria[i][0], 'value': data.categoria[i][1]};
				console.log(dataToadd);
				arrayToReturn.push(dataToadd);
			}
			
			/* Instance donut */
			Morris.Donut({
				 element: 'pie-chart-donut',
				  data: arrayToReturn
			});
		
		});

});

movsControllers.controller('queryOverViewCtrl2', function($scope, $http, $filter, $resource, $timeout) {
	
	$http.get('query/overView').success(				
		function(data) {
			
			/* Set Data for donut*/
			var arrayToPrint=[];
			var dataIn;
			var dataOut;				
				dataIn = {'label': 'ingresos', 'value': data.ingresos.total};
				arrayToPrint.push(dataIn);
				
				dataOut = {'label': 'gastos', 'value': data.gastos.total*-1};
				arrayToPrint.push(dataOut);
				
				
				console.log(arrayToPrint);

			/* Instance donut */
			Morris.Donut({
				 element: 'pie-chart-donut2',
				  data: arrayToPrint
			});
		});

});