app.controller('basicAnalyzer', function($scope, $http) {
	
	$scope.qryHasChange = function(qry) {
		if(qry.fromDate && qry.toDate){
			if(qry.fromDate<=qry.toDate){
				performQuery(qry);
			}
		}
	}
	
	$scope.showWeekDetail=function(sweek){
		$http.post('basicAnalyzer/showWeekDetail?sweek='+sweek).success(function(data){
			$scope.weekDetails=data;
			$scope.accountsDetails=$scope.weekDetails.accounts;
		});
	}
	
	$scope.showYearDetail=function(accountKey){
		$http.post('basicAnalyzer/showYearDetailBottom?bottomDate='+$scope.qry.fromDate+'&accountKey='+accountKey).success(function(data){
			$scope.yearBottomDetails=data;
		});
		$http.post('basicAnalyzer/showYearDetailTop?topDate='+$scope.qry.toDate+'&accountKey='+accountKey).success(function(data){
			$scope.yearTopDetails=data;
		});
	}
	
	$scope.showWeekDetail=function(sweek){
		$http.post('basicAnalyzer/showWeekDetail?sweek='+sweek).success(function(data){
			$scope.weekDetails=data;
			$scope.accountsDetails=$scope.weekDetails.accounts;
		});
	}
	
	$scope.open = function($event) {
	    $scope.status.opened = true;
	  };
	  
	  $scope.status = {
			    opened: false
			  };
	
	function performQuery(qry){
		$http.post('basicAnalyzer/process',{'qry':qry}).success(function(data){
				$scope.result=data;
			});
	}
	
	
});

