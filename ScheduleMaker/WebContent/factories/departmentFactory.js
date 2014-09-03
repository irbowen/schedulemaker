(function () {
	var app = angular.module('scheduleMaker');
	
	/*
    var departmentURL = 'http://localhost:8080/ScheduleMaker/rest/departments/';
    app.factory('departmentFactory', ['$resource',
        function ($resource) {
            console.log($resource(departmentURL, {}, {
                query: { method: 'GET', isArray: true }
            }));
            return $resource(departmentURL, {});
        }
    ]);
    */
    
    app.factory('departmentFactory', ['$http', function($http) {
    	var baseURL = 'http://localhost:8080/ScheduleMaker/rest/departments/';
    	var departmentFactory = {};
    	
    	departmentFactory.getDepartments = function() {
    		return $http.get(baseURL);
    	};
    	
    	departmentFactory.getCourses = function(courseNum) {
    		return $http.get(baseURL + courseNum + "/classes")
    	}
    	
    	return departmentFactory;
    }]);
    
})();