var movsControllers = angular.module('movsControllers', []);

movsControllers.controller('movListCtrl', function ($scope,$http) {
	$http.get('acount_movs.json').success(function(data) {
		$scope.rowCollection = data;
	  });
});