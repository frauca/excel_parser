var app = angular.module('app', [
                'ngTable',
                'movsControllers',
                'categoriesServices',
                'ui.bootstrap'
]);

angular.module('app').config(function ($locationProvider) {
  $locationProvider.html5Mode(true);
});