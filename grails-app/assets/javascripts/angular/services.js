'use strict';

/* Services */

var categoriesServices = angular.module('categoriesServices', ['ngResource']);

categoriesServices.factory('Category', ['$resource',
  function($resource){
    return $resource('category/:id.json', {id: "@id"},{
    	query: {method:'GET', params:{max:'-1'}, isArray:true},
    	update: { method: 'put', isArray: false },
    	create: { method: 'post' },
    	subcat: {method:'GET', params:{max:'-1',father:':father'}, isArray:true},
    });
  }]);

categoriesServices.factory('Account', ['$resource',
function($resource){
  return $resource('account/:id.json', {id: "@id"},{
	  update: { method: 'put', isArray: false },
	  create: { method: 'post' }
  });
}]);

categoriesServices.factory('File', ['$resource',
   function($resource){
     return $resource('file/:id.json?ccc=:ccc', {id: "@id",ccc:"@ccc"},{
    	 query: {method:'GET', params:{max:'30',withRows:'true'}, isArray:true},
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
    	del: {method:'DELETE'},
    	years: {url:'accountMov/availableYears',method:'GET', isArray:true},
    	months: {url:'accountMov/availableMonth', method:'GET', isArray:true},
    	concepts:{url:'accountMov/similarConcepts?id=:idm', method:'GET', isArray:true},
    	addInfo:{url:'accountMov/additionalInfo.json?id=:idm', method:'GET', isArray:false}
     });
   }]);

categoriesServices.factory('Directory', ['$resource',
	function($resource){
	  return $resource('directory/:id.json', {id: "@id"},{
		  update: { method: 'put', isArray: false },
	      create: { method: 'post' }
	  });
	}]);

categoriesServices.factory('SQLQuery', ['$resource',
	function($resource){
	  return $resource('sqlQuery/:id.json', {id: "@id"},{
		  query: {method:'GET', params:{max:'-1'}, isArray:true},
		  update: { method: 'put', isArray: false },
	      create: { method: 'post' }
	  });
	}]);
