'use strict';

/* Services */

var categoriesServices = angular.module('categoriesServices', ['ngResource']);

categoriesServices.factory('Category', ['$resource',
  function($resource){
    return $resource('category', {id: "@id"},{
    	query: {method:'GET', params:{max:'-1'}, isArray:false}
    });
  }]);