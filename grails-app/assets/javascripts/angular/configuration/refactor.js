
app.controller('recalcTotalsCtrl', function($scope, $http, Account,ngTableParams) {
	
	$scope.accounts = Account.query();
	$scope.recalcMovs=[];
	
	$scope.recalc=function(){
		$http.post('accountMov/recalcTotals?sdate='+$scope.fromDate+"&accountId="+$scope.selectAccount).success(function(data){
			$scope.recalcMovs=data;
		});
	}
	
	
	$scope.setTotals=function(){
		$http.post('accountMov/setTotals?sdate='+$scope.fromDateSet+"&accountId="+$scope.selectAccountSet).success(function(data){
			$scope.setMovs=data;
		});
	}
	
	$scope.seeDifsTable = new ngTableParams({
		page : 1, // show first page
		count : 10
	// count per page
	}, {
		counts : [], // hides page sizes
		getData : function($defer, params) {
			$http.get('accountMov/seeDiferences?sdate='+$scope.fromDateSee+"&accountId="+$scope.selectAccountSee).success(
					function(data) {
						params.total(data.length);
						$defer.resolve(data.slice((params.page() - 1)
								* params.count(), params.page()
								* params.count()));
					});
		}
	});
	
	$scope.seeTotals=function(){
		$scope.seeDifsTable.reload()
	}
		
});


app.directive('directive', function($timeout) {
    return {
       link: function(scope, element, attr) {
          $timeout(function() {
        	  $('[data-toggle="popover"]').popover();
          });
       }
    }
});


app.controller('filesValidatorsCtrl', function($scope, $http, $filter, Account,ngTableParams, $modal) {
	
	$http.get('fileValidator/validatorsFiles').success(function(data){
		$scope.files = data
	});
	
	$scope.bindings = new ngTableParams({
		page : 1, // show first page
		count : 10
	// count per page
	}, {
		counts : [], // hides page sizes
		getData : function($defer, params) {
			$http.post('fileValidator/processFileSource?fsid='+$scope.selectedFile.id).success(
					function(data) {
						params.total(data.length);
						var orderedData = params.sorting() ? $filter('orderBy')
								(data, params.orderBy()) : data;
						$defer.resolve(orderedData.slice((params.page() - 1)
								* params.count(), params.page()
								* params.count()));
					});
		}
	});
	
	$scope.readFolder=function(){
		$http.post('fileValidator/loadPathOfValidateFiles?validatePath='+$scope.validatePath).success(function(data){
			$scope.files = data
		});
	}
	
	$scope.processFile=function(fileSource){
		$scope.bindings.reload()
	}
	
	$scope.validateModal = function(bind) {
		$scope.bind = bind
		var modalInstance = $modal.open({
			templateUrl : 'modal/fileValidateModal',
			controller : 'fileValidateModalCtrl',
			scope : $scope,
			size: 'lg'
		});
		modalInstance.result.then(function(bind) {
			$scope.bindings.reload();
		});
	}
	
});

app.controller('fileValidateModalCtrl', function($scope, $http,ngTableParams, $modalInstance) {
	
	$scope.bindeds = new ngTableParams({
		page : 1, // show first page
		count : 10
	// count per page
	}, {
		counts : [] // hides page sizes
	});
	
	$scope.copyTheMov=function(){
		$http.post('accountMovRaw/copyToMov?rawId='+$scope.bind.correctMov.id).success(function(data){
			$modalInstance.close()
		});
	}
});