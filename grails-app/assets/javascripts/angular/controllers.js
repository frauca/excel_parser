var movsControllers = angular.module('movsControllers', []);

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

