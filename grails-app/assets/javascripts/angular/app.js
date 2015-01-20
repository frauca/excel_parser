var app = angular.module('app', [
                'smart-table',
                'movsControllers'
]);

angular.module('app').config(function ($locationProvider) {
  $locationProvider.html5Mode(true);
});