
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

