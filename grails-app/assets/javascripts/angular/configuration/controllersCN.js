movsControllers.controller('movCategoriesCtrl', function($scope, $http,$filter, $resource, $timeout, ngTableParams, Category) {

	$scope.catsHier=[{text:'Inici',id: null}];
	$scope.tableParams = new ngTableParams({
		page : 1, // show first page
		count : 10
	// count per page
	}, {
		counts : [], // hides page sizes
		getData : function($defer, params) {
			$http.get(getURL()).success(
					function(data) {
						params.total(data.length);
						$defer.resolve(data.slice((params.page() - 1)
								* params.count(), params.page()
								* params.count()));
					});
		}
	});

	function getLastHier(){
		return $scope.catsHier[$scope.catsHier.length-1];
	}
	function getURL(){
		var url='category.json?max=-1';
		if(getLastHier().id){
			url=url+"&father="+getLastHier().id;
		}
		return url;	
	}
	// console.log('create table'+cats);

	$scope.editCategory = function(category) {
		if (category.$edit) {
			cat = new Category(category);
			Category.update(cat, function(data) {
				$scope.tableParams.reload();
			});
			category.$edit = false;
		} else {
			category.$edit = true;
		}
	}
	
	$scope.addHier = function(category){
		$scope.catsHier.push({text:category.name,id:category.id})
		$scope.tableParams.reload();
	}
	
	$scope.setHier = function(id){
		var pos=0;
		if(id){
			for (i = 0; i < $scope.catsHier.length; i++) {
			    if($scope.catsHier[i].id==id){
			    	pos=i;
			    }
			}
		}
		$scope.catsHier = $scope.catsHier.slice(0,pos+1);
		$scope.tableParams.reload();
	}

	$scope.addCategory = function(category) {
		cat = new Category(category);
		if(getLastHier().id){
			cat.father=getLastHier().id;
		}
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
