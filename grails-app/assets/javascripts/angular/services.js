'use strict';

/* Services */

var categoriesServices = angular.module('categoriesServices', ['ngResource']);

categoriesServices.factory('Category', ['$resource',
  function($resource){
    return $resource('category/:id.json', {id: "@id"},{
    	query: {method:'GET', params:{max:'-1'}, isArray:true},
    	update: { method: 'put', isArray: false },
    	create: { method: 'post' }
    });
  }]);

categoriesServices.factory('Account', ['$resource',
function($resource){
  return $resource('account/:id.json', {id: "@id"},{
  });
}]);

categoriesServices.factory('File', ['$resource',
   function($resource){
     return $resource('file/:id.json?ccc=:ccc', {id: "@id",ccc:"@ccc"},{
     });
   }]);

categoriesServices.factory('Categoritzation', ['$resource',
    function($resource){
      return $resource('categoritzation/:id.json', {id: "@id"},{
      	query: {method:'GET', params:{max:'-1'}, isArray:true},
      	get: {method:'GET', isArray:false},
      	update: { method: 'put', isArray: false },
      });
    }]);

categoriesServices.factory('Acc_movs', ['$resource',
   function($resource){
     return $resource('acount_movs/:id.json', {id: "@id"},{
     	query: {method:'GET', params:{max:'-1'}, isArray:true},
    	update: { method: 'put', isArray: false },
     });
   }]);