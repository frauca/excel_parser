var movsControllers = angular.module('movsControllers', []);

movsControllers.controller('movListCtrl', function($scope, $http, $filter,ngTableParams, $modal, Acc_movs, Categoritzation, File, Account) {

	setSelects();

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




movsControllers.controller('queryOverViewCtrl', function($scope, $http, $filter, $resource, $timeout, Acc_movs) {

	$scope.years=Acc_movs.years();
	//console.log("test"+$scope.rogerYears);
	
	$scope.initChartsDef = function() {
		$http.get('query/overView').success(function(data) {
			
			$scope.year=$scope.years[0];			
			var params = "";
						
			if($scope.year){
				params += 'year=' + $scope.year;				
				$scope.months=Acc_movs.months({year: $scope.year});				
				$scope.month=$scope.months[1];
			}
			if ($scope.month){
				params += '&month=' + $scope.month;
			}
			$http.get('query/overView?' + params).success(function(data) {
				/* Update data to char Categories */
				drawCategoryBarChartIn(data);
				/* Update data to char Balance */
				drawCategoryBarChartOut(data);
				
				drawBalanceDonutChart(data);
			});
		});
	}
   
	$scope.updateCharts = function() {
		
		console.log("===============updateCharts====================");
		var params = "";
		if ($scope.year)
			params += 'year=' + $scope.year;
		if($scope.year){
			$scope.months=Acc_movs.months({year: $scope.year});
		}
		if ($scope.month)
			params += '&month=' + $scope.month;
		$http.get('query/overView?' + params).success(function(data) {
						
			/* Update data to char Categories */
			drawCategoryBarChartIn(data);
			/* Update data to char Balance */
			drawCategoryBarChartOut(data);
			
			//drawBalanceDonutChart(data);
		});
	}

	var barchartIn = Morris.Bar({
		element : 'pie-chart-barchar-In',
		// TODO: Inicialitzar?? data: {'y': 0, 'a': 0},
		xkey : 'y',
		ykeys : [ 'a' ],
		labels : [ 'Import' ],
		fillOpacity : 0.6,
		hideHover : 'auto',
		behaveLikeLine : true,
		stacked : true,
		resize : true,
		pointFillColors : [ '#ffffff' ],
		pointStrokeColors : [ 'black' ],
		lineColors : [ 'gray', 'red' ],
		grid : true
	});
	
	var barchartOut = Morris.Bar({
		element : 'pie-chart-barchar-Out',
		// TODO: Inicialitzar?? data: {'y': 0, 'a': 0},
		xkey : 'y',
		ykeys : [ 'a' ],
		labels : [ 'Import' ],
		fillOpacity : 0.6,
		hideHover : 'auto',
		behaveLikeLine : true,
		stacked : true,
		resize : true,
		pointFillColors : [ '#ffffff' ],
		pointStrokeColors : [ 'black' ],
		lineColors : [ 'gray', 'red' ],
		grid : true
	});

	var donutchart = Morris.Donut({
		element : 'pie-chart-balance',
		data : [ 'label: ' + "", 'value: ' + 0 ],
		resize : true
	});


	function drawCategoryBarChartIn(data) {
		console.log("===============drawCategoryBarChartIn==");
		
		/* Set Data */
		var arrayToReturn = [];
		var dataToadd;
		for (var i = 0; i < data.ingresos.categoria.length; i++) {
			dataToadd = {
				'y' : data.ingresos.categoria[i][0],
				'a' : data.ingresos.categoria[i][1]
			};
			console.log(dataToadd);
			arrayToReturn.push(dataToadd);
		}
		barchartIn.setData(arrayToReturn);
	}
	
	function drawCategoryBarChartOut(data) {
		console.log("===============drawCategoryBarChartOut==");
		/* Set Data */
		var arrayToReturn = [];
		var dataToadd;
		for (var i = 0; i < data.gastos.categoria.length; i++) {
			dataToadd = {
				'y' : data.gastos.categoria[i][0],
				'a' : data.gastos.categoria[i][1] * -1
			};
			console.log(dataToadd);
			arrayToReturn.push(dataToadd);
		}
		barchartOut.setData(arrayToReturn);
	}

	function drawBalanceDonutChart(data) {
		/* Set Data */
		var arrayToPrint = [];
		var dataIn;
		var dataOut;
		dataIn = {
			'label' : 'ingresos',
			'value' : data.ingresos.total
		};
		arrayToPrint.push(dataIn);
		dataOut = {
			'label' : 'gastos',
			'value' : data.gastos.total * -1
		};
		arrayToPrint.push(dataOut);

		
		donutchart.setData(arrayToPrint);
	}
});


movsControllers.controller('DatepickerFromCtrl', function($scope) {
	$scope.today = function() {
		$scope.dtFrom = new Date();
	};

	$scope.clear = function() {
		$scope.dtFrom = null;
	};

	$scope.open = function($event) {
		$event.preventDefault();
		$event.stopPropagation();

		$scope.opened = true;
	};

	$scope.dateOptionsFrom = {
		formatYear : 'yy',
		startingDay : 1
	};
	// $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy',
	// 'shortDate'];
	$scope.format = 'dd-MM-yyyy';
});


movsControllers.controller('DatepickerToCtrl', function($scope) {
	$scope.today = function() {
		$scope.dtTo = new Date();
	};
	$scope.clear = function() {
		$scope.dtTo = null;
	};

	$scope.open = function($event) {
		$event.preventDefault();
		$event.stopPropagation();

		$scope.opened = true;
	};

	$scope.dateOptionsTo = {
		formatYear : 'yy',
		startingDay : 1
	};
	// $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
	$scope.format = 'dd-MM-yyyy';
});

