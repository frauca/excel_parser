var app = angular.module('app', [
                'ngTable',
                'movsControllers',
                'categoriesServices',
                'ngAnimate',
                'ui.bootstrap',
                'stpa.morris'
]);

angular.module('app').config(function ($locationProvider) {
  $locationProvider.html5Mode(true);
});